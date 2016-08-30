package de.pjog.prinzJuliano.TerminalRPG.util;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.security.GenSig;
import de.pjog.prinzJuliano.TerminalRPG.security.RSAEncrypt;
import de.pjog.prinzJuliano.TerminalRPG.security.VerifySig;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author PrinzJuliano
 */
public class Saves {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void SaveCharacter(RPGCharacter character)
    {
        String toWrite = character.toJSONObject().toString();

        RSAEncrypt enc = new RSAEncrypt(toWrite);

        try{
            String path = "Saves/" + character.getName();

            if(Main.DEBUG)
            {
                System.out.println("Saving \""+toWrite+"\" to '"+path+"'");
            }

            File folder = new File(path);
            if(!folder.exists())
            {
                while(!folder.exists())
                    folder.mkdirs();

            }else if(!folder.isDirectory())
            {
                throw new FileAlreadyExistsException("The path " + folder.getAbsolutePath() + " is a file!");
            }

            enc.writeMessageAndKeyToFile(character.getName(), path + "/key.pkcs8", path + "/character.dat");

            GenSig.execute(new String[]{"-in", path + "/key.pkcs8"});
            GenSig.execute(new String[]{"-in", path + "/character.dat"});
        }catch(Exception e)
        {
            System.err.println("Could not save the files!");
            e.printStackTrace();
            return;
        }

        System.out.println("Saved character [" + character.getName() + "]!");
    }

    public static RPGCharacter loadCharacter(String characterName){
        RSAEncrypt dec = new RSAEncrypt();

        try{

            String path = "Saves/" + characterName;
            File folder = new File(path);

            if(!folder.exists() || !folder.isDirectory())
                throw new FileNotFoundException("The character is not in the saves folder");

            // Verify
            boolean key = VerifySig.verify(path + "/key.pkcs8", path + "/key.pkcs8.key", path + "/key.pkcs8.cert");
            boolean dat = VerifySig.verify(path + "/character.dat", path + "/character.dat.key", path + "/character.dat.cert");

            if(!key || !dat)
                throw new Exception("Integrity damaged. Files corrupted!");

            dec.readPrivateKeyFromFile(characterName, path + "/key.pkcs8");
            String characterJSON = dec.readMessageFromFile(path + "/character.dat");

            JSONObject o = new JSONObject(characterJSON);
            return RPGCharacter.createNewFromJSONObject(o);
        } catch(Exception e)
        {
            System.err.println("Could not load the files!");
            e.printStackTrace();
            return null;
        }
    }

}
