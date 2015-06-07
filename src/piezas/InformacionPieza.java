package piezas;

public class InformacionPieza {
	private int ini;
	private int fin;
	private String tipo;
	private String texto;
	private String nombreHtml;
	private boolean dificil;
	private String[] accionesAplicar;
	
	public InformacionPieza(String texto, String tipo, int ini, int fin, boolean dificil, String ... accionesAplicar){
		this.setAccionesAplicar(accionesAplicar); this.texto=texto; this.tipo=tipo; this.ini=ini; this.fin=fin; this.setNombreHtml(String.valueOf(System.nanoTime())); this.dificil=dificil;
	}
	public int getIni() {
		return ini;
	}
	public void setIni(int ini) {
		this.ini = ini;
	}
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTexto(){
		return this.texto;
	}
	public void setTexto(String texto){
		this.texto = texto;
	}
	public String getNombreHtml() {
		return nombreHtml;
	}
	public void setNombreHtml(String nombreHtml) {
		this.nombreHtml = nombreHtml;
	}
	public boolean isDificil() {
		return dificil;
	}
	public void setDificil(boolean dificil) {
		this.dificil = dificil;
	}
	public String[] getAccionesAplicar() {
		return accionesAplicar;
	}
	public void setAccionesAplicar(String[] accionesAplicar) {
		this.accionesAplicar = accionesAplicar;
	}
	
}
