package com.snofty.codechallenge.main;

import com.snofty.codechallenge.common.CodeChallengerHelper;
import com.snofty.codechallenge.common.SnoftyLogger;
import com.snofty.codechallenge.common.SnoftyLoggerProvider;

import java.io.File;

public class SnoftyMain {

	private static final SnoftyLogger LOGGER = SnoftyLoggerProvider.getMyLogger(SnoftyMain.class);

	public static void main(String[] args) {

			if (args != null && args.length > 0) {
				for (String filePath : args) {
					String absolutePath = "resources" + File.separator + filePath;
					CodeChallengerHelper helper = new CodeChallengerHelper();
					helper.performInstructions(absolutePath);
				}
			}


	}


}
