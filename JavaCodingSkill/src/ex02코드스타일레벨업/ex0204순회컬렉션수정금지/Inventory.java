package ex02코드스타일레벨업.ex0204순회컬렉션수정금지;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ex02코드스타일레벨업.ex0204순회컬렉션수정금지.sub.Supply;

/**
 * 2.4 순회하며 컬렉션 수정하지 않기
 * @author jkoogi
 * 1.List 순회시 List 수정불가
 *  - List 인터페이스를 이용한 표준구현, Set, Queue 등의 Collection 인터페이스의 구현을 순회시
 *    remove에 따른 ConcurrentModificationException 발생 : List 순회시 List 수정불가
 *    - 단일스레드 처리에서 동시(concurrenty) 실행오류  > Collection을 순회하며 대상 컬렉션을 수정하는 상황을 의미함
 *  ! 컴파일 오류로는 잡아내지 못함
 */
public class Inventory {

	private List<Supply> supplies = new ArrayList<>();
	
	void disposeContaminatedSupplies(){
		/* 대상소스 */
//		for (Supply supply : supplies) {
//			if(supply.isContaminated()) {
//				supplies.remove(supply);
//			}
//		}
		
		/* 개선소스 */
		// 1. 직관적 해결 : 리스트 전체순회 후 확인한 대상 제거 - 2단계 접근
		//  - 추가코드, 삭제대상 임시자료구조 관리, 시간과 메모리 소모
		// 2. Iterator를 활용한 while 루프
		//  - 첫번째 원소부터 다음항목(hasNext)으로 이동(next)하며 항목을 얻어 처리함
		// 3. for-each 루프의 경우 iterator 기반으로 코드복잡도를 줄여 순회를 제공하지만, remove 사용 불가
		// 4. CopyOnWriteArrayList같은 특수 List구현에서 순회하며 수정(remove 등)기능을 제공하지만, 
		//  수정, 삭제시 마다 전체 리스트를 복사하여 처리
		// 5. 람다 Collection.removeIf() 기능 - 데이터흐름 이슈(8장) 확인 
		Iterator<Supply> iterator = supplies.iterator();
		while (iterator.hasNext()) {
			if(iterator.next().isContaminated()) {
				iterator.remove();
			}
		}
	}
}
