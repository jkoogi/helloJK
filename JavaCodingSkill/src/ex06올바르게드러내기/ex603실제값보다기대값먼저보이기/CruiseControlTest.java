package ex06올바르게드러내기.ex603실제값보다기대값먼저보이기;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.CruiseControl;
import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.SpeedPreset;

/**
 * 6.3 실제 값보다 기대 값을 먼저 보이기
 * @author jkoogi
 * 1. assertTrue() 실패시 추적 정보가 불충분함(실패한 클래스, 해당 assertTrue의 줄번호 제공)
 *   
 */
public class CruiseControlTest {
	@Test
	void setPlanetarySpeedIs7667() {
		CruiseControl cruiseControl = new CruiseControl();

		cruiseControl.setPreset(SpeedPreset.PLANETARY_SPEED);

		/* 대상소스 */
//		Assertions.assertEquals(cruiseControl.getTargetSpeedKmh(), 7667);
		/* 테스트 결과 */
//		expected: <1337> but was: <7667>

		/* 개선소스
		 * - 기대값이 먼저 표시하고, 결과값을 표시한다.
		 *  */
		Assertions.assertEquals(7667, cruiseControl.getTargetSpeedKmh());
		/* 테스트 결과 */
//		expected: <7667> but was: <1337>

	}

}
