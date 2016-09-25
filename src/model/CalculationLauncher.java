package model;

/**
 * Cette classe permet de lancer une m�thode de calcul � un certain ryhtme
 *  avec tous les param�tres n�cessaires
 *  
 * @author Valentin
 *
 */
public class CalculationLauncher {

	private CalculationMethod cmethod; //M�thode de calcul associ� au launcher
	private int pasEvaluation; // Le pas entre chaque �valuation ( ex : 100)
	private int maxEvaluation; // Le maximum d'�valuation pour chaque ex�cution ( ex : 10000)
	private int nbExecution; //Nombre d'ex�cution de chaque m�thode de calcul ( ex : 10 donc 10 fois des �valuations
	// de 100 � 10000 avec un pas de 100)
	
	
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
	 * M�thode permettant de lancer un type de m�thode de calcul 
	 * en sp�cifiant le fichier .csv de sortie (pas besoin de pr�ciser son extension)
	 * 
	 * @param cm M�thode de calcul � lancer avec son knapsack
	 * @param type Type de m�thode de calcul
	 * @param outputFile Fichier de sortie
	 */
	public void LaunchCalculation(CalculationMethod cm,String type,String outputFile) {
		
		CsvWriter csv = new CsvWriter(outputFile+".csv"); // nom du fichier de sortie avec extension d�j� pr�cis�e
		StringBuilder sb = new StringBuilder(); // Sert � faciliter la concat�nation
		sb.append("NbEvaluations;Fbest\n"); // header du fichier .csv
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
			for (int cntEvaluation=pasEvaluation;cntEvaluation<=maxEvaluation;cntEvaluation+=pasEvaluation) {
				
				double maxFound=0; // le maximum trouv� dans ce nombre d'�valuations pour la m�thode  de calcul
				
				switch(type) {
				
				case "recherche": //m�thode de recherche al�atoire
					maxFound = cm.rechercheAleatoire(cntEvaluation);
					break;
					
				case "marche": // m�thode de marche al�atoire
					maxFound = cm.marcheAleatoire(cntEvaluation);
					break;
					
				case "hillclimber": // m�thode de hillclimber
					maxFound = cm.hillClimber(cntEvaluation);
					break;
					
				case "hillclimberfirst": //m�thode de hillclimberfirst
					maxFound = cm.hillClimberFirst(cntEvaluation);
					break;
					
					default:
						break;
				}
				sb.append(cntEvaluation+";"+maxFound+"\n");
				
			}
			
		}
		
		csv.Write(sb.toString()); //�criture du fichier .csv
		
	}
	
	/**
	 * M�thode permettant de lancer une m�thode de calcul avec un nombre fixe d'�valuations
	 * (ex : 1000)
	 * 
	 * @param cm M�thode de calcul avec knapsack associ�
	 * @param type Type de la m�thode de calcul
	 * @param outputFile Chemin du fichier .csv de sortie
	 * @param fixedEval Nombre d'�valuations fixe
	 */
	public void LaunchCalculationWithFixedEval(CalculationMethod cm,String type,String outputFile,int fixedEval) {
		
		CsvWriter csv = new CsvWriter(outputFile+"_fixed"+fixedEval+".csv"); //nom du fichier .csv sp�cifique � ce cas
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n"); //header du fichier .csv
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
				double maxFound=0; // le maximum trouv� dans ce nombre d'�valuations pour la m�thode  de calcul
				
				switch(type) {
				
				case "recherche": //M�thode de recherche al�atoire
					maxFound = cm.rechercheAleatoire(fixedEval);
					break;
					
				case "marche": //M�thode de marche al�atoire
					maxFound = cm.marcheAleatoire(fixedEval);
					break;
					
				case "hillclimber": //M�thode de hillclimber
					maxFound = cm.hillClimber(fixedEval);
					break;
					
				case "hillclimberfirst": //M�thode de hillclimberfirst
					maxFound = cm.hillClimberFirst(fixedEval);
					break;
					
					default:
						break;
				}
				sb.append(fixedEval+";"+maxFound+"\n");
				
			
			
		}
		
		csv.Write(sb.toString()); //�criture du fichier .csv
		
	}
	
	
}
