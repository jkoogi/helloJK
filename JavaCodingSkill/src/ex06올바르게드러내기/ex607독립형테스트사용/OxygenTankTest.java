package ex06올바르게드러내기.ex607독립형테스트사용;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import ex06올바르게드러내기.ex607독립형테스트사용.sub.OxygenTank;


/**
 * 6.7 독립형 테스트 사용하기
 * @author jkoogi
 * 1. @BeforeEach, @BeforeAll : 테스트의 Given 부분에 필요한 공통 설정 코드 구성 - 테스트를 이해하는데 저해요인
 *  - 설정 : 절반만 채워진 탱크
 *  - 테스트 : 설정된 탱크 사용
 *  > 매 단일 테스트 마다 설정부분을 염두하고 테스트 분석 (테스트가 독립적이지 않음)
 *  설정 기능의 단점 : 테스트를 이해하는데 저해요인으로 작용
 *   - 테스트가 많을경우
 *   - 클래스의 일부만 분석할 경우
 *   - 오류가 발생할 경우 원인사슬은 문제지점부터 표시함에 따라 given 부분을 놓칠 수 있음
 *     (선언부 추적, 상속계층 분석 등 분석시간 추가)
 *   > 테스트와 설정코드를 더 분명히 연결
 */
public class OxygenTankTest {
	OxygenTank tank;
	
	@BeforeEach
	/* 대상소스 */
//	void setup() {
//		tank = OxygenTank.withCapacity(10_000);
//		tank.fill(5_000);
//	}
	/* 개선소스  
	 *  - 테스트를 독립적으로 구성 : given, when, then 부분을 하나의 테스트 메서드 안에서 바로 연결
	 *   > 설정부 @BeforeEach 를  createHalfFilledTank()로 변경해 static 메서드로 분리
	 *    : 메소드 호출마다 실행하지 않고 공유 
	 *  효과 
	 *   - 암묵적 설정된 부분을 찾지 않음 > 명시적 설정부 참조
	 *   - 테스트를 다른 클레스로 이동하기 쉽다.
	 *  * 독립적 테스트 구성, 메서드만으로 전체 테스트를 이해할 수 있는 구성
	 *   - 테스트 설정변수가 세개 이상인 경우 @BeforeEach 대신 테스트 전체설정 클래스 구성
	 * */
	static OxygenTank createHalfFilledTank() {
		OxygenTank tank = OxygenTank.withCapacity(10_000);
		tank.fill(5_000);
		return tank;
	}
	
	@Test
	void depressurizingEmptiesTank() {
		/* 개선소스  추가 */
		OxygenTank tank = createHalfFilledTank();
		
		tank.depressurize();

		Assertions.assertTrue(tank.isEmpty());
	}
	
	@Test
	void completelyFillTankMustBeFull() {
		/* 개선소스  추가 */
		OxygenTank tank = createHalfFilledTank();
		
		tank.fillUp();

		Assertions.assertTrue(tank.isFull());
	}
	

}
