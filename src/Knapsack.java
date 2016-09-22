import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Knapsack {

	private String _filePath;	//Chemin du fichier .txt contenant les infos des objets
	private int _nbObjects;		//Nombre d'objets
	private List<Integer> _profits; //Liste des profits pour chaque objet
	private List<Integer> _poids;	//Liste des poids pour chaque objet
	private int _totalWeight;	//Poids total des objets
	private int _capacity;	//Capacité du sac
	private double _beta;	//Plus grand rapport profit/poids
	private double _totalProfit;	//Total des profits (avec pénalité déduite)
	
	public Knapsack(String file) {
		
		_filePath=file;
		_profits = new ArrayList<>();
		_poids = new ArrayList<>();
		_beta=0;
		_totalProfit=0;
		LoadInfo();
		
	}
	
	public String getFileName() {
		return _filePath;
	}

	public void setFileData(String fileData) {
		this._filePath = fileData;
	}

	public int getNbObjects() {
		return _nbObjects;
	}

	public void setNbObjects(int nbObjects) {
		this._nbObjects = nbObjects;
	}

	public List<Integer> get_profits() {
		return _profits;
	}

	public void set_profits(List<Integer> _profits) {
		this._profits = _profits;
	}

	public List<Integer> get_poids() {
		return _poids;
	}

	public void set_poids(List<Integer> _poids) {
		this._poids = _poids;
	}

	public int getTotalWeight() {
		return _totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this._totalWeight = totalWeight;
	}

	public int getCapacity() {
		return _capacity;
	}

	public void setCapacity(int capacity) {
		this._capacity = capacity;
	}

	public double getSumProfit() {
		return _totalProfit;
	}

	public void setSumProfit(double sumProfit) {
		this._totalProfit = sumProfit;
	}

	///Méthode permettant d'extraire les infos du fichier .txt
	public void LoadInfo() {
		
		int _compteur=0; //Compteur pour repérer les lignes
		
		try (BufferedReader br = new BufferedReader(new FileReader(_filePath)))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				_compteur++;
				
				switch(_compteur) {
				
				case 1: //1ere ligne
					
					_nbObjects=Integer.parseInt(sCurrentLine);
					break;
					
				case 2: // 2e ligne
					
					String[] lesprofits=sCurrentLine.split(" ");
					
					for (int i=0;i<lesprofits.length;i++) {
						_profits.add(Integer.parseInt(lesprofits[i]));
					}
					break;
					
				case 3:	//3e ligne
					
					String[] lespoids=sCurrentLine.split(" ");
					for (int i=0;i<lespoids.length;i++) {
						_poids.add(Integer.parseInt(lespoids[i]));
					}
					break;
					
				case 4 :	//4e ligne
					
					_capacity=Integer.parseInt(sCurrentLine);
					break;
				}
				
			}
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	///Méthode permettant d'évaluer le profit total
	public double evaluate(int[] presence) {
		
		initVar(); //Réinitialisation des variables
		
		for (int cnt=0;cnt<_nbObjects;cnt++) {
			if (presence[cnt]==1) {
				_totalWeight+=_poids.get(cnt);
				_totalProfit+=_profits.get(cnt);
			}
			
			double rapport=Double.parseDouble(_profits.get(cnt).toString())/Double.parseDouble(_poids.get(cnt).toString());
			
			if (rapport>_beta) {
				_beta=rapport;
			}
					
		}
		
		if (_totalWeight>_capacity) {
			_totalProfit-=_beta*(_totalWeight-_capacity);
		}
		
		return _totalProfit;
		
	}
	
	///Méthode permettant d'afficher certains attributs de l'objet
	public void print() {
		
		System.out.println("Nombre d'objets: "+_nbObjects);
		System.out.println("Poids total: "+_totalWeight);
		System.out.println("Profit total: "+_totalProfit);
		System.out.println("Beta: "+_beta);
			
	}
	
	///Méthode permettant de réinitialiser certains attributs (utiles pour calculs)
	private void initVar() {
		_totalWeight=0;
		_totalProfit=0;
		_beta = 0;
	}
	
	
		
}
