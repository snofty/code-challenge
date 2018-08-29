package com.snofty.codechallenge.common;

public class SnoftySystemLogger extends SnoftyLogger {

	public SnoftySystemLogger(Class<?> klass) {
		super(klass);
	}

	@Override
	public void info(Object msg) {
		System.out.println(prepareMessage(msg));
	}

	@Override
	public void warn(Object msg, Throwable th) {
		System.err.println(prepareMessage(msg));
		System.err.println(th.getMessage());
	}

	@Override
	public void warn(Object msg) {
		System.err.println(prepareMessage(msg));
	}

	@Override
	public void debug(Object msg) {
		if("debug".equalsIgnoreCase(mode)){
			System.out.println(prepareMessage(msg));
		}
	}

}
