package main.java.fix;

import java.io.File;
import java.io.FilenameFilter;

public class RansomwareFix {

	private static final String baseDirPath = "D:\\dev\\_log\\_ransomewareTest\\WD";
	private static final String rescueDirPath = "D:\\dev\\_log\\_ransomewareTest\\rescue\\restore1\\share\\CACHEDEV1_DATA";
	private static final String README_FILE = "!!!READ_ME.txt";
	private static final String EXTENTION = ".7z";
	
	public static void main(String[] args) {
		
		System.out.println("RansomwareFix");
		File baseDir = new File(baseDirPath);
		showFilesInDirRecursive(baseDir.getPath());
	}

	// 디렉토리 탐색
	private static void showFilesInDirRecursive(String path) {
		
		File dir = new File(path);
		File filse[] = dir.listFiles();
		
		if(filse == null) return;
		for (File file : filse) {
			if(file.isDirectory()) {
				showFilesInDirRecursive(file.getPath());
				isReadmeDelete(file.getPath());
			}else if (file.getName().endsWith(".7z")) {
				System.out.println("file : " + file);
				File rescueFile = getRescueFile(file); 
				if(rescueFile != null && rescueFile.exists()) {
					moveRescuFile(file, rescueFile);
				}
			}
		}
	}


	// 복구파일 이동
	private static void moveRescuFile(File file, File rescueFile) {
		
		File newFile = new File(file.getParent()+"/"+rescueFile.getName());
		boolean success = rescueFile.renameTo(newFile);
		if (success) {
		    System.out.println("move newFile : "+newFile);
		    file.delete();
		}
	}

	// 복구파일 조회
	private static File getRescueFile(File file) {
		
		String fileName = file.getName().substring(0,file.getName().length()-EXTENTION.length());
		String parantPath = file.getParentFile().toString();
		String rescuePath = parantPath.replace(baseDirPath, rescueDirPath);
		
		File rescueFile = new File(rescuePath+"/"+fileName);
		return rescueFile;
	}

	// 디렉토리에 7z파일이 없으면 ReadMe 파일 삭제 
	private static void isReadmeDelete(String path) {
		
		File checkDir = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".7z");
			}
		};
		
		File files[] = checkDir.listFiles(filter);
		File readmeFile = new File(path+"/"+README_FILE);
		if(files.length==0 && readmeFile.isFile()) {
			System.out.println(">> delete ReadMe : "+readmeFile);
			readmeFile.delete();
		}
	}
}