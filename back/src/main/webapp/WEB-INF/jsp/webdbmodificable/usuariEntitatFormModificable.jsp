<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsPortaFIB"%>
<%@page import="es.caib.portafib.model.fields.UsuariEntitatFields"%>
<script>

 // Politica de Custòdia (ocultar o mostrar valor)
 onChangePoliticaCustodia(document.getElementById("<%=UsuariEntitatFields.POLITICACUSTODIA.fullName.replace('.', '_') %>"));

 function onChangePoliticaCustodia(combo) {
     var value = combo.options[combo.selectedIndex].value;
     if (value == <%=ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO%>) { 
       document.getElementById("<%=UsuariEntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = '';
     } else {
       document.getElementById("<%=UsuariEntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }
 }

 // CustodiaInfo (camp null)
 var sel = document.getElementById("<%=UsuariEntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>").options;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="combobox.seleccionar"/>--';
      break;
    }
 }
 </script>