package piezas;

import Utils.Constantes;


public class Interjeccion extends Pieza {
	public Interjeccion(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.INTERJECCION;
	}

	
}
