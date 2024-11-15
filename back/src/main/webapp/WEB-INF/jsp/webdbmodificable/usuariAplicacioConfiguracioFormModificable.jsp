<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureCommonUtils"%>
<%@page import="java.util.Arrays"%>
<%@page import="es.caib.portafib.logic.utils.SignatureUtils"%>
<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureConstants"%>
<%@page import="java.util.Map"%>
<%@page import="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"%>
<%@page import="es.caib.portafib.utils.ConstantsPortaFIB"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<style>
    div.usMarker {
        display: inline-block;
        background-color: grey;
        color: white;
        font-size: xx-small;
        font-weight: normal;
        border-radius: 5px;
        line-height: 10px;
        padding: 2px;
        margin: 2px;
    }
    div.markerContainer {
        text-align: left;
    }
    span.usFieldMarker {
        background-color: grey;
        margin: 3px;
        padding: 3px;
        border-radius: 5px;
        line-height: 10px;
    }
</style>







 <%
 final Field<?>[] tipusFirma = {
     UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES,
     UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID,
     UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID,
     // UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID,
     UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES
  };
 %>
 <script type="text/javascript">
 

 var modesFirmaPerTipusFirma = {
         <%
         Map<String,Integer[]> modesFirmaByTipusFirma = SignatureConstants.SIGNMODES_BY_SIGNTYPE;
         for(String type : modesFirmaByTipusFirma.keySet() ) { 
         %>
         <%=SignatureUtils.convertApiSignTypeToPortafibSignType(type)%>: <%=Arrays.toString(modesFirmaByTipusFirma.get(type)) %>,
         <%}%>
 }
 
 var modesFirmaToString = {
         <%for (int mode :  SignatureConstants.ALL_SING_MODES) {
             String modeStr = SignatureCommonUtils.signModeToString(mode);
             int i = modeStr.indexOf('(');
             if ( i != -1) {
                 modeStr = modeStr.substring(0, i);
             }
             modeStr = modeStr.replace("SIGN_MODE_", "").replace("_", " ");
         %>
         <%=mode%>: '<%=modeStr%>',
         <%}%>
 }
 

 
 function onChangeTipusFirmaID(combo) {
   if (combo == null) {
         return;
   }
       
     
   var value = combo.options[combo.selectedIndex].value;
   if (value == <%=ConstantsV2.TIPUSFIRMA_PADES%>) {
     //document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = 'none';    
     
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = '';
     <% } %>

     onChangePoliticaTaulaFirmes(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.', '_') %>"));
     
   } else {
     //document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = '';
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = 'none';
     <% } %>
   }
   
   

   
   var signType = combo.options[combo.selectedIndex].value;
   
   var modesFirma = modesFirmaPerTipusFirma[signType];
   
   var modeFirmaInput = document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>");
   
   // Eliminem els modes de firma existents
   modeFirmaInput.innerHTML = "";
   
   // Afegim els modes de firma corresponents (guardats dins l'array modesFirma)
   modesFirma.forEach(function(mode) {
     var option = document.createElement("option");
     option.text = modesFirmaToString[mode];
     option.value = mode;
     modeFirmaInput.add(option);
   });

 }
 
 // Tipus Firma i Mode de Firma
 onChangeTipusFirmaID(document.getElementById("<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMAID.fullName.replace('.', '_') %>"));

</script>



<script type="text/javascript">

var markFieldsRowID = [
    '<%=UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMAID.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA.fullName.replace('.','_')%>_rowid',/*
        UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER,
        UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH,
        UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM,
        UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT,*/
    '<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.','_')%>_rowid',/*
        UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID,
        UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID,
        UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES,*/
    '<%=UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS.fullName.replace('.','_')%>_rowid',/*
        UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID,*/
    '<%=UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID.fullName.replace('.','_')%>_rowid',/*
        UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT,*/    
    '<%=UsuariAplicacioConfiguracioFields.VALIDARFIRMA.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA.fullName.replace('.','_')%>_rowid',
    '<%=UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT.fullName.replace('.','_')%>_rowid'
];

 var usFields = [
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAWEB.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAWS1.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR.fullName%>',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB.fullName%>'
 ];

var usFieldsRowID = [
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAWEB.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAWS1.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR.fullName.replace('.','_')%>_rowid',
     '<%=UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB.fullName.replace('.','_')%>_rowid'
 ];

 var usLabel = [
     '<fmt:message key="usuariaplicacioconfig.fsimpleservidor"/>',
     '<fmt:message key="usuariaplicacioconfig.fsimpleweb"/>',
     '<fmt:message key="usuariaplicacioconfig.fweb"/>',
     '<fmt:message key="usuariaplicacioconfig.fwsv1"/>',
     '<fmt:message key="usuariaplicacioconfig.fasyncrestv2"/>',
     '<fmt:message key="usuariaplicacioconfig.fpassservidor"/>',
     '<fmt:message key="usuariaplicacioconfig.fpassweb"/>'
 ];

