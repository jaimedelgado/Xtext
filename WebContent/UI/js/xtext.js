const FIELDPIEZAS = 'fieldPiezas';
const FIELDACCIONES = 'fieldAcciones';
const BTNCARGAR = 'btnCargar';
const BTNELIMINAR = 'btnEliminar';
const BTNDESHACER = 'btnDeshacer';
const ROWS = 30*2;
const N_PIEZAS = 13;
const N_ACCIONES = 6;
//const WORDREFERENCE = "<p>&nbsp;</p><p>&nbsp;</p><h3 style=\"color:#aaa;font-style:italic;\">Resultados obtenidos de wordreference.com</h3><p>&nbsp;</p><p>&nbsp;</p>";

var contFilas = 0;
var idSesion = 0;
var texto = "";
var firstAplicar = true;
var addRef = false;
var box = new Object();
box.selected = "";
box.opposite = "";
box.row = "";
box.clicked = false;
box.nPiezas = 0;
box.nAcciones = 0;
box.nPiezAcc = 0;
box.selecId = "";
box.PAadded = new Array(ROWS);
box.PAaddedClone = new Array(ROWS);
box.deletedRows = new Array(ROWS);
box.BoxCons = function(selec, row, nPiez, nAcc){
	this.selected = selec + row;
	this.row = row;
	this.clicked = true;
	this.nPiezas = nPiez;
	this.nAcciones = nAcc;
	this.nPiezAcc = this.nPiezas + this.nAcciones;
	this.selecId = selec; 

	if(selec == FIELDPIEZAS){
		this.opposite = FIELDACCIONES + row;
	}
	else{
		this.opposite = FIELDPIEZAS + row;
	}
	
	for(i=0; i<this.PAadded.length; i++){
		this.PAadded[i] = new Array(this.nPiezAcc);
		this.PAaddedClone[i] = new Array(this.nPiezAcc);
		for(j=0; j<this.PAadded[i].length; j++){
			this.PAadded[i][j] = false;
			this.PAaddedClone[i][j] = false;
		}
	}

	for(z=0; z<this.deletedRows.length; z++){		
		this.deletedRows[z] = false;
	}
};

box.setBox = function(selec, row){
	this.selected = selec + row;
	this.selecId = selec; 

	if(selec == FIELDPIEZAS){
		this.opposite = FIELDACCIONES + row;
	}
	else{
		this.opposite = FIELDPIEZAS + row;
	}
	
	this.row = row;
	this.clicked = true;
};

box.setPAadded = function(colum, value){
	this.PAadded[this.row][colum] = value;
};

box.getSelected = function(){
	return this.selected;
};

box.getOpposite = function(){
	return this.opposite;
};

box.getRow = function(){
	return this.row;
};

box.getnPiezAcc = function(){
	return this.nPiezAcc;
};

box.getnPiezas = function(){
	return this.nPiezas;
};

box.getnAcciones = function(){
	return this.nAcciones;
};

box.getSelecId = function(){
	return this.selecId;
};

box.IsAddedPA = function(colum){	
	return this.PAadded[this.row][colum];
};

box.IsAddedPARowCol = function(row, colum){
	return this.PAadded[row][colum];
};

box.IsClicked = function(){
	return this.clicked;
};

box.changeSelecOppos = function(){
	var aux = this.selected;
	this.selected = this.opposite;
	this.opposite = aux;
	
	if(this.selecId == FIELDPIEZAS){
		this.selecId = FIELDACCIONES;	
	}
	else{
		this.selecId = FIELDPIEZAS;	
	}

};

