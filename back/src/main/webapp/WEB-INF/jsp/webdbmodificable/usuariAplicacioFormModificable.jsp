
<script type="text/javascript">

  function onChangeCallbackVersio(select) {
  
	 var value;
     value = select.options[select.selectedIndex].value;

     var callBackUrl = document.getElementById("usuariAplicacio.callbackURL");

     if (value == '0') {
    	 callBackUrl.value="http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack";
     } else if (value == '1') {
    	 callBackUrl.value="http://localhost:8080/portafib/cb/v1/PortaFIBCallBack";
     } else if (value == '2') {
         callBackUrl.value="http://localhost:8080/portafib/cbrest/v1/event";
     } else {
         <%-- Error de desenvolupament --%>
         alert("ID de versió desconeguda: " + value);
     }

  }

</script>