package ex06올바르게드러내기.ex608테스트매개변수화;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance;
import ex06올바르게드러내기.ex608테스트매개변수화.sub.DistanceUnit;

/**
 * 6.8 테스트 매개변수화
 * @author jkoogi
 * 1. 메서드, 메서드 사슬을 같은 방법으로 테스트하며 다양한 매개변수로 테스트
 *  - 매개변수 별 테스트 열겨 : 쉽지만 복잡하다.
 *  : 거리값의 단위를 바꾼 후 다시 변환한 결과가 같은지 테스트 - given-when-then구조를 잘따름
 */
public class DistanceConversionTest {
//	@Test
//	/* 대상소스 
//	 * assertRoundTrip(1) 호출하며 실패 > 나머지 두 테스트는 실행하지 않음
//	 * assertRoundTrip(1) 복구후 실패 > 나머지 테스트는 실행하지 않음
//	 *  - 해결 : 각 테스트를 실행하고 테스트당 어서션 분리... 코드중복과 복잡도 증가
//	 * */
//	public void testConversionRoundTrip() {
//		assertRoundTrip(1);
//		assertRoundTrip(1_000);
//		assertRoundTrip(9_999_999);
//	}
//	org.opentest4j.AssertionFailedError: 
//		expected: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@109bb57> 
//		but was: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@136e911>

//	private void assertRoundTrip(int kilometers) {
//		Distance expectedDistance = new Distance(
//				DistanceUnit.KILOMETERS,
//				kilometers);
//		Distance actualDistance = expectedDistance
//				.convertTo(DistanceUnit.MILES)
//				.convertTo(DistanceUnit.KILOMETERS);
//		Assertions.assertEquals(expectedDistance, actualDistance);
//		
//	}

	/* 개선소스
	 *  - 테스트의 매개변수화 : @ParameterizedTest, @ValueSource
	 *   : 실제 테스트코드와 "매개변수(입력, 기대출력)" 분리
	 *   > JUnit은 각 매개변수마다 별개의 테스트를 실행
	 *  - CSV 파일 같은 외부소스나 메서드 반환값을 매개변수로 넣어 활용가능
	 *  - 매개변수가 적으면 명시적 선언을 추천 
	 *  */
	@Test
	@ParameterizedTest(name="#{index}: {0}km == {0}km->mi->km") // name : 6.6 테스트 설명하기, {index} 변수로 테스트의 인덱스 참조, 중괄호로 테스트 메소드의 인수 참조 : {0}은 첫번째 인수 
	@ValueSource(ints = {1, 1_000, 9_000_000}) //메개변수를 정수배열로 선언 (6.5 예외 처리는 JUnit에 맡기기 참조 - 예외가 일어나지 않기)
	public void testConversionRoundTrip(int kilometers) {
		Distance expectedDistance = new Distance(
				DistanceUnit.KILOMETERS,
				kilometers
				);
		
		Distance actualDistance = expectedDistance
				.convertTo(DistanceUnit.MILES)
				.convertTo(DistanceUnit.KILOMETERS);
		
		Assertions.assertEquals(expectedDistance, actualDistance);
	}
//	ex06올바르게드러내기.ex608테스트매개변수화.DistanceConversionTest
//		testConversionRoundTrip(ex06올바르게드러내기.ex608테스트매개변수화.DistanceConversionTest)
//			#1: 1km == 1km->mi->km
//			 org.opentest4j.AssertionFailedError: expected: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@12d3247> but was: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@cd23d9>
//			  at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:52)
//			#2: 1000km == 1000km->mi->km
//			 org.opentest4j.AssertionFailedError: expected: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@1e8284b> but was: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@16e2f39>
//			  at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:52)
//			#3: 9000000km == 9000000km->mi->km
//			 org.opentest4j.AssertionFailedError: expected: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@c8a2c0> but was: <ex06올바르게드러내기.ex608테스트매개변수화.sub.Distance@e6aa2>
//			  at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:52)
//	ex06올바르게드러내기.ex608테스트매개변수화.DistanceConversionTest
//		java.lang.Exception: Method testConversionRoundTrip should have no parameters
//		 at org.junit.runners.model.FrameworkMethod.validatePublicVoidNoArg(FrameworkMethod.java:76)

}
