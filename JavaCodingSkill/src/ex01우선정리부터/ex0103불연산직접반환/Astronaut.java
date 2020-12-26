package ex01우선정리부터.ex0103불연산직접반환;

/**
 * 1.3 불 표현식을 직접 반환
 * @author jkoogi
 * 1. boolean 반환시 if 조건문으로 적용
 * 2. 조건문 3건 이상인 경우 간소화 검토
 */
public class Astronaut {

	String name;
	int missions;
	
	boolean isValid() {
		/* 대상소스 */
//		if(missions < 0 || name == null || name.trim().isEmpty()) {
//			return false;
//		}else {
//			return true;
//		}

		/* 개선소스 */
		// 1차소스 - boolean 을 반환시 
//		return missions < 0 || name == null || name.trim().isEmpty();

		/* 개선소스 */
		// 2차소스 - 조건문이 세개 이상인 경우 간소화 검토
		boolean isValidMissions = missions >= 0;
		boolean isValidName = name != null && !name.trim().isEmpty();
		return isValidMissions && isValidName;
	}
}
