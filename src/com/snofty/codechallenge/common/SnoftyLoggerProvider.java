package com.snofty.codechallenge.common;

public class SnoftyLoggerProvider {

	private SnoftyLoggerProvider() {

	}

	public static SnoftyLogger getMyLogger(Class<?> klass) {
		return new SnoftySystemLogger(klass);
	}
}
