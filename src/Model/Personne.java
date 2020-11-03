package Model;

public class Personne {

	private String PNom;
	private String Plogin;
	
	
	public Personne (String nom, String login) {
		super();
		this.PNom = nom;
		this.Plogin = login;
	}

	public String getPNom() {
		return PNom;
	}

	public void setPNom(String pNom) {
		PNom = pNom;
	}

	public String getPlogin() {
		return Plogin;
	}

	public void setPlogin(String plogin) {
		Plogin = plogin;
	}

	@Override
	public String toString() {
		return this.PNom;
	}
	
	
}
	
