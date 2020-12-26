package ex02코드스타일레벨업.ex0206새줄그루핑;

/**
 * 2.6 새 줄로 그루핑
 * @author jkoogi
 * 1. 1차 줄구분 : 클래스 변수간 줄구분, 메소드 내 if문 간 줄구분
 * 2. 2차 줄구분 : 연관된 코드와 개념은 함께 그루핑, 서로 다른 그룹은 분리
 */
enum DistanceUnit {
	
	MILES, KILOMETERS;

	/* 대상소스 */
//	static final double MILE_IN_KILOMETERS = 1.60934;
//	static final int IDENTITY = 1;
//	static final double KILOMETER_IN_MILES = 1 / MILE_IN_KILOMETERS;
//	
//	// 마일과 킬로미터 간 변환률을 반환
//	double getConversionRate(DistanceUnit unit) {
//		if(this == unit) {
//			return IDENTITY;
//		}
//		if(this == MILES && unit == KILOMETERS) {
//			return MILE_IN_KILOMETERS;
//		}else {
//			return KILOMETER_IN_MILES;
//		}
//	}
	
	/* 개선소스 */
	static final int IDENTITY = 1;	//특정단위와 독립된, 추상 정보 : 줄구분
	
	static final double MILE_IN_KILOMETERS = 1.60934;
	static final double KILOMETER_IN_MILES = 1 / MILE_IN_KILOMETERS;
	
	// 마일과 킬로미터 간 변환률을 반환
	double getConversionRate(DistanceUnit unit) {
		if(this == unit) {
			return IDENTITY;
		}// if 블록 줄구분
		
		if(this == MILES && unit == KILOMETERS) {
			return MILE_IN_KILOMETERS;
		}else {
			return KILOMETER_IN_MILES;
		}
	}

}
