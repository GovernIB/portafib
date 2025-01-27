
<%@page import="es.caib.portafib.logic.utils.SignatureUtils"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureCommonUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.fundaciobit.pluginsib.utils.signature.SignatureConstants"%>
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="es.caib.portafib.model.fields.PeticioDeFirmaFields"%>




<c:if test="${descripciotipusvisible}">
    <script type="text/javascript">
    onChangeTipusDocumentID(document.getElementById("peticioDeFirma_tipusDocumentID"));

    function onChangeTipusDocumentID(combo) {

        var value = combo.options[combo.selectedIndex].value;
        if (value == 99) { 
          // Altres tipus de Documents
          document.getElementById("peticioDeFirma_descripcioTipusDocument_rowid").style.display = '';
        } else {
          document.getElementById("peticioDeFirma_descripcioTipusDocument_rowid").style.display = 'none';
        }
    }
    </script>
</c:if>

<c:if test="${not __theForm.view}" >
<script>

 
 <%
 final Field<?>[] tipusFirma = {
     PeticioDeFirmaFields.POSICIOTAULAFIRMESID
     //,     PeticioDeFirmaFields.MODEDEFIRMA
  };
 %>
 
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
   
   var signType = combo.options[combo.selectedIndex].value;
   
   var modesFirma = modesFirmaPerTipusFirma[signType];
   
   var modeFirmaInput = document.getElementById("<%=PeticioDeFirmaFields.MODEDEFIRMA.fullName.replace('.', '_') %>");
   
   var modeFirmaCurrent = modeFirmaInput.options[modeFirmaInput.selectedIndex].value;
   
   // Eliminem els modes de firma existents
   modeFirmaInput.innerHTML = "";
   
   // Afegim els modes de firma corresponents (guardats dins l'array modesFirma)
   var modeFirmaToSelect;
   modesFirma.forEach(function(mode) {
     var option = document.createElement("option");
     option.text = modesFirmaToString[mode];
     option.value = mode;
     if (mode == modeFirmaCurrent) {
         modeFirmaToSelect = mode;
     }
     modeFirmaInput.add(option);
   });
   
   // Deixam el mode de firma anterior si es possible
   if (modeFirmaToSelect) {
       modeFirmaInput.value = modeFirmaToSelect;
   }
   
   <%--
   if (value == <%=ConstantsV2.TIPUSFIRMA_PADES%>) {       
     document.getElementById("<%=PeticioDeFirmaFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = 'none';    
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = '';
     <% } %>
   } else {
     document.getElementById("<%=PeticioDeFirmaFields.MODEDEFIRMA.fullName.replace('.', '_') %>_rowid").style.display = '';
     <% for(int c=0;c < tipusFirma.length ;c++) { %>
     document.getElementById("<%=tipusFirma[c].fullName.replace('.', '_') %>_rowid").style.display = 'none';
     <% } %>
   }
   --%>
 }
 
 
//Tipus Firma i Mode de Firma
 onChangeTipusFirmaID(document.getElementById("<%=PeticioDeFirmaFields.TIPUSFIRMAID.fullName.replace('.', '_') %>"));

</script>
</c:if>