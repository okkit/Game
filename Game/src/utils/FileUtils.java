package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {

	public static char[][] readText(String filename) throws FileNotFoundException {

//		ArrayList<int[]> list;
		
		String cwd = System.getProperty("user.dir");
//        System.out.println("Current working directory : " + cwd);
        
		char[][] content = new char[100][160];

		FileInputStream fstream = new FileInputStream(cwd+"/src/resources/rooms/" + filename);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(fstream, "UTF8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String strLine;
		int lineIndex = 0;

		// Read File line By line
		try {
			while ((strLine = br.readLine()) != null) {

				for (int i = 0; i < strLine.length(); i++) {
					content[lineIndex][i] = strLine.charAt(i);
//					System.out.print(content[lineIndex][i]);
				}
//				System.out.println();
				lineIndex ++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close the input stream
		try {
			fstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

}
