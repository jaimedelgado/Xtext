package client;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.ws.Holder;

import org.soaplab.morphosintactic_tagging.freeling3_tagging.Freeling3Tagging;
import org.soaplab.morphosintactic_tagging.freeling3_tagging.Freeling3TaggingService;

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

public class Freeling3TaggingClient {
	public static Map<String, String> tabla;
	
	public static String runOutput(
			//@WebParam(name = "input_direct_data", targetNamespace = "")
		    String inputDirectData,
		    //@WebParam(name = "input_url", targetNamespace = "")
		    String inputUrl,
		    //@WebParam(name = "language", targetNamespace = "")
		    String language,
		    //@WebParam(name = "keeptags", targetNamespace = "")
		    Boolean keeptags,
		    //@WebParam(name = "noner", targetNamespace = "")
		    Boolean noner,
		    //@WebParam(name = "bio", targetNamespace = "")
		    Boolean bio,
		    //@WebParam(name = "nonec", targetNamespace = "")
		    Boolean nonec,
		    //@WebParam(name = "flush", targetNamespace = "")
		    Boolean flush,
		    //@WebParam(name = "noafx", targetNamespace = "")
		    Boolean noafx,
		    //@WebParam(name = "noloc", targetNamespace = "")
		    Boolean noloc,
		    //@WebParam(name = "nonumb", targetNamespace = "")
		    Boolean nonumb,
		    //@WebParam(name = "nopunt", targetNamespace = "")
		    Boolean nopunt,
		    //@WebParam(name = "nodate", targetNamespace = "")
		    Boolean nodate,
		    //@WebParam(name = "noquant", targetNamespace = "")
		    Boolean noquant,
		    //@WebParam(name = "nodict", targetNamespace = "")
		    Boolean nodict,
		    //@WebParam(name = "noprob", targetNamespace = "")
		    Boolean noprob,
		    //@WebParam(name = "xmlcqp", targetNamespace = "")
		    Boolean xmlcqp){
		Freeling3TaggingService Freeling3TaggingService = new Freeling3TaggingService();
		Freeling3Tagging Freeling3Tagging = Freeling3TaggingService.getFreeling3TaggingPort();
		Holder<String>  report = new Holder<String>(), 
						output = new Holder<String>(), 
						outputUrl = new Holder<String>();
		Holder<Long> detailedStatus = new Holder<Long>();
		Freeling3Tagging.runAndWaitFor(inputDirectData, inputUrl, language, keeptags, noner, bio, nonec, flush, noafx, noloc, nonumb, nopunt, nodate, noquant, nodict, noprob, xmlcqp, report, detailedStatus, output, outputUrl);
		String URL = outputUrl.value, salida="";
		String nombreArchivo = String.valueOf(System.nanoTime()) + ".txt";
		if(URL!=null){
			try {
	            // Url con la foto
	            URL url = new URL(URL);
	            // establecemos conexion
	            URLConnection urlCon = url.openConnection();
	 
	            // Se obtiene el inputStream de la foto web y se abre el fichero
	            // local.
	            InputStream is = urlCon.getInputStream();
	            FileOutputStream fos = new FileOutputStream(nombreArchivo);
	 
	            // Lectura de la foto de la web y escritura en fichero local
	            byte[] array = new byte[1000]; // buffer temporal de lectura.
	            int leido = is.read(array);
	            while (leido > 0) {
	                fos.write(array, 0, leido);
	                leido = is.read(array);
	            }
	            
	            // cierre de conexion y fichero.
	            is.close();
	            fos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	
			File f = new File(nombreArchivo);
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
			
				String linea, lineSeparator = System.getProperty("line.separator");
			    while((linea=br.readLine())!=null){
			       salida+=linea+lineSeparator;
			    }
			    br.close();
			    fr.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{ f.delete();}
		}
		return salida;
	}
	public static ArrayList<InformacionPieza> parseaFreeling3TaggingClient(String texto, String salidaFreeling, ArrayList<Pieza> piezas, String ... buscado){
		tabla = inicializaTablaDificiles();
		ArrayList<InformacionPieza> array = new ArrayList<InformacionPieza>();
		int iLinea = 0;
		String[] lineasSalidaFreeling = salidaFreeling.split(Constantes.END_LINE);
		boolean encontradaEtiqueta = false;
		while(iLinea<lineasSalidaFreeling.length){
			String[] lineaSalidaFreeling = lineasSalidaFreeling[iLinea].split(Constantes.TAB);
			if(lineaSalidaFreeling.length>1){ //Comprobación linea vacía
				String tipoPalabra = lineaSalidaFreeling[2];
				String inicialTipo = tipoPalabra.substring(0, 1);
				String palabra = lineaSalidaFreeling[0];
				int ini = Integer.parseInt(lineaSalidaFreeling[4]);
				int fin = Integer.parseInt(lineaSalidaFreeling[5]);
				System.out.println(palabra + " " + ini + " " + fin + " " + texto.substring(ini, fin));
				Pieza pieza = null;
				boolean dificil = false;
				//prueba += palabra + " " + tipoPalabra + " " + ini + " " + fin + Constantes.END_LINE;
				if(palabra.equalsIgnoreCase("<")){ encontradaEtiqueta = true; }
				else if (!encontradaEtiqueta){
				
					//tabla = inicializaTablaDificiles();
					if(!tabla.containsKey(palabra.toLowerCase())){ 
						dificil = true;
						
					}
					
					switch(inicialTipo.toLowerCase()){
					case Constantes.ADJETIVO: pieza=new Adjetivo(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
						break;
					case Constantes.ADVERBIO: pieza=new Adverbio(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.CONJUNCION: pieza=new Conjuncion(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.DETERMINANTE: pieza=new Determinante(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.FECHAHORA: pieza=new FechaHora(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.INTERJECCION: pieza=new Interjeccion(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.NOMBRE: pieza=new Nombre(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.NUMERAL: pieza=new Numeral(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.PREPOSICION: pieza=new Preposicion(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.PRONOMBRE: pieza=new Pronombre(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.PUNTUACION: pieza=new Puntuacion(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.TEXTO: pieza=new Texto(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					case Constantes.VERBO: pieza=new Verbo(new InformacionPieza(texto, tipoPalabra, ini, fin, dificil));  
					break;
					default: break;
					}
					
				
					

					if(dificil){ inicialTipo+=":"+Constantes.DIFICIL;}
					piezas.add(pieza);
					if(encontrado(inicialTipo,buscado)){
						array.add(pieza.getInfo());
					}
					
				}
				else if(palabra.equalsIgnoreCase(">")){ encontradaEtiqueta = false; }
				
			}
			iLinea++;
		}
		
		return array;
	}
	private static boolean encontrado(String t, String[] buscados){
		boolean encontrado = false;
		String[] split = t.split(":");
		for(int i=0; !encontrado && i<buscados.length; i++){
			for(String sp: split){
				if(sp.equalsIgnoreCase(buscados[i])){ encontrado = true; }
			}
		}
		return encontrado;
	}
	@SuppressWarnings("unused")
	private static String salidaFreeling3TaggingClient(String URL){
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
	public static Map<String, String> inicializaTablaDificiles() {
		tabla = new HashMap<String, String>();
		try{
				
	            
		
			File f = new File("/var/www/html/10000_formas.TXT");
			FileReader fr = new FileReader(f);

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			linea = br.readLine();
		
			while(linea!=null){
				String[] palabras = linea.split("\t");
				String palabra = palabras[1];
				palabra = palabra.replaceAll(" ", "");
				tabla.put(palabra, palabra);
				linea = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tabla;
	}	
	public static void main(String[] args){
		//String texto ="<p>Quijote</p>\n<h2>Sin&oacute;nimos</h2>\n<ul>	<li><strong>Quijote</strong>\n	<ol>		<li><strong>quijote: </strong>so&ntilde;ador, idealista, iluso, altruista, desinteresado</li>	</ol>	</li></ul>";
		
		String texto="<p>Quijote</p>\n<h2>Sinónimos\n</h2><ul>	<li><strong>Quijote</strong>\n	<ol>		<li><strong>quijote: </strong>soñador, idealista, iluso, altruista, desinteresado</li>	</ol>	</li></ul>";
		ArrayList<InformacionPieza> info;
		info = parseaFreeling3TaggingClient(texto, 
								runOutput(//"Dos que van corriendo llegan tarde a misa y otros dos llegan a tiempo sin ir deprisa.",
									texto, 
									"",
									"es",
									false,false,false,false,false,false,false,false,false,false,false,false,false,false),
								new ArrayList<Pieza>(), 
								Constantes.NOMBRE);
		/*for(InformacionPieza i: info){
			System.out.println(i.getTexto().substring(i.getIni(), i.getFin()) + ": " + i.getIni() + " " + i.getFin());
		}
		System.out.println(texto.substring(108, 115));
          */ 
	}
}
