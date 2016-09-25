package model;
import java.util.Random;

/**
 * Cette classe permet de g�rer les m�thodes de calcul avec le knapsack associ�
 * 
 * @author Valentin Mullet
 *
 */
public class CalculationMethod {

	private Knapsack knapsack; //Knapsack associ� au type de recherche
	
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
	 * M�thode permettant de lancer une recherche al�atoire sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'�valuations o� on cherche le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'essais
	 */
	public double rechercheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit=0; //Le profit max � retourner
		
		for (int cnt=0;cnt<p_evalBeforeBest;cnt++) {
			
			boolean[] presence = gen_randomArray(knapsack.getNbObjects());
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
				
		}
		
		System.out.println("Recherche al�atoire - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
		
	}
	
	/**
	 * M�thode permettant de lancer une marche al�atoire sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'�valuations avant de trouver le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'�valuations
	 */
	public double marcheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit = 0; //Le profit max � retourner
		
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		for (int cntEval=0;cntEval<p_evalBeforeBest;cntEval++) {
			
			int actualIndex = cntEval%presence.length; //Index du tableau � modifier
			
			presence[actualIndex]=!presence[actualIndex];
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
			
		}
		
		System.out.println("Marche al�atoire - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
		
	}

	/**
	 * M�thode permettant d'effectuer une recherche de type hillClimber sur un knapsack
	 * 
	 * @param p_evalBeforeBest Nombre d'�valuations o� on cherche le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'�valuation
	 */
	public double hillClimber(int p_evalBeforeBest) {
	
		double theMaxProfit = 0; //Le profit max � retourner
		
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		boolean[] bestPresence = new boolean[knapsack.getNbObjects()]; //Le meilleur tableau de voisinage (voisinage)
		
		for (int cnt = 0;cnt<p_evalBeforeBest;cnt++) {
			
			for (int cntObject=0;cntObject<knapsack.getNbObjects();cntObject++) {
				
				double leProfit = knapsack.evaluate(presence);
				
				if (leProfit>theMaxProfit) {
					theMaxProfit = leProfit;
					bestPresence = presence; //Ce tableau devient le meilleur tableau de voisinage
				}
				
				presence[cntObject]=!presence[cntObject]; //Variation de la valeur � cet indice
				
			}
			presence = bestPresence;
			
		}
		
		System.out.println("HillClimber - Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
	}

	/**
	 * M�thode permettant d'effectuer une recherche de type hillClimberFirst sur un knapsack particulier
	 * 
	 * @param p_evalBeforeBest Nombre d'�valuations avant de trouver le meilleur profit
	 * @return Le meilleur profit sur ce nombre d'�valuations
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
	 * M�thode permettant de g�n�rer un tableau al�atoire de bool�en
	 * 
	 * @param size Taille du tableau � g�n�rer
	 * @return Tableau de bool�en g�n�r�
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
