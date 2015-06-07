/**
 * 
 */
package com.Xtext.restws;

import java.util.ArrayList;

import piezas.InformacionPieza;
import piezas.Pieza;

/**
 * @authors Jaime Delgado Linares y Juan Luis García Flores
 *
 */
public class Ejecucion {
	private String[] piezas;
	private String[] acciones;
	private ArrayList<Pieza> listaPiezas;
	private ArrayList<InformacionPieza> infoPiezas;
	private String textoSubrayado;
	private String textoMorfo;
	private String textoAntonimos;
	private String textoSinonimos;
	private String textoTraducciones;
	private String textoDefiniciones;
	private String op;
	
	public Ejecucion(String[] piezas, String[] acciones, ArrayList<Pieza> listaPiezas, ArrayList<InformacionPieza> infoPiezas, String textoSubrayado, String textoMorfo, String textoAntonimos, String textoSinonimos, String textoDefiniciones, String textoTraducciones, String op){
		this.piezas=piezas; 
		this.acciones=acciones; 
		this.listaPiezas=listaPiezas; 
		this.infoPiezas=infoPiezas; 
		this.textoSubrayado=textoSubrayado; 
		this.textoMorfo=textoMorfo; 
		this.setOp(op); 
		this.textoAntonimos=textoAntonimos; 
		this.setTextoDefiniciones(textoDefiniciones); 
		this.textoSinonimos=textoSinonimos; 
		this.textoTraducciones=textoTraducciones;
	}
	public String[] getPiezas() {
		return piezas;
	}
	public void setPiezas(String[] piezas) {
		this.piezas = piezas;
	}
	public String[] getAcciones() {
		return acciones;
	}
	public void setAcciones(String[] acciones) {
		this.acciones = acciones;
	}
	public ArrayList<Pieza> getListaPiezas() {
		return listaPiezas;
	}
	public void setListaPiezas(ArrayList<Pieza> listaPiezas) {
		this.listaPiezas = listaPiezas;
	}
	public ArrayList<InformacionPieza> getInfoPiezas() {
		return infoPiezas;
	}
	public void setInfoPiezas(ArrayList<InformacionPieza> infoPiezas) {
		this.infoPiezas = infoPiezas;
	}
	public String getTextoSubrayado() {
		return textoSubrayado;
	}
	public void setTextoSubrayado(String textoSubrayado) {
		this.textoSubrayado = textoSubrayado;
	}
	public String getTextoMorfo() {
		return textoMorfo;
	}
	public void setTextoMorfo(String textoMorfo) {
		this.textoMorfo = textoMorfo;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getTextoAntonimos() {
		return textoAntonimos;
	}
	public void setTextoAntonimos(String textoAntonimos) {
		this.textoAntonimos = textoAntonimos;
	}
	public String getTextoSinonimos() {
		return textoSinonimos;
	}
	public void setTextoSinonimos(String textoSinonimos) {
		this.textoSinonimos = textoSinonimos;
	}
	public String getTextoTraducciones() {
		return textoTraducciones;
	}
	public void setTextoTraducciones(String textoTraducciones) {
		this.textoTraducciones = textoTraducciones;
	}
	public String getTextoDefiniciones() {
		return this.textoDefiniciones;
	}
	public void setTextoDefiniciones(String textoDefiniciones) {
		this.textoDefiniciones = textoDefiniciones;
	}
}
