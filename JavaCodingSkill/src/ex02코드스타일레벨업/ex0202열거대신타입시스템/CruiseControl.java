package ex02코드스타일레벨업.ex0202열거대신타입시스템;

import java.util.Objects;

/**
 * 2.2 열거할 수 있는 옵션인 경우 자바 타입시스템이 제공
 * @author jkoogi
 * 
 */
public class CruiseControl {
	
	private double targetSpeedKmh;

	/* 대상소스 */
//	void setPreset(int speedPreset) {
//		
//		if(speedPreset == CRUISE_SPEED_PRESET) {
//			setTargetSpeedKmh(CRUISE_SPEED_KMH);
//		}else if(speedPreset == PLANETARY_SPEED_PRESET) {
//			setTargetSpeedKmh(PLANETARY_SPEED_KMH);
//		}else if(speedPreset == STOP_PRESET) {
//			setTargetSpeedKmh(STOP_SPEED_KMH);
//		}
//	}
	
	/* 개선소스 */
	// 1. 열거할 수 있는 옵션인 경우 자바 타입시스템이 제공
	// 정수 파라미터(speedPreset)에 대한 유효성 검사 필요 : int > enum
	void setPreset(SpeedPreset speedPreset) {

		Objects.requireNonNull(speedPreset);
		
		setTargetSpeedKmh(speedPreset.speedKmh);
	}

	private void setTargetSpeedKmh(double speed) {
		targetSpeedKmh = speed;
	}
}

// 유효한 입력값만 처리 : 가능한 옵션을 모두 열거할 수 있는 경우 enum 사용
enum SpeedPreset{
	STOP(0), PLANETARY_SPEED(7667), CRUISE_SPEED(16944);
	
	final double speedKmh;
	
	SpeedPreset(double speedKmh){
		this.speedKmh = speedKmh;
	}
}