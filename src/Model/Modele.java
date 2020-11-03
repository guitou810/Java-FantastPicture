package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Modele {
	
	private ArrayList<Note> ListNote;
	private File file = new File ("Notes.txt");
	private HashMap<String, String> hmap = new HashMap<String, String>();
	
	public Modele() {
		super();
		/*
		 * ListNote = new ArrayList<Note>(); Note nt1 = new Note(new
		 * Personne("Guillaume","fff"),14,new Photo("Arbre","Image/Arbre.jpg")); Note
		 * nt2 = new Note(new Personne("Guillaume","fff"),10,new
		 * Photo("Soleil","Image/Soleil.jpg")); Note nt3 = new Note(new
		 * Personne("Jean","ddd"),11,new Photo("Volcan","Image/Volcan.jpg")); Note nt4 =
		 * new Note (new Personne("Jean","ddd"),16,new Photo("Yeux","Image/Yeux.png"));
		 * ListNote.add(nt1); ListNote.add(nt2); ListNote.add(nt3); ListNote.add(nt4);
		 */
		hmap.put("Guillaume", "fff");
		hmap.put("Jean", "ddd");
	}	

	public ArrayList<Note> getListNote() {
		return ListNote;
	}

	public void setListNote(ArrayList<Note> listNote) {
		ListNote = listNote;
	}
	
	public HashMap<String, String> getHmap() {
		return hmap;
	}

	public void setHmap(HashMap<String, String> hmap) {
		this.hmap = hmap;
	}

	// initialiser le fichier txt si il est vide
	public void writefile() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		if (file.length() > 1) {
			
		}else {
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Note nt : ListNote) {
				bw.write(nt.toString());
			
			}
			bw.close();
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	// item pour la listeView
	public ObservableList<String> getItems() throws IOException {
		ObservableList<String> items = FXCollections.observableArrayList();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			String[] tab = line.split(";");
			if (!items.contains(tab[3])) {
				items.add(tab[3]);
			}
		}
		
		return items;
	}
	
	// ajouter une note au fichier
	public void addNotefile(Note nt) throws IOException {
		Boolean drap = true;
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		Path fileName = Path.of("Notes.txt");
		String oldContent = Files.readString(fileName);
		while ((line = br.readLine()) != null) {
			String[] tab = line.split(";");
			if (tab[1].equals(nt.getPerson().getPNom()) && tab[2].equals(nt.getPho().getImageNom())) {
				String newContent = oldContent.replaceAll(line, String.valueOf(nt.getNote())+";"+tab[1]+";"+tab[2]+";"+tab[3]);
				FileWriter writer = new FileWriter(file);
				writer.write(newContent);
				writer.close();
				drap = false;
				break;
			}else {}
		}	
		if (drap) {
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(nt.toString());
			bw.close();
		}
		br.close();
	}
	
	// Note existant ou pas dans le fichier
	public String existnoteFile(String nom, String url) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] tab = line.split(";");
			if (nom.equals(tab[1]) && url.equals(tab[3])) {
				return tab[0];
			}
		}
		return null;

	}
	
	// Personne existant dans la Hasmap (authentification)
	public String existPersonFile(String nom, String login, HashMap<String, String> hmap) throws Exception {
			System.out.println(hmap.get(nom));
			System.out.println(login);
			if(hmap.get(nom).equals(login)) {
				System.out.println("true");
				return "accès autorisé";
			}else {
				throw new Exception();
			}

	}
	
}

