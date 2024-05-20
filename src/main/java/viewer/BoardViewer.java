package viewer;

import controller.BoardController;
import controller.ReplyController;
import controller.UserController;
import lombok.Setter;
import model.BoardDTO;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardViewer {
    @Setter
    private BoardController boardController;
    @Setter
    private UserController userController;
    @Setter
    private ReplyController replyController;
    @Setter
    private Scanner scanner;
    @Setter
    private UserDTO logIn;

    // 현재 로그인한 사람의 글을 받아오기 때문에

    public void showMenu() {
        String message = "1. 글 작성하기  2. 글목록보기  3. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                //글 잓성
                insert();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void insert() {
        BoardDTO boardDTO = new BoardDTO();


        boardDTO.setWriterId(logIn.getId());

        String message = "글의 제목을 입력해주세요.";
        boardDTO.setTitle(ScannerUtil.nextLine(scanner, message));

        message = "글의 내용을 입력해주세요";
        boardDTO.setContent(ScannerUtil.nextLine(scanner, message));

        boardController.insert(boardDTO);
    }

    private void printList() {
        ArrayList<BoardDTO> list = boardController.selectAll();
        for (BoardDTO b : list) {
            System.out.printf("%d. %s - %s\n", b.getId(), b.getTitle(), userController.selectNicknameById(b.getWriterId()));
        }
        String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        while (!boardController.validateInput(userChoice)) {
            System.out.println("잘못입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(scanner, message);
        }
        if (userChoice != 0) {
            printOne(userChoice);
        }
    }

    private void printOne(int id) {
        BoardDTO boardDTO = boardController.selectOne(id);

        System.out.println("========================================");
        System.out.println("제목: " + boardDTO.getTitle());
        System.out.println("글 번호: " + boardDTO.getId());
        System.out.println("글 작성자: " + userController.selectNicknameById(boardDTO.getWriterId()));
        System.out.println("----------------------------------------");
        System.out.println(boardDTO.getContent());
        System.out.println("========================================");
        ReplyViewer replyViewer = new ReplyViewer();
        replyViewer.replyMenu("");

        // 작성자가 수정할 수 있도록 함.
        if (logIn.getId() == boardDTO.getWriterId()) {
            String message = "1. 수정 2. 삭제 3. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 수정 메소드 실행
                update(id);
            } else if (userChoice == 2) {
                // 삭제 메소드 실행
                delete(id);
            } else if (userChoice == 3) {
                printList();
            }
        } else {
            String message = "1. 뒤로가기";
            //사용자의 행동에 제약을 가함.
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 1);
            printList();
        }
    }

    private void update(int id) {
        BoardDTO boardDTO = boardController.selectOne(id);
        String message = "수정할 제목을 입력해주세요";
        boardDTO.setTitle(ScannerUtil.nextLine(scanner, message));
        message = "수정할 내용을 입력해주세요";
        boardDTO.setContent(ScannerUtil.nextLine(scanner, message));

        boardController.update(boardDTO);
    }

    private void delete(int id) {
        String message = "정말 삭제하시겠습니까? Y/ N";
        String answer = ScannerUtil.nextLine(scanner, message);

        if (answer.equalsIgnoreCase("Y")) {
            // Y인 경우
            message = "비밀번호를 입력해주세요";
            String password = ScannerUtil.nextLine(scanner, message);

            if (password.equals(logIn.getPassword())) {
                boardController.delete(id);
            }
            printList();
        }
    }
}
