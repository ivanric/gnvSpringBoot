
$(function(){
	$('#js-user').click(function(e){
		e.preventDefault();
		if ($('#js-div-logout').hasClass('logout-mostrar')) {
			$('#js-div-logout').removeClass('logout-mostrar')
		} else {
			$('#js-div-logout').addClass('logout-mostrar')
			
		}
	})
	$('header').removeClass('header2')

	// $('#js-div-menu').niceScroll({
		// cursorcolor: "#424242",
		// cursorwidth: '8px',
		// cursorminheight: 32
	// });

	//has= que tiene
	//quiero acceder a los elementos li que tengan ul una lista


	$('body').on('click','.lista-menu li:has(ul)',function(e){
		e.preventDefault();
		//preguntat si este elemento tiene la clase activado
		if($(this).hasClass('activado')){
			$(this).removeClass('activado')
			$(this).children('ul').slideUp();
		}else{
			$('.menu li ul').slideUp();//ocultamos todos nuestros sub menus
			$('.menu li').removeClass();
			$(this).addClass('activado');
			$(this).children('ul').slideDown();//li con ul lo muestra
		}
	})
	// $('.menu li:has(ul)').click(function(e){
	// 	alert('ahh')
	// // $('.menu li ul').click(function(e){
	// 	e.preventDefault();
	// 	//preguntat si este elemento tiene la clase activado
	// 	if($(this).hasClass('activado')){
	// 		$(this).removeClass('activado')
	// 		$(this).children('ul').slideUp();
	// 	}else{
	// 		$('.menu li ul').slideUp();//ocultamos todos nuestros sub menus
	// 		$('.menu li').removeClass();
	// 		$(this).addClass('activado');
	// 		$(this).children('ul').slideDown();//li con ul lo muestra
	// 	}
	// })

	// detectando si el usuario baja la pantalla
	$(window).scroll(function(){
		// alert('se modifico scroll')
		if($(this).scrollTop()>0){
			$('.ir-arriba').slideDown(300);
			// $('header').addClass('header2')
		}else{
			$('.ir-arriba').slideUp(300);
			// $('header').removeClass('header2')
		}
	})

	$('#js-btn-showMenu').on('click',function(e){
		e.preventDefault();
		// $('#js-div-menu').toggleClass('mostrar')
		$('#js-div-menu').toggleClass('mostrar')
		$('#js-nav-menu').toggleClass('mostrar')
	})

	$(window).resize(function(){
		// $('#js-div-menu').removeClass('mostrar')
		$('#js-nav-menu').removeClass('mostrar')
		$('#js-div-menu').removeClass('mostrar')

		// if($(document).width()<450){
			// $('.menu').niceScroll();
			// $('#js-nav-menu').removeClass('mostrar')
		// }
		// else{
			// $('#js-nav-menu').addClass('mostrar')	

			// if($('#js-nav-menu').hasClass('mostrar')){
			// 	$('#js-nav-menu').addClass('mostrar')	
			// }
		// }

	})

	$('#js-ir-arriba').on('click',function(){
		$('body,html').animate({
			scrollTop:'0px'
		},300);
	})
})