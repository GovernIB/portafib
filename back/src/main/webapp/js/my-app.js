// Initialize app and store it to myApp variable for futher access to its methods
var myApp = new Framework7({
    material: false
});

// We need to use custom DOM library, let's save it to $$ variable:
var $$ = Dom7;

//Create notification with close button
//var notificationWithButton = myApp.notification.create({
//  title: 'Framework7',
//  subtitle: 'Notification with close button',
//  closeButton: true,
//});

// Add view
var mainView = myApp.addView('.view-main', {
    // Because we want to use dynamic navbar, we need to enable it for this view:
    dynamicNavbar: true
});

$$(document).on('page:init', function (e) {
	
	var changePageItem = { Page: "PortaFib", Url: e.detail.page.url };
	window.history.pushState(changePageItem, changePageItem.Page, changePageItem.Url);
	
	window['controlMultipleActiu'] = false;
	controlMultipleOff();
	
	$('tr[data-href]').on("click",function(){
		 controlTableRow(this);
	});
	
	$('.sel-mul').on('click', function() {
		toogleControlMultiple();
	});
});

window['controlMultipleActiu'] = false;
controlMultipleOff();

$('tr[data-href]').on("click",function(){
	controlTableRow(this);
});

$('.sel-mul').on('click', function() {
	toogleControlMultiple();
});

$('.out-link').on('click', function() {
	window.location = $(this).data('href');
	return false;
});

function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    if (document.estatDeFirma instanceof HTMLCollection)
    	document.estatDeFirma[1].submit();
    else
    	document.estatDeFirma.submit();
}

function controlTableRow(element) {
	if (window['controlMultipleActiu']) {
		selectRow(element);
	} else {
		window.location = $(element).data('href');
		return false;
	}
}

function selectRow(element) {
	if($(element).hasClass("selected-row")) {
		$(element).removeClass("selected-row");
		$(element).find('input:checkbox').prop('checked', false);
	} else {
		$(element).addClass("selected-row");
		$(element).find('input:checkbox').prop('checked', true);
	}
} 

function deselectRows() {
	$('tr[data-href]').removeClass("selected-row");
	$('tr[data-href]').find('input:checkbox').prop('checked', false);
	$(".marcaTotes").show();
	$(".marcaCap").hide();
}

function selectRows() {
	$('tr[data-href]').addClass("selected-row");
	$('tr[data-href]').find('input:checkbox').prop('checked', true);
	$(".marcaTotes").hide();
	$(".marcaCap").show();
}

function toogleControlMultiple() {
	if (window['controlMultipleActiu']) {
		controlMultipleOff();
	} else {
		controlMultipleOn();
	}
}

function controlMultipleOn() {
	$('.sel-mul').html('<i class="f7-icons addapted-icon">close_round</i> <div class="text-icon">Cancelar</div>');
	$('.sel-mul').removeClass('color-orange').addClass('color-gray');
	$('.ctrl-mul').prop("disabled",false);
	window['controlMultipleActiu'] = true;
}

function controlMultipleOff() {
	$('.sel-mul').html('Selecció Múltiple');
	$('.sel-mul').removeClass('color-gray').addClass('color-orange');
	$('.ctrl-mul').prop("disabled",true);
	deselectRows();
	window['controlMultipleActiu'] = false;
}

function firmarseleccionats() {
    var url = localStorage['firmarSeleccionatsUrl'];
    if (document.estatDeFirma instanceof HTMLCollection) {
    	document.estatDeFirma[1].action = url;
    	document.estatDeFirma[1].submit();
    } else {
    	document.estatDeFirma.action = url;
        document.estatDeFirma.submit();
    }
}

function rebutjarseleccionats() {
	var url = localStorage['rebutjarSeleccionatsUrl'];
    
    var reason = prompt("Motiu de rebuig","");
    
    if (reason!=null) {      
      document.getElementById("motiu").value=reason;
      if (document.estatDeFirma instanceof HTMLCollection) {
      	document.estatDeFirma[1].action = url;
      	document.estatDeFirma[1].submit();
      } else {
      	document.estatDeFirma.action = url;
        document.estatDeFirma.submit();
      }
    }
 }

function goBack() {
    window.history.back();
}


