package piezas;

import Utils.Constantes;

public class Conjuncion extends Pieza {
	public Conjuncion(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.CONJUNCION;
	}
	
}