box.getPABtnId = function(code){
	var sPA = "";
	switch(code){
			case 0:
				sPA = "btnNombres";
				break;
			case 1:
				sPA = "btnVerbos";
				break;
			case 2:
				sPA = "btnAdjetivos";
				break;
			case 3:
				sPA = "btnPronombres";
				break;
			case 4:
				sPA = "btnDeterminantes";
				break;
			case 5:
				sPA = "btnAdverbios";
				break;
			case 6:
				sPA = "btnConjunciones";
				break;
			case 7:
				sPA = "btnPreposiciones";
				break;
			case 8:
				sPA = "btnInterjecciones";
				break;
			case 9:
				sPA = "btnPalDificiles";
				break;
			case 10:
				sPA = "btnNumeral";
				break;
			case 11:
				sPA = "btnFechaHora";
				break;
			case 12:
				sPA = "btnPuntuacion";
				break;
			case 13:
				sPA = "btnSubrayar";
				break;
			case 14:
				sPA = "btnAMorfo";
				break;
			case 15:
				sPA = "btnSinonimo";
				break;
			case 16:
				sPA = "btnAntonimo";
				break;
			case 17:
				sPA = "btnDefinicion";
				break;
			case 18:
				sPA = "btnTraduccion";
				break;
			default:
				sPA = "";
				break;
		}

	return sPA;
};

box.getPALetter = function(letter){
	var lPA = "";
	switch(letter){
			case 0:
				lPA = "Nb";
				break;
			case 1:
				lPA = "Vb";
				break;
			case 2:
				lPA = "Aj";
				break;
			case 3:
				lPA = "Pn";
				break;
			case 4:
				lPA = "Dt";
				break;
			case 5:
				lPA = "Av";
				break;
			case 6:
				lPA = "Cj";
				break;
			case 7:
				lPA = "Pp";
				break;
			case 8:
				lPA = "Ij";
				break;
			case 9:
				lPA = "Pd";
				break;
			case 10:
				lPA = "Nº";
				break;
			case 11:
				lPA = "Fh";
				break;
			case 12:
				lPA = "Sp";
				break;
			case 13:
				lPA = "Sb";
				break;
			case 14:
				lPA = "Mf";
				break;
			case 15:
				lPA = "Si";
				break;
			case 16:
				lPA = "An";
				break;
			case 17:
				lPA = "Df";
				break;
			case 18:
				lPA = "Ti";
				break;
			default:
				lPA = "";
				break;
		}

	return lPA;
};

box.getPiezas = function(row){
	var sPiezas = "";

	for(i = 0; i < this.nPiezas; i++){
		if(this.PAadded[row][i]){		
			switch(i){
				case 0:
					sPiezas += "n:";
					break;
				case 1:
					sPiezas += "v:";
					break;
				case 2:
					sPiezas += "a:";
					break;
				case 3:
					sPiezas += "p:";
					break;
				case 4:
					sPiezas += "d:";
					break;
				case 5:
					sPiezas += "r:";
					break;
				case 6:
					sPiezas += "c:";
					break;
				case 7:
					sPiezas += "s:";
					break;
				case 8:
					sPiezas += "i:";
					break;
				case 9:
					sPiezas += "dificil:";
					break;
				case 10:
					sPiezas += "z:";
					break;
				case 11:
					sPiezas += "w:";
					break;
				case 12:
					sPiezas += "f:";
					break;
				default:
					sPiezas += "";
					break;
			}
		}
	}
	return sPiezas;
};

box.getAcciones = function(row){
	var sAcciones = "";
	for(j = this.nPiezas; j < this.getnPiezAcc(); j++){
		if(this.PAadded[row][j]){		
			switch(j){
				case 13:
					sAcciones += "subrayar:";
					break;
				case 14:
					sAcciones += "morfo:";
					break;
				case 15:
					sAcciones += "sinonimos:";
					break;
				case 16:
					sAcciones += "antonimos:";
					break;
				case 17:
					sAcciones += "definiciones:";
					break;
				case 18:
					sAcciones += "traducciones:";
					break;
				default:
					sAcciones += "";
					break;
			}
		}
	}
	return sAcciones;
};

