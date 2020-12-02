package fr.applications.appli3.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Repas implements Serializable {

	private static final long serialVersionUID = 1L;

	private int identity;
	private LocalDate date;
	private LocalTime hour;
	private List<Aliment> alimentsList;
	
	public int getId() {
		return identity;
	}
	public void setId(int identity) {
		this.identity = identity;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHour() {
		return hour;
	}
	public void setHeure(LocalTime hour) {
		this.hour = hour;
	}
	public List<Aliment> getAlimentsList() {
		return alimentsList;
	}
	public void setListeAliments(List<Aliment> alimentsList) {
		this.alimentsList = alimentsList;
	}
	
	public Repas() {
		this.alimentsList=new ArrayList<>();
	}
	public Repas(LocalDate date, LocalTime hour, List<Aliment> alimentsList) {
		super();
		this.date = date;
		this.hour = hour;
		this.alimentsList = alimentsList;
	}
	public Repas(int identity, LocalDate date, LocalTime hour, List<Aliment> alimentsList) {
		this(date, hour, alimentsList);
		this.identity = identity;
	}
	public Repas(int identity, LocalDate date, LocalTime hour) {
		this(identity, date, hour, new ArrayList<>());
	}
	@Override
	public String toString() {
		return "Repas [id=" + identity + ", date=" + date + ", heure=" + hour + ", listeAliments="
				+ alimentsList + "]";
	}
}