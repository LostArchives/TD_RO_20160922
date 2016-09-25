package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter {

	private String fileName; //Chemin du fichier .csv g�n�r�
	
	public CsvWriter(String pfileName) {
		fileName = pfileName;
	}
	
	///M�thode permettant de cr�er et d'�crire des donn�es dans le fichier .csv
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
