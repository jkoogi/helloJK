package ex08데이터흐름.ex06스트림내예외피하기;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ex08데이터흐름.ex06스트림내예외피하기.sub.LogBook;


/**
 * 8.6 스트림 내 예외 피하기
 * @author jkoogi
 * - 두곳에서 컴파일 오류가 난다. -> LogBook 생성자에 throws IOException 설정으로 해결
 *  > .filter(LogBook::isLogbook)
 *  > } catch (IOException e) {
 * - 람다 표현식에서는 예외처리 대응이 쉽지 않다.
 *  > 예제 : 자바 NIO API를 사용해 하나의 스트림으로 파일 시스템을 순회
 *  Files.walk() : Path 아래 모든파일, 디렉터리의 Stream<Path> 구성
 * * 파일 처리 시 
 *  - IOException 처리 필요.
 *  - 스트림에 검증된 예외(checked exception) 처리 불가
 *  > 반드시 스티림 내에서 예외처리 : map() 연산 내부에서 IOException catch 후 
 *    RuntimeException 확장된 UncheckedIOException으로 변환 (자연스럽지 않음)
 *  ! 자바의 함수형 방식에는 예외(심지어 검증되지 않은 예외 포함 - unchecked exception)를 처리할 
 *    적절한 방법이 없음 (패러다임 부조화)
 *    # 패러다임 부조화 : 함수는 입력을 처리해 출력을 생성 - 함수는 예외를 던지거나 잡지 않음
 *  
 */
public class LogBooks {
	static List<LogBook> getAll() throws IOException {
		/* 대상소스 */
//		return Files.walk(Paths.get("/var/log"))
//					.filter(Files::isRegularFile)
//					.filter(LogBook::isLogbook)
//					.map(path -> {
//						try {
//							return new LogBook(path);
//						} catch (IOException e) {
//							throw new UncheckedIOException(e);
//						}
//					})
//					.collect(Collectors.toList());
		
		/* 개선소스 
		 * - try-catch 블록을 유지하되 검증예외(checked exception)를  비검증예외(unchecked exception)로 변환하지 않도록 개선
		 *  > 스트림에서 예외 원소를 제거 : flatMap() 사용
		 *  flatMap() : map()과 비슷하지만 A타입을 B타입으로 매핑하는 대신 B타입의 Stream으로 매핑
		 *   - 정상 처리 : Stream.of(element) 원소하나가 포함된 새로운 스트림 반환
		 *   - 오류 처리 : Stream.empty() 반환
		 *   > 정상, 오류 모두 입력에 따른 Stream 출력을 발생함 : 가독성 개선 - 함수형 패러다임에 적절
		 *     (로깅으로 예외처리 하는 것은 부수효과의 위험을 동반하며, 순수한 함수형 패러다임에 위배)
		 *  * 함수형 방식으로 예외를 피할 수 있음
		 * */
		try(Stream<Path> stream = Files.walk(Paths.get("/var/log"))) {
			return stream.filter(Files::isRegularFile)
						.filter(LogBook::isLogbook)
						.flatMap(path -> {
							try {
								return Stream.of(new LogBook(path));
							} catch (IOException e) {
								return Stream.empty();
							}
						})
						.collect(Collectors.toList());
		}
	}

}
