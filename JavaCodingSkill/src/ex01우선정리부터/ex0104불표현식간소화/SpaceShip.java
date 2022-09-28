package ex01우선정리부터.ex0104불표현식간소화;

import ex01우선정리부터.ex0104불표현식간소화.sub.Crew;
import ex01우선정리부터.ex0104불표현식간소화.sub.FuelTank;
import ex01우선정리부터.ex0104불표현식간소화.sub.Hull;
import ex01우선정리부터.ex0104불표현식간소화.sub.Navigator;
import ex01우선정리부터.ex0104불표현식간소화.sub.OxygenTank;

/**
 * 1.4 불 표현식 간소화
 * @author jkoogi
 * 1. 여러 조건문을 하나로 합쳐 확인해야 한다면 다른 식으로 묶는 것
 *  - 주제나 추상화 정도에 따라 그루핑
 *  - 한 메서드 안에서는 추상화 수준이 비슷하도록 명령문을 취합
 *  - 높은 수준의 메서드가 다음으로 낮은 수준의 메서드를 호출
 *  * &&가 항상 ||보다 먼저 평가된다
 */
public class SpaceShip {
	Crew crew;				//승선인원
	FuelTank fuelTank;		//연료통
	Hull hull;				//선체
	Navigator navigator;	//경로
	OxygenTank oxygenTank;	//산소통
	
	boolean willCrewSurvive() {
		/* 대상소스 */ // 낮은 수준의 각 세부 내용을 확인
//		return hull.holes == 0 &&
//				fuelTank.fuel >= navigator.requiredFuelToEarth() &&
//				oxygenTank.lastsFor(crew.size) > navigator.timeToEarth();

		/* 개선소스 */
		// 1차소스 - 주제에 맞게 유사한 특징을 취합한 변수 추가 : 변수명과 메서드명으로 원하는 결과를 표현
		boolean hasEnoughResources = hasEnoughFuel() && hasEnoughOxygen();
		return hull.isIntact() && hasEnoughResources;
	}

	private boolean hasEnoughOxygen() {
		return oxygenTank.lastsFor(crew.size) > navigator.timeToEarth();
	}
	private boolean hasEnoughFuel() {
		return fuelTank.fuel >= navigator.requiredFuelToEarth();
	}
	
}
