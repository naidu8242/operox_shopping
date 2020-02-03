	$(document).ready(function(e) {
		$('.side-menu').click(function(){
			$('.side-bar').toggleClass("side-bar-active");
			$('.user-panel').toggleClass("user-panel-active");
			$('.content-wraper').toggleClass("content-wraper-active");
			$('.footer-area').toggleClass("footer-area-active");
			$("div.side-nav ul li").find('ul.collapses, .tree-view').toggleClass("tree-view-active");
			$("div.side-nav ul li").find('.tree-view-new').toggleClass('tree-view-new-active');
			$(".collapses").hide();
			});
		$('.srch-btn-xs').click(function(){
		  $(".search-area").toggleClass("search-area-active");
	    });
		$('html').click(function(){
		  $(".search-area").removeClass("search-area-active");
	    });
		$('.srch-btn-xs, .search-area-txt').click(function(e){
		  e.stopPropagation();
	    });
		$('.login-user').click(function(){
		 $('.user-details').toggleClass('user-details-active');
		 $('.setting-area').removeClass('setting-area-active');
		 $('.alert-area').removeClass('alert-area-active');
		 $('.qlinks-area').removeClass('qlinks-area-active');
		 $('.note-area').removeClass('note-area-active');
	    });
		
		$("#notification").click(function(){
			$('.note-area').toggleClass('note-area-active');
			$('.user-details').removeClass('user-details-active');
			$('.setting-area').removeClass('setting-area-active');
			$('.alert-area').removeClass('alert-area-active');
			$('.qlinks-area').removeClass('qlinks-area-active');
		});
		$("#alert").click(function(){
			$('.alert-area').toggleClass('alert-area-active');
			$('.user-details').removeClass('user-details-active');
			$('.setting-area').removeClass('setting-area-active');
			$('.note-area').removeClass('note-area-active');
			$('.qlinks-area').removeClass('qlinks-area-active');
		});
		$("#qlinks").click(function(){
			$('.qlinks-area').toggleClass('qlinks-area-active');
			$('.alert-area').removeClass('alert-area-active');
			$('.user-details').removeClass('user-details-active');
			$('.setting-area').removeClass('setting-area-active');
			$('.note-area').removeClass('note-area-active');
		});
		$('html').click(function(){
		  $('.user-details').removeClass("user-details-active");
		  $('.setting-area').removeClass('setting-area-active');
		  $('.note-area').removeClass('note-area-active');
		  $('.alert-area').removeClass('alert-area-active');
		  $('.qlinks-area').removeClass('qlinks-area-active');
		  $('.timesheet-menu').removeClass('timesheet-menu-active');
	    });
		$('.header-nav .navbar, .custom-navbar, .user-details, .tree-view-new').click(function(e){
		  e.stopPropagation();
	    });
		$('.settings').click(function(){
		$('.setting-area').toggleClass('setting-area-active');
		$('.note-area').removeClass('note-area-active');
		$('.user-details').removeClass('user-details-active');
		$('.alert-area').removeClass('alert-area-active');
		$('.qlinks-area').removeClass('qlinks-area-active');
	    });
		$(".collpase-div").click(function(){
			$(".toggle-div").slideToggle(800);
		});
		$(".faclose").click(function(){
			$(".remove-div").addClass('hidden');
		});
		$(".bell").click(function(){
				$(this).toggleClass('bell-active');			
			});
       $(".addLevel").click(function(){
		   $(".level-second").toggleClass("level-second-active");
		});
       
       $(".tree-view-new").click(function(){
    	   $('.timesheet-menu').toggleClass('timesheet-menu-active');
    	   });
       
       $(".tree-view").click(function(){
    	   $(this).next('ul').slideToggle();
    	   });
		   /* Accordion script */
		   $(function() {
			$('#accordion .content').hide();
			$('#accordion h2:first').addClass('active').next().slideDown('slow');
			$('#accordion h2').click(function() {
				if($(this).next().is(':hidden')) {
					$('#accordion h2').removeClass('active').next().slideUp('slow');
					$(this).toggleClass('active').next().slideDown('slow');
				}
			});
		});
	/* Accordion script */
	
	
	$("#btn1").click(function () {
		$('head').append('<link rel="stylesheet" href="css/sourcelead-theme-one.css" type="text/css" />');
		$('.footer').append('<script src="js/theme-one-script.js">');
    });
    $("#btn2").click(function () {
		$('head').append('<link rel="stylesheet" href="css/sourcelead-theme-two.css" type="text/css" />');
		$('link[rel=stylesheet][href~="css/sourcelead-theme-one.css"]').remove();
				$('<script src="js/theme-one-script.js">').remove();
    });
    $("#btn3").click(function () {
		 $('link[rel=stylesheet][href~="css/sourcelead-theme-one.css"]').remove();
		 $('link[rel=stylesheet][href~="css/sourcelead-theme-two.css"]').remove();
				$('<script src="js/theme-one-script.js">').remove();
	});
	$(".icon-theme").click(function(){
		$(".theme-color").toggleClass("theme-color-active");
		});
	
    }); 
	/* upload file script */
	document.getElementById("uploadBtn").onchange = function () {
    document.getElementById("uploadFile").value = this.value;
	};