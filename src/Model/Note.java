package Model;

public class Note {

	private int Note;
	private Personne Person;
	private Photo Pho;
	
	public Note(Personne P,int note, Photo Ph) {
		super();
		this.Note = note;
		this.Person = P;
		this.Pho = Ph;
	}

	public int getNote() {
		return Note;
	}

	public void setNote(int note) {
		Note = note;
	}

	public Personne getPerson() {
		return Person;
	}

	public void setPerson(Personne person) {
		Person = person;
	}

	public Photo getPho() {
		return Pho;
	}

	public void setPho(Photo pho) {
		Pho = pho;
	}

	@Override
	public String toString() {
		return this.Note + ";" + Person.toString() + ";" + Pho.toString() + "\n";
	}
	
	
}
