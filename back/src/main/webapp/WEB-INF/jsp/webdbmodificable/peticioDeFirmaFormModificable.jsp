
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
</script>