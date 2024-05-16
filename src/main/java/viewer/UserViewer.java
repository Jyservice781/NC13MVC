package viewer;

import controller.UserController;
import lombok.Getter;
import lombok.Setter;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

//
public class UserViewer {
    @Setter // 의존성 주입
    private UserController userController;
    @Setter
    private Scanner scanner;
    @Getter
    private UserDTO logIn;

    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3. 프로그램종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                //로그인
                auth();
                if (logIn != null) {
                    // 회원 메뉴 실행
                }
            } else if (userChoice == 2) {
                // 회원가입
                register();

            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void auth() {
        String message;
        message = "아이디를 입력하세요";
        String username = ScannerUtil.nextLine(scanner, message);

        message = "비밀번호를 입력하세요";
        String password = ScannerUtil.nextLine(scanner, message);

        // 로그인을 했는지 안했는지 판단
        logIn = userController.auth(username, password);

        if (logIn == null) {
            System.out.println("잘못 입력하셨습니다. 로그인 정보를 다시 확인해주세요");
        }
    }

    //register();
    private void register() {
        //회원가입 안내 문구
        String answer = ScannerUtil.nextLine(scanner, "회원가입 하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            // 회원가입 절차
            // 아이디 비밀번호를 저장할 String - model 에도 있는지 확인해야함.
            // 아이디 저장
            String message;
            message = "사용하실 아이디를 적어주세요";
            String userId = ScannerUtil.nextLine(scanner, message);
            //잘못입력했다는것도 해야함.
            if (userId != null) {
                //true 일때
                UserDTO user = new UserDTO();
                userController.insert(user);
            } else {
                System.out.println("입력한 글자가 없습니다. 아이디를 확인해주세요");
                register();
            }
            //userId 유효성 검사
            // 비밀번호 저장
            message = "사용하실 비밀번호를 적어주세요";
            String userPassword = ScannerUtil.nextLine(scanner, message);
            if (userPassword != null) {
                UserDTO pw = new UserDTO();
                userController.insert(pw);
            }else{
                System.out.println("입력한 글자가 없습니다. 비밀번호를 확인해주세요");
                register();
            }
            //userPassword 유효성 검사
        } else {
            // 리스트로 돌아가기 N
            showIndex();
        }
    }
}
