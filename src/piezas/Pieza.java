package piezas;

import Utils.Constantes;

public class Pieza extends Object{
	private InformacionPieza info;
	
	public Pieza(InformacionPieza info){
		this.info=info;
	}
	public InformacionPieza getInfo(){
		return this.info;
	}
	public int getIni() {
		return this.info.getIni();
	}
	public void setIni(int ini) {
		this.info.setIni(ini);
	}
	public int getFin() {
		return this.info.getFin();
	}
	public void setFin(int fin) {
		this.info.setFin(fin);
	}
	public String getTexto(){
		return this.info.getTexto();
	}
	public String getTipo(){return Constantes.PIEZA;}
	public void setTipo(){
		this.info.setTipo(this.getTipo());
	}
	public String getHtml(){
		return this.info.getNombreHtml();
	}
	public boolean isDificil(){
		return this.info.isDificil();
	}
	public void setDificil(boolean dificil){
		this.info.setDificil(dificil);
	}
}
