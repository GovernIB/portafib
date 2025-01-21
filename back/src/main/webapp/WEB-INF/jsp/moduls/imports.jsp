<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><fmt:message key="app.benvingut" /></title>

<%-- CSS --%>
<link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">

<%-- Select Multiple i Select amb cerca --%>
<link href="<c:url value="/css/select2.min.css"/>" rel="stylesheet">

<link href="<c:url value="/css/genapp.css"/>" rel="stylesheet"> 

<%-- Bootstrap CSS --%>
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/datatables.min.css"/>" rel="stylesheet">

<%-- Bootstrap TREE CSS --%>
<link href="<c:url value="/css/gijgo.min.css"/>" rel="stylesheet">

<%-- Bootstrap DateTimepicker CSS   --%>
<link href="<c:url value="/css/datetimepicker/tempusdominus-bootstrap-4.css"/>" rel="stylesheet">

<link href="<c:url value="/css/stylesapp.css"/>" rel="stylesheet">

<%-- JS --%>
<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>


<%-- Bootstrap JS --%>
<script src="<c:url value="/js/bootstrap.js"/>"></script>


<%-- https://fontawesome.com/icons?d=gallery&m=free --%>
<script src="<c:url value="/js/fontawesome-all.js"/>"></script>

<%-- Bootstrap TREE JS --%>
<script src="<c:url value="/js/gijgo.min.js"/>"></script>

<%-- Bootstrap DateTimePicker JS --%>
<script src="<c:url value="/js/datetimepicker/moment-with-locales.js"/>"></script>
<script src="<c:url value="/js/datetimepicker/tempusdominus-bootstrap-4.js"/>"></script>

<%-- TinyMCE --%>
<script type="text/javascript">
	var lang = '<c:out value="${pageContext.response.locale.language}"/>';
</script>
<script src="<c:url value="/js/tiny_mce/tiny_mce.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/js/tinymce.js"/>" type="text/javascript"></script>

<%-- Select Multiple i Select amb cerca --%>
<script src="<c:url value="/js/select2.min.js"/>"></script>
<script src="<c:url value="/js/select2_i18n/${lang}.js"/>"></script>

<script type="text/javascript">
	function clear_form_elements(ele) {

		tags = ele.getElementsByTagName('input');
		for (i = 0; i < tags.length; i++) {
			switch (tags[i].type) {
			case 'password':
			case 'text':
				tags[i].value = '';
				break;
			case 'checkbox':
			case 'radio':
				tags[i].checked = false;
				break;
			}
		}

		tags = ele.getElementsByTagName('select');
		for (i = 0; i < tags.length; i++) {
			if (tags[i].type == 'select-one') {
				tags[i].selectedIndex = 0;
			} else {
				for (j = 0; j < tags[i].options.length; j++) {
					tags[i].options[j].selected = false;
				}
			}
		}

		tags = ele.getElementsByTagName('textarea');
		for (i = 0; i < tags.length; i++) {
			tags[i].value = '';
		}
		
		$('.select2').val(null).trigger('change');
	}

	function goTo(url) {
		$("body").css("cursor", "progress");
		document.location.href = url;
	}

	function submitTo(formName, url) {
		var __theForm = document.forms[formName];
		__theForm.action = url;
		$("body").css("cursor", "progress");
		__theForm.submit();
	}

	function openModal(url, accio) {
		createDivModal(traduccions.type['dialogoTituloEliminar'],
				traduccions.type['dialogoMensajeEliminar'], url);
		$('#myModal').modal(accio);
	}

	function openModalSubmit(url, accio, formName) {
		var marcados = 0;
		var items = document.getElementsByName("selectedItems");

		for (var i = 0; i < items.length; i++) {
			if (items[i].checked) {
				marcados++;
			}
		}

		if (marcados != 0) {
			createDivModal(traduccions.type['dialogoTituloEliminar'],
					traduccions.type['dialogoMensajeEliminar'], url, formName);
			$('#myModal').modal(accio);
		} else {
			alert("<fmt:message key='genapp.delete.capseleccionat'/>");
		}
	}

	function createDivModal(tituloDialog, msgDialog, url, formName) {
		$('body')
				.append('<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
						+ '<div class="modal-dialog" role="document">'
						+ '<div class="modal-content">'
						+ '<div class="modal-header">'
						+ '<h4 id="myModalLabel"><i class="fas fa-trash"></i>'
    					+ tituloDialog
						+ '</h4>'
                        + '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
						+ '</div>'
						+ '<div class="modal-body">'
						+ '<p>'
						+ msgDialog
						+ '</p>'
						+ '</div>'
						+ '<div class="modal-footer">'
						+ '<button class="btn btn-secondary" data-dismiss="modal" aria-hidden="true">'
						+ traduccions.type['boto.cancelar']
						+ '</button>'
						+ '<button class="btn btn-danger" type="button" onclick="'
						+ (formName ? ('submitTo(\'' + formName + '\',')
								: 'goTo(')
						+ '\''
						+ url
						+ '\')">'
						+ traduccions.type['boto.continuar']
						+ '</button>'
						+ '</div>' 
						+ '</div>' 
						+ '</div>'
						+ '</div>');
	}

	var traduccions = {
		"type" : {
			"dialogoTituloEliminar" : "<fmt:message key="genapp.delete"/>",
			"dialogoMensajeEliminar" : "<fmt:message key="genapp.delete.info"/>",
			//MARILEN
			"boto.cancelar" : "<fmt:message key="genapp.cancel"/>",
			"boto.continuar" : "<fmt:message key="genapp.continue"/>"
		}
	}

	function selectUnselectCheckBoxes(source) {
		checkboxes = document.getElementsByName('selectedItems');
		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<%--// Compatibilitat IE8  --%>
<%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
<%-- WARNING: Respond.js doesn't work if you view the page via file:// --%>
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
 
 
