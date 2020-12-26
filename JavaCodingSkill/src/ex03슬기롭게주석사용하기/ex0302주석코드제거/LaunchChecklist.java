package ex03슬기롭게주석사용하기.ex0302주석코드제거;

import java.util.Arrays;
import java.util.List;

import ex02코드스타일레벨업.ex0203For대신ForEach.sub.Commander;
import ex02코드스타일레벨업.ex0203For대신ForEach.sub.Status;

/**
 * 3.2 주석 처리된 코드 제거
 * @author jkoogi <br/>
 * 
 * 1. 주석처리된 코드영역 삭제
 *  - 과한내용의 주석
 *  - 선언하지 않은 변수가 명시된 주석코드
 *  > 혼란만 가중시키는 쓰레기
 *  * 일반적인 코드주석은 특정기능이 동작하지 않도록 하거나, 재사용을 위해 적용
 */
public class LaunchChecklist {

	/* 대상소스 */
//	List<String> checks = Arrays.asList(
//			"Cabin Leak",
//			//"Communication",	//휴스턴과 정말 통신하고 싶은가? [1]
//			"Engine",
//			"Hull",
//			//"Rover",	// 내 생각에는 필요 없는데...
//			"OxygenTank"
//			//"Supplies"
//	);
//	
//	Status prepareLaunch(Commander commander) {
//		for (String check : checks) {
//			boolean shouldAbortTakeoff = commander.isFailing(check);
//			if(shouldAbortTakeoff) {
//				//System.out.println("REASON FOR ABORT:"+item);
//				return Status.ABORT_TAKE_OFF;
//			}
//		}
//		return Status.READY_FOR_TAKE_OFF;
//	}

	/* 개선소스 */
	List<String> checks = Arrays.asList(
			"Cabin Leak",
			"Engine",
			"Hull",
			"OxygenTank"
	);
	
	Status prepareLaunch(Commander commander) {
		for (String check : checks) {
			boolean shouldAbortTakeoff = commander.isFailing(check);
			if(shouldAbortTakeoff) {
				return Status.ABORT_TAKE_OFF;
			}
		}
		return Status.READY_FOR_TAKE_OFF;
	}	
}
