package piezas;

import Utils.Constantes;

public class Adverbio extends Pieza {
	public Adverbio(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.ADVERBIO;
	}

}
