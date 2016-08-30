package de.pjog.prinzJuliano.TerminalRPG.security;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;

public class GenSig {
	
	public static void execute(String[] args) {
		// Generate DSA Signature
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		if(args.length <= 1 || !args[0].equalsIgnoreCase("-in"))
		{
			System.out.println("Not done correctly!");
		}
		else try{
			
			// Prepare the key generation
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
			
			// Make sure everything is private
			SecureRandom random = new SecureRandom();
			keyGen.initialize(1024, random);
			
			// Generate keys
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();
			
			// Sign the Data
			Signature dsa = Signature.getInstance("SHA1withRSA");
			dsa.initSign(priv);
			
			// Prepare the path
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < args.length; i++)
			{
				sb.append(args[i] + " ");
			}
			String path = sb.toString().trim();
			
			// Read the data and sign it
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bufin = new BufferedInputStream(fis);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufin.read(buffer)) >= 0)
			{
				dsa.update(buffer, 0, len);
			}
			bufin.close();
			
			// Sign the Data
			byte[] realSig = dsa.sign();
			
			// Save the Data to a file
			FileOutputStream sigfos = new FileOutputStream(path+".cert");
			sigfos.write(realSig);
			sigfos.close();
			
			// Save the Public Key
			byte[] key = pub.getEncoded();
			FileOutputStream keyfos = new FileOutputStream(path+".key");
			keyfos.write(key);
			keyfos.close();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
