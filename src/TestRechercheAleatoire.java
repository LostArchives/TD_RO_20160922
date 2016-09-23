import java.io.FileNotFoundException;
import java.util.Random;

public class TestRechercheAleatoire {

	static int pasEvaluation = 40;
	static int nbExecution = 100;
	
	public static void maintoto(String[] args) {
		// TODO Auto-generated method stub
		
		Knapsack k =new Knapsack("src/ks_1000.dat");
		CsvWriter c = new CsvWriter("RandomResearchRA.csv"); //.csv généré à la racine du projet
		
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n");
		
		int nbEvaluation = pasEvaluation;
		
		for (int cnt=0;cnt<nbExecution;cnt++) {
			
			double maxEvaluationRA = rechercheAleatoire(k,nbEvaluation);
			
			sb.append(nbEvaluation+";"+maxEvaluationRA+"\n");
			
			nbEvaluation+= pasEvaluation;
		}
		
		try {
			c.Write(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static int[] gen_randomArray(int size) {
		
		Random r =new Random();
		int[] arr = new int[size];
		
	    for(int i = 0; i < size; i++) {
	    	if (r.nextBoolean()==true)
	    		arr[i]=1;
	    	else
	    		arr[i]=0; 
	    }
	    
	    return arr;
	    
	}
	
	
	
	public static double rechercheAleatoire(Knapsack k,int p_evaluation) {
		
		double leMax=0;
		
		for (int cnt=0;cnt<p_evaluation;cnt++) {
			
			int[] presence = gen_randomArray(k.getNbObjects());
			
			double leProfit=k.evaluate(presence);
			
			if (leProfit > leMax) {
				leMax = leProfit;
			}
			
			
		}
		
		System.out.println("Le profit max est de " + leMax + " pour "+p_evaluation);
		
		return leMax;
	}

}
