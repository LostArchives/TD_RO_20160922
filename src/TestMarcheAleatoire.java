import java.io.FileNotFoundException;

public class TestMarcheAleatoire extends TestRechercheAleatoire {

	static int pasEvaluation = 100;
	static int maxEvaluation = 10000;
	static int nbExecution = 30;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Knapsack k =new Knapsack("src/ks_1000.dat");
		CsvWriter c2 = new CsvWriter("RandomResearchMA.csv"); //.csv généré à la racine du projet
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n");
		
		
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
			for (int cntEvaluation = pasEvaluation;cntEvaluation<maxEvaluation;cntEvaluation+=pasEvaluation ) {
				
				double maxEvaluationMA = marcheAleatoire(k,cntEvaluation);
				
				sb.append(cntEvaluation+";"+maxEvaluationMA+"\n");
				
				cntEvaluation+= pasEvaluation;
			}
			
			
		}
		
		c2.Write(sb.toString());
		
	}
	
	public static double marcheAleatoire(Knapsack k,int p_evaluation) {
		
		double leMax = 0;
		boolean[] presence = gen_randomArray(k.getNbObjects());
		
		for (int cnt=0;cnt<p_evaluation;cnt++) {
			
			int actualIndex = cnt%presence.length;
			
			presence[actualIndex]=!presence[actualIndex];
			
			double leProfit=k.evaluate(presence);
			
			if (leProfit > leMax) {
				leMax = leProfit;
			}
			
		}
		
		return leMax;
		
	}
	
	

}
