package ex02코드스타일레벨업.ex0207서식화;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ex02코드스타일레벨업.ex0207서식화.sub.Logbook;

/**
 * 2.7 이어붙이기 대신 서식화
 *  - 3.6 예제로 설명하기(093쪽)
 * @author jkoogi
 * 1. 서식화 : 어떻게 출력할지와 무엇을 출력할지 목적을 분리하여 정의
 *  - 어떻게 출력할지 : 특수 위치 지정자(%) 문자를 이용해 하나의 블록으로 일관되게 표현
 *  - 무엇을 출력할지 : 순서대로 나열 
 * 2. 긴 문자열은 StringTemplate 사용   
 */
public class Mission {

	Logbook logbook;
	LocalDate start;
	
	void update(String author, String message) {
		/* 대상소스 */
//		LocalDate today = LocalDate.now();
//		String month = String.valueOf(today.getMonthValue());
//		String formattedMonth = month.length() < 2 ? "0"+month : month;
//		String entry = author.toUpperCase() + ": ["+ formattedMonth + "-" +
//				today.getDayOfMonth() + "-" + today.getYear() + "](Day " +
//				(ChronoUnit.DAYS.between(start, today) + 1)+")>"+
//				message + System.lineSeparator();
		
		/* 개선소스 */
		// %S : toString()메소드를 사용해 객체의 대문자로 변환
		// %tm : 월, %te : 날짜, %tY : 연도
		//  - [<]문자를 추가하여 위치지정자 세개가 같은 입력데이터를 참조
		// %d : 십진값, %s : 문자열, %n : 계행
		final LocalDate today = LocalDate.now();
		String entry = String.format("%S: [%tm-%<te-%<tY](Day %d)> %s%n", 
				author, today,
				ChronoUnit.DAYS.between(start, today) + 1,message);
		
		logbook.write(entry);
		
	}
}
