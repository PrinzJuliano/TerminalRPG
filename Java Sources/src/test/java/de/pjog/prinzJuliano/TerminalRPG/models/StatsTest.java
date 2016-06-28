package de.pjog.prinzJuliano.TerminalRPG.models;

import static org.junit.Assert.*;
import org.junit.Test;

import de.pjog.prinzJuliano.TerminalRPG.Main;

public class StatsTest {

	@Test
	public void testStatStringConvergence(){
		Stats s = new Stats(1,2,3,4,5,6,7);
		System.out.println(s);
		
		String head = "%-12s%-12s%-12s%-12s%s";
		String body = "%-12d%-12d%-12d%-12d%d";
		String expected = String.format(head+"\n"+body, "VIT", "STR", "DEX", "INT", "LCK", 1, 2, 3, 4, 5);
		
		assertArrayEquals("Strings where not the same!", s.toString().getBytes(), expected.getBytes());
	}
	@Test
	public void testValueCorrectness(){
		Stats s = new Stats(1,2,3,4,5,6,7);
		Stats s2 = s.clone();
		s2.setVitality(5);
		
		assertNotEquals(s.toString().getBytes(), s2.toString().getBytes());
	}
	
}
