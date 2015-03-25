package Funciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Utils.Constantes;
import client.Freeling3TaggingClient;

public class AnalisisMorfologico {
	public String analiza(String texto, String[] tiposBuscados){
		String idArchivo = "texto";
		//String idArchivo = "textoPalabra";
		String URL = "http://sesat.fdi.ucm.es/" + idArchivo + ".html";
		String textoURL = "/var/www/html/" + idArchivo + ".html";
		//String textoURL = idArchivo + ".html";
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
		String analisisMorfologico = Constantes.END_LINE + "<h2>Análisis morfológico</h2>" + Constantes.END_LINE + Constantes.END_LINE;
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
					analisisMorfologico += palabra + ": " + this.parseaEtiquetasEagles(tipoPalabra);
				}
				else if(palabra.equalsIgnoreCase(">")){ encontradaEtiqueta = false; }
				
			}
			iLinea++;
			
		}
		
		return t.toString() + analisisMorfologico;
	}
	private boolean encontrado(String t, String[] buscados){
		boolean encontrado = false;
		for(int i=0; !encontrado && i<buscados.length; i++){
			if(t.equalsIgnoreCase(buscados[i])){ encontrado = true; }
		}
		return encontrado;
	}
	private String parseaEtiquetasEagles(String etiqueta){
		int i=0;
		String texto = "";
		String letra = etiqueta.substring(i, i+1);
		switch(letra){
		case "A": texto += this.parseaAdjetivo(etiqueta); break;
		case "R": texto += this.parseaAdverbio(etiqueta); break;
		case "D": texto += this.parseaDeterminante(etiqueta); break;
		case "N": texto += this.parseaNombre(etiqueta); break;
		case "V": texto += this.parseaVerbo(etiqueta); break;
		case "P": texto += this.parseaPronombre(etiqueta); break;
		case "C": texto += this.parseaConjuncion(etiqueta); break;
		case "I": texto += this.parseaInterjeccion(etiqueta); break;
		case "S": texto += this.parseaPreposicion(etiqueta); break;
		default: break;
		}
			
		return texto;
	}
	private String parseaAdjetivo(String etiqueta){
		String texto = "Adjetivo";
		String tipo = etiqueta.substring(1, 2);
		String grado = etiqueta.substring(2, 3);
		String genero = etiqueta.substring(3, 4);
		String numero = etiqueta.substring(4, 5);
		String funcion = etiqueta.substring(5, 6);
		if(tipo.equalsIgnoreCase("Q")){
			texto += " calificativo";
		}else if(tipo.equalsIgnoreCase("O")){
			texto += " ordinal";
		}
		if(grado.equalsIgnoreCase("A")){
			texto += " aumentativo";
		}else if(grado.equalsIgnoreCase("D")){
			texto += " diminutivo";
		}else if(grado.equalsIgnoreCase("C")){
			texto += " comparativo";
		}else if(grado.equalsIgnoreCase("S")){
			texto += " superlativo";
		}
		if(genero.equalsIgnoreCase("M")){
			texto += " masculino";
		}else if(genero.equalsIgnoreCase("F")){
			texto += " femenino";
		}else if(genero.equalsIgnoreCase("C")){
			texto += " común";
		}
		if(numero.equalsIgnoreCase("S")){
			texto += " singular";
		}else if(numero.equalsIgnoreCase("P")){
			texto += " plural";
		}else if(numero.equalsIgnoreCase("N")){
			texto += " invariable";
		}
		if(funcion.equalsIgnoreCase("P")){
			texto += " participio";
		}
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaAdverbio(String etiqueta){
		String texto = "Adverbio";
		String tipo = etiqueta.substring(1, 2);
		if(tipo.equalsIgnoreCase("G")){
			texto += " general";
		}else if(tipo.equalsIgnoreCase("N")){
			texto += " negativo";
		}
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaDeterminante(String etiqueta){
		String texto = "Determinante";
		String tipo = etiqueta.substring(1, 2);
		String persona = etiqueta.substring(2, 3);
		String genero = etiqueta.substring(3, 4);
		String numero = etiqueta.substring(4, 5);
		String poseedor = etiqueta.substring(5, 6);
		
		if(tipo.equalsIgnoreCase("D")){
			texto += " demostrativo";
		}else if(tipo.equalsIgnoreCase("P")){
			texto += " posesivo";
		}else if(tipo.equalsIgnoreCase("T")){
			texto += " interrogativo";
		}else if(tipo.equalsIgnoreCase("E")){
			texto += " exclamativo";
		}else if(tipo.equalsIgnoreCase("I")){
			texto += " indefinido";
		}else if(tipo.equalsIgnoreCase("A")){
			texto += " artículo";
		}
		
		if(genero.equalsIgnoreCase("M")){
			texto += " masculino";
		}else if(genero.equalsIgnoreCase("F")){
			texto += " femenino";
		}else if(genero.equalsIgnoreCase("C")){
			texto += " común";
		}else if(genero.equalsIgnoreCase("N")){
			texto += " neutro";
		}
		
		if(persona.equalsIgnoreCase("1")){
			texto += ", primera persona";
		}else if(persona.equalsIgnoreCase("2")){
			texto += ", segunda persona";
		}else if(persona.equalsIgnoreCase("3")){
			texto += ", tercera persona";
		}
		
		
		if(numero.equalsIgnoreCase("S")){
			texto += " singular";
		}else if(numero.equalsIgnoreCase("P")){
			texto += " plural";
		}else if(numero.equalsIgnoreCase("N")){
			texto += " invariable";
		}
		
		if(poseedor.equalsIgnoreCase("S")){
			texto += ", poseedor singular";
		}else if(poseedor.equalsIgnoreCase("P")){
			texto += ", poseedor plural";
		}
		
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaNombre(String etiqueta){
		String texto = "Nombre";
		String tipo = etiqueta.substring(1, 2);
		String genero = etiqueta.substring(2, 3);
		String numero = etiqueta.substring(3, 4);
		String clasificacionSemantica = etiqueta.substring(4, 6);
		String grado = etiqueta.substring(6, 7);
		
		if(tipo.equalsIgnoreCase("C")){
			texto += " común";
		}else if(tipo.equalsIgnoreCase("P")){
			texto += " propio";
		}
		
		if(genero.equalsIgnoreCase("M")){
			texto += " masculino";
		}else if(genero.equalsIgnoreCase("F")){
			texto += " femenino";
		}else if(genero.equalsIgnoreCase("C")){
			texto += " común";
		}
		
		if(numero.equalsIgnoreCase("S")){
			texto += " singular";
		}else if(numero.equalsIgnoreCase("P")){
			texto += " plural";
		}else if(numero.equalsIgnoreCase("N")){
			texto += " invariable";
		}
		
		if(grado.equalsIgnoreCase("A")){
			texto += " aumentativo";
		}else if(grado.equalsIgnoreCase("D")){
			texto += " diminutivo";
		}
		
		if(clasificacionSemantica.equalsIgnoreCase("SP")){
			texto += ", clasificación semántica persona";
		}else if(clasificacionSemantica.equalsIgnoreCase("G0")){
			texto += ", clasificación semántica lugar";
		}else if(clasificacionSemantica.equalsIgnoreCase("O0")){
			texto += ", clasificación semántica organización";
		}else if(clasificacionSemantica.equalsIgnoreCase("V0")){
			texto += ", clasificación semántica otros";
		}
		
		
		
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaVerbo(String etiqueta){
		String texto = "Verbo";
		String tipo = etiqueta.substring(1, 2);
		String modo = etiqueta.substring(2, 3);
		String tiempo = etiqueta.substring(3, 4);
		String persona = etiqueta.substring(4, 5);
		String numero = etiqueta.substring(5, 6);
		String genero = etiqueta.substring(6, 7);
		
		if(tipo.equalsIgnoreCase("M")){
			texto += " principal";
		}else if(tipo.equalsIgnoreCase("A")){
			texto += " auxiliar";
		}else if(tipo.equalsIgnoreCase("S")){
			texto += " semiauxiliar";
		}
		
		if(modo.equalsIgnoreCase("I")){
			texto += ", modo indicativo";
		}else if(modo.equalsIgnoreCase("S")){
			texto += ", modo subjuntivo";
		}else if(modo.equalsIgnoreCase("M")){
			texto += ", modo imperativo";
		}else if(modo.equalsIgnoreCase("N")){
			texto += ", modo infinitivo";
		}else if(modo.equalsIgnoreCase("G")){
			texto += ", modo gerundio";
		}else if(modo.equalsIgnoreCase("P")){
			texto += ", modo participio";
		}
		
		if(tiempo.equalsIgnoreCase("P")){
			texto += ", tiempo presente";
		}else if(tiempo.equalsIgnoreCase("I")){
			texto += ", tiempo imperfecto";
		}else if(tiempo.equalsIgnoreCase("F")){
			texto += ", tiempo futuro";
		}else if(tiempo.equalsIgnoreCase("S")){
			texto += ", tiempo pasado";
		}else if(tiempo.equalsIgnoreCase("C")){
			texto += ", tiempo condicional";
		}
		
		if(persona.equalsIgnoreCase("1")){
			texto += ", primera persona";
		}else if(persona.equalsIgnoreCase("2")){
			texto += ", segunda persona";
		}else if(persona.equalsIgnoreCase("3")){
			texto += ", tercera persona";
		}
		
		if(numero.equalsIgnoreCase("S")){
			texto += " singular";
		}else if(numero.equalsIgnoreCase("P")){
			texto += " plural";
		}
		
		if(genero.equalsIgnoreCase("M")){
			texto += " masculino";
		}else if(genero.equalsIgnoreCase("F")){
			texto += " femenino";
		}
		
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaPronombre(String etiqueta){
		String texto = "Pronombre";
		String tipo = etiqueta.substring(1, 2);
		String persona = etiqueta.substring(2, 3);
		String genero = etiqueta.substring(3, 4);
		String numero = etiqueta.substring(4, 5);
		String caso = etiqueta.substring(5, 6);
		String poseedor = etiqueta.substring(6, 7);
		String politeness = etiqueta.substring(7, 8);
		
		if(tipo.equalsIgnoreCase("P")){
			texto += " personal";
		}else if(tipo.equalsIgnoreCase("D")){
			texto += " demostrativo";
		}else if(tipo.equalsIgnoreCase("X")){
			texto += " posesivo";
		}else if(tipo.equalsIgnoreCase("I")){
			texto += " indefinido";
		}else if(tipo.equalsIgnoreCase("T")){
			texto += " interrogativo";
		}else if(tipo.equalsIgnoreCase("R")){
			texto += " relativo";
		}else if(tipo.equalsIgnoreCase("E")){
			texto += " exclamativo";
		}
		
		if(persona.equalsIgnoreCase("1")){
			texto += ", primera persona";
		}else if(persona.equalsIgnoreCase("2")){
			texto += ", segunda persona";
		}else if(persona.equalsIgnoreCase("3")){
			texto += ", tercera persona";
		}
		
		if(genero.equalsIgnoreCase("M")){
			texto += " masculino";
		}else if(genero.equalsIgnoreCase("F")){
			texto += " femenino";
		}else if(genero.equalsIgnoreCase("C")){
			texto += " común";
		}else if(genero.equalsIgnoreCase("N")){
			texto += " neutro";
		}
		
		if(numero.equalsIgnoreCase("S")){
			texto += " singular";
		}else if(numero.equalsIgnoreCase("P")){
			texto += " plural";
		}else if(numero.equalsIgnoreCase("N")){
			texto += " impersonal invariable";
		}
		
		if(caso.equalsIgnoreCase("N")){
			texto += ", caso nominativo";
		}else if(caso.equalsIgnoreCase("A")){
			texto += ", caso acusativo";
		}else if(caso.equalsIgnoreCase("D")){
			texto += ", caso dativo";
		}else if(caso.equalsIgnoreCase("O")){
			texto += ", caso oblicuo";
		}
		
		if(poseedor.equalsIgnoreCase("S")){
			texto += ", poseedor singular";
		}else if(poseedor.equalsIgnoreCase("P")){
			texto += ", poseedor plural";
		}
		
		if(politeness.equalsIgnoreCase("P")){
			texto += ", cortés";
		}
		
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaConjuncion(String etiqueta){
		String texto = "Conjunción";
		String tipo = etiqueta.substring(1, 2);
		if(tipo.equalsIgnoreCase("C")){
			texto += " coordinada";
		}else if(tipo.equalsIgnoreCase("C")){
			texto += " subordinada";
		}
		return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaInterjeccion(String etiqueta){
		return "Interjección." + Constantes.END_LINE;
	}
	
	private String parseaPreposicion(String etiqueta){
			String texto = "Preposición";
			String forma = etiqueta.substring(2, 3);
			String genero = etiqueta.substring(3, 4);
			String numero = etiqueta.substring(4, 5);
			
			
			if(forma.equalsIgnoreCase("S")){
				texto += " simple";
			}else if(forma.equalsIgnoreCase("C")){
				texto += " contraída";
			}
			
			if(genero.equalsIgnoreCase("M")){
				texto += ", género masculino";
			}
			
			if(numero.equalsIgnoreCase("S")){
				texto += ", número singular";
			}
			
			
			return texto += "." + Constantes.END_LINE;
	}
	
	private String parseaSignoPuntuacion(String etiqueta){
		return "Signo de puntuación." + Constantes.END_LINE;
	}
	
	/*private boolean existeEtiquetaParrafo(StringBuffer str){
		boolean existe = false;
		if(str.substring(0, 3).equalsIgnoreCase("<p>") && str.substring(str.length()-4, str.length()).equalsIgnoreCase("</p>")){ existe = true; }
		return existe;
	}*/
	public static void main(String arg[]){
		AnalisisMorfologico analisis = new AnalisisMorfologico();
		String[] tipos = new String[2];
		tipos[0] = Constantes.VERBO;
		tipos[1] = Constantes.NOMBRE;
		
		//tipo = this.parseaTipoPieza(tipo);
		//System.out.println(analisis.analiza("<p>La Cordera mucho mas formal que sus companeros verdad es que de edad tambien mucho mas madura se abstenia de toda comunicacion con el mundo civilizado y miraba de lejos el palo del telegrafo como lo que era para ella efectivamente como cosa muerta inutil que no le servia siquiera para rascarse era una vaca que habia vivido mucho sentada horas y horas pues experta en pastos sabia aprovechar el tiempo meditaba mas que comia gozaba del placer de vivir en paz bajo el cielo gris y tranquilo de su tierra como quien alimenta el alma que tambien tienen los brutos y si no fuera profanacion podria decirse que los pensamientos de la vaca matrona llena de experiencia debian de parecerse todo lo posible a las mas sosegadas y doctrinales odas de Horacio</p>",tipos));
		System.out.println(analisis.analiza("palabra", tipos));
		//System.out.println(subrayado.subraya("<p><span class=\"marker\">Juan Luis</span> <span class=\"marker\">es</span> un <span class=\"marker\">cabroncete</span>&nbsp;y me come el rabo</p>", tipo));
		
		//Subrayado s = new Subrayado();
		//s.subraya("<p>El le&oacute;n se comi&oacute; a la jirafa.</p>", null);
		//System.out.println(s.parseaTexto("<p>El le&oacute;n se comi&oacute; a la jirafa.</p>"));
		//System.out.println(s.parseaTexto(""));
	
	}
}
