package acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Xtext.restws.Ejecucion;

import piezas.InformacionPieza;
import piezas.Pieza;
import piezas.Texto;
import Utils.Constantes;

public class AnalisisMorfologico extends Accion{
	private Map<String, HashMap<String, String>> analizadas;
	@Override
	public String ejecuta(Pieza ... piezas) {
		int i=0;
		String texto="";
		InformacionPieza[] indices = new InformacionPieza[piezas.length];
		for(Pieza pieza: piezas){
			texto = pieza.getTexto();
			indices[i] = pieza.getInfo();
			i++;
		}
		return analisisMorfologico(texto, indices);
	}

	@Override
	public String deshace(Ejecucion ejecucion, String ... piezas) {
		ArrayList<InformacionPieza> info = ejecucion.getInfoPiezas(), infoAux=new ArrayList<InformacionPieza>();
		boolean igual=false;
		String texto="";
		for(InformacionPieza i: info){
			texto=i.getTexto();
			for(int p=0; !igual && p<piezas.length ; p++){
				if(piezas[p].equalsIgnoreCase(Constantes.DIFICIL) && i.isDificil()){
					igual=true;
				}else if(i.getTipo().substring(0,1).equalsIgnoreCase(piezas[p])){
					igual=true;
				}
			}
			if(!igual){
				infoAux.add(i);
			}
		}
		return analisisMorfologico(texto, infoAux.toArray(new InformacionPieza[infoAux.size()]));
	}
	
	private String analisisMorfologico(String texto, InformacionPieza... indices) {
		this.analizadas = new HashMap<String, HashMap<String, String>>();
		String analisisMorfologico = "";
		if(indices.length>0){ analisisMorfologico = "<h2>Análisis morfológico</h2><ul>";}
		for(InformacionPieza i: indices){
			String tipoPalabra = i.getTipo();
			String palabra = texto.substring(i.getIni(), i.getFin());
			
			/*analisisMorfologico += "<li><strong>" + palabra + ": </strong>"
								+ this.parseaEtiquetasEagles(tipoPalabra) 
								+ "</li>";*/
			String analisis = "<li><strong>" + palabra + ": </strong>"
					+ this.parseaEtiquetasEagles(tipoPalabra) 
					+ "</li>";
			
			if(this.analizadas.containsKey(palabra)){
				HashMap<String, String> definiciones = this.analizadas.get(palabra);
				if(!definiciones.containsKey(analisis)){
					definiciones.put(analisis, analisis);
					analisisMorfologico += analisis;
					this.analizadas.put(palabra, definiciones);
				}
			}else{
				HashMap<String, String> definiciones = new HashMap<String, String>();
				definiciones.put(analisis, analisis);
				this.analizadas.put(palabra, definiciones);
				analisisMorfologico += analisis;
			}
		}
		if(indices.length>0){ analisisMorfologico += "</ul>";}
		return analisisMorfologico;
	}
	private String parseaEtiquetasEagles(String etiqueta){
		int i=0;
		String texto = "";
		String letra = etiqueta.substring(i, i+1);
		switch(letra.toLowerCase()){
		case Constantes.ADJETIVO: texto += this.parseaAdjetivo(etiqueta); break;
		case Constantes.ADVERBIO: texto += this.parseaAdverbio(etiqueta); break;
		case Constantes.DETERMINANTE: texto += this.parseaDeterminante(etiqueta); break;
		case Constantes.NOMBRE: texto += this.parseaNombre(etiqueta); break;
		case Constantes.VERBO: texto += this.parseaVerbo(etiqueta); break;
		case Constantes.PRONOMBRE: texto += this.parseaPronombre(etiqueta); break;
		case Constantes.CONJUNCION: texto += this.parseaConjuncion(etiqueta); break;
		case Constantes.INTERJECCION: texto += this.parseaInterjeccion(etiqueta); break;
		case Constantes.PREPOSICION: texto += this.parseaPreposicion(etiqueta); break;
		case Constantes.PUNTUACION: texto += this.parseaSignoPuntuacion(etiqueta); break;
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
		return texto += ".";
	}
	private String parseaAdverbio(String etiqueta){
		String texto = "Adverbio";
		String tipo = etiqueta.substring(1, 2);
		if(tipo.equalsIgnoreCase("G")){
			texto += " general";
		}else if(tipo.equalsIgnoreCase("N")){
			texto += " negativo";
		}
		return texto += ".";
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
		
		return texto += ".";
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
		
		
		
		return texto += ".";
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
		
		return texto += ".";
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
		
		return texto += ".";
	}
	private String parseaConjuncion(String etiqueta){
		String texto = "Conjunción";
		String tipo = etiqueta.substring(1, 2);
		if(tipo.equalsIgnoreCase("C")){
			texto += " coordinada";
		}else if(tipo.equalsIgnoreCase("S")){
			texto += " subordinada";
		}
		return texto += ".";
	}
	private String parseaInterjeccion(String etiqueta){
		return "Interjección.";
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
			
			
			return texto += ".";
	}
	private String parseaSignoPuntuacion(String etiqueta){
		return "Signo de puntuación.";
	}
	public static void main(String arg[]){
		AnalisisMorfologico t = new AnalisisMorfologico();
		System.out.println(t.ejecuta(new Texto(new InformacionPieza("&eacute;asdas",Constantes.TEXTO,  0, "&eacute;asdas".length(), false))));
	}

	
}
