package piezas;

import Utils.Constantes;

public class Adjetivo extends Pieza {
	public Adjetivo(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.ADJETIVO;
	}

}