box.getPAColor = function(col){
	var color = "";	
	switch(col){
				case 0:
					color = "rgb(3, 169, 244)";		//Azul claro
					break;
				case 1:
					color = "rgb(67, 224, 56)";		//Verde claro
					break;
				case 2:
					color = "rgb(224, 164, 56)";		//Naranja tierra
					break;
				case 3:
					color = "rgb(224, 70, 217)";		//Fucsia
					break;
				case 4:
					color = "rgb(82, 208, 199)";		//Aqua
					break;
				case 5:
					color = "rgb(204, 232, 27)";		//Lima
					break;
				case 6:
					color = "rgb(253, 175, 6)";		//Naranja claro
					break;
				case 7:
					color = "rgb(146, 103, 221)";		//Morado claro
					break;
				case 8:
					color = "rgb(226, 212, 69)";		//Amarillo apagado
					break;
				case 9:
					color = "rgb(113, 187, 153)";		//Verde apagado
					break;
				case 10:
					color = "rgb(126, 88, 19)";		//Ocre
					break;
				case 11:
					color = "rgb(228, 97, 74)";		//Naranja
					break;
				case 12:
					color = "rgb(3, 169, 244)";		//Acul claro
					break;
				case 13:
					color = "rgb(229, 218, 12)";		//Amarillo claro
					break;
				case 14:
					color = "rgb(62, 128, 158)";		//Azul grisaceo
					break;
				case 15:
					color = "rgb(244, 25, 42)";		//Rojo
					break;
				case 16:
					color = "rgb(36, 178, 81)";		//Verde oscuro
					break;
				case 17:
					color = "rgb(72, 219, 214)";		//Azul claro
					break;
				case 18:
					color = "rgb(203, 96, 210)";		//Morado fucsia
					break;
				default:
					break;
			}
	return color;
};

box.cloneRow = function(row){
	for(l=0; l<this.nPiezAcc; l++){
		this.PAaddedClone[row][l] = this.PAadded[row][l];	
	}
};

box.comparePAaddedClone = function(row){
	var diferencia = false;
	for(v=0; v<this.nPiezAcc && !diferencia; v++){
		if(this.PAaddedClone[row][v] != this.PAadded[row][v]){
			diferencia = true;
		}	
	}
	return diferencia;
};

box.IsWordReference = function(pos){
	var encontrado = false;
	for(j = this.nPiezas; j < this.getnPiezAcc() && !encontrado; j++){
		if(this.PAadded[pos][j]){		
			switch(j){
				case 13:
					encontrado = false;
					break;
				case 14:
					encontrado = false;
					break;
				case 15:
					encontrado = true;
					break;
				case 16:
					encontrado = true;
					break;
				case 17:
					encontrado = true;
					break;
				case 18:
					encontrado = true;
					break;
				default:
					encontrado = false;
					break;
			}
		}
	}
	return encontrado;
};

box.deleteRow = function(row){
	this.deletedRows[row] = true;
};

box.IsDeletedRow = function(row){
	return this.deletedRows[row];
};

