package com.tbsm.utils;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CodeGeneratorUtils
{

	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 8;

	public String generateRandomPassword()
	{
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
		String pw = "";
		for (int i=0; i<PASSWORD_LENGTH; i++){
			int index = (int)(RANDOM.nextDouble()*letters.length());
			pw += letters.substring(index, index+1);
		}
		return pw;
	}
	
	public static String uniqueCode(){
		return UUID.randomUUID().toString();
	}
	
	public static void main(String arags[]) {
		CodeGeneratorUtils codeGeneratorUtils = new CodeGeneratorUtils();
		codeGeneratorUtils.generateRandomPassword();
		System.out.println("date"+new Date());
	}
}