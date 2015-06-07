package piezas;

import Utils.Constantes;

public class FechaHora extends Pieza {
	public FechaHora(InformacionPieza info){
		super(info);
	}

	@Override
	public String getTipo() {
		return Constantes.FECHAHORA;
	}
	
}
