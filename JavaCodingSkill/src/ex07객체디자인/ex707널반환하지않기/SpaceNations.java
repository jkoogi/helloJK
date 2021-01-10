package ex07객체디자인.ex707널반환하지않기;

import java.util.Arrays;
import java.util.List;

import ex07객체디자인.ex707널반환하지않기.sub.SpaceNation;

/**
 * 7.7 널 반환하지 않기
 * @author jkoogi
 * - 호출된 메소드에 반환할 값이 없더라도 null을 반환하지 말자 : 프로그램 안정성 위협
 * . 국가코드와 국가명 간 관계를 SpaceNations 클래스로 디자인
 *  > 국가코드(String)를 메서드에 전달하면 국가면(String) 반환
 *    - 알려지지 않은 국가코드의 경우 null 반환으로 오류발생 가능성 보유
 * . 메서드에서 null을 반환할 가능성이 있을 경우 NullPointException을 대비한 명시적 null 체크 필요
 * . getByCode 메서드는 null 응답을 허용하는 잘못된 디자인 사례
 *  > 부적절한 null 대신 무엇을 반환해야하는지 검토 
 */
public class SpaceNations {

	/* 개선소스  추가 */
	static final SpaceNation UNKNOWN_NATION = new SpaceNation("","");
	
	static List<SpaceNation> nations = Arrays.asList(
			new SpaceNation("US", "United States"),
			new SpaceNation("RU", "Uussia")
	);

	static SpaceNation getByCode(String code) {
		for (SpaceNation nation : nations) {
			if(nation.getCode().equals(code)) {
				return nation;
			}
		}

		/* 대상소스 */
//		return null;
		
		/* 개선소스 1 - 널 객체 패넌(null object pattern)
		 * - 객체에 실질적인 값이 없음을 명시적으로 표현한 객체 : UNKNOWN_NATION
		 * > 프로그램 흐름을 방해하지 않으면서 예외 회피
		 * . 호출부에서는 UNKNOWN_NATION 발생시 무시할지, 예외를 던질지 선택하여 처리할 수 있음
		 * * 널 객체 : 비용이 막대한 실수를 예방하기 위한 것 
		 *   - 빈문자열, 빈컬렉션, 특수 클래스 인스턴스 등 다양한 형태로 정의
		 *   > 토니 호어(Tony Hoare)가 저술한 null 참조소개 참고
		 *   (8.7 널 대신 옵셔널 - 값의 존재, 부재를 어떻게 처리하는지 확인) 
		 *  */
//		return UNKNOWN_NATION;
		
		/* 개선소스 2 - IllegalArgumentException 또는 NoSuchElementException 예외던지기
		 * - 문제를 명확히 하여 개선을 유도
		 * - 호출하는 쪽에서 명시적인 문제처리 로직 필요
		 * */
		throw new IllegalArgumentException();
		
	}

	/* 대상소스 실행코드 */
	public static void main(String[] args) {
		String us = SpaceNations.getByCode("US").getName();
		System.out.println("us : "+us);
		// -> "United States"
		String anguilla = SpaceNations.getByCode("AI").getName();
		System.out.println("anguilla : "+anguilla);
		// -> NullPointException
	}
}
