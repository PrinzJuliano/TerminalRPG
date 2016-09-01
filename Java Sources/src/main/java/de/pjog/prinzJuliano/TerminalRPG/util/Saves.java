package de.pjog.prinzJuliano.TerminalRPG.util;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author PrinzJuliano
 */
public class Saves {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void SaveCharacter(RPGCharacter character) {

        JSONSerializer ser = new JSONSerializer();

        String toWrite = ser.prettyPrint(true).serialize(character);

        try {
            String path = "Saves/" + character.getName();

            if (Main.DEBUG) {
                System.out.println("Saving \"" + toWrite + "\" to '" + path + "'");
            }

            File folder = new File(path);
            if (!folder.exists()) {
                while (!folder.exists())
                    folder.mkdirs();

            } else if (!folder.isDirectory()) {
                throw new FileAlreadyExistsException("The path " + folder.getAbsolutePath() + " is a file!");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(path + "/character.json"));
            pw.println(toWrite);
            pw.close();

        } catch (Exception e) {
            System.err.println("Could not save the files!");
            e.printStackTrace();
            return;
        }

        System.out.println("Saved character [" + character.getName() + "]!");
    }

    public static RPGCharacter loadCharacter(String characterName) {
        try {

            String path = "Saves/" + characterName;
            File folder = new File(path);

            if (!folder.exists() || !folder.isDirectory())
                throw new FileNotFoundException("The character is not in the saves folder");

            FileInputStream characterfis = new FileInputStream(path + "/character.json");
            byte[] input = new byte[characterfis.available()];
            characterfis.read(input);
            characterfis.close();

            RPGCharacter r = new JSONDeserializer<RPGCharacter>().deserialize(new String(input, "UTF-8"));
            JSONObject cha = new JSONObject(new String(input, "UTF-8"));
            r.getStats().setHp(cha.getJSONObject("stats").getInt("hp"));
            return r;
        } catch (Exception e) {
            System.err.println("Could not load the files!");
            e.printStackTrace();
            return null;
        }
    }

}
