package com.sim.ch11ex1s1.common.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

	private GenerateCodeUtil() {
	}

	public static String generateCode(){
		final String code;
		try{
			SecureRandom secureRandom = SecureRandom.getInstanceStrong();

			 code = String.valueOf(secureRandom.nextInt(9000) + 1000);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Problem when generating the random code.");
		}

		return code;
	}
}
