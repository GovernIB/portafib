<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${estatDeFirmaFilterForm.visibleMultipleSelection}">
  <a class="btn  btn-small pull-left" style="position: relative; bottom: 50px;" role="button" data-toggle="modal"
    href="#" onclick="firmarseleccionats()"> <i class="icon-edit"></i>
    <fmt:message key="firmarseleccionats" /> 
  </a>
</c:if>

<script>
  function invalidar(url) {
    var x;
    
    var reason = prompt("<fmt:message key="motiuinvalidar"/>","");
    
    if (reason!=null) {      
      document.getElementById("motiu").value=reason;
      document.motiuform.action = url;
      document.motiuform.submit();
    }
  }

  function rebutjar(url, estatID) {

    var motiusRebuig;

    <c:forEach var="entry" items="${rebuigDescriptionByEstat}">
    if (estatID == <c:out value="${entry.key}"/>) {
      motiusRebuig = "${pfi:escapeJavaScript(entry.value)}";
    } else 
    </c:forEach>
    {
      motiusRebuig = "";
    }
    var reason = prompt("<fmt:message key="motiurebutjar"/>:", motiusRebuig);
    
    if (reason != null) {      
      document.getElementById("motiu").value=reason;
      document.motiuform.action = url;
      document.motiuform.submit();
    }
  }

  function firmarseleccionats() {
    document.estatDeFirma.action = '<c:url value="${contexte}/firmarseleccionats"/>';
    document.estatDeFirma.submit();
  }
  
</script>
