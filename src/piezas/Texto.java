package piezas;

import Utils.Constantes;

public class Texto extends Pieza{

	public Texto(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.TEXTO;
	}
	
	
}
