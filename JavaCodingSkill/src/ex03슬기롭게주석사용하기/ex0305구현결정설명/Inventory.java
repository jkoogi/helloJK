package ex03슬기롭게주석사용하기.ex0305구현결정설명;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ex03슬기롭게주석사용하기.ex0305구현결정설명.sub.Supply;



/** 
 * 3.5 구현 결정 설명하기
 * @author jkoogi
 * 1. 결정이 필요한 상황에서 주석으로 설명
 *  - 옳고 그른것이 없는 상황
 *  - 장점과 단점이 모두 있는 상황
 *  > 주석에 표시할 내용 : 템플릿을 정의하여 필요한 요건 표시 
 *   선택근거 : 왜 빠른가, 왜 빨라야하나, binarySearch가 정말 빠른가, 비용이나 트레이드오프는 무엇이었나
 *   명시내용 : 사용사례(use case), 우려사항, 해법, 트레이드오프나 비용 
 *   
 *   In the context of [USE CASE], 	[사용사례]의 맥락에서
 *   facing [CONCERN]				직면하는 [우려사항]과
 *   we decided for [OPTION]		우리가 선택한 [해법]으로
 *   to achieve [QUALITY],			얻게 되는 [품질]과
 *   accepting [DOWNSIDE].			받아들여야하는 [단점]
 *   
 *   
 */

/* 대상소스 */
//public class Inventory {
//
//	private List<Supply> list = new ArrayList<>();
//	
//	void add(Supply supply) {
//		list.add(supply);
//		Collections.sort(list);
//	}
//	
//	boolean isInStock(String name) {
//		// 빠른구현
//		return Collections.binarySearch(list, new Supply(name)) != -1;
//	}
//}

/* 개선소스 */
public class Inventory {
	// 리스트를 정렬된 채로 유지한다. isInStock()를 참고한다.
	private List<Supply> list = new ArrayList<>();
	
	void add(Supply supply) {
		list.add(supply);
		Collections.sort(list);
	}
	
	boolean isInStock(String name) {
		/*
		 * 재고가 남았는지 재고명으로 확인해야 한다면,
		 * 재고가 천개이상 일 때 심각한 성능 이슈에 직면한다.
		 * 1초 안에 항목을 추출하기 위해
		 * 비록 재고를 정렬된 채로 유지해야 하지만
		 * 이진검색 알고리즘을 쓰기로 결정했다.
		 * */
		return Collections.binarySearch(list, new Supply(name)) != -1;
	}
}
