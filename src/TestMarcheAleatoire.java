import java.io.FileNotFoundException;
import java.util.Random;

public class TestMarcheAleatoire extends TestRechercheAleatoire {

	static int pasEvaluation = 40;
	static int nbExecution = 100;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Knapsack k =new Knapsack("src/ks_1000.dat");
		CsvWriter c2 = new CsvWriter("RandomResearchMA.csv"); //.csv généré à la racine du projet
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n");
		
		int nbEvaluation = pasEvaluation;
		
		for (int cnt=0;cnt<nbExecution;cnt++) {
			
			double maxEvaluationMA = marcheAleatoire(k,nbEvaluation);
			
			sb.append(nbEvaluation+";"+maxEvaluationMA+"\n");
			
			nbEvaluation+= pasEvaluation;
		}
		
		try {
			c2.Write(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static double marcheAleatoire(Knapsack k,int p_evaluation) {
		
		double leMax = 0;
		int[] presence = gen_randomArray(k.getNbObjects());
		
		//System.out.println(k.getNbObjects());
		
		for (int cnt=0;cnt<p_evaluation;cnt++) {
			
			
			int actualIndex = cnt%presence.length;
			//System.out.println(actualIndex);
			if (presence[actualIndex]==0)
				presence[actualIndex]=1;
			else
				presence[actualIndex]=0;
			
			double leProfit=k.evaluate(presence);
			
			if (leProfit > leMax) {
				leMax = leProfit;
			}
			
		}
		
		return leMax;
		
	}
	
	

}
