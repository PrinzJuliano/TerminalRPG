package de.pjog.prinzJuliano.TerminalRPG.util;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Resources {

	 public String getFile(String fileName){

			StringBuilder result = new StringBuilder("");

			//Get file from resources folder
			ClassLoader classLoader = this.getClass().getClassLoader();
			File file;
			try {
				file = new File(classLoader.getResource(fileName).toURI());
			} catch (URISyntaxException e1) {
				return null;
			}

			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}

				scanner.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return result.toString();

		  }

}
