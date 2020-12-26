package ex03슬기롭게주석사용하기.ex0301주석제거;

import java.util.ArrayList;
import java.util.List;

import ex03슬기롭게주석사용하기.ex0301주석제거.sub.Supply;

/**
 * 3.1 지나치게 많은주석 없애기
 *  - 코드에 뭔가를 덧붙여 설명하는 주석이 유효한 주석이다.
 * @author jkoogi
 * 1. 클래스 구조(필드, 메서드, 반환)를 강조하는 주석 제거
 * 2. 코드 자체를 설명하는 주석 제거
 * 3. 불필요한 내용 정리(jkoogi)
 */

/* 대상소스 */
//public class Inventory {
//	// 필드(하나만 있음) [1]
//	List<Supply> supplies = new ArrayList<>();// 제품 리스트 [2]
//	
//	// 메서드 [1]
//	int countContaminatedSupplies() {
//		// TODO : 필드가 이미 초기화되었는지(널이 아닌지) 검증한다
//		
//		int countaminatedCounter = 0; // 카운터 [2]
//		// 제품이 없으면 변질도 없다는 뜻이다.
//		for (Supply supply : supplies) {
//			if(supply.isContaminated()) {
//				countaminatedCounter++;// 카운터를 증가시킨다! [2]
//			} // 제품이 변질되었으면 IF 끝 [1]
//		} // FOR 끝 [1]
//		
//		// 변질된 제품 개수를 반환한다. [1]
//		return countaminatedCounter; // 유의해 처리한다! [3]
//	}
//} // Inventory 클래스 끝 [1]

/* 개선소스 */
public class Inventory {
	List<Supply> supplies = new ArrayList<>();
	
	int countContaminatedSupplies() {
		if(supplies == null || supplies.isEmpty()) {
			// 제품이 없으면 오염도 없다는 뜻이다  <- 디자인 결정에 대한 설명 - 온화한 지속, 예외발생 중 디자인 결정을 설명한 주석
			return 0;	
		}

		int countaminatedCounter = 0;
		
		for (Supply supply : supplies) {
			if(supply.isContaminated()) {
				countaminatedCounter++;
			}
		}
		
		return countaminatedCounter;
	}
}