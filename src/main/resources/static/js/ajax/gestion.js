$(function() {
	$('.enlace-proc').on('click',function(){
		//alert('inicio gestiones')
		// var idrol=idrol_select;
		var idrol=$(this).attr('data-idrol')
		var idmod=$(this).attr('accesskey')
		var idproc=$(this).data('id');
		console.log('listaMenus_gestion:',ListaMenus);      
		console.log('idrol: ',idrol,'idmod: ',idmod,'idproc: ',idproc)
		var enlace=$(this).data('href');
		console.log(enlace)
		$.ajax({
			type:'post',
			url:enlace,
			success:function(resp){
				$('#contenedor-gestion').html(resp);
			},
			error:function(){

			}

		})
		$('#opcionesRol').html('');
		var OpcionesSistema=new Object();
		$(ListaMenus).each(function(index,menus){
				if(menus.idmod==idmod){
					console.log('entro menu')
					$(ListaMenus[index].procesos).each(function(index1,procesos){
						if(procesos.idproc==idproc){
							console.log('entro procesos')
								OpcionesSistema.Opciones=new Array();	
								$(procesos.opciones).each(function(index2,opciones){
									console.log('entro acciones')	
									console.log('Acciones',opciones.idopc,': ',opciones.nombre);									
									$('#opcionesRol').append('<button class="btn btn-primary waves-effect" name="">'+opciones.nombre+'</button>')
									OpcionesSistema.Opciones[index2]=opciones.nombre;
								})
							Get_Opciones(OpcionesSistema);	
						}
					})
				}
		})
	})
	var OpcionesSistema;
	function Get_Opciones(opc){
		// alert('alerta  pidio opciones')
		var resp=opc;	
		if(!opc==null && !opc==undefined){		
			OpcionesSistema=opc;
		}

		console.log('OpcionesGestion_dentro: ',OpcionesSistema)
		console.log('opc_dentro: ',opc)
        // $('<script type="" src="../resources/js/ajax/RolMenu.js"></script>').appendTo('body');
        // $('<script type="" src="../resources/js/ajax/gestion.js"></script>').appendTo('body');

		// $('#contenedor-gestion').append();
		// return OpcionesSistema;
	}
	// console.log('OpcionesGestion_fuera: ',Get_Opciones(null))

	// var MostrarOpciones=Get_Opciones(null);
		// console.log('OpcionesGestion_fuera: ',MostrarOpciones)


})
