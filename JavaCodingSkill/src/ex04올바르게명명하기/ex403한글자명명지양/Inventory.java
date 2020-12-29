package ex04올바르게명명하기.ex403한글자명명지양;

import java.util.ArrayList;
import java.util.List;

import ex03슬기롭게주석사용하기.ex0305구현결정설명.sub.Supply;

/**
 * 4.3 한 글자로 명명하지 않기
 * @author jkoogi
 * 
 * 1. 한글자 변수 - 읽기어렵다.
 * - n, l, h, m, c, s
 * - 소문자l, 숫자1 혼선
 * - 대문자O, 숫자0 혼선
 * > 변수명이 길더라도 컴파일 시 이름을 대체(바이트코드)
 */
public class Inventory {

	/* 대상소스 */
//	List<Supply> sl = new ArrayList<>();
//	
//	boolean isInStock(String n) {
//		Supply s = new Supply(n);
//		int l = 0;
//		int h = sl.size() - 1;
//		
//		while (l<=h) {
//			int m = l + (h -1)/2;
//			int c = sl.get(m).compareTo(s);
//			
//			if(c<0) {
//				l = m + 1;
//			}else if(c>0){
//				h = m - 1;
//			}else {
//				return true;
//			}
//		}
//		return false;
//	}
	
	/* 개선소스 */
	List<Supply> sortedList = new ArrayList<>();
	
	boolean isInStock(String name) {
		Supply supply = new Supply(name);
		int low = 0;
		int high = sortedList.size() - 1;
		
		while (low<=high) {
			int middle = low + (high -1)/2;
			int comparison = sortedList.get(middle).compareTo(supply);
			
			if(comparison<0) {
				low = middle + 1;
			}else if(comparison>0){
				high = middle - 1;
			}else {
				return true;
			}
		}
		return false;
	}
}
//3.5 구현결정 설명하기
//2.8 직접만들지 말고 자바API 사용하기
//2.1 매직넘버를 상수로 대체