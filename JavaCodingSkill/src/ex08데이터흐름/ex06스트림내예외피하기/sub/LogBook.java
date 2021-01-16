package ex08데이터흐름.ex06스트림내예외피하기.sub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogBook {
	// 예제코드를 참조하여 throws IOException 적용 
	public LogBook(Path path) throws IOException {
        Files.readAllLines(path);
	}

	static public boolean isLogbook(Path path) {
		// TODO Auto-generated method stub
		return false;
	}
}
