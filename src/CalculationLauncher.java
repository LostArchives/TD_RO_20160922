public class CalculationLauncher {

	private CalculationMethod cmethod;
	private int nbExecution;
	private int pasEvaluation;
	private int maxEvaluation;
	
	
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
	
	public void LaunchCalculation(CalculationMethod cm,String type,String outputFile) {
		
		CsvWriter csv = new CsvWriter(outputFile+".csv");
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n");
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
			for (int cntEvaluation=pasEvaluation;cntEvaluation<=maxEvaluation;cntEvaluation+=pasEvaluation) {
				
				double maxFound=0;
				
				switch(type) {
				
				case "recherche":
					maxFound = cm.rechercheAleatoire(cntEvaluation);
					break;
					
				case "marche":
					maxFound = cm.marcheAleatoire(cntEvaluation);
					break;
					
				case "hillclimber":
					maxFound = cm.hillClimber(cntEvaluation);
					break;
					
				case "hillclimberfirst":
					maxFound = cm.hillClimberFirst(cntEvaluation);
					break;
					
					default:
						break;
				}
				sb.append(cntEvaluation+";"+maxFound+"\n");
				
			}
			
		}
		
		csv.Write(sb.toString());
		
	}
	
	public void LaunchCalculationWithFixedEval(CalculationMethod cm,String type,String outputFile,int fixedEval) {
		
		CsvWriter csv = new CsvWriter(outputFile+"_fixed"+fixedEval+".csv");
		StringBuilder sb = new StringBuilder();
		sb.append("NbEvaluations;Fbest\n");
		
		for (int cntExecution=0;cntExecution<nbExecution;cntExecution++) {
			
				double maxFound=0;
				
				switch(type) {
				
				case "recherche":
					maxFound = cm.rechercheAleatoire(fixedEval);
					break;
					
				case "marche":
					maxFound = cm.marcheAleatoire(fixedEval);
					break;
					
				case "hillclimber":
					maxFound = cm.hillClimber(fixedEval);
					break;
					
				case "hillclimberfirst":
					maxFound = cm.hillClimberFirst(fixedEval);
					break;
					
					default:
						break;
				}
				sb.append(fixedEval+";"+maxFound+"\n");
				
			
			
		}
		
		csv.Write(sb.toString());
		
	}
	
	
}
