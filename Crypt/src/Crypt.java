import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Crypt {

	public static final String keyword =  "crypt";
	private String lineSeparator = System.getProperty("line.separator");
	
	public void encypt(String inputFilename, String outputFilename, String keyword) {
		BufferedReader breader = null;
		FileReader reader;
		Scanner in = null;
		BufferedWriter bwriter = null;
		FileWriter writer = null;
		try {
			reader = new FileReader(inputFilename);
			breader = new BufferedReader(reader);
			in = new Scanner(breader);
			writer = new FileWriter(inputFilename);
			bwriter = new BufferedWriter(writer);
			while (in.hasNextLine()) {
				String input = in.nextLine();
				String next = null;
				if (in.hasNextLine()) {
					next = in.nextLine();
				}
				StringBuffer changingFileData = new StringBuffer();
				changingFileData.append(lineSeparator);
				
				
				
//				bwriter.write();
			}
			bwriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if (bwriter != null) {
				try {
					bwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		char[] keywordArray = keyword.toCharArray();
		for (int t = 0; t < keywordArray.length; t++) {
			keywordArray[t] = Character.toLowerCase(keywordArray[t]);
		}
		int count = 0;
//		for (int i = 0; i < keywordArray.length; i++) {
//			for (int n = i; n < keywordArray.length; n++) {
//				if (keywordArray[n] == 'j')
//					keywordArray[n] = 'i';
//				if (keywordArray[i] != '#' && keywordArray[i] == keywordArray[n]) {
//					keywordArray[n] = '#';
//					count++;
//				}
//			}
//		}
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p','q','r','s','t','u','v','w','y','z'};
		boolean[] hasLetter = new boolean[25];
		for (int i = 0; i < keywordArray.length; i++) {
			if (keywordArray[i] == 'j')
				keywordArray[i] = 'i';
			int n = keywordArray[i] - 97;
			if (n >= 9) 
				n--;
			if (hasLetter[n]) {
				keywordArray[i] = '#';
				count++;
			} else {
				hasLetter[n] = true;
			}
		}
		char[] keywordNoRepeats = new char[25];
		int dif = 0;
		for (int i = 0; i < keywordArray.length; i++) {
			if (keywordArray[i] != '#')
				keywordNoRepeats[i - dif] = keywordArray[i];
			else
				dif++;
		}
//		for (char c : keywordNoRepeats) {
//			int index = c - 97;
//			if (index >= 0 && index < 26) {
//				if (index >= 9) 
//					index--;
//				hasLetter[index] = true;
//			}
//		}
		int dif2 = keywordArray.length - count;
		for (int i = 0; i < alphabet.length; i++) {
			if (!hasLetter[i])
				keywordNoRepeats[i + dif2] = alphabet[i];
				
			else
				dif2--;
		}
		int[] index = new int[25];
//		char[][] charray = new char[5][5];
//		for (int x = 0; x < charray.length; x++) {
//			for (int y = 0; y < charray[x].length; y++) {
//				charray[x][y] = keywordNoRepeats[charray.length*x + y];
//				int i = charray[x][y] - 97;
//				if (i >= 9) 
//					i--;
//				index[i] = charray.length*x + y;
//			}
//		}
		int[][] key = new int[25][25];
		for (int i = 0; i < 25; i++) {
			for (int k = 0; k < 25; k++) {
				int x = index[k]/5;
				int y = index[i]%5;
				key[i][k] = keywordArray[5*x + y];
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		char[] datarray = data.toCharArray();
//		for (int i = 0; i < datarray.length; i+=2) {
//			char a = datarray[i];
//			char b;
//			if (i == datarray.length - 1) {
//				b = 'J';
//			} else {
//				b = datarray[i + 1];
//			}
//		}
		
	}
	
	
}