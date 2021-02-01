<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsPortaFIB"%>
<%@page import="es.caib.portafib.model.fields.UsuariAplicacioFields"%>
<script type="text/javascript">

  function onChangeCallbackVersio(select) {
  
	 var value = select.options[select.selectedIndex].value;
     var callBackUrl = document.getElementById("usuariAplicacio.callbackURL");

     if (value == '0') {
    	 callBackUrl.value="http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack";
     } else if (value == '1') {
    	 callBackUrl.value="http://localhost:8080/portafib/cb/v1/PortaFIBCallBack";
     } else if (value == '2') {
         callBackUrl.value="http://localhost:8080/portafib/cbrest/v1/event";
     } else {
         callBackUrl.value="";
     }
  }

  // Politica de Cust�dia (ocultar o mostrar valor)
  onChangePoliticaCustodia(document.getElementById("<%=UsuariAplicacioFields.POLITICACUSTODIA.fullName.replace('.', '_') %>"));

  function onChangePoliticaCustodia(combo) {
      var value = combo.options[combo.selectedIndex].value;
      if (value == <%=ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO%>) { 
        document.getElementById("<%=UsuariAplicacioFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = '';
      } else {
        document.getElementById("<%=UsuariAplicacioFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
      }
  }

  // CustodiaInfo (camp null)
  var sel = document.getElementById("<%=UsuariAplicacioFields.CUSTODIAINFOID.fullName.replace('.', '_') %>").options;
  for (i = 0; i < sel.length; i++) {
     if (sel[i].value == '') {
       sel[i].innerHTML='--<fmt:message key="combobox.seleccionar"/>--';
       break;
     }
  }
  

</script>