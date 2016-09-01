package de.pjog.prinzJuliano.TerminalRPG.models;

import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.Assert.assertNotEquals;

public class StatsTest {
    @Test
    public void testValueCorrectness() {
        Stats s = new Stats(1, 2, 3, 4, 5, 6, 7, 8);
        Stats s2 = s.clone();
        s2.setVitality(5);

        assertNotEquals(s.toString().getBytes(), s2.toString().getBytes());
    }
}
