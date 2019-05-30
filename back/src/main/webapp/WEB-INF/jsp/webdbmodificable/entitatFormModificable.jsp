
<%@page import="es.caib.portafib.model.fields.EntitatFields"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsPortaFIB"%>
<script>

 // Politica de Firma (ocultar o mostrar valor)

 onChangeUsPoliticaDeFirma(document.getElementById("<%=EntitatFields.USPOLITICADEFIRMA.fullName.replace('.', '_') %>"));

 function onChangeUsPoliticaDeFirma(combo) {
<%
    final Field<?>[] fieldsUsPolitica = {
    EntitatFields.POLICYIDENTIFIER,
    EntitatFields.POLICYIDENTIFIERHASH,
    EntitatFields.POLICYIDENTIFIERHASHALGORITHM,
    EntitatFields.POLICYURLDOCUMENT
    };
%>
     var value = combo.options[combo.selectedIndex].value;
     if (value == <%=ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT%>) { 
       <% for(int c=0;c < fieldsUsPolitica.length ;c++) { %>
       document.getElementById("<%=fieldsUsPolitica[c].fullName.replace('.', '_') %>_rowid").style.display = '';
       <% } %>
     } else {
       <% for(int c=0;c < fieldsUsPolitica.length ;c++) { %>
       document.getElementById("<%=fieldsUsPolitica[c].fullName.replace('.', '_') %>_rowid").style.display = 'none';
       <% } %>
     }

 }
 
 // Politica de Custòdia (ocultar o mostrar valor)
 onChangePoliticaCustodia(document.getElementById("<%=EntitatFields.POLITICACUSTODIA.fullName.replace('.', '_') %>"));

 function onChangePoliticaCustodia(combo) {
     var value = combo.options[combo.selectedIndex].value;
     if (value == <%=ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO %>
       ||value == <%=ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU%>
       || value == <%=ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU%>) {
       document.getElementById("<%=EntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = '';
     } else {
       document.getElementById("<%=EntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }
 }
 
 
 
  // CustodiaInfo (camp null)
 var sel = document.getElementById("<%=EntitatFields.CUSTODIAINFOID.fullName.replace('.', '_') %>").options;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="combobox.seleccionar"/>--';
      break;
    }
 }
 
 </script>