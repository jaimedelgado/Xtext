package piezas;

import Utils.Constantes;

public class Nombre extends Pieza {
	public Nombre(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.NOMBRE;
	}
	
}
