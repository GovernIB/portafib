
<script type="text/javascript">

  function onChangeCallbackVersio(select) {
  
	 var value;
     value = select.options[select.selectedIndex].value;

     var callBackUrl = document.getElementById("usuariAplicacio.callbackURL");

     if (value == '0') {
    	 callBackUrl.value="http://HOST:8080/portafib/cbindra/v0/PortafirmasCallBack";
     } else {
    	 callBackUrl.value="http://HOST:8080/portafib/cb/v1/PortaFIBCallBack";
     }

  }

</script>