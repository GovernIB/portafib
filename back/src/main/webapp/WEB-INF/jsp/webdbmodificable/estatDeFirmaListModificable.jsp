<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<input type="hidden" id="motiu" name="motiu" value=""/>

<c:if test="${pipella ne 'ROLE_COLA'}">
  
<div id="ajaxloader" style="position:absolute; left:0px; top:0px; visibility:hidden; border:none;z-index:100;width:100%;height:100%;background:#CCC;filter: alpha(opacity=80);-moz-opacity:.8; opacity:.8;">
  <table style="width:100%;height:100%;">
  <tr valign="middle"><td align="center">
  <h3 style="color:#FFF;"><fmt:message key="autofirma.jnlp"/></h3><br/>
  <img src="<c:url value="/img/ajax-loader2.gif"/>" /><br/>
  <br/>
  <input type="button" class="btn btn-primary" onclick="javascript:goTo('<c:url value="${contexte}/list" />');" value="<fmt:message key="tornar"/>" />
  </td></tr></table>
</div>

</c:if>


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
      
      if (deployJava.isPluginInstalled()) {
          goTo(url);
      } else {
          document.getElementById("ajaxloader").style.visibility = "visible";
          myTimer = setInterval(function () {closeWhenSign(firmaid)}, 20000);
          goTo(url + '/Firmar.jnlp');
      }
      
  }
  
  function closeWhenSign(firmes) {
      
      if (!firmes || $.trim(firmes) == '') {
          return;
      }
      
      var request;
      if(window.XMLHttpRequest) {
          request = new XMLHttpRequest();
      } else {
          request = new ActiveXObject("Microsoft.XMLHTTP");
      }



      request.open('GET', '<c:url value="${contexte}/estatdelesfirmes/" />' + firmes, false);
      request.send(); 
      <%--  there will be a 'pause' here until the response to come.
      // the object request will be actually modified --%>
      if ((request.status + '') == '200') {
          clearTimeout(myTimer);
          window.location.href = '<c:url value="${contexte}/list" />';
      }
      clearTimeout(myTimer);
      myTimer = setInterval(function () {closeWhenSign(firmes)}, 4000);
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
             url = '<c:url value="${contexte}/firmarseleccionats"/>/Firmar.jnlp';
             document.getElementById("ajaxloader").style.visibility = "visible";
             myTimer = setInterval(function() {closeWhenSign(getCheckboxValues())}, 15000);        
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
