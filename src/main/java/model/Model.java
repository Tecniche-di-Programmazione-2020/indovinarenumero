package model;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	boolean inGioco = false;

	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	/** il metodo nuovaPartita 
	 * <li>estrae un numero
	 * <li>azzera il numero di tentativi fatti
	 * @param inGioco true
	 * 
	 * 
	 * 
	 */
	public void nuovaPartita() {
		segreto = (int) (Math.random() * NMAX) + 1;
		tentativiFatti = 0;
		inGioco = true;
	}
	/**Il metodo verifica il tentativo fatto dall'utente
	 * 
	 * @param tentativo richiede il valore numerico inserito dall'utente
	 * @return Una stringa per l'output
	 */
	public String nuovoTentativo(int tentativo) {
		String temp = "Numero estratto "+this.getSegreto()+"\n";
		tentativiFatti++;
		temp = "Nuovo tentativo --> NUMERO: " + tentativo + " ";

		if (tentativiFatti <= TMAX && inGioco == true) {

			if (tentativo == segreto) {
				temp += "Valore esatto\n";
				inGioco = false;
			}
			if (tentativo > segreto)
				temp += "Valore troppo alto\n";
			if (tentativo < segreto)
				temp += "Valore troppo basso\n";
		}

		if (tentativiFatti == TMAX || tentativo == segreto) {
			inGioco = false;

			if (tentativo == segreto)
				temp += "\n---Hai vinto---";
			else
				temp += "\n---Hai perso---";

		}

		return temp;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTentativiResidui() {
		return TMAX - tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

}
