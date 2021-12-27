package com.iit.imprimerie.entities_response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EnseignantResponse implements Serializable {
private int id;
private String cin;
private String prenom;
private String nom;
private String adress;
private String email;
private String phone;
private String login;
private String password;
@JsonIgnore
private List<MatiereResponse> lm;
@JsonIgnore
private List<DemandeTirageResponse> ld;
@JsonIgnore
private List<DocumentResponse> ldoc;
public EnseignantResponse(int id, String cin, String prenom, String nom, String adress, String email, String phone,
		String login, String password) {
	super();
	this.id = id;
	this.cin = cin;
	this.prenom = prenom;
	this.nom = nom;
	this.adress = adress;
	this.email = email;
	this.phone = phone;
	this.login = login;
	this.password = password;
}



}
