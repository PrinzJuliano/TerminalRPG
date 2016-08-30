package de.pjog.prinzJuliano.TerminalRPG.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author PrinzJuliano
 */
public class RPGTests {

    @Test
    public void testJSONConversion(){
        RPGCharacter r1 = new RPGCharacter("John", FightingClasses.MAGE, 50, 31, new Stats(1,2,3,4,5,6,7,8));
        JSONObject or1 = r1.toJSONObject();
        RPGCharacter r2 = RPGCharacter.createNewFromJSONObject(or1);

        assert r2 != null;
        JSONAssert.assertEquals(or1, r2.toJSONObject(), false);
    }
}
