package viewer;

import controller.BoardController;
import controller.ReplyController;
import controller.UserController;
import lombok.Setter;
import model.BoardDTO;
import model.ReplyDTO;
import util.ScannerUtil;

import javax.crypto.spec.PSource;
import java.util.List;
import java.util.Scanner;

public class ReplyViewer {
    @Setter
    private Scanner scanner;
    @Setter
    private ReplyController replyController;
    @Setter
    private UserController userController;
    @Setter
    private BoardController boardController;

    public void replyMenu(String preview) {
        String message = "1. 댓글쓰기 2. 댓글목록 3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 쓰기
                reply(preview);
            } else if (userChoice == 2) {
                // 댓글 목록 메서드
                // replyList();

            } else if (userChoice == 3) {
                // 뒤로가기 메서드
                // back();
                break;
            }
        }
    }

    //1
    public void reply(String postId) {
        ReplyDTO replyDTO = new ReplyDTO();

        String message="작성자의 이름을 알려주세요";
        String replyId = ScannerUtil.nextLine(scanner, message);

        message ="댓글 내용을 작성해주세요";
        String replyContent = ScannerUtil.nextLine(scanner, message);

        if(replyId.isEmpty()){
            System.out.println("작성되지 않았습니다. 다시 한 번 확인해주세요");
        }

        if(replyContent.isEmpty()){
            System.out.println("작성되지 않았습니다. 다시 한 번 확인해주세요");
        }

        replyDTO.setContent(replyId);
        replyDTO.setContent(replyContent);
        replyDTO.setPostId(postId);

        // 댓글 저장 하는 insert 문
        replyController.insert(replyDTO);
        System.out.println("댓글 작성 완료!");
    }

    public void replyList() {
        // 댓글 목록 1.2.3.

    }


    private void update(int id) {

    }


    private void delete(int id) {

    }


}
