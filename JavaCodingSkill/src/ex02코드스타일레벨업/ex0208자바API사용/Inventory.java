package ex02코드스타일레벨업.ex0208자바API사용;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import ex02코드스타일레벨업.ex0204순회컬렉션수정금지.sub.Supply;

/**
 * 2.8 직접 만들지 말고 자바 API 사용하기
 *  - 7.3 구체 타입보다 추상 타입（190쪽）
 *  - 2.3 For 루프 대신 For-Each（062쪽）
 * @author jkoogi
 * 1. 유틸리티 클래스 활용 : Collection - Collections, Object - Objects
 */
public class Inventory {

	private List<Supply> supplies = new ArrayList<>();

	//재고 내 Supply 수량 조회
	/* 대상소스 */
//	int getQuantity(Supply supply) {
//		if(supply == null) {
//			throw new NullPointerException("supply must not be null");
//		}
//		
//		int quantity = 0;
//		for (Supply supplyInStock : supplies) {
//			if(supply.equals(supplyInStock)) {
//				quantity++;
//			}
//		}
//		return quantity;
//	}
	
	/* 개선소스 */
	// JAVA API를 이용하여 로직을 직접 구현하지 않고 정의
	int getQuantity(Supply supply) {
		Objects.requireNonNull(supply,"supply must not be null");
		//frequency : 객체 출현횟수 조회
		return Collections.frequency(supplies, supply);
	}
}
