<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<input type="hidden" id="motiu" name="motiu" value=""/>

<script>

  <c:if test="${pipella eq 'ROLE_COLA'}">

  function invalidar(url) {
    var x;
    
    var reason = prompt("<fmt:message key="motiuinvalidar"/>","");
    
    if (reason!=null) {      
      document.getElementById("motiu").value=reason;
      document.estatDeFirma.action = url;
      document.estatDeFirma.submit();
    }
  }
  
  

  </c:if>

  <c:if test="${pipella ne 'ROLE_COLA'}">
  
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
      document.estatDeFirma.action = url;
      document.estatDeFirma.submit();
    }
  }

    <c:if test="${ estatDeFirmaFilterForm.visibleMultipleSelection}">

     <%--  Boto de FIRMA MULTIPLE --%>
     
     var botoFirmaMultiple = '<button type="button" class="btn btn-small btn-success" onclick="firmarseleccionats()">'
         + '<i class="icon-edit"></i><fmt:message key="firmarseleccionats" />'
         + '</button>';

     <%--
     var botoFirmaMultiple = '<a class="btn btn-small btn-succes"  role="button" data-toggle="modal"' 
         + ' href="#" onclick="firmarseleccionats()"> <i class="icon-edit"></i>'
         + '<span style="style="color: white;""><fmt:message key="firmarseleccionats" /></span>'
         + '</a>';
      --%>
    

     function firmarseleccionats() {
       document.estatDeFirma.action = '<c:url value="${contexte}/firmarseleccionats"/>';
       document.estatDeFirma.submit();
     }
     
     

     <%--  Boto de REBUIG MULTIPLE --%>
     <%--
     var botoRebuigMultiple = '<a class="btn  btn-small btn-danger"  role="button" data-toggle="modal"' 
         + ' href="#" onclick="rebutjarseleccionats()"> <i class="icon-remove"></i>'
         + '<fmt:message key="rebutjarseleccionats" />'
         + '</a>';
     --%>
     var botoRebuigMultiple = '<button type="button" class="btn btn-small btn-danger" onclick="rebutjarseleccionats()">'
     + '<i class="icon-remove"></i><fmt:message key="rebutjarseleccionats" />'
     + '</button>'
   
     function rebutjarseleccionats() {
         var x;
         
         var reason = prompt("<fmt:message key="motiurebutjar"/>","");
         
         if (reason!=null) {      
           document.getElementById("motiu").value=reason;
           document.estatDeFirma.action = '<c:url value="${contexte}/rebutjarseleccionats"/>';
           document.estatDeFirma.submit();
         }
      }
     
     
     <%-- Afegir botons a l'esquerra --%>
         
     var divEsquerra = document.getElementById('estatDeFirma_pagination_left');
   
     divEsquerra.innerHTML += botoFirmaMultiple + botoRebuigMultiple ;
   
  
    </c:if>
  
  </c:if>

</script>
