
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="es.caib.portafib.model.fields.PeticioDeFirmaFields"%>
<script>
 
 onChangeTipusDocumentID(document.getElementById("peticioDeFirma_tipusDocumentID"));

 /** Mostrar o ocultar la descripció del tipus de document segons 
  *  si és o no de tipus "Altres" (Valor 99)
  */
 function onChangeTipusDocumentID(combo) {

     var value = combo.options[combo.selectedIndex].value;
     if (value == 99) { 
       // Altres tipus de Documents
       document.getElementById("peticioDeFirma_descripcioTipusDocument_rowid").style.display = '';
     } else {
       document.getElementById("peticioDeFirma_descripcioTipusDocument_rowid").style.display = 'none';
     }

 }
 
 
 
 // Tipus Firma i Mode de Firma
 onChangeTipusFirmaID(document.getElementById("<%=PeticioDeFirmaFields.TIPUSFIRMAID.fullName.replace('.', '_') %>"));
 <%
 final Field<?>[] tipusFirma = {
     PeticioDeFirmaFields.POSICIOTAULAFIRMESID
     //,     PeticioDeFirmaFields.MODEDEFIRMA
  };
 %>
 
 function onChangeTipusFirmaID(combo) {
   
   if (combo == null) {
     return;
   }
   
   var value = combo.options[combo.selectedIndex].value;
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
 }

</script>