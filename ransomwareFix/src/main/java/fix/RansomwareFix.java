package main.java.fix;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class RansomwareFix {
	
	public static void main(String[] args) {

		System.out.println("RansomwareFix");
		File baseDir = new File("/");
		System.out.println("5. 재귀용법 필터 표시");
		showFilesInDirRecursive(baseDir.getPath());
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
			}else if (file.getName().endsWith(".7z")) {
				System.out.println("file : " + file);
			}
		}
	}
}