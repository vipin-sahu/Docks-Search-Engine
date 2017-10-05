package AfterCompleteSpeed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class IDFwaight {
	public static void main(String[] arg){
		try{
			
			File folder = new File("C:\\MyEclips8.6\\InvertedIndex\\src\\vipin");
			File[] listOfFiles = folder.listFiles();
			double i = listOfFiles.length;
			System.out.println(i);
	 
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\MyEclips8.6\\InvertedIndex\\Object1.ser")); 
			TreeMap<String, Vector<Docks>> tm = (TreeMap<String, Vector<Docks>>) ois.readObject();
			System.out.println(i);
			Scanner scan = new Scanner(System.in);
			String st = scan.next();
			search(st,tm);
			idfWaight(tm,i);
			
			scan.close();
			
 
		}catch(Exception e){
			System.out.println("File not found");
		}
		
	}

	private static void idfWaight(TreeMap<String, Vector<Docks>> tm,double N) throws FileNotFoundException, IOException {
		 ArrayList<Integer> AL = new ArrayList<Integer>();
		 Iterator<Entry<String, Vector<Docks>>> it = tm.entrySet().iterator();
		 while(it.hasNext()){
			  Entry<String,Vector<Docks>> entry = it.next();
			  Vector<Docks> VD = entry.getValue();
			  double DF = VD.size();
			  for(int i =0; i<DF; i++){
			  tm.get(entry.getKey()).get(i).frequency = (int) (VD.get(i).frequency*(Math.log(N/DF)));
			  } 
		 }
		 
		 @SuppressWarnings("resource")
			ObjectOutputStream object = new ObjectOutputStream(
					new FileOutputStream("Object2.ser"));
			object.writeObject(tm);
	}
	
	public static void PrintAll(TreeMap<String, Vector<Docks>> TM) {
		Iterator<Entry<String, Vector<Docks>>> it = TM.entrySet().iterator(); 
		while (it.hasNext()) {
			Entry<String, Vector<Docks>> entry = it.next();
			System.out.print(entry.getKey() + " : { ");
			Vector<Docks> AL = entry.getValue();
			for (int i = 0; i < AL.size(); i++) {
				System.out.print(AL.get(i).dockName + ":" + AL.get(i).frequency);
				if ((i + 1) < AL.size()) {
					System.out.print(" , ");
				}
			}
			System.out.print(" }\n");
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
				System.out.print(AL.get(i).dockName + ":" + AL.get(i).frequency);
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
}
