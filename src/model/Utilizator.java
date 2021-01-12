package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "utilizator")
public class Utilizator {

	@Id
	@Column(name = "username")
	private StringProperty username;
	
	@Column(name = "parola")
	private StringProperty parola;
	
	@Column(name = "nume")
	private StringProperty nume;
	
	@Column(name = "tip")
	private StringProperty tip;
	
	public Utilizator() {
		this.username = new SimpleStringProperty();
		this.parola = new SimpleStringProperty();
		this.nume = new SimpleStringProperty();
		this.tip = new SimpleStringProperty();
	}
	
	//username_utilizator
	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}
	
	public StringProperty usernameProperty(){
		return username;
	}
	
	//parola_utilizator
	public String getPassword() {
		return parola.get();
	}

	public void setPassword(String parola) {
		this.parola.set(parola);
	}
	
	public StringProperty passwordProperty(){
		return parola;
	}
	
	//nume_utilizator
	public String getNume() {
		return nume.get();
	}

	public void setNume(String nume) {
		this.nume.set(nume);
	}
	
	public StringProperty numeProperty(){
		return nume;
	}
	
	//tip_utilizator
	public String getType() {
		return tip.get();
	}

	public void setType(String tip) {
		this.tip.set(tip);
	}

	public StringProperty tipProperty(){
		return tip;
	}
	
	@Override
	public String toString() {
		return "Utilizator [username=" + username + ", parola=" + parola + ", nume=" + nume + ", tip=" + tip + "]";
	}
}
