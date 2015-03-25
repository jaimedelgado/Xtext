package client;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Holder;

import org.soaplab.morphosintactic_tagging.freeling3_tagging.Freeling3Tagging;
import org.soaplab.morphosintactic_tagging.freeling3_tagging.Freeling3TaggingService;
import org.soaplab.typedws.JobId;

public class Freeling3TaggingClient {
	public Freeling3TaggingClient(){}
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
		
		if(URL!=null){
			try {
	            // Url con la foto
	            URL url = new URL(URL);
	            // establecemos conexion
	            URLConnection urlCon = url.openConnection();
	 
	            // Se obtiene el inputStream de la foto web y se abre el fichero
	            // local.
	            InputStream is = urlCon.getInputStream();
	            FileOutputStream fos = new FileOutputStream("SalidaFreeling.html");
	 
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
		
	
			File f = new File("SalidaFreeling.html");
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
			    f.delete();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salida;
	}
	public static void main(String[] args){
		System.out.println(runOutput(//"Dos que van corriendo llegan tarde a misa y otros dos llegan a tiempo sin ir deprisa.",
									"", 
									"http://sesat.fdi.ucm.es/texto.html",
									"es",
									false,false,false,false,false,false,false,false,false,false,false,false,false,false));
           
	}
}
