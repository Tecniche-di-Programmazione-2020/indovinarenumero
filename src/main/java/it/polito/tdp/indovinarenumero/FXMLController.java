package it.polito.tdp.indovinarenumero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private final int NMAX=100;
	private final int TMAX=8;
	private int segreto;
	private int tentativiFatti;
	boolean inGioco=false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField Rimasti;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doNuova(ActionEvent event) {
    	
    	//inizio una nuova partita - logica
    	this.segreto=(int) (Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	btnProva.setDisable(false);
    	
    	//inizio una nuova partita - grafica
    	
    	txtRisultato.clear();
    	txtRisultato.appendText("Partita iniziata\n");
    	txtRisultato.appendText("Numero estratto "+this.segreto+"\n");
    	txtTentativi.clear();
    	Rimasti.setText(Integer.toString(TMAX));

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//leggo input
    	String ts=txtTentativi.getText();
    	txtTentativi.clear();
    	int tentativo=0;
    	try{
    		tentativo=Integer.parseInt(ts);
    		tentativiFatti++;
    		}catch(Exception e) {txtRisultato.appendText("Inserisci valore numerico\n");tentativiFatti--;}
    	tentativiFatti++;
    	Rimasti.clear();
    	Rimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	
    	txtRisultato.appendText("Nuovo tentativo --> NUMERO: "+tentativo+" ");
    	
    	if(tentativiFatti<=TMAX&&inGioco==true) {
    	
    	if(tentativo==segreto) {txtRisultato.appendText("Valore esatto\n");inGioco=false;btnProva.setDisable(true);}
    	if(tentativo>segreto)txtRisultato.appendText("Valore troppo alto\n");
    	if(tentativo<segreto)txtRisultato.appendText("Valore troppo basso\n");
    	}
    	
    	if(tentativiFatti==TMAX||inGioco==false) {
    		inGioco=false;
    		btnProva.setDisable(true);
    		if(tentativo==segreto)txtRisultato.appendText("\n---Hai vinto---");
    		else txtRisultato.appendText("\n---Hai perso---");
    		
    		
    	}
    	

    }

    @FXML
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Rimasti != null : "fx:id=\"Rimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