$(document).ready(function() {
	$.material.init();
	
	// Replace the <textarea id="editor1"> with an CKEditor instance.
	CKEDITOR.replace( 'editor1', {
		on: {
			focus: onFocus,
			blur: onBlur,

			// Check for availability of corresponding plugins.
			pluginsLoaded: function( evt ) {
				var doc = CKEDITOR.document, ed = evt.editor;
				if ( !ed.getCommand( 'bold' ) )
					doc.getById( 'exec-bold' ).hide();
				if ( !ed.getCommand( 'link' ) )
					doc.getById( 'exec-link' ).hide();
			}
		}
	});

	var d = new Date();
	idSesion = d.getTime();
	box.BoxCons(FIELDPIEZAS, contFilas, N_PIEZAS, N_ACCIONES);
	$("#btnAplicar").hide();
	$("#loaderResp").hide();
	$("#btnPuntuacion").hide();
	document.getElementById(box.getSelected()).style.boxShadow = "0px 3px 10px rgba(24, 255, 255, 0.45),0 3px 10px rgba(0, 0, 0, 0.57)";
	document.getElementById(box.getSelected()).style.minHeight = "5.6em";
	document.getElementById("tabAcciones").style.borderBottom = "2px solid #6E6E6E";
	reloadPanel();

	$("#masFilas").click(function() {
		if(contFilas < ROWS){
			contFilas++;
			document.getElementById("containerFilas").innerHTML += "<div class=\"row\" id=\"fila"+ contFilas +"\"><div class=\"col-xs-5 col-sm-5 col-md-6\"><fieldset id=\"fieldPiezas"+ contFilas +"\" onclick=\"selectBox('fieldPiezas'," + contFilas + ",'P'" +")\"></fieldset></div><div class=\"col-xs-5 col-sm-5 col-md-5\"><fieldset  id=\"fieldAcciones"+ contFilas +"\" onclick=\"selectBox('fieldAcciones'," + contFilas + ",'A'" +")\"></fieldset></div><div class=\"col-xs-2 col-sm-2 col-md-1\"><button type=\"button\" class=\"btn btn-success btn-fab btn-raised\" id=\"btnDeshacer"+ contFilas +"\" onclick=\"undoRow("+ contFilas +")\"><span class=\"glyphicon glyphicon-minus\"></span></button></div></div>";		
			selectBox(FIELDPIEZAS, contFilas, "P");
		}
	});	

	$("#titlePiezas").click(function() {
		$("#acciones").hide();		
		$("#piezas").show();
		document.getElementById(box.getSelected()).style.boxShadow = "rgba(0, 0, 0, 0.227451) 0px 1px 6px, rgba(0, 0, 0, 0.156863) 0px 0px 0px";
		document.getElementById(box.getSelected()).style.minHeight = "3.5em";
		if(box.getSelecId() != FIELDPIEZAS)	{
			box.changeSelecOppos();
		}
		document.getElementById(box.getSelected()).style.minHeight = "5.6em";
		document.getElementById(box.getSelected()).style.boxShadow = "0px 3px 10px rgba(24, 255, 255, 0.45),0 3px 10px rgba(0, 0, 0, 0.57)";
		document.getElementById("tabPiezas").style.borderBottom = "none";
		document.getElementById("tabAcciones").style.borderBottom = "2px solid #6E6E6E";
	});

	$("#titleAcciones").click(function() {
		$("#piezas").hide();
		$("#acciones").show();
		document.getElementById(box.getSelected()).style.boxShadow = "rgba(0, 0, 0, 0.227451) 0px 1px 6px, rgba(0, 0, 0, 0.156863) 0px 0px 0px";
		document.getElementById(box.getSelected()).style.minHeight = "3.5em";
		if(box.getSelecId() != FIELDACCIONES){	
			box.changeSelecOppos();
		}
		document.getElementById(box.getSelected()).style.minHeight = "5.6em";
		document.getElementById(box.getSelected()).style.boxShadow = "0px 3px 10px rgba(24, 255, 255, 0.45),0 3px 10px rgba(0, 0, 0, 0.57)";
		document.getElementById("tabAcciones").style.borderBottom = "none";
		document.getElementById("tabPiezas").style.borderBottom = "2px solid #6E6E6E";
	});

	$("#btnNombres").click(function() {
		clickPAFunction(0, "btnNombres", "Nb");	
	});
	
	$("#btnVerbos").click(function() {
		clickPAFunction(1, "btnVerbos", "Vb");	
	});
	
	$("#btnAdjetivos").click(function() {
		clickPAFunction(2, "btnAdjetivos", "Aj");
	});
	
	$("#btnPronombres").click(function() {
		clickPAFunction(3, "btnPronombres", "Pn");
	});
	
	$("#btnDeterminantes").click(function() {
		clickPAFunction(4, "btnDeterminantes", "Dt");
	});

	$("#btnAdverbios").click(function() {
		clickPAFunction(5, "btnAdverbios", "Av");
	});

	$("#btnConjunciones").click(function() {
		clickPAFunction(6, "btnConjunciones", "Cj");
	});

	$("#btnPreposiciones").click(function() {
		clickPAFunction(7, "btnPreposiciones", "Pp");
	});

	$("#btnInterjecciones").click(function() {
		clickPAFunction(8, "btnInterjecciones", "Ij");
	});
	
	$("#btnPalDificiles").click(function() {
		clickPAFunction(9, "btnPalDificiles", "Pd");
	});

	$("#btnNumeral").click(function() {
		clickPAFunction(10, "btnNumeral", "Nº");
	});

	$("#btnFechaHora").click(function() {
		clickPAFunction(11, "btnFechaHora", "Fh");
	});

	$("#btnPuntuacion").click(function() {
		clickPAFunction(12, "btnPuntuacion", "Sp");
	});

	$("#btnSubrayar").click(function() {
		clickPAFunction(13, "btnSubrayar", "Sb");
	});

	$("#btnAMorfo").click(function() {
		clickPAFunction(14, "btnAMorfo", "Mf");
	});

	$("#btnSinonimo").click(function() {
		clickPAFunction(15, "btnSinonimo", "Si");
	});

	$("#btnAntonimo").click(function() {
		clickPAFunction(16, "btnAntonimo", "An");
	});

	$("#btnDefinicion").click(function() {
		clickPAFunction(17, "btnDefinicion", "Df");
	});

	$("#btnTraduccion").click(function() {
		clickPAFunction(18, "btnTraduccion", "Ti");
	});

	$("#btnAplicar").click(function() {
		var piezas = "";
		var acciones = "";
		var operacion = "aplicar";
		var sesion = 0;
		var filasJson = new Array(contFilas+1);
		var coma = '';
		var entrado = false;

		if(GetContents() == ""){
			alert("Debes introducir texto.");
		}
		else{
			hideAplicar();
						
			//if(firstAplicar){
			texto = GetContents();
			//	firstAplicar = false;
		//	}

			for(k = 0; k <= contFilas; k++){

				if(!box.IsDeletedRow(k)){
					
					piezas = box.getPiezas(k);
					acciones = box.getAcciones(k);
					sesion = idSesion + k;		
				
					if((piezas == "") && (acciones != "")){
						document.getElementById(FIELDPIEZAS+k).style.background = "rgba(253, 6, 6, 0.34)";
					}
					else{
						if((piezas != "") && (acciones == "")){
							document.getElementById(FIELDACCIONES+k).style.background = "rgba(253, 6, 6, 0.34)";
						}
						else{
							if((piezas == "") && (acciones == "")){
								document.getElementById(FIELDPIEZAS+k).style.background = "rgba(253, 6, 6, 0.34)";
								document.getElementById(FIELDACCIONES+k).style.background = "rgba(253, 6, 6, 0.34)";
							}
							else{
								//if(box.comparePAaddedClone(k)){
									
									if(entrado){
										coma = ',';
									}else{
										coma = '';
										entrado = true;
									}
									
									filasJson[k] = {piezas: piezas , acciones: acciones, id: sesion};
									//alert(texto);
									//alert(filasJson);
									//if(box.IsWordReference(k)){
									//	addRef = true;
									//}
								//	box.cloneRow(k);
								//}
							}
						}
					}
				}
			}

			if(entrado){
				var options = {
		    		"backdrop" : "static"
				}
					
				$('#myModal').modal(options);
				$('#myModal').modal('show');
				
				var msg = {texto: texto, filas: filasJson};
				//alert(msg);
				var objmsg = JSON.stringify(msg);
				//alert(objmsg);
				var uri = "http://sesat.fdi.ucm.es:8080/xtextws/rest/XtextWS/chooseService";
						
				$.ajax({
					type: "POST",
					url: uri,
					contentType: "application/json; charset=UTF-8",
					data: objmsg,
					success: function(resp) {
						//if(addRef){
						//SetContents(resp+WORDREFERENCE);					
						//}
						//else{
						SetContents(resp);
						$('#myModal').modal('hide');
						$.mobile.changePage("#pagPrincipal", "slidedown");
						//}
					},
					error: function(e) {
						alert("Error: " + e);
					}
				});
			}
			hideAplicar();
		}
	});
});

