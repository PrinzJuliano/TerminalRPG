package de.pjog.prinzJuliano.TerminalRPG.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class VerifySig {

	public static void execute(String[] args) {
		// Decipher args

		StringBuilder input = new StringBuilder();
		StringBuilder key = new StringBuilder();
		StringBuilder cert = new StringBuilder();

		if (args.length < 4) {
			System.out.println("Not done correctly!");
		} else {
			if (args[0].equalsIgnoreCase("-in")) {
				int i = 1;

				boolean foundKey = false;
				boolean foundCert = false;

				//Read the input
				for (; i < args.length; i++) {
					if (args[i].equalsIgnoreCase("-key")) {
						foundKey = true;
					if(args[i].equalsIgnoreCase("-cert"))
						foundCert = true;
						break;
					}
					input.append(args[i] + " ");
				}
				if (!foundKey && !foundCert) {
					System.out.println("Not done correctly!");
					return;
				}
				i++;
				if(foundKey)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-cert")){
							foundCert = true;
							break;
						}
						key.append(args[i] + " ");
					}
					
					if(!foundCert){
						System.out.println("Not done correctly!");
						return;
					}
					
					i++;
					for (; i < args.length; i++) {
						cert.append(args[i] + " ");
					}
				}
				else if(foundCert)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-key")){
							foundKey = true;
							break;
						}
						cert.append(args[i] + " ");
					}
					
					if(!foundKey){
						System.out.println("Not done correctly!");
						return;
					}
					i++;
					for (; i < args.length; i++) {
						key.append(args[i] + " ");
					}
				}
				
			} else if (args[0].equalsIgnoreCase("-key")) {
				int i = 1;

				boolean foundInput = false;
				boolean foundCert = false;

				//Read the input
				for (; i < args.length; i++) {
					if (args[i].equalsIgnoreCase("-in")) {
						foundInput = true;
					if(args[i].equalsIgnoreCase("-cert"))
						foundCert = true;
						break;
					}
					key.append(args[i] + " ");
				}
				if (!foundInput && !foundCert) {
					System.out.println("Not done correctly!");
					return;
				}
				i++;
				if(foundInput)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-cert")){
							foundCert = true;
							break;
						}
						input.append(args[i] + " ");
					}
					
					if(!foundCert){
						System.out.println("Not done correctly!");
						return;
					}
					
					i++;
					for (; i < args.length; i++) {
						cert.append(args[i] + " ");
					}
				}
				else if(foundCert)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-in")){
							foundInput = true;
							break;
						}
						cert.append(args[i] + " ");
					}
					
					if(!foundInput){
						System.out.println("Not done correctly!");
						return;
					}
					i++;
					for (; i < args.length; i++) {
						input.append(args[i] + " ");
					}
				}
			} else if(args[0].equalsIgnoreCase("-cert"))
			{
				int i = 1;

				boolean foundInput = false;
				boolean foundKey = false;

				//Read the input
				for (; i < args.length; i++) {
					if (args[i].equalsIgnoreCase("-in")) {
						foundInput = true;
					if(args[i].equalsIgnoreCase("-key"))
						foundKey = true;
						break;
					}
					cert.append(args[i] + " ");
				}
				if (!foundInput && !foundKey) {
					System.out.println("Not done correctly!");
					return;
				}
				i++;
				if(foundInput)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-key")){
							foundKey = true;
							break;
						}
						input.append(args[i] + " ");
					}
					
					if(!foundKey){
						System.out.println("Not done correctly!");
						return;
					}
					
					i++;
					for (; i < args.length; i++) {
						key.append(args[i] + " ");
					}
				}
				else if(foundKey)
				{
					for (; i < args.length; i++) {
						if(args[i].equalsIgnoreCase("-in")){
							foundInput = true;
							break;
						}
						key.append(args[i] + " ");
					}
					
					if(!foundInput){
						System.out.println("Not done correctly!");
						return;
					}
					i++;
					for (; i < args.length; i++) {
						input.append(args[i] + " ");
					}
				}
			}
			else
				System.out.println("Not done correctly!");

			System.out.println("OriginalFile: " + input.toString().trim());
			System.out.println("Key         : " + key.toString().trim());
			System.out.println("Certificate : " + cert.toString().trim());
			
			System.out.println("File integrate: " + verify(input.toString().trim(), key.toString().trim(), cert.toString().trim()));
		}
	}
	
	public static boolean verify(String originalFile, String key, String certificate)
	{
		try {
			
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			
			// Read the key
			FileInputStream keyfis = new FileInputStream(key);

			byte[] encKey = new byte[keyfis.available()];
			keyfis.read(encKey);

			keyfis.close();
			
			// Create KeySpec
			X509EncodedKeySpec spec = new X509EncodedKeySpec(encKey);
			
			// Recreate the Key
			KeyFactory keygen = KeyFactory.getInstance("RSA", "BC");
			
			PublicKey pubkey = keygen.generatePublic(spec);
			
			// Read the signature
			FileInputStream sigfis = new FileInputStream(certificate);
			
			byte[] sigToVerify = new byte[sigfis.available()];
			sigfis.read(sigToVerify);
			
			sigfis.close();
			
			// Create Signature
			Signature sig = Signature.getInstance("SHA1withRSA", "BC");
			sig.initVerify(pubkey);
			
			// Read the Original file
			FileInputStream datafis = new FileInputStream(originalFile);
			BufferedInputStream bufin = new BufferedInputStream(datafis);
			
			byte[] buffer = new byte[1024];
			int len;
			while(bufin.available() != 0)
			{
				len = bufin.read(buffer);
				sig.update(buffer, 0, len);
			};
			
			bufin.close();
			
			// Verify the signature
			boolean verifies = sig.verify(sigToVerify);
			
			return verifies;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
}
