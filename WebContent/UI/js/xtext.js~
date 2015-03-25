var piezas = "";
var acciones = "";

$(document).ready(function() {
	$.material.init();

	/*$("#navBarTabs").hide();*/
	
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
	
	/*	
	$("#fieldPiezas").click(function() {
		$("#navBarTabs").show();
	});*/

	$("#btnPalabras").click(function() {
		piezas = piezas + "pal:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnPalabras\">P</button>";
	});
	
	$("#btnNombres").click(function() {
		piezas = piezas + "n:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnNombres\">N</button>";
	});
	
	$("#btnVerbos").click(function() {
		piezas = piezas + "v:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnVerbos\">V</button>";
	});
	
	$("#btnAdjetivos").click(function() {
		piezas = piezas + "a:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnAdjetivos\">A</button>";
	});
	
	$("#btnPronombres").click(function() {
		piezas = piezas + "p:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnPronombres\">P</button>";
	});
	
	$("#btnDeterminantes").click(function() {
		acciones = acciones + "d:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnDeterminantes\">D</button>";
	});

	$("#btnAdverbios").click(function() {
		acciones = acciones + "r:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnAdverbios\">D</button>";
	});

	$("#btnConjunciones").click(function() {
		acciones = acciones + "c:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnConjunciones\">C</button>";
	});

	$("#btnPreposiciones").click(function() {
		acciones = acciones + "s:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnPreposiciones\">P</button>";
	});

	$("#btnInterjecciones").click(function() {
		acciones = acciones + "i:";
		document.getElementById("fieldPiezas").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnInterjecciones\">I</button>";
	});
	
	$("#btnSubrayar").click(function() {
		acciones = acciones + "subrayar:";
		document.getElementById("fieldAcciones").innerHTML += "<button type=\"button\" class=\"btn btn-primary\" id=\"btnSubrayar\">S</button>";
	});

	$("#btnAplicar").click(function() {
		var texto = GetContents();
		alert(texto + piezas + acciones);
		if((texto != "") && (piezas != "") && (acciones != "")){
			//var aux = {"texto": texto, "piezas": piezas, "acciones": acciones};
			//var objAux = JSON.parse(aux);

			//var uri = "http://sesat.fdi.ucm.es:8080/xtextws/rest/XtextWS/t/" + texto + "/p/"  + piezas + "/a/" + acciones;
			//var uri = "http://sesat.fdi.ucm.es:8080/xtextws/rest/XtextWS/newService";

			var uri = "http://sesat.fdi.ucm.es:8080/xtextws/rest/XtextWS?texto=" + texto + "&piezas="  + piezas + "&acciones=" + acciones;
			
			$.ajax({
				type: "GET",
				url: uri,
				data: "",
				success: function(resp) {
					alert(resp);
					SetContents(resp);
				},
				error: function(e) {
					alert("Error: " + e);
				}
					  
			});		
		}
		piezas = "";
		acciones = "";
	});	
});

// The instanceReady event is fired, when an instance of CKEditor has finished
// its initialization.
CKEDITOR.on( 'instanceReady', function( ev ) {
// Show the editor name and description in the browser status bar.
});

function InsertHTML(value) {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	//var value = document.getElementById( 'editor1' ).value;

	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
	// Insert HTML code.
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
	editor.insertHtml( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function InsertText(value) {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	//var value = document.getElementById( 'editor1' ).value;

	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Insert as plain text.
		// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertText
		editor.insertText( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function SetContents(value) {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	//var value = document.getElementById( 'htmlArea' ).value;

	// Set editor contents (replace current contents).
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-setData
	editor.setData( value );
}

function GetContents() {
	// Get the editor instance that you want to interact with.
	var editor = CKEDITOR.instances.editor1;

	// Get editor contents
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-getData
	return editor.getData();
}

function ExecuteCommand( commandName ) {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	
	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Execute the command.
		// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-execCommand
		editor.execCommand( commandName );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}
		
function CheckDirty() {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	// Checks whether the current editor contents present changes when compared
	// to the contents loaded into the editor at startup
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-checkDirty
	alert( editor.checkDirty() );
}

function ResetDirty() {
	// Get the editor instance that we want to interact with.
	var editor = CKEDITOR.instances.editor1;
	// Resets the "dirty state" of the editor (see CheckDirty())
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-resetDirty
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
