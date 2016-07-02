package de.pjog.prinzJuliano.TerminalRPG.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Resources {

	public String getFileContent(String filename){
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		Scanner scanner = new Scanner(classloader.getResourceAsStream(filename));
		StringBuilder sb = new StringBuilder();
		while(scanner.hasNextLine())
		{
			sb.append(scanner.nextLine() + "\n");
		}

		scanner.close();
		
		return sb.toString();
	}
	
	public byte[] getFileContentAsBytes(String filename) throws IOException
	{
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		return IOUtils.toByteArray(classloader.getResourceAsStream(filename));
		
	}
	
	/**
	 * Extracts a file to the root directory of the application
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public File extractFile(String filename) throws IOException
	{
		return extractFile(filename, ".");
	}
	
	/**
	 * Extracts a file to given destination 
	 * @param filename resource from resource path
	 * @param destination folder where to extract to
	 * @return
	 * @throws IOException any errors during reading and writing the file
	 */
	public File extractFile(String filename, String destination) throws IOException
	{
		File dest = new File(destination);
		String[] parts = filename.split("^[/\\\\]$"); //Split for / or \
		String name = parts[parts.length-1];
		File altDest = new File(dest.getPath() + "/" + name);
		if(!dest.exists())
		{
			dest.mkdirs();
		}
		if(dest.exists() && !dest.isDirectory())
		{
			return dest;
		}
		if(altDest.exists())
		{
			return altDest;
		}
		
		if(dest.isDirectory())
		{
			
			byte[] content = getFileContentAsBytes(filename);
			FileUtils.writeByteArrayToFile(altDest, content);
			return altDest;
		}
		else
		{
			byte[] content = getFileContentAsBytes(filename);
			FileUtils.writeByteArrayToFile(dest, content);
			return dest;
		}
	}
	
}
