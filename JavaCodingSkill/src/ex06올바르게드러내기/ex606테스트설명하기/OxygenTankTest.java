package ex06올바르게드러내기.ex606테스트설명하기;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;

import ex06올바르게드러내기.ex604합당한허용값사용.sub.OxygenTank;

/**
 * 6.6 테스트 설명하기
 * @author jkoogi
 * 1. 실패하는 테스트 (실수를 확인하기위한 절차)
 *  - <6.1 Given-When-Then으로 테스트 구조화> 적용
 *  - 비교를 통한 테스트 결과 적용
 *  - assertThrows() 어서션을 이용해 Executable의 예외처리 명시적 구성
 *  but. 좋은이름과 설명의 부재.
 *   어떤 테스트가 실패했는지 테스트 이름 확인 : 테스트이름의 효용성
 */
public class OxygenTankTest {

	static final double PERMILLE = 0.001;

	@Test
	/* 대상소스 */
	void testFill() {
		OxygenTank smallTank = OxygenTank.withCapacity(50);
		
		smallTank.fill(22);
		
		Assertions.assertEquals(0.44, smallTank.getStatus(), PERMILLE);
	}
	/* 개선소스  
	 * - 명명규칙에 따라 '클래스명Test'는 테스트 대상 클랙스의 이름을 알수 있음
	 * - 메서드 이름으로 테스트중인 메서드 확인이 가능하지만, 테스트 목적을 알 수 없음(테스트 번호로 구성된 메서드명 지양-자동생성 메서드명)
	 * > 주석으로 표현하는것 보다 JUnit의 기능 활용 지향
	 * */
	@DisplayName("Expect 44% after filling 221 in an empty 501 tank")
	@Disabled("We don't have small tanks anymoreI TODO： Adapt for big tanks")
	void fillTank() {
		OxygenTank smallTank = OxygenTank.withCapacity(50);
		
		smallTank.fill(22);

		Assertions.assertEquals(0.44, smallTank.getStatus(), PERMILLE);
	}
	
	@Test
	/* 대상소스 */
	void testFill2() {
		OxygenTank bigTank = OxygenTank.withCapacity(10_000);
		bigTank.fill(5344.0);
		
		Executable when = () -> bigTank.fill(6000);
		
		Assertions.assertThrows(IllegalArgumentException.class, when);
	}
	/* 개선소스  
	 * - 불필요한 용어 정리 : 메서드명을 test로 시작하지 않음 (상황, 테스트중인 메서드, 검증할 어서션 표시)
	 * > testFill2() > failOverfillTank() : 메서드명 - 공백불가, 글자와 숫자만 허용
	 * (Java7 부터 Character.isJavaIdentifierPart(int)를 넘겨주는 모든 유니코드 char 사용 가능)
	 * @DisplayName : 공백, % , > 같은 기호를 활용하여 간결한 설명 작성
	 * @Disabled : 테스트를 삭제하지 않고 비활성 처리 시 
	 *   1. 추후 테스트 변경 방향 정보 제공
	 *   2. 활성화 시점에 필요한 정보 제공
	 * */
	@DisplayName("Fail if fill level > tank capacity")
	void failOverfillTank() {
		OxygenTank bigTank = OxygenTank.withCapacity(10_000);
		bigTank.fill(5344.0);
		
		Executable when = () -> bigTank.fill(6000);

		Assertions.assertThrows(IllegalArgumentException.class, when);
	}
}
