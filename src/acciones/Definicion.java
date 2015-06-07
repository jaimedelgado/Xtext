package acciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import piezas.InformacionPieza;
import piezas.Pieza;
import Utils.Constantes;

import com.Xtext.restws.Ejecucion;

public class Definicion extends Accion{
	private Map<String, String> ejecutadas;

	@Override
	public String ejecuta(Pieza ... piezas) {
		ejecutadas = new HashMap<String, String>();
		String texto="";
		if(piezas.length>0){ texto+="<h2>Definiciones</h2><ul>";}
		for(Pieza p: piezas){
			String palabra = p.getTexto().substring(p.getIni(), p.getFin());
			String resultado = this.define(palabra);
			if(!resultado.equals("")){ palabra+=": "; }
			if(!ejecutadas.containsKey(palabra)){
				ejecutadas.put(palabra, resultado);
				texto+="<li><strong>" + palabra + "</strong>" + resultado + "</li>";
			}
		}
		if(piezas.length>0){ texto+="</ul>";}
		return texto;
	}
	
	@Override
	public String deshace(Ejecucion ejecucion, String ... piezas) {
		ArrayList<InformacionPieza> info = ejecucion.getInfoPiezas(), infoAux=new ArrayList<InformacionPieza>();
		boolean igual=false;
		for(InformacionPieza i: info){
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
		return define(infoAux.toArray(new InformacionPieza[infoAux.size()]));
	}	
	private String define(InformacionPieza... indices) {
		String texto = "";
		if(indices.length>0){ texto = "<h2>Definiciones</h2><ul>";}
		for(InformacionPieza i: indices){
			String palabra = texto.substring(i.getIni(), i.getFin());
			texto+="<li><strong>" + palabra + "</strong></li>" + this.define(palabra);
		}
		if(indices.length>0){ texto+="</ul>";}
		return texto;
	}
	public String peticion(String URL){
		String texto ="";
		try {

            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
			con.setRequestMethod("GET");
			//con.setRequestProperty("Cookie", this.getCookie(URL));
			//con.setRequestProperty("Host", "servicios.elpais.com");
			//con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0");		
			//con.setRequestProperty("Acept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			//con.setRequestProperty("Accept-Language", "es");
			//con.setRequestProperty("Accept-Encoding", "identity");
			//con.setRequestProperty("Connection", "keep-alive");
			//con.setRequestProperty("DNT", "1");
			con.setRequestProperty("Accept-Charset", "UTF-8");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine1;
			int i=0;
			StringBuffer response = new StringBuffer();
			while ((inputLine1 = in.readLine()) != null) {
				response.append(i + " " + inputLine1 + Constantes.END_LINE);
				i++;
			}
			in.close();
			texto = response.toString();
			//System.out.println(texto);
		 } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("MAL2");
	     }
		return texto;
		
	}
	
	private String define(String palabra) {
		String URL = "http://www.wordreference.com/definicion/" + palabra;
		String texto = this.peticion(URL);
		System.out.println(texto);
		StringBuffer buf = new StringBuffer(texto);
		String resultado = "";
		String aBuscar = "<ol class='entry'>";
		int maxResultados=5;
		int ini = buf.indexOf(aBuscar), fin=0;
		boolean primeraPalabra=true;
		while(ini!=-1 & maxResultados!=1){
			fin = buf.indexOf("</ol>", ini);
			String[] split = buf.substring(ini, fin).split("<li>");
			for(int i=1; maxResultados>0 && i<split.length; i++){
				String[] division = split[i].split(":");
				if(division.length>1){
					//System.out.println(division[0]);
					String[] division2 = division[0].split("\\. ");
					if(primeraPalabra){
						primeraPalabra=false;
						resultado+=division2[division2.length-1];
					}else{
						resultado+="; " + division2[division2.length-1];
					}
					
				}else{
					//System.out.println(division[0]);
					String[] division2 = division[0].split("\\. ");
					if(primeraPalabra){
						primeraPalabra=false;
						resultado+=division2[division2.length-1];
					}else{
						resultado+="; " + division2[division2.length-1];
					}
				}
				maxResultados--;
			}
			ini = buf.indexOf(aBuscar, fin);
			/*while(fin!=-1 && maxResultados!=1){
				resultado += buf.substring(ini, fin).replaceAll("<br>", "");
				System.out.println(resultado);
				maxResultados--;
				ini = fin;
				fin = buf.indexOf("<li>", fin+"li>".length());
				int classentrySig = buf.indexOf(aBuscar, ini+"li>".length());
				while(classentrySig>fin){
					fin = buf.indexOf("<li>", fin+"li>".length());
					classentrySig = buf.indexOf(aBuscar, ini+"li>".length());
				}
				//if(classentrySig<=fin){maxResultados--;}
				 
				 
			}*/
			
		}

		return resultado.replaceAll("  ", " ").replaceAll("<br>", "");
	}
	
	public static void main(String arg[]){
		Definicion t = new Definicion();
		System.out.println(t.define("equipo"));
	}
}