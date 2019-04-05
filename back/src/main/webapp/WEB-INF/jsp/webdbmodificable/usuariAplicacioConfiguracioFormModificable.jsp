<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsPortaFIB"%>
<%@page import="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"%>
<script>

 // Politica de Firma (ocultar o mostrar valor)
 onChangeUsPoliticaDeFirma(document.getElementById("<%=UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA.fullName.replace('.', '_') %>"));

 function onChangeUsPoliticaDeFirma(combo) {
<%
    final Field<?>[] fieldsUsPolitica = {
       UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER,
       UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH,
       UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM,
       UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT
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
 

 
 
 // Politica de Taula de Firmes (ocultar o mostrar valor)
 onChangePoliticaTaulaFirmes(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.', '_') %>"));

 function onChangePoliticaTaulaFirmes(combo) {
     var value = combo.options[combo.selectedIndex].value;
     //alert("VALUE onChangePoliticaTaulaFirmes: ]["  +  value + "")
     if (value == <%=ConstantsPortaFIB.POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT%>
        || value == <%=ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET%>) { 
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = 'none';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     } else {
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>_rowid").style.display = '';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = '';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID.fullName.replace('.', '_') %>_rowid").style.display = '';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = '';
     }
 }


 // Politica de Segellat de Temps (ocultar o mostrar valor)
 onChangePoliticaSegellatDeTemps(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS.fullName.replace('.', '_') %>"));

 function onChangePoliticaSegellatDeTemps(combo) {
     var value = combo.options[combo.selectedIndex].value;
     if (value == <%=ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT%>
        || value == <%=ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR %>) { 
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     } else {
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID.fullName.replace('.', '_') %>_rowid").style.display = '';
     }
 }


 // Plugin Firma en Servidor
 {
 var sel = document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID.fullName.replace('.', '_') %>").options;
 var i;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="usuariaplicacioconfig.nofirmaservidor"/>--';
      break;
    }
 }
 }
 
 
 // Tipus-format de Upgrade de Firma
 {
 var sel = document.getElementById("<%=UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT.fullName.replace('.', '_') %>").options;
 var i;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="usuariaplicacioconfig.noupgradesignature"/>--';
      break;
    }
 }
 }
 
 onChangePluginFirmaServidorID(document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID.fullName.replace('.', '_') %>"));

 function onChangePluginFirmaServidorID(combo) {
   var value = combo.options[combo.selectedIndex].value;
   if (value == '') {
     document.getElementById("<%=UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT.fullName.replace('.', '_') %>_rowid").style.display = 'none';
   } else {
     document.getElementById("<%=UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT.fullName.replace('.', '_') %>_rowid").style.display = '';
   }
 }
 
 
 
 // Tipus Firma i Mode de Firma
 onChangeTipusFirmaID(document.getElementById("<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMAID.fullName.replace('.', '_') %>"));
 <%
 final Field<?>[] tipusFirma = {
     
     UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES,
     //UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID,
     //UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES,
     //UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID,
     //UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID
  };
 %>
 
 function onChangeTipusFirmaID(combo) {
   var value = combo.options[combo.selectedIndex].value;
   if (value == <%=ConstantsV2.TIPUSFIRMA_PADES%>) {
     document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = 'none';    
     
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = '';
     <% } %>
     
     
   } else {
     document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = '';
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = 'none';
     <% } %>
   }
   
   onChangePoliticaTaulaFirmes(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.', '_') %>"));
 }

 // ALGORISME DE FIRMA
 var sel = document.getElementById("<%=UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID.fullName.replace('.', '_') %>").options;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="definitenentitat."/>--';
      break;
    }
 }

 
 // Segellat de Temps (camp null)
 var sel = document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID.fullName.replace('.', '_') %>").options;
 for (i = 0; i < sel.length; i++) {
    if (sel[i].value == '') {
      sel[i].innerHTML='--<fmt:message key="combobox.seleccionar"/>--';
      break;
    }
 }

 <%--
 // XYZ ZZZ Posicio Taula de Firmes
 // var sel = document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>").options;
 //for (i = 0; i < sel.length; i++) {
 //   if (sel[i].value == '') {
 //     sel[i].innerHTML='--<fmt:message key="combobox.seleccionar"/>--';
 //     break;
 //   }
 //}
 
 alert("SURT");
 --%>

</script>

