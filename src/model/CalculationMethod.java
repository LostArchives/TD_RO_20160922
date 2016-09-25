package model;
import java.util.Random;

public class CalculationMethod {

	private Knapsack knapsack;
	
	public Knapsack getKnapsack() {
		return knapsack;
	}

	public void setKnapsack(Knapsack knapsack) {
		this.knapsack = knapsack;
	}


	public CalculationMethod(Knapsack k) {
		knapsack = k;
	}
	
	public double rechercheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit=0;
		
		for (int cnt=0;cnt<p_evalBeforeBest;cnt++) {
			
			boolean[] presence = gen_randomArray(knapsack.getNbObjects());
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
				
		}
		
		System.out.println("Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		
		return theMaxProfit;
		
	}
	
	public double marcheAleatoire(int p_evalBeforeBest) {
		
		double theMaxProfit = 0;
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		for (int cnt=0;cnt<p_evalBeforeBest;cnt++) {
			
			int actualIndex = cnt%presence.length;
			
			presence[actualIndex]=!presence[actualIndex];
			
			double leProfit=knapsack.evaluate(presence);
			
			if (leProfit > theMaxProfit) {
				theMaxProfit = leProfit;
			}
			
		}
		
		System.out.println("Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		return theMaxProfit;
		
	}

	public double hillClimber(int p_evalBeforeBest) {
	
		double theMaxProfit = 0;
		boolean[] presence = gen_randomArray(knapsack.getNbObjects());
		
		boolean[] bestPresence = new boolean[knapsack.getNbObjects()];
		
		for (int cnt = 0;cnt<p_evalBeforeBest;cnt++) {
			
			for (int cntObject=0;cntObject<knapsack.getNbObjects();cntObject++) {
				
				double leProfit = knapsack.evaluate(presence);
				
				if (leProfit>theMaxProfit) {
					theMaxProfit = leProfit;
					bestPresence = presence;
				}
				
				presence[cntObject]=!presence[cntObject];
				
			}
			presence = bestPresence;
			
		}
		
		System.out.println("Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		return theMaxProfit;
	}

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
		System.out.println("Le profit max est de " + theMaxProfit + " pour "+p_evalBeforeBest);
		return theMaxProfit;
	}
	
	
	
	private boolean[] gen_randomArray(int size) {
		
		Random r =new Random();
		boolean[] arr = new boolean[size];
		
	    for(int i = 0; i < size; i++) {
	    	arr[i]=r.nextBoolean();
	    }
	    
	    return arr;
	    
	}
	
}
