package com.pibic.controller;

public class Link {
	private int ID;
	private double peso;
	
	public Link(int id){
		this.ID = id;
		this.peso = 1.0;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public void incPeso() {
		this.peso++;
	}
	

}
