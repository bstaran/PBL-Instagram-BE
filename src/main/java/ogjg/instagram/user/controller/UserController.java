package ogjg.instagram.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import ogjg.instagram.user.dto.SignupRequestDto;
import ogjg.instagram.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.registerUser(signupRequestDto);
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(HttpServletRequest request, HttpServletResponse response) {
        userService.generateToken(request, response);
        return new ResponseEntity<>("Access Token 재발급 성공", HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable @Email String email) {
        if (userService.isEmailAlreadyInUse(email)) {
            return new ResponseEntity<>("이미 사용되고 있는 이메일입니다.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
        }
    }

}
