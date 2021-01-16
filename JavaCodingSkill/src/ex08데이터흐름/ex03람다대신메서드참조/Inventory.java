package ex08데이터흐름.ex03람다대신메서드참조;

import java.util.ArrayList;
import java.util.List;

import ex08데이터흐름.ex02명령형방식대신함수형.sub.Supply;

/**
 * 8.3 람다 대신 메서드 참조
 * @author jkoogi
 * - 람다장점 : 코드 가독성 증가
 * - 람다장점 : 스트림 중간 부터 실행 불가(스트림 전체를 실행해야함)
 *  > (단위 테스트 제약)검증이 어려움
 * - filter, map으로 구성된 아래 예제 코드는 (변수로 참조하지 않는 관계로)재사용 불가.
 *  > 람다표현식은 자신을 감싼 메서드에 귀속됨
 *  * 람다식이 복잡해지면 오류가능성이 높아짐
 * # 메서드 호출에 기반한 코드는 테스트가 쉽고, 각각 호출이 가능하여 구조화 용이
 *  - 함수형 프로그래밍에서 메서드 참조 활용
 */
public class Inventory {
	List<Supply> supplies = new ArrayList<>();
	
	long countDifferentKinds() {
		/* 대상소스 */
//		return supplies.stream()
//				.filter(supply->supply.isUncontaminated())
//				.map(supply->supply.getName())
//				.distinct()
//				.count();
		/* 개선소스  
		 * - 람다 표현식을 정의하는 대신 미리정의된 메서드를 스트림 내에서 바로 참조
		 *  > 1.2 부정피하기 적용
		 *  > 스트림을 메서드와 조합한 처리로 구성하면서 테스트, 코드가독성에 유리
		 * - 문법 : 스트림의 메서드 참조 문법 (ClassName::methodName) 사용
		 *  > 필터 연산에는 Predicate 인터페이스에 맞는 메서드 참조(객체 파라미터, 불 반환)
		 *  > 맵 연산에는 Function 인터페이스에 맞는 메서드 참조(객체 파라미터, 객체 반환)
		 *  > 람다 표현식을 메서드로 변환하는 방법(복잡한 표현식이나, 중복 표현식에 따른 '람다중복' 회피 시)
		 *  > 람다 메서드 참조 : [CalssName::new] 생성자  참조 가능 - 스트림을 컬렉션으로 전환 시
		 *    collect(Collectors.toCollecton(TreeSet::new))  
		 * */
		return supplies.stream()
				.filter(Supply::isUncontaminated)
				.map(Supply::getName)
				.distinct()
				.count();
	}
}
