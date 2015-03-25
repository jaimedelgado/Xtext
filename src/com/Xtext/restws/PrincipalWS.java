package com.Xtext.restws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

import Funciones.Subrayado;
import Utils.Constantes;

/**
* 
* @author Jaime Delgado y Juan Luis García
* 
*/

//@Path here defines class level path. Identifies the URI path that 
//a resource class will serve requests for.
@Path("XtextWS")
public class PrincipalWS {
	
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	//@Path("/t/{texto}/p/{piezas}/a/{acciones}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces("text/plain")
	//@Produces(MediaType.TEXT_PLAIN)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String elegirServicio(@QueryParam("texto") String texto, @QueryParam("piezas") String piezas, @QueryParam("acciones") String acciones) {
		Subrayado subrayado = new Subrayado();
		String[] tipo = piezas.split(":");
		
		//tipo = this.parseaTipoPieza(tipo);
		return subrayado.subraya(texto, tipo);
	}
	/*
	@GET
	@Path("/newService")
	@Consumes("aplication/json")
	@Produces("text/plain")
	public String newService(String json) {
		return json;
	}*/
	
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
}