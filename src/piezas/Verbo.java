package piezas;

import Utils.Constantes;

public class Verbo extends Pieza {
	public Verbo(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.VERBO;
	}
	
}
