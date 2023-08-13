package ogjg.instagram.comment.controller;

import lombok.RequiredArgsConstructor;
import ogjg.instagram.comment.dto.response.InnerCommentListResponseDto;
import ogjg.instagram.comment.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;

    /**
     * 피드 대댓글 보기 - 전부 내려주기
     */
    @GetMapping("/comments/{commentId}/inner-comments")
    public ResponseEntity<?> innerCommentList(
            @PathVariable ("commentId") Long commentId
    ) {
        return ResponseEntity.ok(
                InnerCommentListResponseDto.from(
                        commentId,
                        commentService.findInnerComments(commentId)
                ));
    }

    /**
     * 피드 댓글 작성
     */
    @PostMapping("/feeds/{feedId}/comments")
    public ResponseEntity<?> writeComment(
            @RequestBody String content,
            @PathVariable("feedId") Long feedId
    ) {
        Long jwt_myId = 1L;

        commentService.write(feedId, content); //todo : id 반환받아서 내려주기
        return ResponseEntity.ok().build();
    }

    /**
     * 피드 댓글 삭제
     */
    @DeleteMapping("/comments/{commentId}") // todo : url feedID 불필요, url 정리
    public ResponseEntity<?> deleteInnerComment(
            @PathVariable("commentId") Long commentId
    ) {
        Long jwt_myId = 1L;

        commentService.delete(jwt_myId, commentId);
        return ResponseEntity.ok().build();
    }


}
