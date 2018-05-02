
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
 
 </script>