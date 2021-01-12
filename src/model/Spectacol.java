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
@Table(name = "spectacol")
public class Spectacol {
	
	@Id
	@Column(name = "denumire")
	private StringProperty denumire;
	
	@Column(name = "gen")
	private StringProperty gen;
	
	@Column(name = "regie")
	private StringProperty regie;
	
	@Column(name = "distributie")
	private StringProperty distributie;
	
	@Column(name = "premiera")
	private StringProperty premiera;
	
	@Column(name = "nr_bilete")
	private IntegerProperty nr_bilete;
	
	
	public Spectacol() {
		this.denumire = new SimpleStringProperty();
		this.gen = new SimpleStringProperty();
		this.regie = new SimpleStringProperty();
		this.distributie = new SimpleStringProperty();
		this.premiera = new SimpleStringProperty();
		this.nr_bilete = new SimpleIntegerProperty();
	}
	
	//denumire_spectacol
	public String getDenumire() {
		return denumire.get();
	}

	public void setDenumire(String denumire) {
		this.denumire.set(denumire);
	}
	
	public StringProperty spectacolDenumireProperty(){
		return denumire;
	}
	
	//gen_spectacol
	public String getGen() {
		return gen.get();
	}

	public void setGen(String gen) {
		this.gen.set(gen);
	}
	
	public StringProperty genProperty(){
		return gen;
	}
	
	//regie_spectacol
	public String getRegie() {
		return regie.get();
	}

	public void setRegie(String regie) {
		this.regie.set(regie);
	}
	
	public StringProperty regieProperty(){
		return regie;
	}
	
	//distributie_spectacol
	public String getDistributie() {
		return distributie.get();
	}

	public void setDistributie(String distributie) {
		this.distributie.set(distributie);
	}
	
	public StringProperty distributieProperty(){
		return distributie;
	}
	
	//premiera_spectacol
	public String getPremiera() {
		return premiera.get();
	}

	public void setPremiera(String premiera) {
		this.premiera.set(premiera);
	}
	
	public StringProperty premieraProperty(){
		return premiera;
	}
	
	//nr_bilete_spectacol
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
		return "Spectacol [denumire=" + denumire + ", gen=" + gen + ", regie=" + regie + ", distributie=" + distributie
				+ ", premiera=" + premiera + ", nr_bilete=" + nr_bilete + "]";
	}
}
