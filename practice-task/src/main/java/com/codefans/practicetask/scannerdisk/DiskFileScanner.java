package com.codefans.practicetask.scannerdisk;

import com.codefans.reusablecode.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiskFileScanner {

	private int levelOne;
	private int levelTwo;
	private int levelThree;

	private int totalCount;

	private List<String> foldersInLevelOne = new ArrayList<String>();
	private List<String> foldersInLevelTwo = new ArrayList<String>();
	private List<String> foldersInLevelThree = new ArrayList<String>();

	private IOUtils pathUtil;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		DiskFileScanner diskFileScanner = new DiskFileScanner();
		diskFileScanner.scannerDiskFile();
	}

	public void scannerDiskFile() throws IOException {
		// String dir = "D:\\software";
		// String dir = "D:\\360云盘";
		String dir = "C:\\";
		// FileType fileType = FileType.App;
		FileType fileType = FileType.Video;
		levelOne = 3;
		levelTwo = 2;
		levelThree = 1;

		// level range:3,2,1 --> total cost:10s
		// level range:3,2,2 --> total cost:17s

		pathUtil = new IOUtils("D:/paths.txt");
		long begin = System.currentTimeMillis();

		// =====================scanner level one==============================
		long start = begin;
		this.scannerInLevels(dir, fileType, foldersInLevelOne, levelOne, -1);
		long end = System.currentTimeMillis();
		// pathUtil.append("path:[" + dir + "] scan over, find folders in this
		// level total count:[" + foldersInLevelOne.size() + "], total cost:[" +
		// (end - start) / 1000 + "s]");
		System.out.println("path:[" + dir + "] scan over, find folders in this level total count:["
				+ foldersInLevelOne.size() + "], total cost:[" + (end - start) / 1000 + "s]");

		IOUtils folderUtil = new IOUtils("D:/folders.txt");
		folderUtil.append(foldersInLevelOne);

		// =====================scanner level two==============================
		start = System.currentTimeMillis();
		for (int i = 0; i < foldersInLevelOne.size(); i++) {
			dir = foldersInLevelOne.get(i);
			this.scannerInLevels(dir, fileType, foldersInLevelTwo, levelTwo, -1);
		}
		end = System.currentTimeMillis();
		System.out.println("second scan over, find folders in this level total count:[" + foldersInLevelTwo.size()
				+ "], total cost:[" + (end - start) / 1000 + "s]");
		folderUtil.append(foldersInLevelTwo);

		// =====================scanner level
		// three==============================
		start = System.currentTimeMillis();
		for (int i = 0; i < foldersInLevelTwo.size(); i++) {
			dir = foldersInLevelTwo.get(i);
			this.scannerInLevels(dir, fileType, foldersInLevelThree, levelThree, -1);
		}
		end = System.currentTimeMillis();
		System.out.println("third directory scan over, find folders in this level total count:["
				+ foldersInLevelThree.size() + "], total cost:[" + (end - start) / 1000 + "s]");
		folderUtil.append(foldersInLevelThree);

		// =====================scanner rest==============================
		start = System.currentTimeMillis();
		for (int i = 0; i < foldersInLevelThree.size(); i++) {
			dir = foldersInLevelThree.get(i);
			this.scannerAll(dir, fileType);
		}
		end = System.currentTimeMillis();
		System.out.println("rest scan over, total cost:[" + (end - start) / 1000 + "s]");

		pathUtil.close();
		folderUtil.close();

		end = System.currentTimeMillis();

		System.out.println("found total count:[" + totalCount + "], total cost:[" + (end - begin) / 1000 + "s]");

	}

	public void scannerInLevels(String dir, FileType fileType, List<String> foldersInThisLevel, int levelCount,
                                int level) throws IOException {
		// System.out.println("level:[" + level + "]");
		File path = new File(dir);
		if (path.isDirectory()) {
			// if(level == levelCount) {
			// foldersInThisLevel.add(dir);
			// return;
			// }
			level = level + 1;

			File[] files = path.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.isDirectory()) {
						if (level == levelCount) {
							if (!foldersInThisLevel.contains(dir)) {
								foldersInThisLevel.add(dir);
							}
							continue;
						} else {
							scannerInLevels(dir + File.separator + file.getName(), fileType, foldersInThisLevel,
									levelCount, level);
						}
					} else {
						// System.out.println(file.getAbsolutePath());
						if (FileFilter.filter(file.getAbsolutePath(), fileType)) {
							totalCount++;
							// System.out.println(file.getAbsolutePath());
							pathUtil.append(file.getAbsolutePath());
						}
					}
				}
			}
		}
	}

	public void scannerAll(String dir, FileType fileType) throws IOException {
		File path = new File(dir);
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.isDirectory()) {
						scannerAll(dir + File.separator + file.getName(), fileType);
					} else {
						// System.out.println(file.getAbsolutePath());
						if (FileFilter.filter(file.getAbsolutePath(), fileType)) {
							totalCount++;
							// System.out.println(file.getAbsolutePath());
							pathUtil.append(file.getAbsolutePath());
						}
					}
				}
			}
		}
	}

}
