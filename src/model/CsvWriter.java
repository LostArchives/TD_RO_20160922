package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter {

	private String fileName; //Chemin du fichier .csv généré
	
	public CsvWriter(String pfileName) {
		fileName = pfileName;
	}
	
	///Méthode permettant de créer et d'écrire des données dans le fichier .csv
	public void Write(String data) {
		
		try {
			
			PrintWriter	pw = new PrintWriter(new File(fileName));
			 pw.write(data);
		     pw.close();
		     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
	}
}
