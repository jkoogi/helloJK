package ex06올바르게드러내기.ex602의미있는어서션사용;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.CruiseControl;
import ex06올바르게드러내기.ex601GivenWhenThen테스트구조화.sub.SpeedPreset;

/**
 * 6.2 의미 있는 어서션 사용하기
 * @author jkoogi
 * 1. assertTrue() 실패시 추적 정보가 불충분함(실패한 클래스, 해당 assertTrue의 줄번호 제공)
 *  - assertEquals() 사용 : 문자열 비교 메시지 표시(5.3 메시지로 원인설명)
 *  > 테스트 목적에 맞는 assertion 사용
 *  : assertTrue, assertEquals, assertFalse, assertNotEquals, assertSame
 *  , assertArrayEquals, assertLinesMatch, assertIterableEquals
 *  , assertAll (여러 어서션 통합)
 *  , assertTimeout (실행시간 체크)
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
