package ex05문제발생에대비하기.ex501빠른실패;

/**
 * 5.1 빠른실패
 * @author jkoogi
 * 1. if조건문으로 분기 예외처리 구성이유까지 확인하기 어려움
 *  - 정상 유형의 제어흐름을 쉽게 파악하기 어려움(중간에 위치)
 *  - 생각의 흐름에 유의해 로직을 배치할 필요성 : 매개변수 예외처리 > 정상처리 > 매개변수 예외처리 
 *  > 어색한 처리 흐름으로 코드이해 시간을 낭비함
 * 2. if조건분기의 단점- 연결된 분기의 모든 조건을 함께 이해해야 한다.
 *  - 조건1 : speedKmh < 0 (양호)
 *  - 조건2 : !(speedKmh <= 0) && speedKmh <= SPEED_LIMIT
 *  - 조건3 : !(!(speedKmh <= 0) && speedKmh <= SPEED_LIMIT)
 *   
 */
public class CruiseControl {

	static final double SPEED_OF_LIGHT_KMH = 1079252850;
	static final double SPEED_LIMIT = SPEED_OF_LIGHT_KMH;
	
	private double targetSpeedKmh;

	/* 대상소스 */
//	void setTargetSpeedKmh(double speedKmh) {
//		if(speedKmh < 0) {
//			throw new IllegalArgumentException();			
//		}else if (speedKmh <= SPEED_LIMIT) {
//			targetSpeedKmh = speedKmh;
//		} else {
//			throw new IllegalArgumentException();
//		}
//	}

	/* 개선소스 
	 * 1. 매개변수 검증과 일반 처리를 분리
	 * 2. 매개변수 검증 통합
	 * 3. if 조건분기 순서 정리 : 매개변수 검증 상단 배치
	 *  - 검증결과에 따른 빠른실패 처리
	 *  * 메서드 전체를 쉽게 이해하고, 일반처리를 한단계 더 들여쓰지 않을 수 있음
	 *  * 보안 검증 후 일반경로로 구성된 구조에서는 선택적 분석이 가능
	 *  (추가개선 - 5.3 메시지로 원인설명, 불표현식 단순화)
	 * */
	void setTargetSpeedKmh(double speedKmh) {
		if(speedKmh < 0 || speedKmh > SPEED_LIMIT) {
			throw new IllegalArgumentException();			
		}
		targetSpeedKmh = speedKmh;
	}
}
