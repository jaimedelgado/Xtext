package piezas;

import Utils.Constantes;

public class Pronombre extends Pieza {
	public Pronombre(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.PRONOMBRE;
	}
	
	
}