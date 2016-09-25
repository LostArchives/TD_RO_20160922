import java.io.FileNotFoundException;
import java.util.Random;

public class TestRechercheAleatoire {

	static int pasEvaluation = 100;
	static int maxEvaluation = 10000;
	static int nbExecution = 30;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}

	public static boolean[] gen_randomArray(int size) {
		
		Random r =new Random();
		boolean[] arr = new boolean[size];
		
	    for(int i = 0; i < size; i++) {
	    	arr[i]=r.nextBoolean();
	    }
	    
	    return arr;
	    
	}
	
	
	
	public static double rechercheAleatoire(Knapsack k,int p_evaluation) {
		
		double leMax=0;
		
		for (int cnt=0;cnt<p_evaluation;cnt++) {
			
			boolean[] presence = gen_randomArray(k.getNbObjects());
			
			double leProfit=k.evaluate(presence);
			
			if (leProfit > leMax) {
				leMax = leProfit;
			}
				
		}
		
		System.out.println("Le profit max est de " + leMax + " pour "+p_evaluation);
		
		return leMax;
	}

}
