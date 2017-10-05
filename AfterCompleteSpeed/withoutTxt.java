package AfterCompleteSpeed;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;

public class withoutTxt {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void p(String s) {
		System.out.println(s);
	}

	public static void p1(int s) {
		System.out.println(s);
	}

	StringTokenizer st1;
	static ArrayList<String> documents;
	static String basePath = "C:\\MyEclips8.6\\InvertedIndex\\src\\vipin\\";

	public static void main(String[] arg) throws FileNotFoundException,
			IOException {
		documents = new ArrayList<>();
		File folder = new File("C:\\MyEclips8.6\\InvertedIndex\\src\\vipin");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				documents.add(listOfFiles[i].getName());
			}
		}

		TreeMap<String, Vector<Docks>> TM = new TreeMap<String, Vector<Docks>>();

		wordToDocument(TM);
		//printAll(TM);
	}

	private static void printAll(TreeMap<String, Vector<Docks>> tM) {

		java.util.Iterator<Entry<String, Vector<Docks>>> it = tM.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, Vector<Docks>> entry = it.next();
			System.out.print(entry.getKey() + " : { ");
			Vector<Docks> AL = entry.getValue();
			for (int i = 0; i < AL.size(); i++) {
				System.out
						.print(AL.get(i).dockName + ":" + AL.get(i).frequency);

				if ((i + 1) < AL.size()) {
					System.out.print(" , ");
				}
			}
			System.out.print(" }\n");
		}
	}

	@SuppressWarnings("deprecation")
	private static void wordToDocument(TreeMap<String, Vector<Docks>> tM)
			throws FileNotFoundException, IOException {

		BufferedInputStream isr;
		int count;
		StringTokenizer word;

		int freq;
		String word1;
		float fileIndexed = 0;
		float totalFilesToIndex = 50691;
		String s = "";
		for (int x = 0; x < documents.size(); x++) {
			try {

				Reader reader = new FileReader(basePath + documents.get(x));
				BufferedReader bufferedReader = new BufferedReader(reader);
				try{
					String line = bufferedReader.readLine();
					while (line != null) {
						word = new StringTokenizer(line, " ,></");
						
						while (word.hasMoreTokens()) {
							word1 = word.nextToken();
							
								sb.delete(0, sb.length());
 								sb.append(word1);
 								remove_suffix(sb);
 								 
								String result = sb.toString();
	
							addCount(result, tM, documents.get(x));
						}		 
						line = bufferedReader.readLine();
					}
				}catch(Exception e){
					p("e");
				}

				reader.close();
			} catch (Exception e) {
				String s1 = "File not found Exceptioin";
				p(s1);
			}

			fileIndexed += 1;
			float percentageCompleted = (fileIndexed / totalFilesToIndex) * 100;

			p("Indexed " + percentageCompleted + " %");

		}
		System.out.println(documents.size());
		@SuppressWarnings("resource")
		ObjectOutputStream object = new ObjectOutputStream(
				new FileOutputStream("Object1.ser"));
		object.writeObject(tM);

	}

	private static void addCount(String word,
			TreeMap<String, Vector<Docks>> tM, String docksName) {

		Vector<Docks> VD = tM.get(word);
		if (tM.containsKey(word)) {

			boolean flag = false;
			for (int i = 0; i < VD.size(); i++) {
				if (VD.get(i).dockName.equals(docksName)) {
					tM.get(word).get(i).frequency = VD.get(i).frequency + 1;
					flag = true;
				}
			}
			if (!flag) {
				tM.get(word).add(new Docks(docksName, 1));
			}
		} else {
			VD = new Vector<Docks>();
			VD.add(new Docks(docksName, 1));
			tM.put(word, VD);
		}

	}
	
	public static void remove_suffix(StringBuilder word) {
		int len = word.length() - 1;
		 
		/* article */
		
		if (len > 4) {
			if (word.substring( len- 2, len+1).equals("िया")) {
				word.delete(len-2 , len + 1);
				return;
			}
			if (word.substring( len- 2, len+1).equals("ियो")) {
				word.delete(len-2 , len + 1);
				return;
			}
			
		} /* end if len >4 */
		if (len > 3) {
			if (word.substring(len-1, len+1).equals("ाए")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals(" ाओ")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals(" ुआ")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals(" ुओ")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring( len- 1, len+1).equals("ये")) {
				word.delete(len-1 , len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals(" ेन")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals(" ेण")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring( len- 1, len+1).equals(" ीय")) {
				word.delete(len-1 , len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals("टी")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals("ार")) {
				word.delete(len - 1, len + 1);
				return;
			}
			if (word.substring(len-1, len+1).equals("ाई")) {
				word.delete(len - 1, len + 1);
				return;
			}
			
		} /* end if len > 3 */
		 
		if (len > 2) {
			 
			if (word.substring(len, len+1).equals(" ा")) {
				word.delete(len , len + 1);
			 
				return;
			}
			if (word.substring(len, len+1).equals(" े")) {
				word.delete(len , len + 1);
			 
				return;
			}
			if (word.substring(len, len+1).equals(" ी")) {
				word.delete(len , len + 1);
				 
				return;
			}
			if (word.substring(len, len+1).equals(" ो")) {
				word.delete(len , len + 1);
			 
				return;
			}
			if (word.substring(len, len+1).equals("ि ")) {
				word.delete(len , len + 1);
 
				return;
			}
			if (word.substring(len, len+1).equals("अ")) {
				word.delete(len , len + 1);
				return;
			}
			
		} /* end if len > 2 */
		return;
	}


}
