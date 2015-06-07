package piezas;

import Utils.Constantes;

public class Numeral extends Pieza {
	public Numeral(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.NUMERAL;
	}

	
}
