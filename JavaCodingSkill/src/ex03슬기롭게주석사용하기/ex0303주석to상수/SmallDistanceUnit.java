package ex03슬기롭게주석사용하기.ex0303주석to상수;

/**
 * 3.3 주석을 상수로 대체
 * @author jkoogi
 * 1. 코드로 직접 설명하자
 *  - 주석을 제거하고 상수명으로 대체
 *  - 상수의 장점 : 이름으로 의미를 드러낸다
 *  > 주석이 실제 코드로 변한다.
 *  * 일반적으로 코드만큼 주석에 엄격하지 않다.코드느 바뀌어도 주석은 무시하거나 주석없이 조건을 추가하기도 함
 */
enum SmallDistanceUnit {
	
	CENTIMETER,
	INCH;

	/* 대상소스 */
//	double getConversionRate(SmallDistanceUnit unit) {
//		if(this == unit) {
//			return 1;	// 동등 변환률
//		}
//		
//		if(this == CENTIMETER && unit == INCH) {
//			return 0.393701;// 1센티미터당 인치
//		} else {
//			return 2.54;	// 1인치당 센티미터
//		}
//	}
	
	/* 개선소스 */
	static final double INCHES_IN_CENTIMETERS = 2.54;
	static final double CENTIMETER_IN_INCHES = 1/INCHES_IN_CENTIMETERS;
	static final int IDENTITY = 1;
	
	double getConversionRate(SmallDistanceUnit unit) {
		if(this == unit) {
			return IDENTITY;
		}
		
		if(this == CENTIMETER && unit == INCH) {
			return CENTIMETER_IN_INCHES;
		} else {
			return INCHES_IN_CENTIMETERS;
		}
	}
}
