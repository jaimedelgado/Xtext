package com.Xtext.restws;

public class XtextData {
	private String piezas;
	private String acciones;
	private double id;
	
	public XtextData(){
	}
	
	public XtextData(String piezas, String acciones, double id){
		this.piezas = piezas;
		this.acciones = acciones;
		this.id = id;
	}
	
	public String getPiezas(){
		return this.piezas;
	} 
	
	public String getAcciones(){
		return this.acciones;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}
}
