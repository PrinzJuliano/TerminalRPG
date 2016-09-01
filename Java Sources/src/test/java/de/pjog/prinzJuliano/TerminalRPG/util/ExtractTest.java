package de.pjog.prinzJuliano.TerminalRPG.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExtractTest {

    @Test
    public void testExtract() throws IOException {

        if (new File("Names.txt").exists())
            new File("Names.txt").delete();

        File f = Resources.extractFile("Names.txt");

        assertEquals(f.exists(), true);
        //f.delete();
    }

}
