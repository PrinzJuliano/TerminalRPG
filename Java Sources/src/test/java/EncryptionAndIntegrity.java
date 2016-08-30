

import java.io.File;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import de.pjog.prinzJuliano.TerminalRPG.security.GenSig;
import de.pjog.prinzJuliano.TerminalRPG.security.RSAEncrypt;
import de.pjog.prinzJuliano.TerminalRPG.security.VerifySig;

public class EncryptionAndIntegrity {

	@Test
	public void testEncryptionAndIntegrity() {
		
		String iMessage = "Hi!";
		
		RSAEncrypt enc = new RSAEncrypt(iMessage);
		
		try {
			new File("Test").mkdir();
			//Writing
			enc.writeMessageAndKeyToFile("This is lucas.", "Test/message.pkcs8", "Test/message.dat");
			
			
			GenSig.execute(new String[]{"-in", "Test/message.dat"});
			GenSig.execute(new String[]{"-in", "Test/message.pkcs8"});
			
			// READING
			
			RSAEncrypt dec = new RSAEncrypt();
			boolean key = VerifySig.verify("Test/message.pkcs8", "Test/message.pkcs8.key", "Test/message.pkcs8.cert");
			boolean dat = VerifySig.verify("Test/message.dat", "Test/message.dat.key", "Test/message.dat.cert");
			
			if(!key || !dat)
			{
				System.err.println("The files have been changed and lost their integrity!");
				return;
			}
			
			dec.readPrivateKeyFromFile("This is lucas.", "Test/message.pkcs8");
			String newMessage = dec.readMessageFromFile("Test/message.dat");
			
			assertArrayEquals(iMessage.getBytes(), newMessage.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
