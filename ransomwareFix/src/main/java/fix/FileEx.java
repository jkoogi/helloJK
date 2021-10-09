package main.java.fix;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FileEx {

	public static void main(String[] args) {
		System.out.println("FileEx");
		File baseDir = new File("/");
		
//		System.out.println("1. 1단계 디렉토리 표시");
//		showFirstDir(baseDir);
		
//		System.out.println("2. 재귀디랙토리 표시");
//		showFilesInDir(baseDir.getPath());
		
//		System.out.println("3. 파일명 필터된 목록 표시");
//		showFirstFilter(baseDir);
		
//		System.out.println("4. 파일로 필터된 목록 표시");
//		showFileFilter(baseDir);
		
//		System.out.println("5. 재귀용법 필터 표시");
//		showFilesInDirRecursive(baseDir.getPath());
		
//		System.out.println("6. 경로없이 파일명만 표시");
//		showOnlyFilename(baseDir);
		
//		System.out.println("7. 필터적용 파일명만 표시");
//		showFilterFilename(baseDir);
		
	}
	
	/**
	 * 1. 1단계 디렉토리 표시
	 * @param rootDir
	 */
	@SuppressWarnings("unused")
	private static void showFirstDir(File rootDir) {
		File rootFiles[] = rootDir.listFiles();
		for (File file : rootFiles) {
			System.out.println("file : " + file);
		}
	}

	/**
	 * 2. 재귀디랙토리 표시
	 * @param dirPath
	 */
	@SuppressWarnings("unused")
	private static void showFilesInDir(String dirPath) {
		System.out.println("dirPath : " + dirPath);
		File dir = new File(dirPath);
		File dirFiles[] = dir.listFiles();
		
		if(dirFiles == null) return;
		for (File file : dirFiles) {
			if(file.isDirectory()) {
				showFilesInDir(file.getPath());
			} else if(file != null){
				System.out.println("file : " + file);
			}
		}
	}

	/**
	 * 3. 파일명 필터된 목록 표시
	 * @param rootDir
	 */
	@SuppressWarnings("unused")
	private static void showFirstFilter(File rootDir) {
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("jkoogi");
			}
		};
		
		File files[] = rootDir.listFiles(filter);
		for (File file : files) {
			System.out.println("file : " + file);
		}
	}

	/**
	 * 4. 파일로 필터된 목록 표시
	 * @param rootDir
	 */
	@SuppressWarnings("unused")
	private static void showFileFilter(File rootDir) {
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith("up");
			}
		};
		File files[] = rootDir.listFiles(filter);
		for (File file : files) {
			System.out.println("file : " + file);
		}
	}

	/**
	 * 5. 재귀용법 필터 표시
	 * @param path
	 */
	@SuppressWarnings("unused")
	private static void showFilesInDirRecursive(String path) {
		File dir = new File(path);
		File filse[] = dir.listFiles();

		if(filse == null) return;
		for (File file : filse) {
			if(file.isDirectory()) {
				showFilesInDirRecursive(file.getPath());
			}else if (file.getName().endsWith(".txt")) {
				System.out.println("file : " + file);
			}
		}
	}

	/**
	 * 6. 경로없이 파일명만 표시
	 * @param baseDir
	 */
	@SuppressWarnings("unused")
	private static void showOnlyFilename(File baseDir) {
		String filename[] = baseDir.list();
		for (String fileName : filename) {
			System.out.println("file : "+fileName);
		}
	}

	/**
	 * 7. 필터적용 파일명만 표시
	 * @param baseDir
	 */
	@SuppressWarnings("unused")
	private static void showFilterFilename(File baseDir) {
		String filenames[] = baseDir.list((f,name) -> name.endsWith("up"));
		for (String fileName : filenames) {
			System.out.println("file : "+fileName);
		}
	}
	
//	출처 : https://codechacha.com/ko/java-list-files/
}
