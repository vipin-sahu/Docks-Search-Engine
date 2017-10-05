package AfterCompleteSpeed;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

public class objectReader {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] arg) {
		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"C:\\MyEclips8.6\\InvertedIndex\\Object1.ser"));
			TreeMap<String, Vector<Docks>> tm = (TreeMap<String, Vector<Docks>>) ois
					.readObject();

			Scanner scan = new Scanner(System.in);
			String term = scan.next();
			search(term, tm);
			// PrintAll(tm);
			scan.close();
		} catch (Exception e) {
			System.out.println("File not found");
		}

	}

	public static void search(String term, TreeMap<String, Vector<Docks>> TM) {
		boolean resultFound = false;
		// System.out.print("**");
		Iterator<Entry<String, Vector<Docks>>> it = TM.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Vector<Docks>> entry = it.next();
			if (!entry.getKey().equals(term.toLowerCase())) {
				continue;
			}
			System.out.print(entry.getKey() + " : { ");
			Vector<Docks> AL = entry.getValue();
			resultFound = true;
			for (int i = 0; i < AL.size(); i++) {
				System.out
						.print(AL.get(i).dockName + ":" + AL.get(i).frequency);
				if ((i + 1) < AL.size()) {
					System.out.print(" , ");
				}
			}
			System.out.print(" }\n");
		}
		if (!resultFound) {
			System.out.println("Result Not Found In any Document:");
		}
	}

	public static void PrintAll(TreeMap<String, Vector<Docks>> TM) {
		Iterator<Entry<String, Vector<Docks>>> it = TM.entrySet().iterator();
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
}
