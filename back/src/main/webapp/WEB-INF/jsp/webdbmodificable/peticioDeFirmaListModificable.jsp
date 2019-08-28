<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<input type="hidden" id="motiuRebuig" name="motiuRebuig" value=""/>

<script type="text/javascript">


  function rebutjar(peticioID) {


    var reason = prompt("<fmt:message key="motiurebutjar"/>:", "");
    
    if (reason != null) {      
      document.getElementById("motiuRebuig").value=reason;
      document.peticioDeFirma.action = '<c:url value="${contexte}/rebutjar/"/>' + peticioID;
      document.peticioDeFirma.submit();
    }
  }
  
  
  
  function rebutjarseleccionades() {
    var x;
    var reason = prompt("<fmt:message key="motiurebutjar"/>","");
    
    if (reason!=null) {      
      document.getElementById("motiuRebuig").value=reason;
      document.peticioDeFirma.action = '<c:url value="${contexte}/rebutjarSeleccionades"/>';
      document.peticioDeFirma.submit();
    }
 }
  
  
  
</script>