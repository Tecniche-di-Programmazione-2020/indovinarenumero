package model;

import java.util.LinkedList;

public class Model {
	private int NMAX ;
	private int TMAX ;
	private int segreto;
	private int tentativiFatti;
	boolean inGioco = false;
	LinkedList<Integer>storico=new LinkedList<Integer>();

	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
		
	}
	
	
	public GameStatus nuovaPartita(GameStatus stato) {
		this.storico.clear();
		tentativiFatti = 0;
		inGioco = true;
		if(stato.getLivello()==1) {NMAX=10;TMAX=4;}
		if(stato.getLivello()==2) {NMAX=100;TMAX=7;}
		if(stato.getLivello()==3) {NMAX=1000;TMAX=10;}
		segreto = (int) (Math.random() * NMAX) + 1;
		stato.nuovoGiocoRX(inGioco, this.getTentativiResidui(), NMAX/2, NMAX/2);
		return stato;
	}
	
	public GameStatus nuovoTentativo(GameStatus stato) {
		int esitoTentativo=-3;
		if(storico.contains(stato.getTentativoFatto())) {esitoTentativo=(5);}
		else {
		tentativiFatti++;
		
		
		storico.add(stato.getTentativoFatto());
		
		if(storico.contains(stato.getTentativoFatto())) {esitoTentativo=(5);}

		if (tentativiFatti <= TMAX && inGioco == true) {
			
			if (stato.getTentativoFatto() == segreto) {
				esitoTentativo=(0);
				inGioco = false;
			}
			if (stato.getTentativoFatto() > segreto)
				esitoTentativo=(1);
				
			if (stato.getTentativoFatto() < segreto)
				esitoTentativo=(-1);
				
		}
		
		if(esitoTentativo!=0) {
		
			
			
		}

		if (tentativiFatti == TMAX ) inGioco = false;
		}	
		stato.risultatoTentativo(inGioco, getTentativiResidui(), esitoTentativo,0,0);
		return stato;
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
