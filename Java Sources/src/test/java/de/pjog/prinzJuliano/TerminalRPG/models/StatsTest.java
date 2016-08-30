package de.pjog.prinzJuliano.TerminalRPG.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class StatsTest {
	@Test
	public void testValueCorrectness(){
		Stats s = new Stats(1,2,3,4,5,6,7,8);
		Stats s2 = s.clone();
		s2.setVitality(5);
		
		assertNotEquals(s.toString().getBytes(), s2.toString().getBytes());
	}

	@Test
	public void testJSONConversion(){
		Stats s1 = new Stats(1,2,3,4,5,6,7,8);
        JSONObject os1 = s1.toJSONObject();
        Stats s2 = Stats.createNewFromJSONObject(os1);

        assert s2 != null;
        JSONAssert.assertEquals(os1, s2.toJSONObject(), false);
	}
	
}
