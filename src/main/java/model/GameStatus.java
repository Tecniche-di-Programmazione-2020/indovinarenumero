package model;



public class GameStatus {
	
	boolean inGioco;
	int tentativoFatto;
	int esitoTentativo;
	int tentativiTotali;
	int tentativiResidui;
	int livello;
	int modalita;
	int suggerimento1;
	int suggerimento2;
	

	public void nuovoGiocoTX(int livello,	int modalita) {
		this.tentativoFatto=-1;
		this.livello = livello;
		this.modalita = modalita;
		
	}
	
	public void nuovoGiocoRX(boolean inGioco,int tentativiResidui, int suggerimento1, int suggerimento2) {
		this.tentativiTotali=tentativiResidui;
		this.inGioco = inGioco;
		this.tentativiResidui = tentativiResidui;
		
		this.suggerimento1 = suggerimento1;
		this.suggerimento2 = suggerimento2;
		
	}
	
	public void inviaTentativo(int tentativoFatto) {
		
		this.tentativoFatto = tentativoFatto;
		
	}
	
	public void risultatoTentativo(boolean inGioco,int tentativiResidui,int esitoTentativo,int suggerimento1, int suggerimento2) {
		
		this.inGioco = inGioco;
		this.esitoTentativo = esitoTentativo;
		this.tentativiResidui = tentativiResidui;
		this.suggerimento1 = suggerimento1;
		this.suggerimento2 = suggerimento2;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTentativoFatto() {
		return tentativoFatto;
	}

	public int getEsitoTentativo() {
		return esitoTentativo;
	}

	public int getTentativiResidui() {
		return tentativiResidui;
	}

	public int getLivello() {
		return livello;
	}

	public int getModalita() {
		return modalita;
	}

	public int getSuggerimento1() {
		return suggerimento1;
	}

	public int getSuggerimento2() {
		return suggerimento2;
	}

	public int getTentativiTotali() {
		return tentativiTotali;
	}

	public void setEsitoTentativo(int esitoTentativo) {
		this.esitoTentativo = esitoTentativo;
	}

	
	
	
	
	
}
