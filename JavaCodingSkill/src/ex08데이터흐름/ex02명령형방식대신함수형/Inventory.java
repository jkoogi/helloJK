package ex08데이터흐름.ex02명령형방식대신함수형;

import java.util.ArrayList;
import java.util.List;

import ex08데이터흐름.ex02명령형방식대신함수형.sub.Supply;

/**
 * 8.2 명령형 방식 대신 함수형
 * @author jkoogi
 * - 컬렉션 처리 가독성은 함수형 프로그래밍 방식이 명령형 프로그래밍 방식보다 읽기 쉽다.
 * (명령형 프로그래밍은 무엇을 '어떻게' 할 것인가를 중심으로 구현하고, 함수형 프로그램이은 '무엇'을 할 것인가에 중심을 두고 구현한다.)
 */
public class Inventory {
	List<Supply> supplies = new ArrayList<>();

	/* 대상소스 
	 * - 컬렉션 처리를 위한 메소드는 복잡하다
	 *  > 순회하는 과정에서 몇가지 조건을 수행하는 코드로 이해하는데 직관적이지 않다.
	 *  ! 무엇을 하는지, 루프와 조건, 변수 할당문, 메서드 호출 결과는 어떤지 등의 분석을 위해 좋은 이름과 주석이 필요함
	 *  일반적인 관심사 : '무엇' ('어떻게'는 관심사가 아님) 
	 *  * 한줄씩 읽을 수 밖게 없는 예제의 코드는 메서드의 의도를 흐림
	 * - 람다식은 '무엇'이 이루어지는지를 명시할 수 있지만, '어떻게'는 표현할 수 없음 : 간결하고 읽기 쉬운 코드
	 * */
//	long countDifferentKinds() {
//		List<String> names = new ArrayList<>();
//		for (Supply supply : supplies) {
//			if(supply.isUncontaminated()) {
//				String name = supply.getName();
//				if (!names.contains(name)) {
//					names.add(name);
//				}
//			}
//		}
//		return names.size();
//	}

	/* 개선소스  
	 * - stream() 컬렉션 : 컬렉션을 스트림으로 변환(함수형 프로그래밍 시작)
	 *  > 8.2_컬렉션 파이프 라인.pdf
	 * - filter() : 특정 조건을 충족하는 대상만 적용(isUncontaminated())
	 *  > true, false의 boolean 결과를 판단하는 Predicate(근거)
	 *  > 인스턴스의 유일한 추상메서드 서명 : boolean test(Supply supply)
	 * - 타입매핑 : Supply > String(name)
	 * - distinct() : 중복 결과 버림
	 * - count() : 스트림 내 남은 수 반환(스트림을 종료하고 명령형 방식으로 전환하는 종료연산자)
	 * * 8.3_java.util.stream (Java SE 9 및 JDK 9).pdf
	 * */
	long countDifferentKinds() {
		return supplies.stream()
				.filter(supply -> supply.isUncontaminated())
				.map(supply -> supply.getName())
				.distinct()
				.count();
	}
}
