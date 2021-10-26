<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<input type="hidden" id="motiu" name="motiu" value=""/>

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
  
  <%--  Revisor --%>
  function acceptar(url, firmaid) {
    goTo(url);
  }
  

  function firmar(url, firmaid) {
      url = url + "?url_user=" + encodeURIComponent(window.location.href);
      goTo(url);
  }
  
  function getCheckboxValues() {
      
      var theForm = document.getElementById("estatDeFirmaFilterForm");
      <%-- console.log("---------- theForm = " + theForm); --%>
      var firmes = theForm.elements['selectedItems'];
      <%-- console.log("firmes = " + firmes); --%>
      var str = "";
      var iLen=firmes.length;
      
      if(typeof iLen === 'undefined'){
        <%--  És un sol element --%>
        str = firmes.value;
        <%-- console.log("Unique Value= " + firmes.value); --%>
      } else {
          <%--  És un array d'elements --%>
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

     <%--  Boto de PROCESSAR --%>
     var botoProcessar = '<button type="button" class="btn btn-small btn-warning" onclick="processarInici()">'
         + '<i class="icon-tasks"></i><fmt:message key="carret.processar.inici" />'
         + '</button>';

     function processarInici() {
       var url;
       url = '<c:url value="${contexte}/processar/inici"/>';
       document.estatDeFirma.action = url;
       document.estatDeFirma.submit();
     }

     <%--  Boto de FIRMA MULTIPLE --%>
     var botoFirmaMultiple = '<button type="button" class="btn btn-small btn-success" onclick="firmarseleccionats()">'
         + '<i class="icon-edit"></i><fmt:message key="firmarseleccionats" />'
         + '</button>';

     function firmarseleccionats() {
       var url;
       url = '<c:url value="${contexte}/firmarseleccionats"/>?url_user=' + encodeURIComponent(window.location.href);
       document.estatDeFirma.action = url;
       document.estatDeFirma.submit();
     }

     <%--  Boto de REBUIG MULTIPLE --%>
     var botoRebuigMultiple = '<button type="button" class="btn btn-small btn-danger" onclick="rebutjarseleccionats()">'
     + '<i class="fas fa-times"></i><fmt:message key="rebutjarseleccionats" />'
     + '</button>'
   
     function rebutjarseleccionats() {
         var reason = prompt("<fmt:message key="motiurebutjar"/>","");
         if (reason!=null) {      
           document.getElementById("motiu").value=reason;
           document.estatDeFirma.action = '<c:url value="${contexte}/rebutjarseleccionats"/>';
           document.estatDeFirma.submit();
         }
      }

     <%-- Afegir botons a l'esquerra --%>
     var divEsquerra = document.getElementById('estatDeFirma_pagination_left');
     divEsquerra.innerHTML += botoProcessar + botoFirmaMultiple + botoRebuigMultiple;

    </c:if>
  
  </c:if>

</script>

<c:if test="${not empty checkoutList}">
    <script type="text/javascript">
        $(document).ready(function() {
            var base = document.getElementById('${formName}_pagination');
            base.style.visibility='hidden';
        });

        function processarExecutar() {
            var url = '<c:url value="${contexte}/processar/executar"/>?url_user=' + encodeURIComponent(window.location.href);
            document.estatDeFirma.action = url;
            document.estatDeFirma.submit();
        }
    </script>
</c:if>
