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

public class BuscaAntonimos extends Accion{
	private Map<String, String> ejecutadas;

	@Override
	public String ejecuta(Pieza ... piezas) {
		ejecutadas = new HashMap<String, String>();
		String texto="";
		if(piezas.length>0){ texto+="<h2>Antónimos</h2><ul>";}
		for(Pieza p: piezas){
			String palabra = p.getTexto().substring(p.getIni(), p.getFin());
			String resultado = this.buscaAntonimos(palabra);
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
		return buscaAntonimos(infoAux.toArray(new InformacionPieza[infoAux.size()]));
	}	
	private String buscaAntonimos(InformacionPieza... indices) {
		String texto = "";
		if(indices.length>0){ texto = "<h2>Antónimos</h2><ul>";}
		for(InformacionPieza i: indices){
			String palabra = texto.substring(i.getIni(), i.getFin());
			texto+="<li><strong>" + palabra + "</strong></li>" + this.buscaAntonimos(palabra);
		}
		if(indices.length>0){ texto+="</ul>";}
		return texto;
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
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
	
	private String buscaAntonimos (String palabra1){
		String[] split = palabra1.split(" ");
		String URL = "http://www.wordreference.com/sinonimos/" + palabra1;
		/*for(String spl: split){
			URL+=spl+"%20%";
		}*/
		String texto = this.peticion(URL);
		System.out.println(texto);
		StringBuffer buf = new StringBuffer(texto);
		String resultado = "";
		String aBuscar = "class='trans clickable'><h3>";
		int maxResultados=3;
		int ini = buf.indexOf(aBuscar);
		
		while(ini!=-1 & maxResultados!=0){
			int fin = buf.indexOf("<", ini+aBuscar.length());
			String palabra = buf.substring(ini+aBuscar.length(), fin);
			int iniAntonimos = buf.indexOf("Antónimos", ini);
			int iniSiguiente = buf.indexOf(aBuscar, fin);
			if((iniAntonimos!=-1 && iniSiguiente!=-1 && iniAntonimos<iniSiguiente)||(iniAntonimos!=-1 && iniSiguiente==-1)){ 
				resultado += "(<em>" + palabra + "</em>) "; }
			while((iniAntonimos!=-1 && iniSiguiente!=-1 && iniAntonimos<iniSiguiente)||(iniAntonimos!=-1 && iniSiguiente==-1)){
				iniAntonimos = iniAntonimos+"Antónimos: ".length();
				int finAntonimos = buf.indexOf("<", iniAntonimos);
				resultado += buf.substring(iniAntonimos, finAntonimos) ;
				iniAntonimos = buf.indexOf("Antónimos", finAntonimos);
				if((iniAntonimos!=-1 && iniSiguiente!=-1 && iniAntonimos<iniSiguiente)||(iniAntonimos!=-1 && iniSiguiente==-1)){
					resultado += ", ";
				}
			}
			ini = buf.indexOf(aBuscar, fin);
			fin = buf.indexOf("<", ini+aBuscar.length());
			palabra = buf.substring(ini+aBuscar.length(), fin);
			//resultado += "</li></ol>";
		}
		return resultado;
	}
	
	
	public static void main(String arg[]){
		BuscaAntonimos t = new BuscaAntonimos();
		System.out.println(t.buscaAntonimos("es"));
	}
}