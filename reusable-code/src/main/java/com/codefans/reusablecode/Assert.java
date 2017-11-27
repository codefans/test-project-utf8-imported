package com.codefans.reusablecode;

import java.io.File;
import java.io.FileNotFoundException;

public class Assert {

	public static void assertNotNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void assertNotNull(Object object) {
		assertNotNull(object, null);
	}

	public static void assertTrue(boolean condition) {
		assertTrue(condition, null);
	}

	public static void assertTrue(boolean condition, String message) {
		if (!condition) {
			throw new IllegalStateException(message);
		}
	}

	public static void assertFalse(boolean condition) {
		assertFalse(condition, null);
	}

	public static void assertFalse(boolean condition, String message) {
		assertTrue(!condition, message);
	}

	public static void assertFileExists(File file) throws FileNotFoundException {
		assertFileExists(file, null);
	}

	public static void assertFileExists(File file, String message) throws FileNotFoundException {
		if (file == null) {
			throw new IllegalArgumentException(message);
		}
		if (!file.exists()) {
			throw new FileNotFoundException(message);
		}
	}

	public static void main(String[] args) {
		// Assert.assertNotNull(null);
		Assert.assertFalse(true);
	}
}
