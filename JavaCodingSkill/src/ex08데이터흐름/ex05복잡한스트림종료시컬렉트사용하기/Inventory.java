package ex08데이터흐름.ex05복잡한스트림종료시컬렉트사용하기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import ex08데이터흐름.ex02명령형방식대신함수형.sub.Supply;

/**
 * 8.5 복잡한 스트림 종료 시 컬렉트 사용하기
 * @author jkoogi
 *  - supplies 리스트 내 고유 원소수를 모두 세는 대신 
 *    남은 제품 수를 이름별로 묶어 계산해 Map<String, Long> 형태로 구성
 *   > SQL : SELECT name, count(*) FROM supplies GROUP BY name
 *   > Map<String, Long> nameToCount를 계산할 때 부수효과에 의존
 *   > addToNames 구현이 8.4 부수 효과 피하기 예제의 구현보다 복잡
 */
public class Inventory {
	List<Supply> supplies = new ArrayList<>();

	/* 대상소스 */
//	Map<String, Long> countDifferentKinds(){
//		Map<String, Long> nameToCount = new HashMap<>();
//		
//		Consumer<String> addToNames = name -> {
//			if(!nameToCount.containsKey(name)) {
//				nameToCount.put(name, 0L);
//			}
//			nameToCount.put(name, nameToCount.get(name)+1);
//		};
//		
//		supplies.stream()
//				.filter(Supply::isUncontaminated)
//				.map(Supply::getName)
//				.forEach(addToNames);
//		return nameToCount;
//	}

	/* 개선소스 
	 * - 자바는 (8.4 부수효과 피하기) 스트림 결과를 Collection으로 만들 경우에 대해 미리 정의된 여러 Collectors를 제공
	 *  > collect()
	 *  > toList() : List 자료구조 반환
	 *  > toSet() : Set 자료구조 반환
	 *  > toMap() : Map 자료구조 반환
	 *  > groupingBy() : Map 자료구조 반환
	 *   - Supply 객체를 이름별로 그루핑하기 위해 Supply::getNmae 전달
	 *     참조를 전달함으로써 결과 Map의 키 타입. 
	 *     즉 String까지 명시 : Map<String, Collection<Supply>> 로 반환
	 *     : groupingBy()을 이용하여 .map 연산자 불필요
	 *   - Collectors.counting() : 한 그룹 내의 Supply 인스턴스 수 count
	 *     : Map<String, Long> (이름별 항목수) 응답
	 *     
	 *  * collect()를 사용하는 방법이 더 간결하고, 요점전달에 용이 (like SQL 쿼리)
	 *    - 컬렉터 기능
	 *    > groupingBy(), partitioningBy(), maxBy(), joining()
	 *    , mapping(), summingInt(), averagingLong(), reducing()
	 *    , filtering(), flatMapping() - 자바9
	 * */
	Map<String, Long> countDifferentKinds(){
		return supplies.stream()
						.filter(Supply::isUncontaminated)
						.collect(Collectors.groupingBy(Supply::getName,
								 Collectors.counting())
						);
	}
}
