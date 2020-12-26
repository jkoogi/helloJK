package ex02코드스타일레벨업.ex0205계산연산순회제한;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import ex02코드스타일레벨업.ex0204순회컬렉션수정금지.sub.Supply;

/**
 * 2.5 순회하며 계산 집약적 연산하지 않기
 * @author jkoogi
 * 1.자료구조 순회시 수행할 연산유형 주의(정규식 등)
 *  - 반복문 처리시 비효율적 구조 개선
 *  > 정규식 컴파일 후 표현식 실행 
 */
public class Inventory {

	private List<Supply> supplies = new ArrayList<>();

	//(거대한 텍스트 집합에 효율적으로 질의하기 위해)정규식으로 supply 객체를 찾는 예
	List<Supply> find(String regex){
		List<Supply> result = new LinkedList<>();

		/* 대상소스 */
//		for (Supply supply : supplies) {
//			// regex로 부터 특수 목적의 오토마톤(automaton) 생성 : 패턴을 따르는 문자열만 허옹하고 나머지는 모드 거절
//			// - 정규식 오토마톤 컴파일은 시간, 처리전력을 소모 - 반복시마다 실행시 비효율적
//			if(Pattern.matches(regex, supply.toString())) {
//				result.add(supply);
//			}
//		}
		
		/* 개선소스 */
		// 1. 정규식을 한번만 컴파일 - 반복문 전 표현식 문자열 구성
		//  - Pattern.matches()의 두 연산을 분해 : 표현식 컴파일, 검색문자열 실행 분리
		//  > Pattern.compile() : Pattern의 인스턴스인 컴파일된 정규식 생성
		//  > 컴파일된 표현식 실행 : 검색대상을 위한 Matcher 생성, matches()로 정규식과 검색대상 확인
		Pattern pattern = Pattern.compile(regex);
		for (Supply supply : supplies) {
			// regex로 부터 특수 목적의 오토마톤(automaton) 생성 : 패턴을 따르는 문자열만 허옹하고 나머지는 모드 거절
			// - 정규식 오토마톤 컴파일은 시간, 처리전력을 소모 - 반복시마다 실행시 비효율적
			if(pattern.matcher(supply.toString()).matches()) {
				result.add(supply);
			}
		}
		return result;
	}
}
