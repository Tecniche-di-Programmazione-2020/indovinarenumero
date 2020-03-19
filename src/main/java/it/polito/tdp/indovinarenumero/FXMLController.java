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
import model.Model;

public class FXMLController {
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
	private TextField txtTentativi;

	@FXML
	private Button btnProva;

	@FXML
	private TextArea txtRisultato;

	@FXML
	void doNuova(ActionEvent event) {

		// acquisisco i parametri
		int sceltaLivello = 0;

		if (livello.getSelectedToggle().equals(livello1))
			sceltaLivello = 1;
		if (livello.getSelectedToggle().equals(livello2))
			sceltaLivello = 2;
		if (livello.getSelectedToggle().equals(livello3))
			sceltaLivello = 3;

		// inizio una nuova partita - logica

		String testo = model.nuovaPartita(sceltaLivello);
		
	

		// inizio una nuova partita - grafica
		btnProva.setDisable(false);
		//radioLivello.setDisable(true);

		txtRisultato.clear();
		txtRisultato.appendText("Partita iniziata\n");
		Rimasti.setText(Integer.toString(model.getTentativiResidui()));
		progressBar.setProgress((double) model.getTentativiResidui() / model.getTMAX());
		
		txtRisultato.appendText(testo);

		txtRisultato.appendText("Numero estratto " + model.getSegreto() + "\n");

	}

	@FXML
	void doTentativo(ActionEvent event) {

		// leggo input

		String ts = txtTentativi.getText();
		txtTentativi.clear();
		int tentativo = 0;
		// Provo la conversione ad intero
		try {
			tentativo = Integer.parseInt(ts);

		} catch (Exception e) {
			txtRisultato.appendText("Inserisci valore numerico\n");
			return;
		}

		// Richiamo il metodo con la logica
		txtRisultato.appendText(model.nuovoTentativo(tentativo) + "\n");

		// Gestisco la grafica
		if (model.isInGioco() == false)
			btnProva.setDisable(true);
		Rimasti.setText(Integer.toString(model.getTentativiResidui()));
		progressBar.setProgress((double) model.getTentativiResidui() / model.getTMAX());

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
}
