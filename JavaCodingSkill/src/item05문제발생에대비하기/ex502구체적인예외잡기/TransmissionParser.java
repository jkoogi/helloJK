package item05문제발생에대비하기.ex502구체적인예외잡기;

import java.io.IOError;
import java.io.IOException;

import item05문제발생에대비하기.ex502구체적인예외잡기.sub.Transmission;

/**
 * 5.2 항상 가장 구체적인 예외 잡기
 * @author jkoogi
 * 1. Exception 처럼 일반 예외를 처리하도록 구현하면 의도하지 않은 오류의 충돌까지 처리하게 된다.
 *  - 가능한 예외는 구체적인 예외유형에 맞게 처리하고 
 *  - 가능한 코드를 수정하여 버그를 처리할 수 있게 구현해야한다.
 *  * 예외를 잡아서 버그를 숨기는 것은 고친것이 아니다. (언젠가 더 불편한 상황에서 프로그램을 실패시킨다.)
 *  
 */
public class TransmissionParser {

	static Transmission parse(String rawMessage) {
		if(rawMessage != null
				&& rawMessage.length() != Transmission.MESSAGE_LENGTH
				) {
			throw new IllegalArgumentException("Bad message received!");
		}
		
		String rawId = rawMessage.substring(0,Transmission.ID_LENGTH);
		String rawContent = rawMessage.substring(Transmission.ID_LENGTH);
		try {
			int id = Integer.parseInt(rawId);
			String content = rawContent.trim();
			return new Transmission(id, content);

		/* 대상소스 */
//		} catch (Exception e) {

		/* 개선소스 
		 * - rawId에 Integer 형변환 오류 발생시 : parseInt(rawId)  
		 * */
		} catch (NumberFormatException e) {
		// 여러 예외처리 예
//		} catch (NumberFormatException | NullPointerException e) {
			throw new IllegalArgumentException("Bad message received!");
		}
	}
}
