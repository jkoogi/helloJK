package ex03슬기롭게주석사용하기.ex0304주석to유틸리티메서드;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.4 주석을 유틸리티 메서드로 대체
 * @author jkoogi
 *
 */
public class FuelSystem {

	List<Double> tanks = new ArrayList<>();
	
	int getAverageTankFillingPercent() {
		double sum = 0;
		for(double tankFilling : tanks) {
			sum += tankFilling;
		}
		double averageFuel = sum / tanks.size();
		/* 대상소스 */
		//정수 백분율로 반올림
//		return Math.toIntExact(Math.round(averageFuel)*100);
		
		/* 개선소스 */
		// 1차 - 변수명을 이용한 코드의 명확성을 확보했지만, 바로 변환되는 불필요한 변수의 역할
//		int roundedToPercent = Math.toIntExact(Math.round(averageFuel*100));
//		return roundedToPercent;
		
		// 2차 - 유틸리티 메소드 적용
		// 1. 변수명과 같이 메소드명으로 코드의 명확성 확보
		// 2. 별도의 줄바꿈으로 영역을 구분하지 않을 수 있음  (메서드 구분으로 관리코드가 분산되어 이해도 증가)
		// 3. 메서드 재사용
		// 4. 메서드 계증구조로 이해도 개선 (getAverageTankFillingPercent 가 roundToIntegerPercent 호출)
		// 5. 각 메서드는 비슷한 추상화 정도를 갖는, 명명된 명령문의 나열 구조를 유도하여 
		//    (평균을 계산하는 코드도 새로운 유틸리티 메서드로 추출 가능) 
		//    - 코드를 더 모듈화 하고 추상화 수준도 균형을 이룰 수 있음
		return roundToIntegerPercent(averageFuel);
	}

	private int roundToIntegerPercent(double averageFuel) {
		return Math.toIntExact(Math.round(averageFuel*100));
	}
}