function clickPAFunction(codePA, idPA, letra){
	document.getElementById(box.getSelected()).style.background	= "#fff";
	showAplicar();
		if(box.IsAddedPA(codePA)){
			document.getElementById(idPA).style.background = box.getPAColor(codePA);
			var id = document.getElementById(idPA + box.getRow());
			var padre = id.parentNode;
			padre.removeChild(id);
			box.setPAadded(codePA, false);
		}
		else{
			document.getElementById(idPA).style.background = "rgb(74, 74, 74)";
			
			if(codePA < 13){
				document.getElementById(box.getSelected()).innerHTML += "<button type=\"button\" class=\"btn btn-info btn-fab btn-raised\" id=\""+ idPA + box.getRow() +"\" onclick=\"deletePAFunction('"+ codePA +"','" + idPA + "','"+ FIELDPIEZAS +"',"+ box.getRow() +",'P')\">"+ letra +"</button>";
			}
			else{
				document.getElementById(box.getSelected()).innerHTML += "<button type=\"button\" class=\"btn btn-info btn-fab btn-raised\" id=\""+ idPA + box.getRow() +"\" onclick=\"deletePAFunction('"+ codePA +"','" + idPA + "','"+ FIELDACCIONES +"',"+ box.getRow() +",'A')\">"+ letra +"</button>";
			}
			document.getElementById(idPA+box.getRow()).style.background = box.getPAColor(codePA);
			box.setPAadded(codePA, true);
		}
}

