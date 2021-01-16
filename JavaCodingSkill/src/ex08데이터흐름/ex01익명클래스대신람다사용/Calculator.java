package ex08데이터흐름.ex01익명클래스대신람다사용;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 8.1 익명 클래스 대신 람다 사용하기
 * @author jkoogi
 * - computeIfAbsent() : 자바8 부터 일부 클래스에 유용한 메서드 추가
 *  > 키를 이용해 맵에서 값을 얻는데, 맵에 키가 없으면 값을 먼저 계산
 *  ! 맵에 키가 없을 경우 계산하는 로직을 입력 매개변수로 제공 필요
 * - 타입관점 : computeIfAbsent()에는 Function<Double, Double> 인터페이스 구현시 
 *   Double apply(Double value) 메서드를 포함하는 클래스의 인스턴스 필요
 *  > Double 타입은 values 맵에 정의된 타입에 따라 동적으로 구현
 * - 인터페이스를 구현할 익명클래스(anonymous class)를 초기화.
 *  > 클래스명이 없고, 클래스에 인스턴스가 하나만 있는 클래스 구현
 *  ! 익명클래스는 코드량이 늘고, 들여쓰기도 복잡해짐(인터페이스 타입과 메서드의 반복)
 *  * [return value * value]코드를 위해 불필요한 복잡도 증가 > 람다로 간결한 구현 
 */
public class Calculator {

	Map<Double, Double> values = new HashMap<>();

	/* 대상소스 */
//	Double square(Double x) {
//		Function<Double, Double> squareFunction = 
//				new Function<Double, Double>() {
//					@Override
//					public Double apply(Double value) {
//						return value * value;
//					}
//				};
//		return values.computeIfAbsent(x, squareFunction);
//	}
	/* 개선소스 
	 * - 연산로직이 바로 보이고 장황한 코드 정리
	 * > 람다 : 함수형 인터페이스 - 단일 추상메서드(SAM, Single Abstrct Method)를 포함하는 인터페이스를 구현
	 *  - Function 인터페이스가 apply() 추상메서드 하나만 포함
	 *  - 한줄, 여러줄로 작성 가능 
	 * */
	Double square(Double value) {
		/* 한줄  - return 키워드와 중괄호 없음 
		 *  - 매우 짧고 간결한 글루코드(glue code - 상호 호환이나 운용을 위해 프로그램 각 부분을 이어주는 코드)에 적합
		 *  > 가능한 여러줄 지양 (8.3 람다 대신 메서드 참조 - 적용)
		 *  > 암묵적이거나 명시적인 타입선언 가능 : 자바는 명시적으로 타입선언하는것이 기본이지만
		 *  , 람다 표현식의 매개변수는 컴파일러가 타입을 인지할 수 있어서 생략 가능 (타입추론 - typr inference)
		 *  > 매개변수가 두개 이상이거나 타입을 명시적으로 선언할 경우 소괄호 필수 (매개변수 하나, 타입 없을 경우 소괄호 생략) 
		 * */
		Function<Double, Double> squarFunction = factor -> factor * factor;
		/*타입선언*/
		Function<Double, Double> squarFunction_typeDef = (Double factor) -> factor * factor;
		/*여러줄*/
		Function<Double, Double> squarFunction_multiLine = factor -> {
			return factor * factor;
		};
		return values.computeIfAbsent(value, squarFunction);
	}
}
