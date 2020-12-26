package ex02코드스타일레벨업.ex0201메직넘버to상수;

/**
 * 2.1 매직 넘버를 상수로 대체 - 프로그램의 동작을 제어하는 표면상 의미가 없는 숫자 : 매직 넘버
 * @author jkoogi
 * 
 */
public class CruiseControl {
	
	// 1. 매직 넘버를 상수(static:최적화, final:안정화)로 선언 : 모두 대문자(자바코드규칙)
	// 사전설정옵션
	static final int STOP_PRESET = 0;
	static final int PLANETARY_SPEED_PRESET = 1;
	static final int CRUISE_SPEED_PRESET = 2;
	
	// 타깃속도
	static final double CRUISE_SPEED_KMH = 16944;
	static final double PLANETARY_SPEED_KMH = 7667;
	static final double STOP_SPEED_KMH = 0;
	
	private double targetSpeedKmh;
	
	void setPreset(int speedPreset) {
		
		/* 대상소스 */
//		if(speedPreset == 2) {
//			setTargetSpeedKmh(16944); 	//16944 옵션을 외워야 한다
//		}else if(speedPreset == 1) {
//			setTargetSpeedKmh(7667);	//컴파일러도 대응할 수 없다
//		}else if(speedPreset == 0) {
//			setTargetSpeedKmh(0);
//		}
		
		/* 개선소스 */
		// 1. 매직넘버 정리
		if(speedPreset == CRUISE_SPEED_PRESET) {
			setTargetSpeedKmh(CRUISE_SPEED_KMH);
		}else if(speedPreset == PLANETARY_SPEED_PRESET) {
			setTargetSpeedKmh(PLANETARY_SPEED_KMH);
		}else if(speedPreset == STOP_PRESET) {
			setTargetSpeedKmh(STOP_SPEED_KMH);
		}
	}

	private void setTargetSpeedKmh(double speed) {
		targetSpeedKmh = speed;
	}
}
