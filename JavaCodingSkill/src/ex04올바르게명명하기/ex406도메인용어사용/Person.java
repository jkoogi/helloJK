package ex04올바르게명명하기.ex406도메인용어사용;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * 4.6 도메인 용어 사용하기 
 * @author jkoogi
 * 1. 개발 코드는 특정 도메인에 속하고 도메인마다 어휘가 다르다.(종목별, 도메인별)
 *  - 공을... 힘차게 던지다(hurling), 던지다(throwing), 차다(kicking), 구부리다(bending), 밀어넣다(dunking)
 *  > 적당한 도메인 용어를 코드에 많이 적용하는것이 좋다.
 * 2. Person : 성씨, 직무, 출장횟수, 고용일
 *  - 포괄적 이름(Person)
 *  - 고용정보에 포함된 내용 (왜 특정 속성으로만 구성했는지)
 *  - 이름말고 성씨만 속성으로 구성한 이유
 *  - 출장횟수 용도
 *  - serializeAsLine() : 포괄적 메소드명 (해석 : 사람정보를 이용하여 줄바꿈없이 한문자열로 직렬화 함, 형식에 대한 판단은 불가)
 *  * 클래스, 패키지 등 범위가 넓은 이름이 더 중요
 */
public class Person { //대상소스
//	public class Astronaut { //개선소스(도메인 맥락을 고려한 클래스명) : 우주비행사

	/* 대상소스 */
//	String lastName;
//	String role;
//	int travels;
//	LocalDate employedSince;
//	
//	String serializeAsLine() {
//		return String.join(",", 
//				Arrays.asList(lastName, 
//						role,
//						String.valueOf(travels),
//						String.valueOf(employedSince))
//				);
//	}

	/* 개선소스 
	 * - 도메인 : 화성탐사작전 (도메인 적합성에 따른 명명) 맥락에 맞춘 자연스럽고 명확한 코드
	 * - 클래스명 : Astronaut 우주비행사
	 * - 속성 : tagName(이름표), role(대상이 속한 rank), travels(우주수행미션 수), activeDytySince(employedSince)
	 * - 메서드 : toCSV - 기술적 개념에 따른 용어집합을 고려한 기술적 도메인 대응 명명
	 *   우주비행사 정보를 직렬화 한 표현형식 : 한줄짜리 쉼표로 구분된 값
	 *   CSV - 프로그래밍 도메인에서 매우 흔한 용어(4.4 축약쓰지않기)
	 *   
	 *   * 명명기준 : 도메인에 맞고, 포괄적인 표현을 지양
	 *   * 완벽한 명명은 불가능하지만, 충분한 가치가 있다.
	 * */
	String tagName;
	String rank;
	int missions;
	LocalDate activeDutySince;
	
	String toCSV() {
		return String.join(",", 
				Arrays.asList(tagName, 
						rank,
						String.valueOf(missions),
						String.valueOf(activeDutySince))
				);
	}
}
