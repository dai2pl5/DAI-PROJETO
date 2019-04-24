package com.boot.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.boot.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="home", uniqueConstraints= {
		@UniqueConstraint(columnNames= {
				"morada"
		})
})
public class Home {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHome;
	
	@NotBlank
	@Size(max=50)
	private String morada;
	
	private int area;
	
	private int ano;
	
	@NotBlank
	@Size(max=2)
	private String topologia;
	
	@ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore()
	private User user;

	public Home() {}
	
	public Home(String morada, int area, int ano, String topologia, User user) {
	        this.morada = morada;
	        this.area = area;
	        this.ano = ano;
	        this.topologia = topologia;
	        this.user = user;
	}
	
	public long getIdHome() {
		return idHome;
	}

	public void setIdHome(long idHome) {
		this.idHome = idHome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public double getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTopologia() {
		return topologia;
	}

	public void setTopologia(String topologia) {
		this.topologia = topologia;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
