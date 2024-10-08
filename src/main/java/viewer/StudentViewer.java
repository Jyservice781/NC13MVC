package viewer;

// 실제 사용자가 보게 될 UI를 담당할 Viewer
// 단, 우리가 자바 콘솔에서 화면을 보기 때문에 Viewer 클래스가 있는 것이지
// 웹 프로그래밍에서는 웹페이지가 Viewer 가 된다.

import controller.StudentController;
import model.StudentDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentViewer {
    private final Scanner SCANNER = new Scanner(System.in);
    // 값을 보여주기만 하는 viewer 임
    // controller 에 의존적이게 되버림
    private StudentController studentController;

    // 의존성을 강제 주입
    public StudentViewer(StudentController studentController) {
        this.studentController = studentController;
    }

    public void showMenu() {
        String message = "1, 입력 2, 출력 3, 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insertStudent();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void insertStudent() {
        StudentDTO s = new StudentDTO();

        String message;

        message = "학생의 이름을 입력해주세요";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "학생의 국어점수를 입력해주세요";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message));

        message = "학생의 영어점수를 입력해주세요";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message));

        message = "학생의 수학점수를 입력해주세요";
        s.setMath(ScannerUtil.nextInt(SCANNER, message));

        studentController.insert(s);
    }

    //출력 혼자 해보려던것. 주말에 다시 만지기
 /*   private void printList() {
        StudentDTO s = new StudentDTO();
        if (studentController.selectAll().isEmpty()) {
            //입력값이 없을때
            System.out.println("입력된 값이 없습니다.");
        } else {
            for (int i = 0; i <= validate(s); i++) {
                s = studentController.insert(s).get(s);
                System.out.printf("%d. - %s\n", s.getId(), s.getName());
            }
        }
    }
    //value 값 구하기
    // 유효성 검사하기
    public boolean validate(int id) {
        if (!(id == 0)) {
            return true;
        }
    }*/

    // 선생님이랑
    private void printList() {
        ArrayList<StudentDTO> list = studentController.selectAll();
        if (list.isEmpty()) {
            System.out.println("아직 등록된 학생 정보가 없습니다.");
        } else {
            briefList(list);
            String message = "상세보기할 학생의 번호나 뒤로 가시려면 0을 입력하세요";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (!validateValue(userChoice)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            //validateValue => true
            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void briefList(ArrayList<StudentDTO> list) {
        for (StudentDTO s : list) {
            System.out.printf("%d. %s\n", s.getId(), s.getName());
        }
    }

    //userChoiceValue 유효성 검사
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }
        return studentController.selectOne(value) != null;
        // 없으면 null 이 나오게 하기때문에 리스트에 스튜던트 객체가 없음을 알 수 있음.
    }

    private void printOne(int id) {
        StudentDTO s = studentController.selectOne(id);
        System.out.println("====================================================");
        System.out.println(s.getName() + "학생의 정보");
        System.out.println("-----------------------------------------------------");
        System.out.printf("번호: %02d번 이름: %s\n", s.getId(), s.getName());
        System.out.printf("국어: %03d점 영어:%03d점 수학:%03d점\n", s.getKorean(), s.getEnglish(), s.getMath());
        System.out.printf("총점: %03d점 평균: %06.2f점\n", studentController.calculateSum(s), studentController.calculateAverage(s));
        System.out.println("======================================================");

        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }

    private void update(int id) {
        StudentDTO s = studentController.selectOne(id);
        String message;

        message = "새로운 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 국어점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message));

        message = "새로운 영어점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message));

        message = "새로운 수학점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message));

        studentController.update(s);
        printOne(id);
    }

    private void delete(int id) {
        String answer = ScannerUtil.nextLine(SCANNER, "정말로 삭제하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            studentController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
}
