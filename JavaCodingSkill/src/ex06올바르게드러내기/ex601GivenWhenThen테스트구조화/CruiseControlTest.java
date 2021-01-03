package ex06올바르게드러내기.ex601GivenWhenThen테스트구조화;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.CruiseControl;
import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.SpeedPreset;

/**
 * 6.1 Given-When-Then으로 테스트 구조화
 * @author jkoogi
 * 1. 테스트 구성요소
 *  - Given : 테스트를 준비하는 단계 (2가 찍혀있는 계산기가 주어졌을 때)
 *  - When : 테스트 연산 수행 (숫자 3을 더할 경우)
 *  - Then : 수행결과와 기대값 비교(숫자5가 나타난다)
 * 2. 구조화된 코드 구분 (2.6 새 줄로 그루핑)
 * 3. 구성별 주석
 *   
 */
public class CruiseControlTest {
	@Test
	void setPlanetarySpeedIs7667() {
		// given
		CruiseControl cruiseControl = new CruiseControl();
		
		// when
		cruiseControl.setPreset(SpeedPreset.PLANETARY_SPEED);
		
		// then
		Assertions.assertTrue(7667 == cruiseControl.getTargetSpeedKmh());

	}

}
