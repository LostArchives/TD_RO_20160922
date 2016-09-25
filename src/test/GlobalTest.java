package test;
import model.CalculationLauncher;
import model.CalculationMethod;
import model.Knapsack;

/**
 * Classe de test pour le CalculationLauncher et les classes liées
 * 
 * @author Valentin Mullet
 *
 */
public class GlobalTest {

	static int startEvaluation = 100; // De 100
	static int maxEvaluation = 10000; // à 10000
	static int pasEvaluation = 100; // avec un pas de 100
	static int nbExecution = 10; // le tout 10 fois
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Knapsack k = new Knapsack("src/ks_1000.dat");
		CalculationMethod cm = new CalculationMethod(new Knapsack("resources/ks_1000.dat"));
		CalculationLauncher cl = new CalculationLauncher(cm,nbExecution,startEvaluation,maxEvaluation,pasEvaluation);
		
		cl.LaunchCalculation(cm,"recherche","output/rechercheAleatoire");
		cl.LaunchCalculation(cm,"marche","output/marcheAleatoire");
		//cl.LaunchCalculation(cm,"hillclimber","output/hillClimber");
		//cl.LaunchCalculation(cm,"hillclimberfirst","output/hillClimberFirst");
		
		
		///Test avec une évaluation fixe à 1000
		
		cl.LaunchCalculationWithFixedEval(cm,"recherche","output/rechercheAleatoire",1000);
		cl.LaunchCalculationWithFixedEval(cm,"marche","output/marcheAleatoire",1000);
		//cl.LaunchCalculationWithFixedEval(cm,"hillclimber","hillClimber",500);
		//cl.LaunchCalculationWithFixedEval(cm,"hillclimberfirst","hillClimberFirst",500);
	}

}
