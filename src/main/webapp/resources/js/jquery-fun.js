// Sing in Error
$(".sign_btn").click(function(){
	$(".signin_wrap p").slideDown(100);	
});

// forgot password
$(document).ready(function() {
	$("#forgot-pass").hide();
    $("#fp-click").click(function(){
		$("#signin").hide();
		$("#forgot-pass").show();
	});
    $(".cancel_for").click(function(){
		$("#signin").show();
		$("#forgot-pass").hide();
	});	
});


// registration tabs

//$(document).ready(function() {
//	$("#comp-regis").hide();
//   $("#pro-tab").click(function(){
//		$("#pro-tab").addClass("activetab");
//		$("#comp-tab").removeClass("activetab");
//		$("#comp-regis").hide();
//		$("#profe-regis").show();
//	});
 //   $("#comp-tab").click(function(){
//		$("#pro-tab").removeClass("activetab");
//		$("#comp-tab").addClass("activetab");
//		$("#comp-regis").show();
//		$("#profe-regis").hide();
//	});	
//});	


// Approved Popup
$(document).ready(function() {
    $("#appro-pop").click(function(){
		$(".approved_pop").slideToggle(200);
	});
    $("html").click(function() {
        $(".approved_pop").slideUp(200);
    });
    $(".approved_pop, #appro-pop").click(function(e) {
        e.stopPropagation();
    });	
});

// Export Popup
$(document).ready(function() {
    $("#export-pop").click(function(){
		$(".export_pop").slideToggle(200);
	});
    $("html").click(function() {
        $(".export_pop").slideUp(200);
    });
    $(".export_pop, #export-pop").click(function(e) {
        e.stopPropagation();
    });	
});	

// Stus Popup
$(document).ready(function() {
    $("#status-pop").click(function(){
		$("#statusPop").slideToggle(200);
	});
    $("html").click(function() {
        $("#statusPop").slideUp(200);
    });
    $("#statusPop, #status-pop").click(function(e) {
        e.stopPropagation();
    });	
});

// Roles Popup
$(document).ready(function() {
    $("#roles-pop").click(function(){
		$("#rolesPop").slideToggle(200);
	});
    $("html").click(function() {
        $("#rolesPop").slideUp(200);
    });
    $("#rolesPop, #roles-pop").click(function(e) {
        e.stopPropagation();
    });	
});
 
//$(function(){
//  var currencies = [
//    { value: 'Australia'},
//    { value: 'France'},
//    { value: 'India'},
//    { value: 'Singapore'},
//    { value: 'United Kingdom'},
//    { value: 'United States'},
//  ];
//  
//  // setup autocomplete function pulling from currencies[] array
//  $('#professional-autocomplete, #company-autocomplete').autocomplete({
//    lookup: currencies,
//    onSelect: function (suggestion) {}	
//  }).focus(function () {  
//          $("#professional-autocomplete").autocomplete("ssss");
//  });
//   
//});


$(document).ready(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 200) {
            $('.backtotop').fadeIn('slow');
        } else {
            $('.backtotop').fadeOut('slow');
        }
    });
    $('.backtotop').click(function() {
        $("html, body").animate({scrollTop: 0}, 500);
        return false;
    });
});




 $(function() {
            var availableTutorials = [
               "Australia",
               "France",
               "India",
               "Singapore",
			   "United Kingdom",
			   "United States",
            ];
            $( "#professional-autocomplete, #company-autocomplete" ).autocomplete({
               source: availableTutorials,
               minLength: 0,   
            }).focus(function () {
                $(this).autocomplete("search");
           });

         });
 
 
 $(function() {
	    $('.time_cont_res').hide();
	    $('#accordion h2:first').addClass('res-active').next().slideDown('slow');
	    $('#accordion h2').click(function() {
	        if($(this).next().is(':hidden')) {
	            $('#accordion h2').removeClass('res-active').next().slideUp('slow');
	            $(this).toggleClass('res-active').next().slideDown('slow');
			}
		});
	});


 
 
 
 
// project side bar
$(document).ready(function() {
    $(".proj_show").click(function(){
		$(".project_side_wrap").addClass("project_side_wrap_active");
		$(".side_mask").fadeIn(200);
		$("body").css('overflow-y', 'hidden');
	});
    $(".proj_close, .side_mask").click(function(){
		$(".project_side_wrap").removeClass("project_side_wrap_active");
		$(".side_mask").fadeOut(200);
		$("body").css('overflow-y', 'auto');
	});	
});

// project side bar tabs
$(document).ready(function() {
	$("#tab-1").show()
    $(".project-tabs-menu a").click(function(event) {
        event.preventDefault();
        $(this).parent().addClass("current");
        $(this).parent().siblings().removeClass("current");
        var tab = $(this).attr("href");
        $(".project_tabs_inn").not(tab).css("display", "none");
        $(tab).fadeIn();
    });
});

// project active dropdown
$(document).ready(function() {
    $("#ac-drop").click(function(){
		$(".project_details_cta ul").slideToggle(200);
	});
    $("html").click(function() {
        $(".project_details_cta ul").slideUp(200);
    });
    $(".project_details_cta ul, #ac-drop").click(function(e) {
        e.stopPropagation();
    });
});

// project error message
$(document).ready(function() {
    $(".project_tabs_inn_table .glyphicon-trash").click(function(){
		$(".project_error").slideDown(200);
		$(".project_inn_mask").fadeIn(200);
	});
    $(".project_error a").click(function() {
        $(".project_error").slideUp(200);
		$(".project_inn_mask").fadeOut(200);
    });
});




