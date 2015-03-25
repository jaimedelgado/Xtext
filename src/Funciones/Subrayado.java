/**
 * 
 */
package Funciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import client.Freeling3TaggingClient;
//import client.KwicClient;
//import client.MaltParserClient;
import Utils.Constantes;

/**
 * @author Jaime Delgado y Juan Luis García
 *
 */
public class Subrayado{
	public String subraya(String texto, String[] tiposBuscados){
		String idArchivo = "texto";
		String URL = "http://sesat.fdi.ucm.es/" + idArchivo + ".html";
		String textoURL = "/var/www/html/" + idArchivo + ".html";
		String salidaFreeling = "";
		File f = new File(textoURL);
		FileWriter archivoHTML = null;
		PrintWriter pwArchivoHTML = null;
		try {
			texto = this.parseaTexto(texto);
			archivoHTML = new FileWriter(textoURL);
			pwArchivoHTML = new PrintWriter(archivoHTML);
			pwArchivoHTML.print(texto);
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				archivoHTML.close();
				pwArchivoHTML.close();
				salidaFreeling = this.salidaFreeling3TaggingClient(URL);
			} catch (IOException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				f.delete();
			}
		}
		return this.parseaFreeling3TaggingClient(texto, salidaFreeling, tiposBuscados);
	}
		
	private String parseaTexto(String texto) {
		StringBuffer t = new StringBuffer(texto);
		String palabra = "0";
		int posini=0, posfin=0;
		while(!palabra.equals("")){
			int[] pos = siguienteCaracterCodificado(t, posini);
			posini = pos[0]; posfin=pos[1];
			palabra = t.substring(pos[0], pos[1]);
			if(palabra.equals("&aacute")){ t.replace(posini, posfin+1, "á"); }
			else if(palabra.equals("&eacute")){t.replace(posini, posfin+1, "é"); }
			else if(palabra.equals("&iacute")){ t.replace(posini, posfin+1, "í"); }
			else if(palabra.equals( "&oacute")){ t.replace(posini, posfin+1, "ó"); }
			else if(palabra.equals( "&uacute")){ t.replace(posini, posfin+1, "ú"); }
			else if(palabra.equals( "&ntilde")){ t.replace(posini, posfin+1, "ñ"); }
			else if(palabra.equals( "&uuml")){ t.replace(posini, posfin+1, "ü"); }
			else if(palabra.equals( "&ordf")){ t.replace(posini, posfin+1, "ª"); }
			else if(palabra.equals( "&ordm")){ t.replace(posini, posfin+1, "º"); }
			else if(palabra.equals( "&Aacute")){ t.replace(posini, posfin+1, "Á"); }
			else if(palabra.equals( "&Eacute")){ t.replace(posini, posfin+1, "É");}
			else if(palabra.equals( "&Iacute")){ t.replace(posini, posfin+1, "Í"); }
			else if(palabra.equals( "&Oacute")){ t.replace(posini, posfin+1, "Ó"); }
			else if(palabra.equals( "&Uacute")){ t.replace(posini, posfin+1, "Ú"); }
			else if(palabra.equals( "&Ntilde")){ t.replace(posini, posfin+1, "Ñ");}
			else if(palabra.equals( "&Uuml")){ t.replace(posini, posfin+1, "Ü"); }
			else if(palabra.equals( "&iexcl")){ t.replace(posini, posfin+1, "¡"); }
			else if(palabra.equals( "&iquest")){ t.replace(posini, posfin+1, "¿"); }
			posini++;
		}
		return t.toString();
	}
	// 0<=posfin<texto.length()
	private int[] siguienteCaracterCodificado(StringBuffer texto, int posini) {
		int[] pos = new int[2];
		String caracter="";
		int i = 0, posfin=posini;
		//encontrar "&"
		if(posini < texto.length()-1){
			caracter = texto.substring(posini, posini+1);
			while(posini<texto.length()-1 && !caracter.equals("&")){ 
				posini ++;
				caracter = texto.substring(posini, posini+1);
			}
		}
		posfin = posini;
		if(posini < texto.length()-1){
			posfin++;
			//encontrar ";"
			caracter = texto.substring(posfin, posfin+1);
			while(i<8 && posfin < texto.length() && !caracter.equals("&") && !caracter.equals(";") ){
				posfin ++;
				i++;
				caracter = texto.substring(posfin, posfin+1);
			}
		}
		pos[0] = posini; pos[1] = posfin;
		return pos;
	}

	private String salidaFreeling3TaggingClient(String URL){
		return Freeling3TaggingClient.runOutput(
				"",//inputDirectData, 
				URL, //inputUrl, 
				"es", //language, 
				false, //keeptags, x
				false, //noner, x
				false, //bio, 
				false, //nonec, x 
				false, //flush, x
				false, //noafx,x
				false, //noloc, x
				false, //nonumb, x
				false, //nopunt, x
				false, //nodate, x
				false, //noquant, x
				false, //nodict, x
				false, //noprob, x
				false);//xmlcqp);
	}
	
	private String parseaFreeling3TaggingClient(String texto, String salidaFreeling, String[] buscado){
		StringBuffer t = new StringBuffer(texto);	
		int iLinea = 0, numLetrasInsertadas=0;
		boolean encontradaEtiqueta = false;
		String[] lineasSalidaFreeling = salidaFreeling.split(Constantes.END_LINE);
		while(iLinea<lineasSalidaFreeling.length){
			String[] lineaSalidaFreeling = lineasSalidaFreeling[iLinea].split(Constantes.TAB);
			if(lineaSalidaFreeling.length>1){ //Comprobación linea vacía
				String tipoPalabra = lineaSalidaFreeling[2];
				String palabra = lineaSalidaFreeling[0];
				if(palabra.equalsIgnoreCase("<")){ encontradaEtiqueta = true; }
				else if (!encontradaEtiqueta && this.encontrado(tipoPalabra.substring(0,1),buscado)){
					int ini = Integer.parseInt(lineaSalidaFreeling[4]) + numLetrasInsertadas;
					int fin = Integer.parseInt(lineaSalidaFreeling[5]) + numLetrasInsertadas;
					t.insert(fin, Constantes.ETIQUETA_SUBRAYAR_FIN);
					t.insert(ini, Constantes.ETIQUETA_SUBRAYAR_INI);
					numLetrasInsertadas += Constantes.ETIQUETA_SUBRAYAR_FIN.length() + Constantes.ETIQUETA_SUBRAYAR_INI.length();
				}
				else if(palabra.equalsIgnoreCase(">")){ encontradaEtiqueta = false; }
				
			}
			iLinea++;
			
		}
		/*if(!this.existeEtiquetaParrafo(t)){
			t.insert(0, Constantes.ETIQUETA_PARRAFO_INI);
			t.insert(t.length(), Constantes.ETIQUETA_PARRAFO_FIN);
		}*/
		return t.toString();
	}
	private boolean encontrado(String t, String[] buscados){
		boolean encontrado = false;
		for(int i=0; !encontrado && i<buscados.length; i++){
			if(t.equalsIgnoreCase(buscados[i])){ encontrado = true; }
		}
		return encontrado;
	}
	/*private boolean existeEtiquetaParrafo(StringBuffer str){
		boolean existe = false;
		if(str.substring(0, 3).equalsIgnoreCase("<p>") && str.substring(str.length()-4, str.length()).equalsIgnoreCase("</p>")){ existe = true; }
		return existe;
	}*/
	public static void main(String arg[]){
		Subrayado subrayado = new Subrayado();
		String[] tipos = new String[2];
		tipos[0] = Constantes.VERBO;
		tipos[1] = Constantes.NOMBRE;
		
		//tipo = this.parseaTipoPieza(tipo);
		System.out.println(subrayado.subraya("<p>La Cordera mucho mas formal que sus companeros verdad es que de edad tambien mucho mas madura se abstenia de toda comunicacion con el mundo civilizado y miraba de lejos el palo del telegrafo como lo que era para ella efectivamente como cosa muerta inutil que no le servia siquiera para rascarse era una vaca que habia vivido mucho sentada horas y horas pues experta en pastos sabia aprovechar el tiempo meditaba mas que comia gozaba del placer de vivir en paz bajo el cielo gris y tranquilo de su tierra como quien alimenta el alma que tambien tienen los brutos y si no fuera profanacion podria decirse que los pensamientos de la vaca matrona llena de experiencia debian de parecerse todo lo posible a las mas sosegadas y doctrinales odas de Horacio</p>",tipos));
		//System.out.println(subrayado.subraya("<p><span class=\"marker\">Juan Luis</span> <span class=\"marker\">es</span> un <span class=\"marker\">cabroncete</span>&nbsp;y me come el rabo</p>", tipo));
		
		//Subrayado s = new Subrayado();
		//s.subraya("<p>El le&oacute;n se comi&oacute; a la jirafa.</p>", null);
		//System.out.println(s.parseaTexto("<p>El le&oacute;n se comi&oacute; a la jirafa.</p>"));
		//System.out.println(s.parseaTexto(""));
	
	}
}
