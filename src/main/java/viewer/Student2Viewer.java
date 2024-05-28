package viewer;

import controller.Student2Controller;
import lombok.Getter;
import lombok.Setter;
import model.StudentDTO;
import util.ScannerUtil;
import java.util.Scanner;

public class Student2Viewer {
    @Setter
    private Scanner scanner;
    @Getter
    private Student2Controller student2Controller;

    public void showMenu(){
        String message = "1. 입력  2. 출력 3. 종료";
        while(true){
            int userChoice = ScannerUtil.nextInt(scanner,message);

            if(userChoice == 1){
                insertStudent();
            }else if(userChoice == 2){
                // 출력
                // printList();
            }else if(userChoice == 3){
                // 종료
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }








    private void insertStudent(){
        // DB
        StudentDTO s = new StudentDTO();
        String message = "1. 학생을 이름 입력해주세요";
        String studentName = ScannerUtil.nextLine(scanner, message);

        message = "2. 학생의 국어점수를 입력해주세요";
        int studentKorean = ScannerUtil.nextInt(scanner,message);

        message = "3. 학생의 영어점수를 입력해주세요 ";
        int studentEnglish = ScannerUtil.nextInt(scanner,message);

        message = "3. 학생의 영어점수를 입력해주세요 ";
        int studentMath = ScannerUtil.nextInt(scanner,message);

        s.setName(studentName);
        s.setKorean(studentKorean);
        s.setEnglish(studentEnglish);
        s.setMath(studentMath);

        student2Controller.insert(s);
    }
}
