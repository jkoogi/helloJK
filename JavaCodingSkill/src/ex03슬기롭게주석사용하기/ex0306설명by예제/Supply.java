package ex03슬기롭게주석사용하기.ex0306설명by예제;

import java.util.regex.Pattern;

/**
 * 3.6 예제로 설명하기
 * @author jkoogi
 * 1. 반 자연어（scmi-natur시 language）로 형식을 설명하면서 
 *    유효한 예제와 유효하지 않은 예제 몇 가지를 제공
 *  - (앞 예제의 내용을)한줄로 요약
 *  - 실제 의미를 설명
 *  - 구체적인 예제(표현식을 한번에 이해할 수 있도록) : 단위테스트 추가
 *  * 구체적인 의미의 변수명 적용
 */
public class Supply {

	/* 대상소스 */
	/** 
	 * 아래 코드는 어디서든 재고를 식별한다.
	 * 
	 * S로 시작해 숫자 다섯자리 재고 번호가 나오고
	 * 뒤이어 앞의 재고 번호와 구분하기 위한 역 슬래시가 나오고
	 * 국가 코드가 나오는 엄격한 형식을 따른다.
	 * 국가 코드는 반드시 창여 국가인 (US, EUZ RU, CN) 중
	 * 하나를 뜻하는 대문자 두 개여야 한다.
	 * 이어서 마침표와 실제 재고명이 소문자로 나온다.
	 * */
//	static final Pattern CODE = 
//			Pattern.compile("^S\\d{5}\\\\(US|EU|RU|CN)\\.[a-z]+$");
	// 강력하지만, 복잡한 구조체(construct) - 정규식
	
	/* 개선소스 */
	/**
	* 아래 표현식은 어디서든 재고 코드를 식별한다.
	* 
	* 형식 : "S<inventory-number>\<COUNTRY-CODE>.<name>"
	* 
	* 유효한 예: "S12345W.pasta", "S08342\CN.wrench",
	* 		  "S88888XEU.laptop", "S12233XRU.brush"
	* 
	* 유효하지 않은 예: 
	* "R12345\RU.fuel."		(재고가 아닌 자원)
	* "S1234XUS.light"		(숫자가 다석 개여야 함)
	* "S01234\AI.coconut"	(잘못된 국가코드. US나 EU, RU, or CN 중 하나를 사용한다.)
	* " S88888\EU.laptop "	(마지막에 여백이 있음.)
	*/
	static final Pattern SUPPLY_CODE = 
			Pattern.compile("^S\\d{5}\\\\(US|EU|RU|CN)\\.[a-z]+$");
	
}
