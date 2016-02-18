<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<input type="hidden" id="motiu" name="motiu" value=""/>


<script src="<c:url value="/js/deployJava.jsp"/>"></script>


<script type="text/javascript">

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

  
  function firmar(url, firmaid) {
      if (deployJava && deployJava.isPluginInstalled()) {
          goTo(url);
      } else {
          goTo(url + '/Firmar.jnlp');
      }
  }
  
  function getCheckboxValues() {
      
      var theForm = document.getElementById("estatDeFirmaFilterForm");
      <%-- console.log("---------- theForm = " + theForm); --%>
      var firmes = theForm.elements['selectedItems'];
      <%-- console.log("firmes = " + firmes); --%>
      var str = "";
      var iLen=firmes.length;
      
      if(typeof iLen === 'undefined'){
        <%--  és un sol element --%>
        str = firmes.value;
        <%-- console.log("Unique Value= " + firmes.value); --%>
      } else {
          <%--  és un array d'elements --%>
          console.log("iLen = " + iLen);
          for (var i=0; i<iLen; i++) {
            if (firmes[i].checked) {
                if (str.length != 0) {
                    str = str + ",";
                }
                <%-- console.log("Value[" + i + "]= " + firmes[i].value); --%>
                str = str + firmes[i].value;
            }
          }
      }
      return str;
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
      document.estatDeFirma.action = url;
      document.estatDeFirma.submit();
    }
  }

    <c:if test="${ estatDeFirmaFilterForm.visibleMultipleSelection}">

     <%--  Boto de FIRMA MULTIPLE --%>
     var botoFirmaMultiple = '<button type="button" class="btn btn-small btn-success" onclick="firmarseleccionats()">'
         + '<i class="icon-edit"></i><fmt:message key="firmarseleccionats" />'
         + '</button>';

     function firmarseleccionats() {
       var url;
       if (deployJava.isPluginInstalled()) {
             url = '<c:url value="${contexte}/firmarseleccionats"/>';
       } else {
           <%-- NOTA: AIXó ES CORRECTE. FYI. --%>
             url = '<c:url value="${contexte}/firmarseleccionats"/>/Firmar.jnlp';    
       }
         
       document.estatDeFirma.action = url;
       document.estatDeFirma.submit();
     }
     
     

     <%--  Boto de REBUIG MULTIPLE --%>
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