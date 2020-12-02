package fr.applications.appli3.bo;

import java.io.Serializable;

public class Aliment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int identity;
	private String name;
	
	public int getId() {
		return identity;
	}
	public void setId(int identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Aliment() {
	}
	
	public Aliment(String name) {
		super();
		this.name = name;
	}
	public Aliment(int identity, String name) {
		this(name);
		this.identity = identity;
	}
	@Override
	public String toString() {
		return "Aliment [id=" + identity + ", nom=" + name + "]";
	}
	
	
}

