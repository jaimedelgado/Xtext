package piezas;

import Utils.Constantes;

public class Puntuacion extends Pieza {
	public Puntuacion(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.PUNTUACION;
	}

	
}
