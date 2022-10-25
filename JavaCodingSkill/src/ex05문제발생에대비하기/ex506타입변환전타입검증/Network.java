package ex05문제발생에대비하기.ex506타입변환전타입검증;

import java.io.IOException;
import java.io.ObjectInputStream;

import ex05문제발생에대비하기.ex506타입변환전타입검증.sub.CremMessage;
import ex05문제발생에대비하기.ex506타입변환전타입검증.sub.InterCom;

/**
 * 5.6 타입 변환 전에 항상 타입 검증하기
 * @author jkoogi
 * 1. 런타입으로 동적객체타입 처리
 *  - 동적객체 : 직렬화된 자바객체를 채널로 주고받을 때
 *  - 명시적으로 타입을 변환(casting)하여 사용 : 부적절한 타입 변환시 RuntimeException 같은 예외 충돌 발생
 * 2. InputStream의 readObject() 메서드를 호출해서 메시지를 읽어 기본타입인 Object 변수에 설정
 *  - 사용자가 Object로 설정한 변수를 적절한 비지니스 처리타입(객체)로 변환해야 함.
 *  
 * 
 * *스트림으로 읽은 결과를 signal 지역변수(Object)에 저장 : 타입오류 없음
 *  - 타입으로 변환시 다른 타입(예 ClassifiedMessage)이 들어온 경우 
 *    계획한 타입(CrewMessage)으로 변환 과정에서 ClassCastException 예외 충돌 발생
 *  > 예외로 처리를 구현하지 말고 코드로 구현 : 변환 전 타입검증   
 */
public class Network {

	ObjectInputStream inputStream;
	InterCom interCom;
	
	void listen() throws IOException, ClassNotFoundException {
		while (true) {
			Object signal = inputStream.readObject();

			/* 대상소스 */
//			CremMessage cremMessage = (CremMessage) signal;
//			interCom.broadcast(cremMessage);
			/* 개선소스 
			 * - signal 변수(Object)에 스트림으로 주입된 정보 저장
			 * - instanceof 연산자로 타입검증 후 signal 처리 : 참인 경우만 명시적 변환 (예외충돌 회피)
			 *  > 여러 타입별 처리를 하는경우 차례대로 검증
			 * 1. ClassCastException, ClassNotFoundException 구분
			 *  - ClassCastException : 클래스 변환시 잘못된 변환 예외충돌
			 *  - ClassNotFoundException : ObjectInputStream이 classpath에 없는 객체를 참조할때 예외충돌
			 *  > 타입검증으로 ClassNotFoundException 체크 안됨 - 설계문제 (코드 버그 아님) : 개발중 설계 보완 코드 구현
			 *  
			 *  * 외부와 상호작용시(스크림 사용등) 예상치 못한 입력을 처리하도록 대비.
			 * */
			if(signal instanceof CremMessage) {
				CremMessage cremMessage = (CremMessage) signal;
				interCom.broadcast(cremMessage);
			}
		}
	}
	
}
