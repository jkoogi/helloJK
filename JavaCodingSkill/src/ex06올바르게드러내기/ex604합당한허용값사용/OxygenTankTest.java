package ex06올바르게드러내기.ex604합당한허용값사용;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import ex06올바르게드러내기.ex604합당한허용값사용.sub.OxygenTank;

/**
 * 6.4 합당한 허용값 사용하기
 * @author jkoogi
 * - 정수값이 아닌 부동소수점 수를 비교하는 테스트
 * > 자바는 부동소수점 (자바는 IEEE 754부동소스점 산술연산 표준을 따름) 수를 모두 유한한 비트수로 표현 불가
 * * 모든 프로그래밍 언어는 부동소수 값을 근사값으로 계산 : 오차발생 > 연산 시 소수점 자릿수 명시
 */
public class OxygenTankTest {
	
	/* 개선소스 - 추가 */
	static final double TOLERANCE = 0.00001;
	
	@Test
	public void testNewTankIsEmpty() {
		OxygenTank tank = OxygenTank.withCapacity(100);
		System.out.println("tnak : " + tank.getStatus());
		Assertions.assertEquals(0, tank.getStatus());
	}

	@Test
	public void testFilling() {
		OxygenTank tank = OxygenTank.withCapacity(100);
		
		tank.fill(5.8);// double 부동소수(근사값)
		tank.fill(5.6);// double 부동소스(근사값)

		/* 대상소스 */		
//		Assertions.assertEquals(0.114, tank.getStatus());
		/* 테스트 결과 */
		// expected: <0.114> but was: <0.11399999999999999>

		/* 개선소스 
		 * - 부동소스점 연산은 근사값 계산에 따른 오차를 감수해야함 : 기대값과 실제값의 차이를 허용
		 * > assertEquals(double expected, doubel actual, double delta)
		 *  허용값 delta (소숫점 둘째자리 허용 : 0.1*10^-2 = 0.001)
		 *  
		 *  * assertEquals()에 float, double을 사용하는 경우 자릿수에 따른 허용수준을 명시
		 *  * 돈 계산에는 부동소스를 사용하지 말것
		 *    - (달러를 센트 단위까지 계산하기 위해 double로 사용하지 말고, 센트는 long, BigDecimal 사용)
		 * */
		Assertions.assertEquals(0.114, tank.getStatus(), TOLERANCE);
	}

}
