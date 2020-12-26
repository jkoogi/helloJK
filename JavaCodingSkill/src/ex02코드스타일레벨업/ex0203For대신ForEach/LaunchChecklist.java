package ex02코드스타일레벨업.ex0203For대신ForEach;

import java.util.Arrays;
import java.util.List;

import ex02코드스타일레벨업.ex0203For대신ForEach.sub.Commander;
import ex02코드스타일레벨업.ex0203For대신ForEach.sub.Status;

/**
 * 2.3 For 루프 대신 For—Each
 * @author jkoogi
 * 1.인덱싱을 사용하지 않는 문법 적용 : 배열, set 등 인덱싱되지 않는 컬렉션도 가능
 */
public class LaunchChecklist {

	List<String> checks = Arrays.asList( "Cabin Pressure"
										,"Communication"
										,"Engine"
										);
	/* 대상소스 */
//	Status prepareForTakeoff(Commander commander) {
//		// for : set, map 을 제외한 인덱싱(i)된 컬렉션 순회
//	    // - 거의 사용하지 않음(컬렉션의 특정부분 순회, 명시적인 다른 목적이 필요한 경우 사용)
//		for (int i = 0; i < checks.size(); i++) {
//			// i=i+1
//			//  - 리스트의 다음원소 접근 외 사용되지 않는 경우 의미없는 사용
//			//  - 덮어쓸수 있는 위험 
//			//  - 인덱싱 종료시점 부호에 집중(<, <=) : 주의(IndexOutOfBoundsExceptions)
//			boolean shuldAbortTakeoff = commander.isFailing(checks.get(i));
//			if(shuldAbortTakeoff) {
//				return Status.ABORT_TAKE_OFF;
//			}
//		}
//		return Status.READY_FOR_TAKE_OFF;
//	}
	
	/* 개선소스 */
	Status prepareForTakeoff(Commander commander) {
		// 1.인덱싱을 사용하지 않는 문법 적용 : 배열, set 등 인덱싱되지 않는 컬렉션도 가능
		for (String check : checks) {
			boolean shuldAbortTakeoff = commander.isFailing(check);
			if(shuldAbortTakeoff) {
				return Status.ABORT_TAKE_OFF;
			}
		}
		return Status.READY_FOR_TAKE_OFF;
	}
}
