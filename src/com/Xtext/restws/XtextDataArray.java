package com.Xtext.restws;

public class XtextDataArray {
	private String texto;
	private XtextData[] filas;
	
	public XtextDataArray(String texto, XtextData ... filas){
		this.texto = texto;
		this.filas = filas;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public XtextData[] getFilas() {
		return filas;
	}
	public void setFilas(XtextData ... filas) {
		this.filas = filas;
	}
	
}