var usBgColor = [ 'red', 'green', 'blue', 'orange', 'grey', 'brown', 'violet' ];

 var fieldUses = [              /*FI.CER|TI.OPE|TI.FIR|ALG.FI|MOD.FI|POL.FI|MOT.DE|P.TA.F|P.SG.T|HTPLFW|P.F.SV|VAL.FI|CK.N.M|CM.NIF|VAL.CR*/
/* USENFIRMAAPISIMPLESERVIDOR */ [true,  true,  true,  true,  true,  true,  false, true,  true,  false, true,  true,  true,  true,  false ],
/* USENFIRMAAPISIMPLEWEB      */ [true,  true,  true,  true,  true,  true,  false, true,  true,  true,  false, true,  true,  true,  false ],
/* USENFIRMAWEB               */ [false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false ],
/* USENFIRMAWS1               */ [true,  true,  false, false, false, true,  true,  false, true,  false, false, true,  true,  true,  false ],
/* USENFIRMAASYNCREST2        */ [true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  false, true,  true,  true,  false ],
/* USENFIRMAPASSARELASERVIDOR */ [false, false, false, false, false, false, false, false, true,  false, true,  true,  true,  true,  false ],
/* USENFIRMAPASSARELAWEB      */ [false, false, false, false, false, false, false, false, true,  false, false, true,  true,  true,  false ]
 ];

 $("input[type='checkbox']").change(function() {
     if (usFields.indexOf(this.name) !== -1) {
         onChangeCheckbox();
     }
 });

 for (i = 0; i < usFieldsRowID.length; i++) {
     $("#" + usFieldsRowID[i] + " label").append('<span class="usFieldMarker" style="background-color: ' + usBgColor[i] +';">&nbsp;</span>');
 }

 for (i = 0; i < markFieldsRowID.length; i++) {
     $("#" + markFieldsRowID[i] + " > td:first-child").prepend('<div class="markerContainer"></div>');
 }

 function onChangeCheckbox() {
     var usChecked = [];
     for (var i = 0; i < usFields.length; i++) {
         usChecked.push(document.getElementsByName(usFields[i])[0].checked);
     }
     // Per cada camp que es mostra o amaga
     for (var j = 0; j < markFieldsRowID.length; j++) {
         // Inicialment fixam a false i buidam els marcadors.
         $("#" + markFieldsRowID[j] + " div.markerContainer").text("");
         var mostrar = false;
         // Per cada camp d'us
         for (var k = 0; k < usFields.length; k++) {
             if (usChecked[k] && fieldUses[k][j]) {
                $("#" + markFieldsRowID[j] + " div.markerContainer")
                    .prepend('<div class="usMarker" style="background-color: ' + usBgColor[k] + '">'+usLabel[k]+'</div>');
                mostrar = true;
             }
         }
         document.getElementById(markFieldsRowID[j]).style.display = mostrar ? '' : 'none';
     }

     if (document.getElementById('<%=UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA.fullName.replace('.','_')%>_rowid').style.display == '') {
         onChangeUsPoliticaDeFirma(document.getElementById("<%=UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA.fullName.replace('.', '_') %>"));
     } else {
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }

     // No fa falta perquè depèn de TipusFirma i per tant ja s'actualitzarà al onChange del tipus de firma.
     /* if (document.getElementById('<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.','_')%>_rowid').style.display == '') {
         onChangePoliticaTaulaFirmes(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.', '_') %>"));
     } else {
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         // document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }*/

     if (document.getElementById('<%=UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS.fullName.replace('.','_')%>_rowid').style.display == '') {
         onChangePoliticaSegellatDeTemps(document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS.fullName.replace('.', '_') %>"));
     } else {
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }


     if (document.getElementById('<%=UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID.fullName.replace('.','_')%>_rowid').style.display == '') {
         onChangePluginFirmaServidorID(document.getElementById("<%=UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID.fullName.replace('.', '_') %>"));
     } else {
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }

     if (document.getElementById('<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMAID.fullName.replace('.','_')%>_rowid').style.display == '') {
         onChangeTipusFirmaID(document.getElementById("<%=UsuariAplicacioConfiguracioFields.TIPUSFIRMAID.fullName.replace('.', '_') %>"));
     } else {
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         // document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
         document.getElementById("<%=UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = 'none';

         document.getElementById("<%=UsuariAplicacioConfiguracioFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     }
 }

 // Primera crida per l'estat inicial.
 onChangeCheckbox();

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
       //document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = 'none';
     } else {
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID.fullName.replace('.', '_') %>_rowid").style.display = '';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES.fullName.replace('.', '_') %>_rowid").style.display = '';
       document.getElementById("<%=UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID.fullName.replace('.', '_') %>_rowid").style.display = '';
       //document.getElementById("<%=UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID.fullName.replace('.', '_') %>_rowid").style.display = '';
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




