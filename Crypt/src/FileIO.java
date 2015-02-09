import java.io.*;
import java.util.Scanner;

public class FileIO {
	
	private String lineSeparator;
	
	public FileIO() {
		lineSeparator = System.getProperty("line.separator");
	}
	
	public String readFile(String filename) {
		FileReader reader;
		String fileData = null;
		Scanner in = null;
		try {
			reader = new FileReader(filename);
			in = new Scanner(reader);
			StringBuffer changingFileData = new StringBuffer();
			while (in.hasNextLine()) {
				String input = in.nextLine();
				changingFileData.append(input);
				changingFileData.append(lineSeparator);
			}
			fileData = changingFileData.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}
		return fileData;
	}
	
	public void writeFile(String filename, String data) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filename);
			writer.write(data);
		} catch (FileNotFoundException e) {
//			Better reaction than printing a stack trace
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
