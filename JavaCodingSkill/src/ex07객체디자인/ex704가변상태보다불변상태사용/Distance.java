package ex07객체디자인.ex704가변상태보다불변상태사용;

import ex07객체디자인.ex704가변상태보다불변상태사용.sub.DistanceUnit;

/**
 * 7.4 가변 상태보다 불변 상태 사용하기
 * @author jkoogi
 * . 객체의 기본은 불변이다.(가변은 잘못 사용될 위험이 있다)
 * - 비행계획을 세우는데 필요한 거리를 계산하여 반환하는 예제
 * > Distance 클래스 오용의 여지.
 */
/* 대상소스 */
//public class Distance {
//	static DistanceUnit unit;
//	double value;
//	
//	public Distance(DistanceUnit unit, double value) {
//		this.unit = unit;
//		this.value = value;
//	}
//
//	void add(Distance distance) {
//		distance.converTo(unit);
//		value += distance.value;
//	}
//
//	void converTo(DistanceUnit otherUnit) {
//		double conversionRate = unit.getConversionRate(otherUnit);
//		unit = otherUnit;
//		value = conversionRate * value;
//	}
//
//	/* 대상소스 실행코드
//	 * - 지구~화성 : toMars 
//	 * - 화성~금성 : marsToVenus
//	 * - 지구~화성~금성 : toVenusViaMars (= toMars ? 객체가 같다.)
//	 *  > toVenusViaMars.add(marsToVenus) 호출시 toMars의 값도 영향을 받아 함께 변경
//	 *  이후 toMars를 사용할 경우 변경된 상태가 적용됨 (의도하지 않는 동작인 경우 오류)
//	 * */
//	Distance toMars = new Distance(DistanceUnit.KILOMETERS, 56_000_000);
//	Distance marsToVenus = new Distance(DistanceUnit.LIGHTYEARS, 0.000012656528);
//	Distance toVenusViaMars = toMars;
//	toVenusViaMars.add(marsToVenus);
//}

/* 개선소스 
 * - 가변성을 제한하면 의도하지 않은 변경을 방지할 수 있음
 * 1. final 키워드 사용
 *  - 생성된 이후에는 변경 불가
 * 2. 거리를 계산하려면 매번 새로운 인스턴스로 생성
 * * 단점 : 객체를 더 많이 생성한다.(작은객체의 비용은 적다)
 * [값 객체-Value Object] - 백분율, 돈, 통화, 시간, 날짜, 좌표, 거리 등
 *   - 값이 서로 같으면 구분이 어려움.
 *   > 불변으로 처리해야 의도하지 않은 사용을 방지함.
 * . final 클래스 정의 시 더이상 확장 불가  - 언제든 가변상태를 다시 추가 할 수 있음
 * */
final public class Distance {
	final DistanceUnit unit;
	final double value;

	public Distance(DistanceUnit unit, double value) {
		this.unit = unit;
		this.value = value;
	}
	
	Distance add(Distance distance) {
		return new Distance(unit, value+distance.convertTo(unit).value);
	}
	Distance convertTo(DistanceUnit otherUnit) {
		double conversionRate = unit.getConversionRate(otherUnit);
		return new Distance(otherUnit, conversionRate*value);
	}
	/* 개선소스 실행코드 */
//	Distance toMars = new Distance(DistanceUnit.KILOMETERS, 56_000_000);
//	Distance marsToVenus = new Distance(DistanceUnit.LIGHTYEARS, 0.000012656528);
//	Distance toVenusViaMars = toMars.add(marsToVenus).convertTo(DistanceUnit.MILES);
}
