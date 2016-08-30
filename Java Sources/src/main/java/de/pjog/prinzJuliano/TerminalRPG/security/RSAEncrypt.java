package de.pjog.prinzJuliano.TerminalRPG.security;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import org.apache.commons.ssl.PKCS8Key;
import org.bouncycastle.operator.OperatorCreationException;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAEncrypt {

	private PrivateKey priv;
	private PublicKey pub;
	private String message;
	
	public RSAEncrypt(){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	public RSAEncrypt(String message) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		try {
			SecureRandom random = new SecureRandom();

			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

			generator.initialize(2048, random);
			KeyPair pair = generator.generateKeyPair();
			pub = pair.getPublic();
			priv = pair.getPrivate();
			this.message = message;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public byte[] encrypt(PublicKey publicKey, String message) {
		try {
			byte[] input = new String(message).getBytes("UTF-8");

			Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");

			SecureRandom random = new SecureRandom();

			cipher.init(Cipher.ENCRYPT_MODE, publicKey, random);

			byte[] cipherText = cipher.doFinal(input);
			return cipherText;
		} catch (Exception e) {
			return null;
		}

	}

	public String decrypt(PrivateKey privateKey, byte[] cipherText) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");

			SecureRandom random = new SecureRandom();

			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

			generator.initialize(2048, random);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] plainText = cipher.doFinal(cipherText);

			Key priv = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey.getEncoded()));

			cipher.init(Cipher.DECRYPT_MODE, priv);
			plainText = cipher.doFinal(cipherText);

			return new String(plainText, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	public void writeMessageAndKeyToFile(String passphrase, String keyPath, String dataPath) throws IOException, OperatorCreationException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidParameterSpecException {
		writePrivateKeyToFile(passphrase, keyPath);
		writeMessageToFile(dataPath);
	}
	
	public void writePrivateKeyToFile(String passphrase, String keyPath) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidParameterSpecException, IOException{
		
		byte[] encodedprivkey = priv.getEncoded();

		// We must use a PasswordBasedEncryption algorithm in order to encrypt the private key, you may use any common algorithm supported by openssl, you can check them in the openssl documentation http://www.openssl.org/docs/apps/pkcs8.html
		String MYPBEALG = "PBEWithSHA1AndDESede";

		int count = 20;// hash iteration count
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		// Create PBE parameter set
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(passphrase.toCharArray());
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance(MYPBEALG);
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

		Cipher pbeCipher = Cipher.getInstance(MYPBEALG);

		// Initialize PBE Cipher with key and parameters
		pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

		// Encrypt the encoded Private Key with the PBE key
		byte[] ciphertext = pbeCipher.doFinal(encodedprivkey);

		// Now construct  PKCS #8 EncryptedPrivateKeyInfo object
		AlgorithmParameters algparms = AlgorithmParameters.getInstance(MYPBEALG);
		algparms.init(pbeParamSpec);
		EncryptedPrivateKeyInfo encinfo = new EncryptedPrivateKeyInfo(algparms, ciphertext);

		// and here we have it! a DER encoded PKCS#8 encrypted key!
		byte[] encryptedPkcs8 = encinfo.getEncoded();
		
		FileOutputStream fos = new FileOutputStream(keyPath);
		fos.write(encryptedPkcs8);
		fos.close();
	}
	
	public void writeMessageToFile(String dataPath) throws IOException
	{
		byte[] bMessage = encrypt(pub, message);

		if(Main.DEBUG)
			System.out.println("Try to write to " + dataPath);

		FileOutputStream datafos = new FileOutputStream(dataPath);

		datafos.write(bMessage);
		datafos.close();
	}
	
	public String readMessageFromFile(String dataPath) throws IOException, FileNotFoundException
	{
		FileInputStream datafis = new FileInputStream(dataPath);

		byte[] data = new byte[datafis.available()];
		datafis.read(data);

		datafis.close();
		
		return decrypt(priv, data);
	}

	public void readPrivateKeyFromFile(String passphrase, String keyPath) throws GeneralSecurityException, IOException{


		FileInputStream in = new FileInputStream( keyPath );

		PKCS8Key pkcs8 = new PKCS8Key( in, passphrase.toCharArray() );

        // A Java PrivateKey object is born.
		priv = pkcs8.getPrivateKey();

	}
	

	public PrivateKey getPriv() {
		return priv;
	}

	public PublicKey getPub() {
		return pub;
	}
	
	
}
