package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Modele;
import Model.Note;
import Model.Personne;
import Model.Photo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

	private Modele modele;
	private File file;
	private ObservableList<String> ObList;
	private HashMap<String, String> hmap;
	private String Nomsaisi;
	private String Loginsaisi;
	private String ImageUrlSelect;
	
	@FXML
	private ImageView Album;
	
	@FXML
	private ListView <String> ListImage;
	
	@FXML
	private TextField NomInput;
	
	@FXML
	private TextField LoginInput;
	
	@FXML
	private Spinner NoteInput;
	
	@FXML
	private Button NoteButton;
	
	@FXML
	private Button LoginButton;
	
	@FXML
	private Label LoginError;
	
	@FXML
	private Label NoteError;
	
	// appel modele
	public void init() throws IOException {
		this.modele = new Modele();
		this.file = this.modele.getFile();
		this.ObList = this.modele.getItems();
		this.hmap = this.modele.getHmap();
	}
	
	// initialiser le listView et le Spinner
	public void initialize() throws IOException {
		init();
		ListImage.setItems(ObList);
		
		ListImage.getSelectionModel().selectedItemProperty().addListener (new ChangeListener<String> () { 
			
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	ImageUrlSelect = newValue;
				Image image = new Image(newValue);
				Album.setImage(image);
				NoteInput.getValueFactory().setValue(0);
				try {
					existNote(newValue);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			    });
		ListImage.setDisable(true);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 10);
		NoteInput.setValueFactory(valueFactory);
		NoteInput.setDisable(true);
	}
	
	// Bouton Validation login
	public void validlogin() {
		Nomsaisi = NomInput.getText();
		Loginsaisi = LoginInput.getText();
		
		try{
			String Access = this.modele.existPersonFile(Nomsaisi,Loginsaisi,hmap);
			LoginError.setText("");
			ListImage.setDisable(false);
			NoteInput.setDisable(false);
		}catch(Exception e) {
			LoginError.setText("Erreur de saisie");
		}
	}
	
	// Note existante ou pas
	public void existNote(String url) throws IOException {
		try{
			String note = this.modele.existnoteFile(Nomsaisi, url);
			NoteError.setText("");
			NoteInput.getValueFactory().setValue(Integer.parseInt(note));
		}catch(NumberFormatException e) {
			NoteError.setText("Aucune note pour cette image");
		}
	}
	
	// Bouton Ajout d'une note
	public void addNote() throws IOException {
		int Notesaisi = (Integer) NoteInput.getValue();
		Personne p = new Personne(Nomsaisi,Loginsaisi);
		String NomPhoto = ImageUrlSelect.substring(6, ImageUrlSelect.length()-4);
		Photo ph = new Photo(NomPhoto,ImageUrlSelect);
		Note NvNote = new Note(p,Notesaisi,ph); 
		this.modele.addNotefile(NvNote);
		init();
	}
}
