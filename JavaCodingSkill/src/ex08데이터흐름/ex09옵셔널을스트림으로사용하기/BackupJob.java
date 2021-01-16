package ex08데이터흐름.ex09옵셔널을스트림으로사용하기;

import java.util.Optional;

/**
 * 8.9 옵셔널을 스트림으로 사용하기
 * @author jkoogi
 * - Optional은 null 대체 가능
 * - 람다 표현식에 Optional 클래스 추가시 장점
 * - Optional은 0개, 1개 원소만 포함하는 특별한 형식의 스트림
 *  > filter(), map() 같은 일반 스트림 연산을 모두 Optional에 적용 가능 : 클린코드 적합
 *  예제 : connectionOptional 변수(존재, 부재)에 값을 저장
 *   1) isPresent()를 호출해 실제 값의 존재 검증
 *   2) get()을 호출해 대상 값을 추출
 *   > 변수명 적절성, 설명여부 검토 필요 : Optional을 이상한 이름의 변수에 저장 - 임피던스 불일치(impedance mismatch)
 *   * Optional 클래스는 함수형 프로그래밍 방식용도로 사용 
 *    - 명령형 방식으로 사용을 위해 get()메서드로 변환하여 사용 : 문맥 교환 코드 필요
 *    > 문맥교환 같은 코드 불필요 : 전체 구조를 함수적으로 구현하자.
 *
 */
public class BackupJob {

	Communicator communicator;
	Storage storage;

	/* 대상소스 */
//	void backupToEarth() {
//		Optional<Connection> connectionOptional = 
//				communicator.getConnectionToEarth();
//		if(!connectionOptional.isPresent()) {
//			throw new IllegalStateException();
//		}
//		
//		Connection connection = connectionOptional.get();
//		if(!connection.isFree()) {
//			throw new IllegalStateException();
//		}
//		connection.send(storage.getBackup());
//	}
	
	/* 개선소스 1
	 * - Optional 클래스의 메서드 활용
	 *  > filter()를 사용하여 사용여부 확인
	 *  > 사용불가인 경우 orElseThrow()를 사용하여 예외 발생
	 * - connectionOptional 변수 제거, orElseThrow() 활용으로 연결이 끊기거나 사용불가일 경우 처리까지 구현
	 *  > 예외발생이 아닌 존재여부만 고려한다면 ifPresent() 사용
	 *  > Optional을 조회만 할 경우, map(조회값).orElse(기본값) 사용
	 * */
//	void backupToEarth() {
//		Connection connection = communicator.getConnectionToEarth()
//				.filter(Connection::isFree)
//				.orElseThrow(IllegalStateException::new);
//		connection.send(storage.getBackup());
//	}

	/* 개선소스 2 */
	void backupToEarth() {
		String state = communicator.getConnectionToEarth()
									.map(Connection::isFree)
									.map(isFree -> isFree ? "free":"busy")
									.orElse("absent");
	}
}

class Communicator {

	public Optional<Connection> getConnectionToEarth() {
		// TODO Auto-generated method stub
		return null;
	}
}

class Storage {

	public Object getBackup() {
		// TODO Auto-generated method stub
		return null;
	}
}

class Connection {

	public boolean isFree() {
		// TODO Auto-generated method stub
		return false;
	}

	public void send(Object backup) {
		// TODO Auto-generated method stub
	}
}