package com.sim.ch04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch04Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch04Application.class, args);
		KeyGenerator.stringKeyGenerator();
		KeyGenerator.bytesKeyGenerator();
		KeyGenerator.bytesKeyGeneratorWithShared(16);

		Encryptor.bytesEncryptor();
		Encryptor.bytesEncryptorStronger();
		Encryptor.textEncryptor();
		Encryptor.textEncryptorDummy();
	}

}
