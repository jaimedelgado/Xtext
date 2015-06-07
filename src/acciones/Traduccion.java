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

public class Traduccion extends Accion{
	private Map<String, String> ejecutadas;

	@Override
	public String ejecuta(Pieza ... piezas) {
		ejecutadas = new HashMap<String, String>();
		String texto="";
		if(piezas.length>0){ texto+="<h2>Traducciones</h2><ul>";}
		for(Pieza p: piezas){
			String palabra = p.getTexto().substring(p.getIni(), p.getFin());
			String resultado = this.traduce(palabra);
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
		return traduce(infoAux.toArray(new InformacionPieza[infoAux.size()]));
	}	
	private String traduce(InformacionPieza... indices) {
		String texto="";
		if(indices.length>0){ texto+="<h2>Traducciones</h2><ul>";}
		for(InformacionPieza i: indices){
			String palabra = texto.substring(i.getIni(), i.getFin());
			texto+="<li><strong>" + palabra + "</strong></li>" + this.traduce(palabra);
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
	
	private String traduce (String palabra1){
		String URL = "http://www.wordreference.com/es/en/translation.asp?spen=" + palabra1;
		String texto = this.peticion(URL);
		StringBuffer buf = new StringBuffer(texto);
		String resultado = "";
		String aBuscar = "<td class='FrWrd'>";
		int maxResultados=5;
		int ini = buf.indexOf(aBuscar) + aBuscar.length(), iniAux=ini;
		boolean salir = false, primeraPalabra=true;
		while(!salir && ini!=-1 & maxResultados!=0 && ini>=iniAux){
			int fin = buf.indexOf("/strong>", ini)+ "/strong>".length();
			//System.out.println("INI: " + ini + ", FIN: " + fin + ", " + buf.substring(ini, fin));
			if(ini!=-1&&fin!=-1&&ini<fin&&ini>=iniAux){
				String palabra = buf.substring(ini, fin).replaceAll("<br>", "");
				System.out.print("Palabra: " + palabra);
				aBuscar = "</em></td><td> (";
				ini = buf.indexOf(aBuscar, fin) + aBuscar.length()-1;
				fin = buf.indexOf("<", ini);
				//System.out.println("INI: " + ini + ", FIN: " + fin + ", " + buf.substring(ini, fin));
				if(ini!=-1&&fin!=-1&&ini<fin&&ini>=iniAux){
					String definicion = buf.substring(ini, fin);
					System.out.print(", Definicion: " + definicion);
					aBuscar = "<td class='ToWrd' >";
					ini = buf.indexOf(aBuscar, fin) + aBuscar.length();
					fin = buf.indexOf("<", ini) -1;
					//System.out.println("INI: " + ini + ", FIN: " + fin + ", " + buf.substring(ini, fin));
					if(ini!=-1&&fin!=-1&&ini<fin&&ini>=iniAux){
						String traduccion = buf.substring(ini, fin);
						System.out.println(", Traduccion: " + traduccion);
						if(primeraPalabra){
							resultado += "<em>" + traduccion + "</em>" + " " + definicion ;
							primeraPalabra=false;
						}else{
							resultado += "; " + "<em>" + traduccion + "</em>" + " " + definicion ;
						}
						//resultado += traduccion + " " + definicion + ": " + traduccion + ";" + Constantes.END_LINE;
						aBuscar = "<td class='FrWrd'>";
						maxResultados--;
						ini = buf.indexOf(aBuscar, fin) + aBuscar.length();
						//System.out.println("INI: " + ini + ", FIN: " + fin + ", " + buf.substring(ini, fin));
					}else{
						//System.out.println("INI: " + ini + ", FIN: " + fin + ", " + buf.substring(ini, fin));
						salir=true;
					}
				}else{
					salir=true;
				}
			}else{
				salir=true;
			}
		}
		return resultado;
	}
	
	
	public static void main(String arg[]){
		Traduccion t = new Traduccion();
		System.out.println(t.traduce("casa"));
	}
}