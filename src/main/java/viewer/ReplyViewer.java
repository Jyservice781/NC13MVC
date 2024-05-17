package viewer;

import controller.BoardController;
import controller.ReplyController;
import controller.UserController;
import lombok.Setter;
import model.BoardDTO;
import model.ReplyDTO;
import util.ScannerUtil;

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

    public void replyMenu() {
        String message = "1. 댓글쓰기 2. 댓글목록 3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice == 1){
                // 쓰기
                reply();
            }else if(userChoice ==2){
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
    public void reply(){
        String message = "댓글 내용을 입력하세요.";
        String content = ScannerUtil.nextLine(scanner, message);

        System.out.println("작성내용: " + content);
      /*  if(){

        }else{

        }*/
    }

































    private void update(int id){

    }


    private void delete(int id){

    }


}
