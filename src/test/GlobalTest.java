package test;
import model.CalculationLauncher;
import model.CalculationMethod;
import model.Knapsack;

public class GlobalTest {

	static int pasEvaluation = 100;
	static int maxEvaluation = 1000;
	static int nbExecution = 10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Knapsack k = new Knapsack("src/ks_1000.dat");
		CalculationMethod cm = new CalculationMethod(new Knapsack("resources/ks_1000.dat"));
		CalculationLauncher cl = new CalculationLauncher(cm,nbExecution,pasEvaluation,maxEvaluation);
		
		//cl.LaunchCalculation(cm,"recherche","rechercheAleatoire");
		//cl.LaunchCalculation(cm,"marche","marcheAleatoire");
		cl.LaunchCalculation(cm,"hillclimber","output/hillClimber");
		cl.LaunchCalculation(cm,"hillclimberfirst","output/hillClimberFirst");
		
		
		///Test avec évaluation fixe à 1000
		//cl.LaunchCalculationWithFixedEval(cm,"recherche","rechercheAleatoire",1000);
		//cl.LaunchCalculationWithFixedEval(cm,"marche","marcheAleatoire",1000);
		//cl.LaunchCalculationWithFixedEval(cm,"hillclimber","hillClimber",500);
		//cl.LaunchCalculationWithFixedEval(cm,"hillclimberfirst","hillClimberFirst",500);
	}

}