function deletePAFunction(codePA, idPA, idbox, row, type){
	showAplicar();
	selectBox(idbox, row, type);
	document.getElementById(idPA).style.background = box.getPAColor(codePA);
	var id = document.getElementById(idPA+row);
	var padre = id.parentNode;
	padre.removeChild(id);
	box.setPAadded(codePA, false);
}


function selectBox(idbox, row, type){
	if(document.getElementById(box.getSelected())){
		document.getElementById(box.getSelected()).style.boxShadow = "rgba(0, 0, 0, 0.227451) 0px 1px 6px, rgba(0, 0, 0, 0.156863) 0px 0px 0px";	
		document.getElementById(box.getSelected()).style.minHeight = "3.5em";	
	}
	box.setBox(idbox, row);	

	document.getElementById(box.getSelected()).style.boxShadow = "0px 3px 10px rgba(24, 255, 255, 0.45),0 3px 10px rgba(0, 0, 0, 0.57)";
	
	document.getElementById(box.getSelected()).style.minHeight = "5.6em";

	reloadPanel();

	$("#navBarTabs").show();
	
	if(type == "P"){		
		$("#tabPiezas").addClass("active");
		$("#titlePiezas").attr({
			'aria-expanded': 'true'		
		});
		$("#piezas").addClass("tab-pane active");

		$("#tabAcciones").removeClass("active");
		$("#titleAcciones").attr({
			'aria-expanded': 'false'		
		});
		$("#acciones").addClass("tab-pane");
		
		$("#piezas").show();
		$("#acciones").hide();
		document.getElementById("tabPiezas").style.borderBottom = "none";
		document.getElementById("tabAcciones").style.borderBottom = "2px solid #6E6E6E";
	}
	else{
		$("#tabAcciones").addClass("active");
		$("#titleAcciones").attr({
			'aria-expanded': 'true'		
		});
		$("#acciones").addClass("tab-pane active");
		$("#tabPiezas").removeClass("active");
		$("#titlePiezas").attr({
			'aria-expanded': 'false'		
		});
		$("#piezas").addClass("tab-pane");
		
		$("#acciones").show();
		$("#piezas").hide();
		document.getElementById("tabAcciones").style.borderBottom = "none";
		document.getElementById("tabPiezas").style.borderBottom = "2px solid #6E6E6E";
	}
}

