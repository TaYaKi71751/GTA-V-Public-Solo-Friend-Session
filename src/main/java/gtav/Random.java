package gtav;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

public class Random {
	public static String randomString() throws NoSuchAlgorithmException{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair pair = kpg.generateKeyPair();
		PrivateKey privKey = pair.getPrivate();
		String privString = privKey.toString().replaceAll("\r", "").replaceAll("\n", "");
		return privString;
	}
}