<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
<c:set var="peticioID" value="${peticioDeFirma.peticioDeFirmaID}" />
<c:set var="estatID" value="${estatDeFirma.estatDeFirmaID}" />

  <!-- Contingut de la pagina -->
 
  <c:if test="${fn:length(fitxers) gt 1}">
    <button class="btn btn-sm" type="button" onclick="mostrarDiv(index - 1)"> &lt;&lt; </button>
  </c:if>
    <div class="btn-group">
    <button class="btn btn-sm dropdown-toggle" data-toggle="dropdown"><b id="filename"></b> <span class="caret"></span></button>
    <ul class="dropdown-menu">
    <c:if test="${fn:length(fitxers) gt 1}">
    <c:forEach var="fitxer" items="${fitxers}" varStatus="theCount">
      <li><a href="javascript:mostrarDiv(${theCount.index})">${fitxer.key.nom}</a></li>
      <c:if test="${theCount.index == 0 }">
         <li class="divider"></li> 
      </c:if>
    </c:forEach>
    </c:if>
    </ul>
    </div>
    <c:if test="${fn:length(fitxers) gt 1}">
    <button class="btn btn-sm" type="button" onclick="mostrarDiv(index + 1)"> &gt;&gt; </button>
    </c:if>  
    <br />

  
  <!-- Contingut de la pagina -->

  <c:forEach var="fitxer" items="${fitxers}" varStatus="theCount">

    <c:url var="urlfile" value="${pfi:fileUrl(fitxer.key)}"/>
    <c:set var="nomfile" value="${fitxer.key.nom}"/>
    <fmt:parseNumber var="type" type="number" value="${fitxer.value}" />
    
    <div id="annex_${theCount.index}" style="display:none" >
       <c:if test="${not empty signatures[fitxer.key.fitxerID]}">
         <div>
             <style>
                 .bs-docs-signatures:after {
                     content: "<fmt:message key="firmes.previes" />";
                 }
             </style>
             <form class="bs-docs-signatures bs-docs-example form-inline" style="margin-bottom: 5px;">
             <table>
                 <tr>
                   <td>
               <ul style="list-style-type:square;">
               <c:forEach var="signature" items="${signatures[fitxer.key.fitxerID]}">
               <li>
                <table>
                 <tr>
                   <td>
                       <img src="<%=request.getContextPath()%>/img/${signature.isTimeStamp?'segelldetemps.png':'firma.png'}" alt="Logo" />
                   </td><td>
               <small>
                  <strong>${signature.signerName} (${signature.signerAdministrationId})<br />
                  <c:if test="${not empty signature.organizationName}">
                     <fmt:message key="firmes.representacio" >
                       <fmt:param value="${signature.organizationName}" />
                       <fmt:param value="${signature.organizationAdministrationId}" />
                     </fmt:message><br />
                  </c:if>
                  <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${signature.signingTime}" /></strong>
               </small>
               </td></tr></table>
                
               </li>
               </c:forEach>
               </ul>
               </td></tr>
               <tr><td>
               <c:choose>
                   <c:when test="${empty signaturesValidation}">
                      <c:url var="validatingUrl" value="${contexte}/fullView/${estatID}/${peticioID}">
                        <c:param name="validar" value="1"/>
                      </c:url>
                      <a class="btn btn-sm btn-warning" href="#" onclick="goTo('${validatingUrl}')">
                         <i class="far fa-check-square"></i> <fmt:message key="validarfirmesprevies" /> </a>
                   </c:when>
                   <c:otherwise>
                       <p class="${signaturesValidation[fitxer.key.fitxerID].statusStyle}">
                            <fmt:message key="validarfirmespreviesresultat" />
                           <strong>
                               <c:out value="${signaturesValidation[fitxer.key.fitxerID].status}" />&nbsp;
                               <c:out value="${signaturesValidation[fitxer.key.fitxerID].message}" />
                           </strong>
                       </p>
                   </c:otherwise>
               </c:choose>
                </td> 
                 </tr>
               </table>
             </form>
         </div>
       </c:if>

       <c:choose>
          <c:when test="${type eq Constants.DOC_PDF}">
             <object data="${urlfile}" type="application/pdf" width="100%" height="900">    
               <p style="margin: 10px; font-weight: bolder;">
               <fmt:message key="vistacompleta.navegadornopdf" /><br />
               <fmt:message key="vistacompleta.potdescarregardocument" /><a href="${urlfile}"><c:out value="${nomfile}"/></a>
               </p>
            </object>
          </c:when>
          <c:when test="${type eq Constants.DOC_TXT}">
             <br/><br/>
             <table width="100%" border="4"><tr><td>
             <object data="${urlfile}" type="${fitxer.key.mime}" width="100%" height="750">    
               <center><a href="${urlfile}"><c:out value="${nomfile}" /></a></center>
             </object>
             </td></tr></table>
          </c:when>
          <c:when test="${type eq Constants.DOC_IMG}">
              <img src="${urlfile}" alt="<c:out value="${nomfile}"/>" />
          </c:when>
          <%-- Constants.DOC_BIN --%>
          <c:otherwise>
              <br/><br/><br/>
              <center><a target="_blank" href="${urlfile}"><c:out value="${nomfile}" /></a> <br/></center>
          </c:otherwise>
       </c:choose>
    </div>
      
  </c:forEach>



   <c:set var="ids" value="" />
   <c:set var="filenames" value="" />
   
   <c:forEach var="fitxer" items="${fitxers}" varStatus="theCount">
      <c:if test="${theCount.index > 0}">
        <c:set var="ids" value="${ids}, " />
        <c:set var="filenames">${filenames}, </c:set>
      </c:if>
   
      <c:set var="ids" value="${ids}'annex_${theCount.index}'" />
      <c:set var="filenames">${filenames}'<c:out value="${fitxer.key.nom}"/>'</c:set>
   </c:forEach>

   
   
  <script type="text/javascript">
  
      var index = 0;
      
      var ids = [${ids}];
      
      var filenames = [${filenames}];
  
      function mostrarDiv(pos) {
          if (pos < 0) {
            pos = filenames.length - 1; 
          }
  
          index = pos % filenames.length;
  
          //alert('Abans: ' + pos + '  ][ Despres: ' + index);
          if (document.getElementById) {
              ocultarTots();
              
              var el = document.getElementById(ids[index]);
              el.style.display = 'block';
              el.style.webkitTransform = 'scale(1.0001)';
  
              var f = document.getElementById('filename');
              f.innerHTML = filenames[index];
          }
      }
  
  
  
      function ocultarTots() {
          for (i = 0; i < ids.length; i++) {
              if (document.getElementById(ids[i])) {
                  document.getElementById(ids[i]).style.display = 'none';
              }
          }
      }
  
      mostrarDiv(0);
  
  </script>
   
   