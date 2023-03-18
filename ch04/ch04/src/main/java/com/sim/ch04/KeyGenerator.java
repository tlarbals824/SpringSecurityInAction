package com.sim.ch04;

import java.util.Arrays;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class KeyGenerator {

	public static void stringKeyGenerator(){
		StringKeyGenerator keyGenerator = KeyGenerators.string();
		String salt = keyGenerator.generateKey();
		System.out.println("key="+salt);
	}

	public static void bytesKeyGenerator(){
		BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
		byte[] bytes = keyGenerator.generateKey();
		int keyLength = keyGenerator.getKeyLength();

		System.out.println("key="+Arrays.toString(bytes));
		System.out.println("keyLength="+keyLength);
	}

	public static void bytesKeyGeneratorWithShared(int shared){
		BytesKeyGenerator keyGenerator = KeyGenerators.shared(shared);
		byte[] bytes1 = keyGenerator.generateKey();
		byte[] bytes2 = keyGenerator.generateKey();

		System.out.println("bytes1="+Arrays.toString(bytes1)+"  bytes2="+Arrays.toString(bytes2));
		assert bytes1== bytes2;
	}

}
