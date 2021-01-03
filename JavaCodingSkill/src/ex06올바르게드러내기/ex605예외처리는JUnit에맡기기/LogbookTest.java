package ex06올바르게드러내기.ex605예외처리는JUnit에맡기기;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import ex06올바르게드러내기.ex605예외처리는JUnit에맡기기.sub.Logbook;


/**
 * 6.5 예외 처리는 JUnit에 맡기기
 * @author jkoogi
 *  - 서로 다른방식으로 예외를 사용하는 두가지 테스트 비교
 *  1st : IOException > 실패 - 실패한 테스트를 잘 추적할 수 있도록 예외 메시지 표시
 *   - 원인사슬이 깨짐 (메시지만 제공하고 끝 - 전체 예외를 스택으로 추적 하지 않음)
 *  2nd : IOException > 성공 - fail() 어서션으로 테스트 실패 처리, 성공시 catch로 넘겨 별도 처리 않음
 *   - 예외가 발생할 목적으로 구현했으나 목적을 알수 없음
 *
 */
public class LogbookTest {
	@Test
	/* 대상소스 */
//	void readLogbook() {
//		Logbook logbook = new Logbook();
//		try {
//			List<String> entries = logbook.readAllEntries();
//			Assertions.assertEquals(13, entries.size());
//		} catch (IOException e) {
//			Assertions.fail(e.getMessage());
//		}
//	}
	/* 개선소스 
	 * - JUnit 테스트는 어떤예외도 발생하지 않는다. : 암묵적 어서션 동작 (예외 발생 시 JUnit이 실패에 대한 전체 스택을 표시)
	 * > try-catch블록과 fail 호출 제외 : 코드 간소화, 가독성 증가, 원시사슬 유지
	 * */
	void readLogbook() throws IOException  {
		Logbook logbook = new Logbook();
		
		List<String> entries = logbook.readAllEntries();
		
		Assertions.assertEquals(13, entries.size());
	}

	@Test
	/* 대상소스 */
//	void readLogbookFail() {
//		Logbook logbook = new Logbook();
//		
//		try {
//			logbook.readAllEntries();
//			Assertions.fail("read shold fail");
//		} catch (IOException e) {}
//	}
	/* 개선소스 
	 *  - assertThrows() : 테스트에서 발생할 예외를 면시적으로 처리
	 *  > try-catch블록과 fail 호출 제외 : 예외 발생시 정의한 예외를 assertion으로 전달 (JUnit5의 Excutable 형태의 람다식 처리한 결과)
	 *  (8.1 익명클래스 대신 람다 사용하기 - 익명클래스(new Executable(){...})를 생성하는 방법에서 개선)
	 *  * 
	 * */
	void readLogbookFail() {
		Logbook logbook = new Logbook();
		
		Executable when = () -> logbook.readAllEntries();
		
		Assertions.assertThrows(IOException.class, when);
	}
}
