package acciones;

import com.Xtext.restws.Ejecucion;

import piezas.Pieza;

public abstract class Accion {
	public abstract String ejecuta(Pieza ... piezas);
	public abstract String deshace(Ejecucion ejecucion, String ... piezas);
}
