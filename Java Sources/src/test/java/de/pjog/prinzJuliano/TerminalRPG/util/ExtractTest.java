package de.pjog.prinzJuliano.TerminalRPG.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ExtractTest {
	
	@Test
	public void testExtract() throws IOException{
		Resources r  = new Resources();
		
		if(new File("Names.txt").exists())
			new File("Names.txt").delete();
		
		File f = r.extractFile("Names.txt");
		
		assertEquals(f.exists(), true);
		//f.delete();
	}

}
