package _00_init.utils;

public class CreateVerificationCode {
	
	public static String getVerificationCode() {
		String allChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String verificationCode = "";
		while (verificationCode.length() != 8) {
			verificationCode += allChars.charAt((int) (Math.random() * allChars.length()));
		}
		return verificationCode;
	}

	
}
