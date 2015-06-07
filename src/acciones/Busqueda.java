package acciones;

import java.util.ArrayList;

import piezas.InformacionPieza;
import piezas.Pieza;
import Utils.Constantes;
import client.Freeling3TaggingClient;

import com.Xtext.restws.Ejecucion;

public class Busqueda extends Accion {

	@Override
	public String ejecuta(Pieza ... piezas) {
		String texto="";
		if(piezas.length!=0){ texto = piezas[0].getTexto(); }
		return texto;
	}
	@Override
	public String deshace(Ejecucion ejecucion, String ... piezas) {
		// TODO Auto-generated method stub
		return null;
	}	
	public ArrayList<InformacionPieza> busqueda(String texto, String html, ArrayList<Pieza> listPiezas, String ... piezas) {
		ArrayList<InformacionPieza> array = new ArrayList<InformacionPieza>();
		if(piezas.length>0){
				array.addAll(Freeling3TaggingClient.parseaFreeling3TaggingClient(
								texto, 
								Freeling3TaggingClient.runOutput("",
										html,
										"es",
										false,false,false,false,false,false,false,false,false,false,false,false,false,false),
								listPiezas,
								piezas));
		}	
		return array;
	}
	
	/*private ArrayList<Boolean> busca(Map<String, String> tabla, String ... palabras){
		ArrayList<Boolean> indices = new ArrayList<Boolean>();
		for(String palabra: palabras){
			if(!tabla.containsKey(palabra.toLowerCase())){
				indices.add(true);
			}else{
				indices.add(false);
			}
		}
		return indices;
	}*/
	
	public static void main(String arg[]){
		Busqueda t = new Busqueda();
		String texto = "<table>";
		String html = "hola";
		ArrayList<InformacionPieza> info = t.busqueda(texto, html, new ArrayList<Pieza>(), Constantes.VERBO);
		for(InformacionPieza i: info){
			System.out.println(i.getTexto().substring(i.getIni(), i.getFin()) + " " + i.getTipo());
		}
		//System.out.println(t.ejecuta(new Texto(new InformacionPieza("&eacute;asdas", "texto", 0, "&eacute;asdas".length(), false))));
	}
	
	
}
