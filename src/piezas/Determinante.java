package piezas;

import Utils.Constantes;

public class Determinante extends Pieza {
	public Determinante(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.DETERMINANTE;
	}
	
}
