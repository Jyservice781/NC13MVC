package viewer;

import controller.UserController;
import lombok.Getter;
import lombok.Setter;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

//
public class UserViewer {
    // 의존성 주입 - Setter
    @Setter
    private UserController userController;
    @Setter
    private Scanner scanner;
    @Getter
    private UserDTO logIn;
    @Setter
    private BoardViewer boardViewer;

    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3. 프로그램종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                //로그인
                auth();
                if (logIn != null) {
                    // 회원 메뉴 실행 - 로그인이 되었을때의 if 문임.
                    boardViewer.setLogIn(logIn); //
                    showMenu();
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
    /*private void register() {
        //회원가입 안내 문구
        String answer = ScannerUtil.nextLine(scanner, "회원가입 하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            // 회원가입 절차
            // 아이디 저장
            String message;
            message = "사용하실 아이디를 적어주세요";
            String userId = ScannerUtil.nextLine(scanner, message);
            //잘못입력했다는것도 해야함.
            if (!userId.isEmpty()) {
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
            } else {
                System.out.println("입력한 글자가 없습니다. 비밀번호를 확인해주세요");
                register();
            }
            //userPassword 유효성 검사
        } else {
            // 리스트로 돌아가기 N
            showIndex();
        }
    }*/

    // 선생님이랑 짠 코드
   private void register() {
        String message = "사용하실 아이디를 입력해주세요";
        String username = ScannerUtil.nextLine(scanner, message);

        if (userController.validateUsername(username)) {
            //중복되지 않은 아이디이므로 나머지 정보를 입력받는다.
            message = "사용하실 비밀번호를 입력해주세요";
            String password = ScannerUtil.nextLine(scanner, message);

            message = "사용하실 닉네임을 입력해주세요";
            String nickname = ScannerUtil.nextLine(scanner, message);

            UserDTO temp = new UserDTO();
            temp.setUsername(username);
            temp.setPassword(password);
            temp.setNickname(nickname);

            userController.insert(temp);
        } else {
            System.out.println("이미 사용 중인 아이디입니다.");
        }
    }

    private void showMenu() {
        String message = "1. 게시판으로 2. 회원 정보 수정 3. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                // 반드시 로그인이 되어있는 상태에서만 가능함.
                boardViewer.showMenu();
            } else if (userChoice == 2) {
                printInfo();
            } else if (userChoice == 3) {
                //로그아웃은 로그인은 null 로
                logIn = null;
                System.out.println("성공적으로 로그아웃 되었습니다.");
            }
        }
    }

    private void printInfo() {
        System.out.println("==========================================");
        System.out.println(logIn.getNickname() + "회원님의 정보");
        System.out.println("------------------------------------------");
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 아이디: " + logIn.getUsername());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("==========================================");
        String message = "1. 회원 정보 수정 2. 회원 탈퇴 3. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            //탈퇴
            delete();
        }
    }

    private void delete() {
        String message = "정말로 탈퇴하시겠습니까? Y/N";
        String answer = ScannerUtil.nextLine(scanner, message);
        if (answer.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요";
            String password = ScannerUtil.nextLine(scanner, message);

            if (password.equals(logIn.getPassword())) {
                userController.delete(logIn.getId());
                logIn = null;
            }
        }
    }

    private void update() {
        String message = "새로운 닉네임을 입력해주세요";
        String newNickname = ScannerUtil.nextLine(scanner, message);

        message = "새로운 비밀번호를 입력해주세요";
        String newPassword = ScannerUtil.nextLine(scanner, message);

        message = "기존 비밀번호를 입력해주세요";
        String oldPassword = ScannerUtil.nextLine(scanner, message);
        if (oldPassword.equals(logIn.getPassword())) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            userController.update(logIn);
        } else {
            System.out.println("기존 비밀번호와 틀려서 회원정보 수정을 할 수 없습니다.");
        }
    }


}
