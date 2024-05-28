package main;

// 자바에서 SQL 시간/날짜 다루기
// 자바에서의 시간은 그리니치 표준 시간대 1970년 1월 1일 0시 0분 0초를 기준으로 하여
// 이후 시간대는 +, 이전 시간대는 - 로 Long 데이터타입을 기준으로 표시를 한다.
// java.util.Date, java.util.Calendar 를 사용하여 현재 시간을 저장/변경하고
// SimpleDateFormat 을 사용하여 출력하는 방법을 알아보자.

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeMain {
    public static void main(String[] args) {
        // java.util.Date 객체를 만들어보자
        Date date = new Date();
        // date 안에 뭐가있을까?
        System.out.println(date);

        // 현재 시간을 바꿀 때에는 ?
        date.setHours(20);
        System.out.println(date);

        // 만약 우리가 시간의 표시형식을 바꾸고 싶다면 -> SimpleDateFormat 을 사용하게 됨.
        // SimpleDateFormat 의경우, 생성자의 파라미터로 우리가 원하는
        // 형식을 넣어주는데
        // y: 연도
        // m: 월
        // d: 일
        // E: 요일
        // h: 12시간 체제의 시각
        // H: 24시간 체제의 시각
        // m: 분
        // s: 초
        // S: 밀리초


        // ####-##-## ##:##:## 형식으로 연월일 시분초를 출력하고 싶다면
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E요일 HH:mm:ss");

        // SimpleDateFormat 의 객체를 통하여 위의 만든 Date 객체 date 를 출력해보자
        System.out.println(simpleDateFormat.format(date));

        // 또한, SimpleDateFormat 을 사용하면 우리가 String을 시간으로 변환하는 것도 가능하다.
        simpleDateFormat = new SimpleDateFormat("yyMMdd HHmmss");
        simpleDateFormat.setLenient(false);
        String time = "981231 233030";
        try {
            date = simpleDateFormat.parse(time);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
