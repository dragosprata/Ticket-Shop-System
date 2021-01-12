package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "bilet")
public class Bilet {

	@Column(name = "spectacol")
	private StringProperty spectacol;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numar")
	private IntegerProperty numar;
	
	@Column(name = "rand")
	private IntegerProperty rand;
	
	public Bilet() {
		this.spectacol = new SimpleStringProperty();
		this.numar = new SimpleIntegerProperty();
		this.rand = new SimpleIntegerProperty();
	}
	
	//denumire_spectacol
	public String getDenumireSpectacol() {
		return spectacol.get();
	}

	public void setDenumireSpectacol(String spectacol) {
		this.spectacol.set(spectacol);
	}
	
	public StringProperty spectacolDenumireProperty(){
		return spectacol;
	}
	
	//rand
	public int getRand() {
		return rand.get();
	}

	public void setRand(int rand) {
		this.rand.set(rand);
	}
	
	public IntegerProperty randProperty(){
		return rand;
	}
	//numar
	public int getNumarBilet() {
		return numar.get();
	}

	public void setNumarBilet(int numar) {
		this.numar.set(numar);
	}
	
	public IntegerProperty numarBiletProperty(){
		return numar;
	}

	@Override
	public String toString() {
		return "Bilet [numar=" + numar + ", rand=" + rand + ", spectacol=" + spectacol + "]";
	}
}
