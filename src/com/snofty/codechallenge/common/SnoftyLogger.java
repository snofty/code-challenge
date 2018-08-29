package com.snofty.codechallenge.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Properties;

public abstract class SnoftyLogger {

	private Class<?> klass;
	private static final String LOG_FORMAT = "[{time}:{className}]{msg}";
	private String log = LOG_FORMAT;
	protected String mode;

	public SnoftyLogger(Class<?> klass) {
		this.klass = klass;
		Properties properties = new Properties();
		try {
			properties.load(Files.newInputStream(Paths.get("resources" + File.separator + "configuration.properties"),
					StandardOpenOption.READ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mode = properties.getProperty("codechallenge.log.mode");
	}

	public abstract void info(Object msg);

	public abstract void debug(Object msg);

	public abstract void warn(Object msg, Throwable th);

	public abstract void warn(Object msg);

	protected String prepareMessage(Object msg) {
		return log.replace("{time}", Calendar.getInstance().getTime().toString())
				.replace("{className}", klass.getSimpleName()).replace("{msg}", msg != null ? msg.toString() : null);
	}
}
