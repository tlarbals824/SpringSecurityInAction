package com.sim.ch04;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Encryptor {

	public static void bytesEncryptor(){
		String salt = KeyGenerators.string().generateKey();
		String password = "password";
		String valueToEncrypt = "valueToEncrypt";

		BytesEncryptor e = Encryptors.standard(password, salt);
		byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
		byte[] decrypted = e.decrypt(encrypted);
		System.out.println("BytesEncryptor encrypted="+new String(encrypted));
		System.out.println("BytesEncryptor decrypted="+new String(decrypted));
	}

	public static void bytesEncryptorStronger(){
		String salt = KeyGenerators.string().generateKey();
		String password = "password";
		String valueToEncrypt = "valueToEncrypt";

		BytesEncryptor e = Encryptors.stronger(password, salt);
		byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
		byte[] decrypted = e.decrypt(encrypted);
		System.out.println("BytesEncryptorStronger encrypted="+new String(encrypted));
		System.out.println("BytesEncryptorStronger decrypted="+new String(decrypted));
	}

	public static void textEncryptor(){
		String salt = KeyGenerators.string().generateKey();
		String password = "password";
		String valueToEncrypt = "valueToEncrypt";

		TextEncryptor e = Encryptors.text(password, salt);
		// 매 출력마다 값이 다름
		String encrypted = e.encrypt(valueToEncrypt);
		String decrypted = e.decrypt(encrypted);
		System.out.println("TextEncryptor encrypted="+encrypted);
		System.out.println("TextEncryptor decrypted="+decrypted);
	}

	public static void textEncryptorDummy(){
		String salt = KeyGenerators.string().generateKey();
		String password = "password";
		String valueToEncrypt = "valueToEncrypt";

		TextEncryptor e = Encryptors.text(password, salt);
		String encrypted = e.encrypt(valueToEncrypt);
		String decrypted = e.decrypt(encrypted);
		System.out.println("TextEncryptorDummy encrypted="+encrypted);
		System.out.println("TextEncryptorDummy decrypted="+decrypted);
	}


	// Encryptors.queryableText()는 deprecated 되었다.
	public static void textEncryptorQueryableText(){
		// String salt = KeyGenerators.string().generateKey();
		// String password = "password";
		// String valueToEncrypt = "valueToEncrypt";
		//
		// TextEncryptor e = Encryptors.queryableText(password, salt);
		// // 매 출력마다 값이 다름
		// String encrypted = e.encrypt(valueToEncrypt);
		// String decrypted = e.decrypt(encrypted);
		// System.out.println("TextEncryptor encrypted="+encrypted);
		// System.out.println("TextEncryptor decrypted="+decrypted);
	}


}
