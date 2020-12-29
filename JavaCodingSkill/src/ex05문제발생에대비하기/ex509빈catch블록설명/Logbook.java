package ex05문제발생에대비하기.ex509빈catch블록설명;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 5.9 빈 catch 블록 설명하기
 * @author jkoogi
 * 1. 예외는 의미있게 처리할 수 있을때 잡자 : catch 블록에는 예외처리 코드로 구현
 *  - 다른코드로 재시도
 *  - 로깅
 *  - 예외 넘기기
 *  * 빈 상태로 catch문을 방치하지 말것.(분석에너지의 낭비)
 */
public class Logbook {
	static final Path LOG_FOLDER = Paths.get("/var/log");
	static final String FILE_FILTER = "*.log";
	
	List<Path> getLogs() throws IOException{
		List<Path> result = new ArrayList<>();
		
		try (DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER)){
			for (Path logFile : directoryStream) {
				result.add(logFile);
			} 
		/* 대상소스 */
//		}catch (NotDirectoryException e) {

		/* 개선소스 
		 * - 변수명을 ignored로 명시적 의도 표시
		 * > 변수명을 이해해 IDE도 경고를 표시하지 않음
		 * - 예외 무시에 대한 사유를 주석으로 표현 (3.5 구현결정 설명하기)
		 * */
		}catch (NotDirectoryException ignored) {
			// 디렉터리가 없으면 -> 로그도 없다! /* 빈 catch 방지 로그 :  CONDITION -> EFFECT 템플릿 형식*/
		}
		return result;
	}
}
