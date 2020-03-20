package it.polito.tdp.indovinarenumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import model.GameStatus;
import model.Model;

public class FXMLController {
	GameStatus stato= new GameStatus();
	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnNuova;

	@FXML
	private TextField Rimasti;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private HBox radioLivello;

	@FXML
	private ToggleGroup livello;

	@FXML
	private RadioButton livello1;

	@FXML
	private RadioButton livello2;

	@FXML
	private RadioButton livello3;

	@FXML
	private RadioButton radioAutonomo;

	@FXML
	private ToggleGroup modalita;

	@FXML
	private RadioButton radioAssistito;
	
	@FXML
    private HBox assistenza;

	@FXML
	private TextField suggerimento1;

	@FXML
	private TextField suggerimento2;

	@FXML
    private HBox hboxinserimenti;
	
	@FXML
	private TextField txtTentativi;

	@FXML
	private Button btnProva;

	@FXML
	private TextArea txtRisultato;

	@FXML
	void doNuova(ActionEvent event) {
		//GameStatus stato= new GameStatus();
		// acquisisco i parametri
		int sceltaLivello = 0;
		if (livello.getSelectedToggle().equals(livello1))			sceltaLivello = 1;
		if (livello.getSelectedToggle().equals(livello2))			sceltaLivello = 2;
		if (livello.getSelectedToggle().equals(livello3))			sceltaLivello = 3;
		int sceltaModalita=0;
		if(modalita.getSelectedToggle().equals(radioAutonomo))		sceltaModalita=1;
		if(modalita.getSelectedToggle().equals(radioAssistito))		sceltaModalita=2;
		
		// inizio una nuova partita - logica
		stato.nuovoGiocoTX(sceltaLivello, sceltaModalita);
		stato = model.nuovaPartita(stato);

		// inizio una nuova partita - grafica
		this.aggiornaGrafica(stato);

	}

	@FXML
	void doTentativo(ActionEvent event) {
		int tentativo = 0;
		// leggo input

		String ts = txtTentativi.getText();
		txtTentativi.clear();
		
		
		// Provo la conversione ad intero
		try {
			tentativo = Integer.parseInt(ts);
			
		} catch (Exception e) {
			txtRisultato.appendText("Inserisci valore numerico\n");
			return;
		}
		
		
		
		// Richiamo il metodo con la logica
		stato.inviaTentativo(tentativo);
		stato=model.nuovoTentativo(stato);
		txtRisultato.appendText("Nuovo tentativo --> NUMERO: " + stato.getTentativoFatto() + " ");
		switch(stato.getEsitoTentativo()) {
		case 0: txtRisultato.appendText("Valore esatto\n"); break;
		case 1: txtRisultato.appendText("Valore troppo alto\n"); break;
		case -1: txtRisultato.appendText("Valore troppo basso\n"); break;
		case 5: txtRisultato.appendText("Valore gia inserito\n"); break;
		default: txtRisultato.appendText("ERRORE SISTEMA\n");}
		
		// Gestisco la grafica
		this.aggiornaGrafica(stato);
		
		}
		
		

		
			
	

	

	@FXML
	void initialize() {
		assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
		assert Rimasti != null : "fx:id=\"Rimasti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void aggiornaGrafica(GameStatus stato) {
		
		progressBar.setProgress((double) stato.getTentativiResidui() / stato.getTentativiTotali());
		Rimasti.setText(Integer.toString(model.getTentativiResidui()));
		
		if(stato.isInGioco()==true) {
			hboxinserimenti.setDisable(false);
			//btnProva.setDisable(false);
			suggerimento1.setText(Integer.toString(stato.getSuggerimento1()));
			suggerimento2.setText(Integer.toString(stato.getSuggerimento2()));
			
			
			//solo all'avvio della partita
			if(stato.getTentativoFatto()==-1) {
				txtRisultato.clear();
				txtRisultato.appendText("Partita iniziata\n");
				//stampo il livello scelto
				if(stato.getLivello()==1) {txtRisultato.appendText("il livello scelto è facile\n");}
				if(stato.getLivello()==2) {txtRisultato.appendText("il livello scelto è medio\n");}
				if(stato.getLivello()==3) {txtRisultato.appendText("il livello scelto è difficile\n");}
				if(stato.getModalita()==1) {assistenza.setVisible(false);}
				if(stato.getModalita()==2) {assistenza.setVisible(true);}
				}
			
		}
		if(stato.isInGioco()==false) {
			hboxinserimenti.setDisable(true);
			//btnProva.setDisable(true);
			
			
			
			if(stato.getEsitoTentativo()==0) {txtRisultato.appendText("\n---Hai vinto---");}
			else txtRisultato.appendText("\n---Hai perso---");
			}
			
		}
		
		
		
	
}
