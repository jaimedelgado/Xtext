/**
 * 
 */
package acciones;

//import client.KwicClient;
//import client.MaltParserClient;
import java.util.ArrayList;

import com.Xtext.restws.Ejecucion;

import piezas.InformacionPieza;
import piezas.Nombre;
import piezas.Pieza;
import piezas.Texto;
import piezas.Verbo;
import Utils.Constantes;

/**
 * @author Jaime Delgado y Juan Luis García
 *
 */
public class Subrayado extends Accion{

	@Override
	public String ejecuta(Pieza ... piezas) {
		String texto="";
		int masTexto=0;
		if(piezas.length!=0){
			StringBuffer t = new StringBuffer(piezas[0].getTexto());
			for(Pieza pieza: piezas){
				t.insert(pieza.getFin()+masTexto, Constantes.ETIQUETA_SUBRAYAR_FIN);
				t.insert(pieza.getIni()+masTexto, Constantes.ETIQUETA_SUBRAYAR_INI);
				masTexto += Constantes.ETIQUETA_SUBRAYAR_FIN.length() + Constantes.ETIQUETA_SUBRAYAR_INI.length();
			}
			texto = t.toString();
		}
		return texto;
	}
	@Override
	public String deshace(Ejecucion ejecucion, String ... piezas) {
		ArrayList<InformacionPieza> info = ejecucion.getInfoPiezas();
		StringBuffer texto= new StringBuffer(ejecucion.getTextoSubrayado());
		for(InformacionPieza i: info){
			for(int p=0; p<piezas.length ; p++){
				if(esDificil(piezas[p]) && i.isDificil()){
					int ini = i.getIni();
					int fin = i.getFin();
					texto.delete(ini, ini+Constantes.ETIQUETA_SUBRAYAR_INI.length());
					texto.delete(fin, fin+Constantes.ETIQUETA_SUBRAYAR_FIN.length());
					
				}else if(i.getTipo().substring(0,1).equalsIgnoreCase(piezas[p])){
					int ini = i.getIni();
					int fin = i.getFin();
					texto.delete(ini, ini+Constantes.ETIQUETA_SUBRAYAR_INI.length());
					texto.delete(fin, fin+Constantes.ETIQUETA_SUBRAYAR_FIN.length());
					
				}
			}
			
		}
		return texto.toString();
	}	
	public static boolean esDificil(String pieza){
		boolean encontrado = false;
		String[] split = pieza.split(":");
		
			for(String sp: split){
				if(sp.equalsIgnoreCase(Constantes.DIFICIL)){ encontrado = true; }
			}
		
		return encontrado;
	}
	public static void main(String arg[]){
		Subrayado t = new Subrayado();
		Pieza pieza1 = new Nombre(new InformacionPieza("el perro es muy bonito", "n", 3, 3+"perro".length(), false));
		Pieza pieza2 = new Verbo(new InformacionPieza("el perro es muy bonito", "v", 9, 11, true));
		String[] piezas = new String[2];
		piezas[0] = "n";
		piezas[1] = "v";
		String[] acciones = new String[2];
		//piezas[0] = Constantes.ACCION_SUBRAYAR;
		System.out.println(t.ejecuta(pieza1,
									pieza2));
		ArrayList<Pieza> listPiezas = new ArrayList<Pieza>();
		listPiezas.add(pieza1);
		listPiezas.add(pieza2);
		ArrayList<InformacionPieza> listInfo = new ArrayList<InformacionPieza>();
		listInfo.add(new InformacionPieza("<p>el perro es muy bonito</p>", "n", 6, 6+"perro".length(), false));
		listInfo.add(new InformacionPieza("<p>el perro es muy bonito</p>", "v", 12, 14, true));
		System.out.println(t.deshace(
				new Ejecucion(piezas, acciones, listPiezas, listInfo, "<p>el <span class=\"marker\">perro</span> <span class=\"marker\">es</span> muy bonito</p>", "", "", "", "", "", "aplicar"),
				piezas));
	}
	
}
