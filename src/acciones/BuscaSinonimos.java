package acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import piezas.InformacionPieza;
import piezas.Pieza;
import Utils.Constantes;

import com.Xtext.restws.Ejecucion;

public class BuscaSinonimos extends Accion{
	private Map<String, String> ejecutadas;

	@Override
	public String ejecuta(Pieza ... piezas) {
		ejecutadas = new HashMap<String, String>();
		String texto="";
		if(piezas.length>0){ texto+="<h2>Sinónimos</h2><ul>";}
		for(Pieza p: piezas){
			String palabra = p.getTexto().substring(p.getIni(), p.getFin());
			String resultado = this.buscaSinonimos(palabra);
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
		return buscaSinonimos(infoAux.toArray(new InformacionPieza[infoAux.size()]));
	}
	private String buscaSinonimos(InformacionPieza... indices) {
		String texto = "";
		if(indices.length>0){ texto = "<h2>Sinónimos</h2><ul>";}
		for(InformacionPieza i: indices){
			String palabra = texto.substring(i.getIni(), i.getFin());
			texto+="<li><strong>" + palabra + "</strong></li>" + this.buscaSinonimos(palabra);
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
			con.disconnect();
			//System.out.println(texto);
		 } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("MAL2");
	     }
		return texto;
		
	}
	
	private String buscaSinonimos (String palabra1){
		String URL = "http://www.wordreference.com/sinonimos/" + palabra1;
		String texto = this.peticion(URL);
		//System.out.println(texto);
		StringBuffer buf = new StringBuffer(texto);
		String resultado = "";
		String aBuscar = "class='trans clickable'><h3>";
		int maxResultados=3;
		int ini = buf.indexOf(aBuscar), fin=0;
		while(ini!=-1 & maxResultados!=0){
			aBuscar = "</h3>";
			fin = buf.indexOf(aBuscar, ini+"class='trans clickable'><h3>".length());
			resultado += "(<em>" + buf.substring(ini+"class='trans clickable'><h3>".length(),  fin) + "</em>) ";  //nombre, expresion
			ini = fin+"</h3><ul><li>".length();
			fin = buf.indexOf("</li>", ini);
			resultado += buf.substring(ini, fin);
			aBuscar = "class='trans clickable'><h3>";
			ini = buf.indexOf(aBuscar, fin);
			while(ini!=-1 && maxResultados!=0){
				aBuscar = "</h3>";
				fin = buf.indexOf(aBuscar, ini+"class='trans clickable'><h3>".length());
				resultado += "; (<em>" + buf.substring(ini+"class='trans clickable'><h3>".length(),  fin) + "</em> )";  //nombre, expresion
				ini = fin+"</h3><ul><li>".length();
				fin = buf.indexOf("</li>", ini);
				resultado += buf.substring(ini, fin);
				aBuscar = "class='trans clickable'><h3>";
				ini = buf.indexOf(aBuscar, fin);
				maxResultados--;
			}
			//resultado+="</li></ol>";
		}
		return resultado;
	}
	
	
	public static void main(String arg[]){
		BuscaSinonimos t = new BuscaSinonimos();
		System.out.println(t.buscaSinonimos("casa"));
	}
}