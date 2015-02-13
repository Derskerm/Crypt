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
			int n = getAlphaIndex(keywordArray[i]);
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
		char[][] key = new char[25][25];
		for (int i = 0; i < 25; i++) {
			for (int k = 0; k < 25; k++) {
				int x = index[k]/5;
				int y = index[i]%5;
				key[i][k] = keywordNoRepeats[5*x + y];
			}
		}
		
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
			char[] nextLine = null;
			int start = 0;
			while (in.hasNextLine()) {
				char[] line;
				if (nextLine == null) {
					String input = in.nextLine();
					line = input.toCharArray();
					start = 0;
				} else {
					line = nextLine;
				}
				int c = -1;
				for (int i = start; i < line.length; i++) {
					if (Character.isLetter(line[i])) {
						if (c == -1) { 
							c = i;
						} else {
							boolean capsI = Character.isUpperCase(line[i]);
							boolean capsC = Character.isUpperCase(line[c]);
							int alphaIndexI = getAlphaIndex(Character.toLowerCase(line[i]));
							int alphaIndexC = getAlphaIndex(Character.toLowerCase(line[c]));
							line[c] = key[alphaIndexC][alphaIndexI];
							line[i] = key[alphaIndexI][alphaIndexC];
							if (capsI) {
								Character.toUpperCase(line[i]);
							}
							if (capsC) {
								Character.toUpperCase(line[c]);
							}
							c = -1;
						}
					}
				}
				if (c == -1) {
					bwriter.write(line);
					bwriter.write(lineSeparator);
				} else {
					while (c != -1) {
						if (!in.hasNextLine()) {
							boolean capsC = Character.isUpperCase(line[c]);
							int alphaIndexI = getAlphaIndex('j');
							int alphaIndexC = getAlphaIndex(Character.toLowerCase(line[c]));
							line[c] = key[alphaIndexC][alphaIndexI];
							if (capsC) {
								Character.toUpperCase(line[c]);
							}
							c = -1;
						}
						start = 0;
						while (in.hasNextLine() && start == 0) {
							String next = in.nextLine();
							nextLine = next.toCharArray();
							for (int n = 0; n < nextLine.length && start == 0; n++) {
								if (Character.isLetter(line[n])) {
									boolean capsN = Character.isUpperCase(nextLine[n]);
									boolean capsC = Character.isUpperCase(line[c]);
									int alphaIndexN = getAlphaIndex(Character.toLowerCase(nextLine[n]));
									int alphaIndexC = getAlphaIndex(Character.toLowerCase(line[c]));
									line[c] = key[alphaIndexC][alphaIndexN];
									nextLine[n] = key[alphaIndexN][alphaIndexC];
									if (capsN) {
										Character.toUpperCase(nextLine[n]);
									}
									if (capsC) {
										Character.toUpperCase(line[c]);
									}
									c = -1;
									if (n + 1 < nextLine.length)
										start = n + 1;
									else {
										start = -1;
									}
								}
							}
						}
					}
					System.out.println(line);
					bwriter.write(line);
					bwriter.write(lineSeparator);
					if (start == -1) {
						start = 0;
						nextLine = null;
						System.out.println(nextLine);
						bwriter.write(nextLine);
						bwriter.write(lineSeparator);
					}
				}
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
		
	}
	
	private int getAlphaIndex(char c) {
		int index = c - 97;
		if (index >= 9) 
			index--;
		return index;
	}
	
	
}