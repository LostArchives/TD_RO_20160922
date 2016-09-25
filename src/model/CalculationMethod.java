package model;
import java.util.Random;

/**
 * Cette classe permet de gérer les méthodes de calcul avec le knapsack associé
 * 
 * @author Valentin Mullet
 *
 */
public class CalculationMethod {

	private Knapsack knapsack; //Knapsack associé au type de recherche
	
	public Knapsack getKnapsack() {
		return knapsack;
	}

	public void setKnapsack(Knapsack knapsack) {
		this.knapsack = knapsack;
	}


	public CalculationMethod(Knapsack k) {
		knapsack = k;
	}
	
	/**
	 * Méthode permettant de lancer une recherche aléatoire sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'évaluations où on cherche le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'essais
	 */
	public double rechercheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit=0; //Le profit max à retourner
		
		for (int cnt=0;cnt<p_evalBeforeBest;cnt++) {
			
			boolean[] presence = gen_randomArray(knapsack.getNbObjects());
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
				
		}
		
		System.out.println("Recherche aléatoire - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
		
	}
	
	/**
	 * Méthode permettant de lancer une marche aléatoire sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'évaluations avant de trouver le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'évaluations
	 */
	public double marcheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit = 0; //Le profit max à retourner
		
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		for (int cntEval=0;cntEval<p_evalBeforeBest;cntEval++) {
			
			int actualIndex = cntEval%presence.length; //Index du tableau à modifier
			
			presence[actualIndex]=!presence[actualIndex];
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
			
		}
		
		System.out.println("Marche aléatoire - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
		
	}

	/**
	 * Méthode permettant d'effectuer une recherche de type hillClimber sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'évaluations où on cherche le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'évaluation
	 */
	public double hillClimber(int p_evalBeforeBest) {
	
		double theMaxProfit = 0; //Le profit max à retourner
		
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		boolean[] bestPresence = new boolean[knapsack.getNbObjects()]; //Le meilleur tableau de voisinage (voisinage)
		
		for (int cnt = 0;cnt<p_evalBeforeBest;cnt++) {
			
			for (int cntObject=0;cntObject<knapsack.getNbObjects();cntObject++) {
				
				double leProfit = knapsack.evaluate(presence);
				
				if (leProfit>theMaxProfit) {
					theMaxProfit = leProfit;
					bestPresence = presence; //Ce tableau devient le meilleur tableau de voisinage
				}
				
				presence[cntObject]=!presence[cntObject]; //Variation de la valeur à cet indice
				
			}
			presence = bestPresence;
			
		}
		
		System.out.println("HillClimber - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
	}

	/**
	 * Méthode permettant d'effectuer une recherche de type hillClimberFirst sur un knapsack particulier
	 * 
	 * @param p_evalBeforeBest Nombre d'évaluations avant de trouver le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'évaluations
	 */
	public double hillClimberFirst(int p_evalBeforeBest) {
	
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		double theMaxProfit = knapsack.evaluate(presence);
		
		for (int cnt=0;cnt<p_evalBeforeBest;cnt++) {
			
			for (int cntObject=0;cntObject<knapsack.getNbObjects();cntObject++) {
				
				presence[cntObject]=!presence[cntObject];
				double theProfit = knapsack.evaluate(presence);
				
				if (theProfit>theMaxProfit) {
					theMaxProfit = theProfit;
					continue;
				}
			}
			
		}
		
		System.out.println("HillClimberFirst - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
	}
	
	
	/**
	 * Méthode permettant de générer un tableau aléatoire de booléen
	 * 
	 * @param size Taille du tableau à générer
	 * @return Tableau de booléen généré
	 */
	private boolean[] gen_randomArray(int size) {
		
		Random r =new Random();
		boolean[] arr = new boolean[size];
		
	    for(int i = 0; i < size; i++) {
	    	arr[i]=r.nextBoolean();
	    }
	    
	    return arr;
	    
	}
	
}
