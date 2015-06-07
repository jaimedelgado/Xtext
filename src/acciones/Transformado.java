package acciones;

import piezas.Pieza;

import com.Xtext.restws.Ejecucion;

public class Transformado extends Accion{
	@Override
	public String ejecuta(Pieza ... piezas) {
		String texto = "";
		if(piezas.length != 0){ 
			texto = this.parseaTexto(piezas[0].getTexto());}
		return texto;
	}		
	@Override
	public String deshace(Ejecucion ejecucion, String ... piezas) {
		// TODO Auto-generated method stub
		return null;
	}	
	private String parseaTexto(String texto) {
		texto = texto.replaceAll("\n", "");
		//str = str.replaceAll("(\r\n|\n)", "<br />");
		//System.out.println(texto);
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
			else if(palabra.equals( "&auml")){ t.replace(posini, posfin+1, "ä"); }
			else if(palabra.equals( "&euml")){ t.replace(posini, posfin+1, "ë"); }
			else if(palabra.equals( "&iuml")){ t.replace(posini, posfin+1, "ï"); }
			else if(palabra.equals( "&ouml")){ t.replace(posini, posfin+1, "ö"); }
			else if(palabra.equals( "&uuml")){ t.replace(posini, posfin+1, "ü"); }
			else if(palabra.equals( "&ordf")){ t.replace(posini, posfin+1, "ª"); }
			else if(palabra.equals( "&ordm")){ t.replace(posini, posfin+1, "º"); }
			else if(palabra.equals( "&Aacute")){ t.replace(posini, posfin+1, "Á"); }
			else if(palabra.equals( "&Eacute")){ t.replace(posini, posfin+1, "É");}
			else if(palabra.equals( "&Iacute")){ t.replace(posini, posfin+1, "Í"); }
			else if(palabra.equals( "&Oacute")){ t.replace(posini, posfin+1, "Ó"); }
			else if(palabra.equals( "&Uacute")){ t.replace(posini, posfin+1, "Ú"); }
			else if(palabra.equals( "&Ntilde")){ t.replace(posini, posfin+1, "Ñ");}
			else if(palabra.equals( "&Auml")){ t.replace(posini, posfin+1, "Ä"); }
			else if(palabra.equals( "&Euml")){ t.replace(posini, posfin+1, "Ë"); }
			else if(palabra.equals( "&Iuml")){ t.replace(posini, posfin+1, "Ï"); }
			else if(palabra.equals( "&Ouml")){ t.replace(posini, posfin+1, "Ö"); }
			else if(palabra.equals( "&Uuml")){ t.replace(posini, posfin+1, "Ü"); }
			else if(palabra.equals( "&iexcl")){ t.replace(posini, posfin+1, "¡"); }
			else if(palabra.equals( "&iquest")){ t.replace(posini, posfin+1, "¿"); }
			else if(palabra.equals( "&nbsp")){ t.replace(posini, posfin+1, ""); }
			else if(palabra.equals( "&laquo")){ t.replace(posini, posfin+1, "«");}
			else if(palabra.equals( "&raquo")){ t.replace(posini, posfin+1, "»");}
			else if(palabra.equals( "&ccedil")){ t.replace(posini, posfin+1, "ç");}
			
		
			posini++;
		}
		return t.toString();
	}
	@SuppressWarnings("unused")
	private String parseaEtiqueta(String palabra) {
		
		if(palabra.equals("&aacute")){ palabra =  "á"; }
		else if(palabra.equals("&eacute")){palabra = "é"; }
		else if(palabra.equals("&iacute")){ palabra = "í"; }
		else if(palabra.equals( "&oacute")){ palabra = "ó"; }
		else if(palabra.equals( "&uacute")){ palabra = "ú"; }
		else if(palabra.equals( "&ntilde")){ palabra = "ñ"; }
		else if(palabra.equals( "&uuml")){ palabra = "ü"; }
		else if(palabra.equals( "&ordf")){ palabra = "ª"; }
		else if(palabra.equals( "&ordm")){ palabra = "º"; }
		else if(palabra.equals( "&Aacute")){ palabra = "Á"; }
		else if(palabra.equals( "&Eacute")){ palabra = "É";}
		else if(palabra.equals( "&Iacute")){ palabra = "Í"; }
		else if(palabra.equals( "&Oacute")){ palabra = "Ó"; }
		else if(palabra.equals( "&Uacute")){ palabra = "Ú"; }
		else if(palabra.equals( "&Ntilde")){ palabra = "Ñ";}
		else if(palabra.equals( "&Uuml")){ palabra = "Ü"; }
		else if(palabra.equals( "&iexcl")){ palabra = "¡"; }
		else if(palabra.equals( "&iquest")){ palabra = "¿"; }
		else if(palabra.equals( "&nbsp")){ palabra = " "; }

		
		return palabra;
	}
	// 0<=posfin<texto.length()
	private int[] siguienteCaracterCodificado(StringBuffer texto, int posini) {
		int[] pos = new int[2];
		boolean encontradaEtiqueta=false;
		String caracter="";
		int i = 0, posfin=posini;
		//encontrar "&"
		if(posini < texto.length()-1){
			caracter = texto.substring(posini, posini+1);
			while(posini<texto.length()-1 && !caracter.equals("&")){ 
				if(caracter.equals("<")){
					encontradaEtiqueta = true;
				}
				while(posini<texto.length()-1 && encontradaEtiqueta){
					posini ++;
					caracter = texto.substring(posini, posini+1);
					if(caracter.equals(">")){ encontradaEtiqueta = false; posini--;}
				}

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
		//System.out.println(texto.length() + " " + posini + " " + posfin);
		return pos;
	}
	public static void main(String arg[]){
		Transformado t = new Transformado();
		//System.out.println(t.ejecuta(new Texto(new InformacionPieza("&eacute;asdas", "texto", 0, "&eacute;asdas".length(), false))));
		/*int [] posiciones = t.siguienteCaracterCodificado(new StringBuffer("espa<&ntilde;>a"), 0);
		for(int i: posiciones){
			System.out.println(i + " ");
		}*/
		System.out.println(t.parseaTexto("<table><tbody><tr><th><div style=\"background:#E6ECFF; border:1px solid #ccc; padding:0.2em 0.4em\"><a href=\"https://es.wikipedia.org/wiki/Wikipedia:Art%C3%ADculos_destacados\"><img alt=\"Artículo destacado\" src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Cscr-featured.svg/20px-Cscr-featured.svg.png\" style=\"height:19px; width:20px\" /></a> <a href=\"https://es.wikipedia.org/wiki/Wikipedia:Art%C3%ADculos_destacados\">Art&iacute;culo destacado</a></div>"));
	}
}
