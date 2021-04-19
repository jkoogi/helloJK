package iloveyouboss.ex130;

import static org.junit.Assert.*;

import org.junit.Test;

import iloveyouboss.ex120.ScoreCollection;
import static org.hamcrest.CoreMatchers.*;
public class ScoreCollectionTest {

	@Test
	public void answersArithmeticMeanOfTwoNumbers() {
		//준비(arrange) : 인스턴스 생성 후 구현객체 설정
		ScoreCollection collection = new ScoreCollection();
		collection.add(()->5); // getScore 메서드가 반환할 겂을 설정할수 있게 하는 구현을 적용할 수 있지만, 별도의 불필요한 코드가 필요하여 
		collection.add(()->7); // 단순한 람다표현식으로 Scoreable 인스턴스에서 값을 반환하도록 지정 [()->7]
		
		//실행(act)
		int actualResult = collection.arithmeticMean();
		
		//단언(assert)
		assertThat(actualResult, equalTo(6));   //실제 결과외 matcher 객체를 인자로 반환 - 기대값 6과 비교
		
		// equalTo 매처를 사용하려면 org.hamcrest.CoreMatchers 클래스를 정적 임포트하세요
	}

}
/*iloveyouboss_04/test/iloveyouboss/ScoreCollectionTest.java*/