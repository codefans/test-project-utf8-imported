package com.codefans.practicetask.scannerdisk;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileIterator implements Iterator<File>, Iterable<File> {

	private Iterator<String> iterator;
	private List<String> paths;
	private List<File> dirs;

	public FileIterator(List<String> paths) {
		this.paths = new ArrayList<String>(paths);
		this.dirs = new ArrayList<File>();
		this.iterator = this.paths.iterator();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileIterator iter = new FileIterator(Arrays.asList("D:\\360云盘"));
		for (File file : iter) {
			System.out.println(file);
		}
	}

	@Override
	public Iterator<File> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (!iterator.hasNext()) {
			paths.clear();
			for (File dir : dirs) {
				for (String name : dir.list()) {
					paths.add(dir.getPath() + File.separator + name);
				}
			}
			dirs.clear();

			if (paths.size() == 0) {
				return false;
			}
			iterator = paths.iterator();
		}
		return true;
	}

	@Override
	public File next() {
		File file = null;
		if (hasNext()) {
			file = new File(iterator.next());
			if (file.isDirectory()) {
				dirs.add(file);
			}
		}
		return file;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