function undoRow(row){
	showAplicar();
	var id = document.getElementById("fila"+row);
	if(id){								
		var padre = id.parentNode;
		padre.removeChild(id);
		box.deleteRow(row);
	}
}

//Actualiza los colores de las piezas/acciones del panel
function reloadPanel(){
	var idPA = "";
	for(i=0; i<box.getnPiezAcc(); i++){
		idPA = box.getPABtnId(i);
		if(idPA != ""){
			if(box.IsAddedPA(i))
				document.getElementById(idPA).style.background = "rgb(74, 74, 74)";
			else
				document.getElementById(idPA).style.background = box.getPAColor(i);
		}
	}
}

function reloadPABoxes(){
	var idPA = "";
	for(i=0; i<box.getnPiezAcc(); i++){
		idPA = box.getPABtnId(i);
		if(idPA != ""){
			if(box.IsAddedPA(i)){
				$("#"+idPA).show();	
			}		
		}
	}
}

function showAplicar(){
	$("#btnAplicar").show();
};

function hideAplicar(){
	$("#btnAplicar").hide();
};

/*function RefreshRow(idRow){
	var piezas = box.getPiezas(idRow);
	var acciones = box.getAcciones(idRow);

	alert("Fila: "+ idRow +"\nPiezas -> "+ piezas + "\nAcciones -> " + acciones);
			
	if((piezas != "") && (acciones != "")){
		var aux = {texto: "", piezas: piezas, acciones: acciones, fila: idRow, op: "cargar"};
		var objAux = JSON.stringify(aux);
		var uri = "http://sesat.fdi.ucm.es:8080/Xtext/rest/XtextWS/chooseService";

		$.ajax({
			type: "POST",
			url: uri,
			contentType: "application/json; charset=UTF-8",
			data: objAux,
			success: function(resp) {
				alert(resp);
				SetContents(resp);
			},
			error: function(e) {
				alert("Error: " + e);
			}
		});
	}
}*/

/*
function BoxResalt(){
	document.getElementById("fieldAcciones0").style.boxShadow = "0px 1px 6px rgba(24, 255, 255, 0.45),0 1px 6px rgba(0, 0, 0, 0.57)";
}

function BoxNoResalt(){
	document.getElementById("fieldAcciones0").style.boxShadow = "rgba(0, 0, 0, 0.227451) 0px 1px 6px, rgba(0, 0, 0, 0.156863) 0px 0px 0px";
}*/


CKEDITOR.on( 'instanceReady', function( ev ) {
});

function InsertHTML(value) {
	var editor = CKEDITOR.instances.editor1;

	if ( editor.mode == 'wysiwyg' ){
		editor.insertHtml( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function InsertText(value) {
	var editor = CKEDITOR.instances.editor1;

	if ( editor.mode == 'wysiwyg' ){
		editor.insertText( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function SetContents(value) {
	var editor = CKEDITOR.instances.editor1;
	editor.setData( value );
}

function GetContents() {
	var editor = CKEDITOR.instances.editor1;
	return editor.getData();
}

function ExecuteCommand( commandName ) {
	var editor = CKEDITOR.instances.editor1;
	
	if ( editor.mode == 'wysiwyg' ){
		editor.execCommand( commandName );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}
		
function CheckDirty() {
	var editor = CKEDITOR.instances.editor1;
	alert( editor.checkDirty() );
}

function ResetDirty() {
	var editor = CKEDITOR.instances.editor1;
	editor.resetDirty();
	alert( 'The "IsDirty" status has been reset' );
}

function Focus() {
	CKEDITOR.instances.editor1.focus();
}

function onFocus() {
}

function onBlur() {
}
