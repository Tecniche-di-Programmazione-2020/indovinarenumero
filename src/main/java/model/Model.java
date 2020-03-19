package model;

public class Model {
	private int NMAX ;
	private int TMAX ;
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
	 */
	public String nuovaPartita(int sceltaLivello) {
		String temp = null;
		if(sceltaLivello==1) {NMAX=10;TMAX=4;temp="il livello scelto è facile\n";}
		if(sceltaLivello==2) {NMAX=100;TMAX=7;temp="il livello scelto è medio\n";}
		if(sceltaLivello==3) {NMAX=1000;TMAX=10;temp="il livello scelto è difficile\n";}
		segreto = (int) (Math.random() * NMAX) + 1;
		tentativiFatti = 0;
		inGioco = true;
		
		return temp;
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
				temp += "Valore esatto";
				inGioco = false;
			}
			if (tentativo > segreto)
				temp += "Valore troppo alto";
			if (tentativo < segreto)
				temp += "Valore troppo basso";
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
		int residui;
		residui=TMAX - tentativiFatti;
		return residui;
	}

	public boolean isInGioco() {
		return inGioco;
	}

}
