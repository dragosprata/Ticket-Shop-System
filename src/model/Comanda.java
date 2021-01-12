package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "comanda")
public class Comanda {
	
	@Id
	@Column(name = "cumparator")
	private StringProperty cumparator;
	
	@Column(name = "spectacol")
	private StringProperty spectacol;
	
	@Column(name = "nr_bilete")
	private IntegerProperty nr_bilete;
	
	public Comanda(){
		this.cumparator = new SimpleStringProperty();
		this.spectacol = new SimpleStringProperty();
		this.nr_bilete = new SimpleIntegerProperty();
	}
	
	//cumparator
	public String getCumparator() {
		return cumparator.get();
	}

	public void setCumparator(String cumparator) {
		this.cumparator.set(cumparator);
	}
	
	public StringProperty cumparatorProperty(){
		return cumparator;
	}
	
	//spectacol
	public String getSpectacol() {
		return spectacol.get();
	}

	public void setSpectacol(String spectacol) {
		this.spectacol.set(spectacol);
	}
	
	public StringProperty spectacolProperty(){
		return spectacol;
	}
	
	//nr_bilete
	public int getNrBilete() {
		return nr_bilete.get();
	}

	public void setNrBilete(int nr_bilete) {
		this.nr_bilete.set(nr_bilete);
	}
	
	public IntegerProperty nrBileteProperty(){
		return nr_bilete;
	}

	@Override
	public String toString() {
		return "Comanda [cumparator=" + cumparator + ", spectacol=" + spectacol + ", nr_bilete=" + nr_bilete + "]";
	}
}
