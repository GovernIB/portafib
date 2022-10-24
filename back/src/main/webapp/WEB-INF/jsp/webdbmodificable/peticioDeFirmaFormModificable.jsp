
<%@page import="org.fundaciobit.genapp.common.query.Field"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="es.caib.portafib.model.fields.PeticioDeFirmaFields"%>




<c:if test="${descripcioTipusVisible}">
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

<script>

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