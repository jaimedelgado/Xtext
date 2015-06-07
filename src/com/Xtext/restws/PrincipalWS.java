package com.Xtext.restws;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import piezas.Adjetivo;
import piezas.Adverbio;
import piezas.Conjuncion;
import piezas.Determinante;
import piezas.FechaHora;
import piezas.InformacionPieza;
import piezas.Interjeccion;
import piezas.Nombre;
import piezas.Numeral;
import piezas.Pieza;
import piezas.Preposicion;
import piezas.Pronombre;
import piezas.Puntuacion;
import piezas.Texto;
import piezas.Verbo;
import Utils.Constantes;
import acciones.Accion;
import acciones.AnalisisMorfologico;
import acciones.BuscaAntonimos;
import acciones.BuscaSinonimos;
import acciones.Busqueda;
import acciones.Definicion;
import acciones.Subrayado;
import acciones.Traduccion;
import acciones.Transformado;

import com.google.gson.Gson;

/**
* 
* @authors Jaime Delgado y Juan Luis García
* 
*/

//@Path here defines class level path. Identifies the URI path that 
//a resource class will serve requests for.
@Path("XtextWS")
public class PrincipalWS {
	//private Map<Double,Ejecucion> ejecuciones = new HashMap<Double, Ejecucion>();
	private Map<String, String> piezasSubrayar;
	private Map<String, String> piezasMorfo;
	private Map<String, String> piezasAntonimos;
	private Map<String, String> piezasSinonimos;
	private Map<String, String> piezasDefinicion;
	private Map<String, String> piezasTraduccion;
	private Map<String, String> piezasEjecutar;
	private Map<String, String> accionesEjecutar;
	@POST
	@Path("/chooseService")
	@Consumes("application/json")
	@Produces("text/plain")
	public String elegirServicio(String datos) {
		Gson gson = new Gson();
		XtextDataArray xtextDataArray = gson.fromJson(datos, XtextDataArray.class);
		XtextData[] dataArray = xtextDataArray.getFilas();
		/*this.piezasAntonimos = new HashMap<String, String>();
		this.piezasAntonimos.keySet().toArray(new String[piezasAntonimos.size()]);
		this.piezasAntonimos.put("Sdfsdfsg", "sdafsadfsadf");
		this.piezasAntonimos.keySet().toArray(new String[piezasAntonimos.size()]);*/
		this.inicializa(dataArray);
		ArrayList<Pieza> pSubrayar = new ArrayList<Pieza>();
		ArrayList<Pieza> pMorfo = new ArrayList<Pieza>();
		Map<String, Pieza> pAntonimos = new HashMap<String, Pieza>();
		Map<String, Pieza> pSinonimos = new HashMap<String, Pieza>();
		Map<String, Pieza> pDefiniciones = new HashMap<String, Pieza>();
		Map<String, Pieza> pTraducciones = new HashMap<String, Pieza>();
		
		/*String[] tipos = dataArray.getPiezas().split(":");
		String [] saAcciones = data.getAcciones().split(":");
		@SuppressWarnings("unused")
		String texto = data.getTexto(), prueba="";
		String opcion = data.getOp();	
		double fila = data.getId();
		String textoSubrayado="", textoMorfo="", textoAntonimos="", textoSinonimos="", textoTraducciones="", textoDefiniciones="";
		Accion a = null;
		ArrayList<InformacionPieza> info = null;
		ArrayList<Pieza> ps ,piezasTexto = null;*/
		/*prueba+="Tipos:";
		for(String t:tipos){
			prueba+= " " + t;
		}
		prueba+=Constantes.END_LINE + "Acciones:";
		for(String accion:saAcciones){
			prueba+= " " + accion;
		}
		prueba += Constantes.END_LINE + "Fila: " + fila;
		*/
		
			//ArrayList<Pieza> ps = new ArrayList<Pieza>();
			ArrayList<Pieza> piezasTexto = new ArrayList<Pieza>();
			Pieza pieza=null;
			String texto = xtextDataArray.getTexto();
			texto = this.eliminaEnriquecido(texto);
			texto = new Transformado().ejecuta(new Texto(new InformacionPieza(texto, Constantes.TEXTO, 0, texto.length(), false)));
			
			Busqueda busq = new Busqueda();
			/*
			 * {texto: texto, piezas: piezas, acciones: acciones, fila: k, op: operacion}
			 */
			/*ArrayList<InformacionPieza> info;
			info = Freeling3TaggingClient.parseaFreeling3TaggingClient("una palabra difícil sería externocleidomastoideo", 
									Freeling3TaggingClient.runOutput(//"Dos que van corriendo llegan tarde a misa y otros dos llegan a tiempo sin ir deprisa.",
										"una palabra difícil sería externocleidomastoideo", 
										"",
										"es",
										false,false,false,false,false,false,false,false,false,false,false,false,false,false),
									new ArrayList<Pieza>(), 
									Constantes.DIFICIL);
			*/
			String nombreArchivo = String.valueOf(System.nanoTime());
			String URL = "http://sesat.fdi.ucm.es/" + nombreArchivo +".html";
			this.creaArchivoTexto(texto, nombreArchivo);
			ArrayList<InformacionPieza> info = busq.busqueda(texto, URL, piezasTexto, this.piezasEjecutar.keySet().toArray(new String[this.piezasEjecutar.size()]));
			/*for(int j=0; j<info.size(); j++){
				prueba += "si entra ";
				InformacionPieza i = info.get(j);
				prueba += i.getTexto().substring(i.getIni(), i.getFin()) + " " + i.getTipo() + " " + i.getIni() + " " + i.getFin() + Constantes.END_LINE;
			}*/
	
			for(int j=0; j<info.size(); j++){
				InformacionPieza i = info.get(j);
				switch(i.getTipo().substring(0, 1).toLowerCase()){
				case Constantes.ADJETIVO: pieza=new Adjetivo(i);
					break;
				case Constantes.ADVERBIO: pieza=new Adverbio(i);
				break;
				case Constantes.CONJUNCION: pieza=new Conjuncion(i);
				break;
				case Constantes.DETERMINANTE: pieza=new Determinante(i);
				break;
				case Constantes.FECHAHORA: pieza=new FechaHora(i);
				break;
				case Constantes.INTERJECCION: pieza=new Interjeccion(i);
				break;
				case Constantes.NOMBRE: pieza=new Nombre(i);
				break;
				case Constantes.NUMERAL: pieza=new Numeral(i);
				break;
				case Constantes.PREPOSICION: pieza=new Preposicion(i);
				break;
				case Constantes.PRONOMBRE: pieza=new Pronombre(i);
				break;
				case Constantes.PUNTUACION: pieza=new Puntuacion(i);
				break;
				case Constantes.TEXTO: pieza=new Texto(i);
				break;
				case Constantes.VERBO: pieza=new Verbo(i);
				break;
				default: break;
				}
				String piezaTexto = pieza.getTexto().substring(pieza.getIni(), pieza.getFin());
				if(this.piezasAntonimos.containsKey(pieza.getTipo())||(this.piezasAntonimos.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pAntonimos.put(piezaTexto, pieza);
				}
				if(this.piezasSinonimos.containsKey(pieza.getTipo())||(this.piezasSinonimos.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pSinonimos.put(piezaTexto, pieza);
				}
				if(this.piezasDefinicion.containsKey(pieza.getTipo())||(this.piezasDefinicion.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pDefiniciones.put(piezaTexto, pieza);
				}
				if(this.piezasMorfo.containsKey(pieza.getTipo())||(this.piezasMorfo.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pMorfo.add(pieza);
				}
				if(this.piezasSubrayar.containsKey(pieza.getTipo())||(this.piezasSubrayar.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pSubrayar.add(pieza);
				}
				if(this.piezasTraduccion.containsKey(pieza.getTipo())||(this.piezasTraduccion.containsKey(Constantes.DIFICIL) && pieza.isDificil())){
					pTraducciones.put(piezaTexto, pieza);
				}
				//ps.add(pieza);
				
			}
			
			this.borraArchivoTexto(nombreArchivo);
			//Pieza[] arrayPiezas =null;
			String textoSubrayado="", textoMorfo="", textoAntonimos="", textoSinonimos="", textoTraducciones="", textoDefiniciones="";
			String licencia="";
			textoSubrayado = texto;
			//arrayPiezas = ps.toArray(new Pieza[ps.size()]);
			Accion a = null;
			for(String accion: accionesEjecutar.keySet()){
				switch(accion){
				case Constantes.ACCION_SUBRAYAR: 
					a = new Subrayado();
					String textoAux = a.ejecuta(pSubrayar.toArray(new Pieza[pSubrayar.size()]));
					if(!textoAux.equalsIgnoreCase("")){textoSubrayado = textoAux;}
					break;
				case Constantes.ACCION_MORFOLOGICO: 
					a = new AnalisisMorfologico(); 
					textoMorfo = a.ejecuta(pMorfo.toArray(new Pieza[pMorfo.size()]));
					break;
				case Constantes.ACCION_ANTONIMOS:
					a = new BuscaAntonimos();
					textoAntonimos = a.ejecuta(pAntonimos.values().toArray(new Pieza[pAntonimos.size()]));
					licencia = "<p>&nbsp;</p><p>&nbsp;</p><h3 style=\"color:#aaa;font-style:italic;\">Resultados obtenidos de wordreference.com</h3><p>&nbsp;</p><p>&nbsp;</p>";
					break;
				case Constantes.ACCION_SINONIMOS:
					a = new BuscaSinonimos();
					textoSinonimos = a.ejecuta(pSinonimos.values().toArray(new Pieza[pSinonimos.size()]));
					licencia = "<p>&nbsp;</p><p>&nbsp;</p><h3 style=\"color:#aaa;font-style:italic;\">Resultados obtenidos de wordreference.com</h3><p>&nbsp;</p><p>&nbsp;</p>";
					break;
				case Constantes.ACCION_DEFINICIONES:
					a = new Definicion();
					textoDefiniciones = a.ejecuta(pDefiniciones.values().toArray(new Pieza[pDefiniciones.size()]));
					licencia = "<p>&nbsp;</p><p>&nbsp;</p><h3 style=\"color:#aaa;font-style:italic;\">Resultados obtenidos de wordreference.com</h3><p>&nbsp;</p><p>&nbsp;</p>";
					break;
				case Constantes.ACCION_TRADUCCIONES:
					a = new Traduccion();
					textoTraducciones = a.ejecuta(pTraducciones.values().toArray(new Pieza[pTraducciones.size()]));
					licencia = "<p>&nbsp;</p><p>&nbsp;</p><h3 style=\"color:#aaa;font-style:italic;\">Resultados obtenidos de wordreference.com</h3><p>&nbsp;</p><p>&nbsp;</p>";
					break;
				default: break;
				}
			}
			
		
		//PrincipalWS.ejecuciones.put(fila, new Ejecucion(tipos, saAcciones, piezasTexto, info, textoSubrayado, textoMorfo, textoAntonimos, textoSinonimos, textoDefiniciones, textoTraducciones, opcion));

		//this.borraArchivoTexto(nombreArchivo);
		return textoSubrayado + textoMorfo + textoAntonimos + textoSinonimos + textoDefiniciones + textoTraducciones + licencia;
	
		/*prueba = Freeling3TaggingClient.parseaFreeling3TaggingClient(texto, 
				Freeling3TaggingClient.runOutput(//"Dos que van corriendo llegan tarde a misa y otros dos llegan a tiempo sin ir deprisa.",
					"", 
					URL,
					"es",
					false,false,false,false,false,false,false,false,false,false,false,false,false,false),
				new ArrayList<Pieza>(), 
				tipos);*/
		//return prueba;
	}
	private String eliminaEnriquecido(String texto) {
		StringBuffer buf = new StringBuffer(texto);
		String etiquetaMorfo = "<h2>An&aacute;lisis morfol&oacute;gico</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>";
		String etiquetaAntonimos = "<h2>Ant&oacute;nimos</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>";
		String etiquetaSinonimos = "<h2>Sin&oacute;nimos</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>";
		String etiquetaDefiniciones = "<h2>Definiciones</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>";
		String etiquetaTraducciones = "<h2>Traducciones</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>";
		//System.err.println(buf.toString());
		buf = this.eliminaTipoEnriquecido(buf, etiquetaMorfo);
		buf = this.eliminaTipoEnriquecido(buf, etiquetaAntonimos);
		buf = this.eliminaTipoEnriquecido(buf, etiquetaSinonimos);
		buf = this.eliminaTipoEnriquecido(buf, etiquetaDefiniciones);
		buf = this.eliminaTipoEnriquecido(buf, etiquetaTraducciones);
		buf = this.eliminaSubrayado(buf);
		return buf.toString().replaceAll("<p>&nbsp;</p>" + Constantes.END_LINE + Constantes.END_LINE + "<p>&nbsp;</p>" + Constantes.END_LINE + Constantes.END_LINE + "<h3 style=\"color:#aaa; font-style:italic\">Resultados obtenidos de wordreference.com</h3>" + Constantes.END_LINE + Constantes.END_LINE + "<p>&nbsp;</p>" + Constantes.END_LINE + Constantes.END_LINE + "<p>&nbsp;</p>" + Constantes.END_LINE,
				"");
	}

	private StringBuffer eliminaSubrayado(StringBuffer buf) {
		String etSub = "<span class=\"marker\">";
		String etSpanCierre = "</span>";
		String etCualquierSpan = "<span";
		int indIni = buf.indexOf(etSub);
		while(indIni!=-1){
			int indFin = buf.indexOf(etSpanCierre, indIni);
			int indSpan = buf.indexOf(etCualquierSpan, indIni+1);
			while(indSpan!=-1 && indSpan<indFin){
				indFin = buf.indexOf(etSpanCierre, indFin+1);
				indSpan = buf.indexOf(etCualquierSpan, indSpan+1);
			}
			buf.delete(indFin, indFin+etSpanCierre.length());
			buf.delete(indIni, indIni+etSub.length());
			
			indIni = buf.indexOf(etSub);
		}
		return buf;
	}
	private StringBuffer eliminaTipoEnriquecido(StringBuffer buf, String etiqueta) {
		int ind = buf.indexOf(etiqueta);
		while(ind>-1){
			int aux = buf.indexOf("</ul>", ind);
			while(buf.indexOf("<ul>", aux)==aux+"</ul>".length()){
				aux = buf.indexOf("</ul>", aux+"</ul>".length());
			}
			aux+= "</ul>".length();
			buf.delete(ind, aux);
			ind = buf.indexOf(etiqueta);
		}
		return buf;
	}
	private void inicializa(XtextData ... dataArray) {
		this.accionesEjecutar = new HashMap<String, String>();
		this.piezasEjecutar = new HashMap<String, String>();
		this.piezasAntonimos = new HashMap<String, String>();
		this.piezasDefinicion = new HashMap<String, String>();
		this.piezasMorfo = new HashMap<String, String>();
		this.piezasSinonimos = new HashMap<String, String>();
		this.piezasSubrayar = new HashMap<String, String>();
		this.piezasTraduccion = new HashMap<String, String>();
		for(XtextData data: dataArray){
			if(data!=null){
				String acciones = data.getAcciones();
				String piezas = data.getPiezas();
				String[] accionesArray = acciones.split(":");
				String[] piezasArray = piezas.split(":");
				for(String a: accionesArray){
					this.accionesEjecutar.put(a, a);
					for(String p: piezasArray){
						this.piezasEjecutar.put(p,p);
						switch(a){
						case Constantes.ACCION_ANTONIMOS: this.piezasAntonimos.put(p, p); break;
						case Constantes.ACCION_DEFINICIONES: this.piezasDefinicion.put(p, p); break;
						case Constantes.ACCION_MORFOLOGICO: this.piezasMorfo.put(p, p); break;
						case Constantes.ACCION_SINONIMOS: this.piezasSinonimos.put(p, p); break;
						case Constantes.ACCION_SUBRAYAR: this.piezasSubrayar.put(p, p); break;
						case Constantes.ACCION_TRADUCCIONES: this.piezasTraduccion.put(p, p); break;
						default: break;
						}
					}
				}
			}
		}
	}

	private void borraArchivoTexto(String idArchivo) {
		String textoURL = "/var/www/html/" + idArchivo + ".html";
		File f = new File(textoURL);
		f.delete();
	}

	private void creaArchivoTexto(String texto, String idArchivo) {
		String textoURL = "/var/www/html/" + idArchivo + ".html";
		File f = new File(textoURL);
		FileWriter archivoHTML = null;
		PrintWriter pwArchivoHTML = null;
		try {
			archivoHTML = new FileWriter(f);
			pwArchivoHTML = new PrintWriter(archivoHTML);
			pwArchivoHTML.print(texto);
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				archivoHTML.close();
				pwArchivoHTML.close();
			} catch (IOException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//f.delete();
			}
		}
	}

	/*
	private String[] parseaTipoPieza(String[] tipo){
		for(int i=0; i<tipo.length; i++){
			
			if("NOMBRES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.NOMBRE; }
			else if("VERBOS".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.VERBO; }
			else if("ADJETIVOS".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.ADJETIVO; }
			else if("ADVERBIOS".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.ADVERBIO; }
			else if("DETERMINANTES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.DETERMINANTE; }
			else if("PRONOMBRES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.PRONOMBRE; }
			else if("CONJUNCIONES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.CONJUNCION; }
			else if("INTERJECCIONES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.INTERJECCION; }
			else if("PREPOSICIONES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.PREPOSICION; }
			else if("PUNTUACIONES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.PUNTUACION; }
			else if("NUMERALES".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.NUMERAL; }
			else if("FECHAHORAS".equalsIgnoreCase(tipo[i])){ tipo[i] = Constantes.FECHAHORA; }
		}
		return tipo;
	}*/
	
	public static void main(String arg[]){
		PrincipalWS p = new PrincipalWS();
		System.out.println(
				p.eliminaEnriquecido("<p><span class=\"marker\"><span class=\"marker\">es</span> esto</span> <span class=\"marker\">una</span> <span class=\"marker\">prueba</span></p><h2>Análisis morfológico</h2>" + Constantes.END_LINE + Constantes.END_LINE + "<ul>" + Constantes.END_LINE + " <li></li></ul>").toString()
				);
		/*System.out.println(Integer.MAX_VALUE);
		System.out.println(Double.MAX_VALUE);
		PrincipalWS p = new PrincipalWS();
		String texto ="esto es una prueba";
		//String datos = "{texto: \"Quijote\", piezas: \"n:\", acciones: \"sinonimos:\", id: \"198623\", op: \"aplicar\"}";
		//String ejec = p.elegirServicio(datos);
		//String datos = "{texto: \""+texto+ "\", filas: [{piezas: \"n:\", acciones: \"sinonimos:\", id: \"198624\", op: \"aplicar\"}]}";
		String datos = "{\"texto\":\"<p>esto es una prueba</p>\n\",\"filas\":[null,null,null,null,{\"piezas\":\"v:\",\"acciones\":\"subrayar:\",\"id\":1433522626350,\"op\":\"aplicar\"},{\"piezas\":\"n:\",\"acciones\":\"subrayar:morfo:\",\"id\":1433522626351,\"op\":\"aplicar\"}]}";
		System.out.println(p.elegirServicio(datos));*/
	}

}
