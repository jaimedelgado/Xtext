package piezas;

import Utils.Constantes;

public class Preposicion extends Pieza {
	public Preposicion(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.PREPOSICION;
	}
	
}
