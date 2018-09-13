var idrol_select;
function enviarIdrol(id_rol){
	cambiarRol(id_rol);
	idrol_select=id_rol;
} 

// var idrol_init=$('select-Roles option:selected').val();
var ListaMenus;
function cambiarRol(rol){
	var valor;
	//$('#select-Roles').on('change',function(){
	if(isNaN(rol)){
		valor = $(rol).val();
		
	}else{
		valor=rol;
		// console.log('es numero')
	}
	idrol_select=valor;
	// console.log('entro menus_idrol:',rol);
	// console.log('entro menus_idrol:',valor);
	//alert('se pulso')
	// console.log('ajaxRoles: ',$(this).val());
	// console.log('ajax: ',valor);
	$('#contenedor-gestion').html('');
	$.ajax({
		url:'../usuario/MenuRol',
		type:'post',
		dataType:"json",
		data:{
			idrol:valor
		},
		success:function(resp){
			// $('#menuPrincipal').remove();
			//$('#menuPrincipal').html(resp);
			// ListaMenus=resp;
			console.log('respActual: ',resp);
			var name="";
			$('.lista-menu').empty();//remove igual borra todos los li dentro
			$(resp).each(function(index,item){
				var proc=item;
			//console.log('idex: ',index);
			//console.log('item: ',item);
				$('.lista-menu').append(
						'<li class="sub-menu">'+
						'<a href="#" id="mod" data-id="'+item.idmod+'"><i class="'+item.icono+'"></i>'+
                    	'<span class="" style="padding-left:15px;">'+item.nombre+'</span>'+
                    	'<i class="fa arrow"></i></a>'+
                    	
					//	'<ul class="nav nav-second-level list-procesos'+(index+1)+'" data-id="'+item.idmod+'" onclick=(CargarProcesos(this))>'+
                    	'<ul class="list-procesos'+(index+1)+'" data-id="'+item.idmod+'" >'+
                		'</ul>'+
                    	'</li>'
					// 	'<script type="" src="../resources/js/ajax/gestion.js"></script>'
					// 	'<script type="" src="../resources/js/tema/menu/metisMenu.js"></script>'+
					// 	'<script type="" src="../resources/js/tema/sb-admin-2.js"></script>' 	
                );
            	$(resp[index].procesos).each(function (index1,item1){
					//	console.log('item1_',index1,' valor:',item1.nombre);
					//	console.log('item1_');
					//  var idm=$('.list-procesos').attr('data-id');
					//	console.log('codmod: '+idm);
					//	if(idm==item.idmod){}
            		// console.log('Modulo',(index+1),'\nProceso',(index1+1),item1.nombre)
            		var idlist='.list-procesos'+(index+1);
					//  console.log('idlist',idlist);
            		$(idlist).append('<li class="sub-proceso"><a href="#" data-href="'+item1.enlace+'" data-id="'+item1.idproc+'" accesskey="'+item.idmod+'" class="enlace-proc" >'+item1.nombre+'</a></li>')
					//  $('.list-procesos').removeClass('.list-procesos');
            		$(item1.opciones).each(function(index2,item2) {
						// console.log('opciones: ',item2.nombre);
					})
            	});
           		$('#contenedor-gestion').html('');
            	// $('<script type="" src="../resources/js/ajax/gestion.js"></script>').appendTo('body');
			})
			ObtenerMenus(resp);
		}
	})
}
function ObtenerMenus(Menus){
	ListaMenus=Menus;
	// console.log('listaM:',ListaMenus);
}


	