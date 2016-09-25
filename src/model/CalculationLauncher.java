package model;

/**
 * Cette classe permet de lancer une méthode de calcul à un certain ryhtme
 *  avec tous les paramètres nécessaires
 *  
 * @author Valentin
 *
 */
public class CalculationLauncher {

	private CalculationMethod cmethod; //Méthode de calcul associé au launcher
	private int pasEvaluation; // Le pas entre chaque évaluation ( ex : 100)
	private int maxEvaluation; // Le maximum d'évaluation pour chaque exécution ( ex : 10000)
	private int nbExecution; //Nombre d'exécution de chaque méthode de calcul ( ex : 10 donc 10 fois des évaluations
	// de 100 à 10000 avec un pas de 100)
	
	
	public CalculationMethod getCmethod() {
		return cmethod;
	}


	public void setCmethod(CalculationMethod cmethod) {
		this.cmethod = cmethod;
	}


	public int getNbExecution() {
		return nbExecution;
	}


	public void setNbExecution(int nbExecution) {
		this.nbExecution = nbExecution;
	}


	public int getPasEvaluation() {
		return pasEvaluation;
	}


	public void setPasEvaluation(int pasEvaluation) {
		this.pasEvaluation = pasEvaluation;
	}


	public int getMaxEvaluation() {
		return maxEvaluation;
	}


	public void setMaxEvaluation(int maxEvaluation) {
		this.maxEvaluation = maxEvaluation;
	}


	public CalculationLauncher(CalculationMethod p_cm,int p_nbExecution,int p_pasEvaluation,int p_maxevaluation) {
		cmethod=p_cm;
		nbExecution = p_nbExecution;
		pasEvaluation = p_pasEvaluation;
		maxEvaluation = p_maxevaluation;
	}
	
	/**
	 * Méthode permettant de lancer un type de méthode de calcul 
	 * en spécifiant le fichier .csv de sortie (pas besoin de préciser son extension)
	 * 
	 * @param cm Méthode de calcul à lancer avec son knapsack
	 * @param type Type de méthode de calcul
	 * @param outputFile Fichier de sortie
	 */
	public void LaunchCalculation(CalculationMethod cm,String type,String outputFile) {
		
		CsvWriter csv = new CsvWriter(outputFile+".csv"); // nom du fichier de sortie avec extension déjà précisée
		StringBuilder sb = new StringBuilder(); // Sert à faciliter la concaténation
		sb.append("NbEvaluations;Fbest\n"); // header du fichier .csv
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
			for (int cntEvaluation=pasEvaluation;cntEvaluation<=maxEvaluation;cntEvaluation+=pasEvaluation) {
				
				double maxFound=0; // le maximum trouvé dans ce nombre d'évaluations pour la méthode  de calcul
				
				switch(type) {
				
				case "recherche": //méthode de recherche aléatoire
					maxFound = cm.rechercheAleatoire(cntEvaluation);
					break;
					
				case "marche": // méthode de marche aléatoire
					maxFound = cm.marcheAleatoire(cntEvaluation);
					break;
					
				case "hillclimber": // méthode de hillclimber
					maxFound = cm.hillClimber(cntEvaluation);
					break;
					
				case "hillclimberfirst": //méthode de hillclimberfirst
					maxFound = cm.hillClimberFirst(cntEvaluation);
					break;
					
					default:
						break;
				}
				sb.append(cntEvaluation+";"+maxFound+"\n");
				
			}
			
		}
		
		csv.Write(sb.toString()); //écriture du fichier .csv
		
	}
	
	/**
	 * Méthode permettant de lancer une méthode de calcul avec un nombre fixe d'évaluations
	 * (ex : 1000)
	 * 
	 * @param cm Méthode de calcul avec knapsack associé
	 * @param type Type de la méthode de calcul
	 * @param outputFile Chemin du fichier .csv de sortie
	 * @param fixedEval Nombre d'évaluations fixe
	 */
	public void LaunchCalculationWithFixedEval(CalculationMethod cm,String type,String outputFile,int fixedEval) {
		
		CsvWriter csv = new CsvWriter(outputFile+"_fixed"+fixedEval+".csv"); //nom du fichier .csv spécifique à ce cas
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n"); //header du fichier .csv
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
				double maxFound=0; // le maximum trouvé dans ce nombre d'évaluations pour la méthode  de calcul
				
				switch(type) {
				
				case "recherche": //Méthode de recherche aléatoire
					maxFound = cm.rechercheAleatoire(fixedEval);
					break;
					
				case "marche": //Méthode de marche aléatoire
					maxFound = cm.marcheAleatoire(fixedEval);
					break;
					
				case "hillclimber": //Méthode de hillclimber
					maxFound = cm.hillClimber(fixedEval);
					break;
					
				case "hillclimberfirst": //Méthode de hillclimberfirst
					maxFound = cm.hillClimberFirst(fixedEval);
					break;
					
					default:
						break;
				}
				sb.append(fixedEval+";"+maxFound+"\n");
				
			
			
		}
		
		csv.Write(sb.toString()); //écriture du fichier .csv
		
	}
	
	
}
