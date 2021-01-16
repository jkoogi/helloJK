package ex08데이터흐름.ex04부수효과피하기;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ex08데이터흐름.ex02명령형방식대신함수형.sub.Supply;

/**
 * 8.4 부수 효과 피하기
 * @author jkoogi
 * - 이론 : 함수형 프로그래밍에는 부수효과(side effect)가 없음
 *  > 입력 데이터에 따른 응답 결과 생성
 *  > 오가는 데이터는 불변
 *  ! 명령형, 객체지향 프로그래밍은 부수효과에 의존 - 프로시저나 메서드에 따른 데이터, 상태의 변경
 * - 자바 : 명령형, 객체지향, 함수형 - 혼합구현이 가능함에 따라 부수효과 최소화 필요
 *  > 자바는 여러 스레드 간 부수효과 발생에 안전장치가 없음
 *  > 람다표현식을 병렬처리해도 부수효과 발생 가능(ArrayList는 thread-safe 하지않음)
 *  * 멀티스레드환경에서 안전
 *   > Collections의 synchronizedXXX()메서드 사용 (병렬 처리 불가)
 *  * 멀티스레드환경에서 안전하면서도, 스레드가 병렬적으로 작업을 처리할 수 있도록
 *   > java.util.concurrent 패키지 제공 클래스 사용
 *   - ConcurrentHashMap
 *   - ConcurrentLinkedQueue
 * - 예제는 목표를 구현하기 위해 부수효과에 큰 의존 중.
 *  > 스트림의 forEach(). Consumer addToNames 주의
 *    Consumer는 람다 표현식 밖에 있는 리스트에 원소를 추가. 
 *  > 코드에 기능상의 오류는 없으나, 동시 실행이 진행되면 쉽게 고장.
 */
public class Inventory {
	List<Supply> supplies = new ArrayList<>();
	/* 대상소스
	 * - 함수형 프로그래밍을 처음 접하는 자바 개발자의 접근방법
	 *  > 명령형 방식으로 돌아가 스트림 종료 : 부수효과를 통해야만 가능
	 * */
//	long countDifferentKinds() {
//		List<String> names = new ArrayList<>();
//		
//		Consumer<String> addToNames = name -> names.add(name);
//		
//		supplies.stream()
//				.filter(Supply::isUncontaminated)
//				.map(Supply::getName)
//				.distinct()
//				.forEach(addToNames);
//		return names.size();
//	}
	/* 개선소스 1 
	 * - 리스트를 직접 만들지 않고 컬렉션 내 스트림에 남아있는 각 원소를 collect()
	 *  > 리스트를 만들려면 collect(Collectors.toList())로 스트림을 종료 : Set으로 반환하려면 Collectors.toSet() 사용
	 * */
//	long countDifferentKinds() {
//		List<String> names = supplies.stream()
//									 .filter(Supply::isUncontaminated)
//									 .map(Supply::getName)
//									 .distinct()
//									 .collect(Collectors.toList());
//		return names.size();
//	}

	/* 개선소스 2 
	 * - count() : 스트림 내 남은 원소 수 반환 (reduce()연산자를 사용해 스트림을 하나의 long 값으로 전환)
	 *  > Stream클래스의 reduce() 연산자 : reduce(0, (currentResult, streamElement) - > currentResult + 1)
	 *    리스트를 하나의 정숫값으로 리듀스. (0은 초깃값, 스트림 내 각 원소마다 결과에 1씩 더함)
	 *  * forEach()는 쉽게 부수효과를 일으키니 지양.(8.5 복잡한 스트림 종료 시 컬렉트 사용하기)
	 *    collect()와 reduce() 지향 : 스트림을 직접 종료(List, Set, long등 원하는 자료구조를 생성)
	 *    
	 * */
	long countDifferentKinds() {
		return supplies.stream()
				 .filter(Supply::isUncontaminated)
				 .map(Supply::getName)
				 .distinct()
				 .count();
	}
	
}
