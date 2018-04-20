<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<!-- Bootstrap CSS-->
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-responsive.css"/>" rel="stylesheet">

<%-- <link href="<c:url value="/css/default.css"/>" rel="stylesheet"> --%>

<link href="<c:url value="/css/genapp.css"/>" rel="stylesheet">

 <!-- Path to your custom app styles-->
   <link href="<c:url value="/css/my-app.css"/>" rel="stylesheet">

   <!-- Path to Framework7 iOS CSS theme styles -->
    <link href="<c:url value="/css/framework7.ios.min.css"/>" rel="stylesheet">
   <!-- Path to Framework7 iOS related color styles -->
    <link href="<c:url value="/css/framework7.ios.colors.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/framework7-icons.css"/>" rel="stylesheet">

<!-- Path to Framework7 iOS CSS theme styles-->
   <!--<link rel="stylesheet" href="css/framework7.material.min.css">-->
   <!-- Path to Framework7 iOS related color styles -->
   <!--<link rel="stylesheet" href="css/framework7.material.colors.min.css">-->


<!-- TinyMCE -->
<script type="text/javascript">
    var lang = '<c:out value="${pageContext.response.locale.language}"/>';
</script>
<script src="<c:url value="/js/tiny_mce/tiny_mce.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/tinymce.js"/>" type="text/javascript"></script>

<script type="text/javascript">

    function clear_form_elements(ele) {
    
        tags = ele.getElementsByTagName('input');
        for(i = 0; i < tags.length; i++) {
            switch(tags[i].type) {
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
        for(i = 0; i < tags.length; i++) {
            if(tags[i].type == 'select-one') {
                tags[i].selectedIndex = 0;
            }
            else {
                for(j = 0; j < tags[i].options.length; j++) {
                    tags[i].options[j].selected = false;
                }
            }
        }
    
        tags = ele.getElementsByTagName('textarea');
        for(i = 0; i < tags.length; i++) {
            tags[i].value = '';
        }
    }

	function goTo(url) {
		document.location.href = url;
	}

	function submitTo(formName, url) {
      var __theForm = document.forms[formName]; 
      __theForm.action = url;
      __theForm.submit();
    }

    function openModal(url,accio) {
             createDivModal(traduccions.type['dialogoTituloEliminar'],
         traduccions.type['dialogoMensajeEliminar'],url);
         $('#myModal').modal(accio);
    }


    function openModalSubmit(url,accio, formName) {
          createDivModal(traduccions.type['dialogoTituloEliminar'],
      traduccions.type['dialogoMensajeEliminar'],url, formName);
      $('#myModal').modal(accio);
    }
    
    

    function createDivModal(tituloDialog, msgDialog,url, formName) {
    $('body')
         .append(
                 '<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
                 + '<div class="modal-header">'
                 + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></button>'
                 + '<h3 id="myModalLabel">'+tituloDialog+'</h3>'
                 + '</div>'
                 + '<div class="modal-body">'
                 + '<p>'+msgDialog+'</p>'
                 + '</div>'
                 + '<div class="modal-footer">'
                 + '<button class="btn" data-dismiss="modal" aria-hidden="true">'+traduccions.type['boto.cancelar']+'</button>'
                 + '<button class="btn btn-danger" type="button" onclick="' 
                 + (formName? ('submitTo(\'' + formName + '\',') : 'goTo(') 
                 + '\''+url+'\')">'+traduccions.type['boto.continuar']+'</button>'
                 + '</div></div>');
    }
    
    var traduccions = {
      "type" : {
         "dialogoTituloEliminar" : "<fmt:message key="genapp.delete"/>",
         "dialogoMensajeEliminar" : "<fmt:message key="genapp.delete.info"/>",
        //MARILEN
         "boto.cancelar":"<fmt:message key="genapp.cancel"/>",
         "boto.continuar":"<fmt:message key="genapp.continue"/>"
       }
    }

    function selectUnselectCheckBoxes(source) {
      checkboxes = document.getElementsByName('selectedItems');
      for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = source.checked;
      }
    }

</script>