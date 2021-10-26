<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
<c:set var="peticioID" value="${peticioDeFirma.peticioDeFirmaID}" />
<c:set var="estatID" value="${estatDeFirma.estatDeFirmaID}" />
<!--  INICI MENU -->
<div class="mainMenu span5">

  <iframe scrolling="auto"
    src="<c:url value="/${rolecontext}/plantilla/viewonlyflux/${peticioDeFirma.fluxDeFirmesID}?readOnly=true"/>"
    style="width: 100%; height: 375px;"> </iframe>

  <br /> <br />
  
  <c:if test="${empty estatDeFirma.tipusEstatDeFirmaFinalID }">
      <div style="text-align: center">     
        <c:choose>
            <%-- Comprovam que hi ha carret i que coincideix amb la petició --%>
            <c:when test="${not empty carretEstatsPeticio && carretEstatsPeticio.estat eq estatDeFirma.estatDeFirmaID }">
                
                <fmt:message key="carret.processar.progress">
                    <fmt:param value="${carretEstatsPeticio.position}" />
                    <fmt:param value="${carretEstatsPeticio.total}" />
                </fmt:message>
                
                <br />
                
                <a class="btn btn-success" href="#" onclick="goTo('<c:url value="${contexte}/processar/afegirFirmar"/>')">
                  <i class="icon-edit"></i> <fmt:message key="carret.processar.firmar" /> </a>   

                <a class="btn btn-warning" href="#" onclick="goTo('<c:url value="${contexte}/processar/ignorar"/>')">
                  <i class="icon-edit"></i> <fmt:message key="carret.processar.ignorar" /> </a>   
                             
                <a class="btn btn-danger" href="#" onclick="rebutjar('<c:url value="${contexte}/processar/afegirRebutjar"/>')">
                  <i class="fas fa-times"></i> <fmt:message key="carret.processar.rebutjar" /> </a>            
                                
                <a class="btn" href="#" onclick="goTo('<c:url value="${contexte}/processar/cancelar"/>')">
                  <i class="icon-off"></i> <fmt:message key="carret.processar.cancelar" /> </a>

            </c:when>     
            <c:otherwise>
                <%-- Ens asseguram que eliminam instàncies obsoletes  --%>
                <c:remove var="carretEstatsPeticio" scope="session" />
                
                <c:if test="${ rolecontext eq 'revi' }" >
                   <a class="btn btn-success" href="#"
                  onclick="goTo('<c:url value="${contexte}/acceptar/${estatID}/${peticioID}"/>')"> <i
                  class="far fa-check-square"></i> <fmt:message key="revisor.acceptar" /> </a> &nbsp;&nbsp; 
                </c:if>
                <c:if test="${ rolecontext eq 'dest' || rolecontext eq 'dele' || rolecontext eq 'common' }" >
                  <a class="btn btn-success" href="#"
                  onclick="firmar('<c:url value="${contexte}/firmar/${estatID}/${peticioID}"/>')"> <i
                  class="icon-edit"></i> <fmt:message key="firmar" /> </a> &nbsp;&nbsp; 
                </c:if>        
                <a class="btn btn-danger" href="#"
                  onclick="rebutjar('<c:url value="${contexte}/rebutjar/${estatID}/${peticioID}"/>')">
                  <i class="fas fa-times"></i> <fmt:message key="rebutjar" /> </a>            
            </c:otherwise>      
        </c:choose>
      </div>
  </c:if>

  <div>
    <style>
        .bs-docs-peticio:after {
        	content: "<fmt:message key="peticioDeFirma.peticioDeFirma" />";
        }
    </style>
    <form class="bs-docs-peticio bs-docs-example form-inline" style="margin-bottom: 5px;">
      <small>
       <b> <fmt:message key="peticioDeFirma.titol" />:</b> 
           <c:out  value="${peticioDeFirma.titol}" /><br /> 

       <b> <fmt:message key="peticioDeFirma.motiu" />:</b>
           <c:out value="${peticioDeFirma.motiu}" /><br />

       <b> <fmt:message key="peticioDeFirma.descripcio" />:</b>
           <c:out  value="${peticioDeFirma.descripcio}" /><br />

       <b> <fmt:message key="peticioDeFirma.remitentNom" />:</b>
           <c:out  value="${peticioDeFirma.remitentNom}" /><br />

       <c:if test="${not empty peticioDeFirma.remitentDescripcio}">
       <b> <fmt:message key="peticioDeFirma.remitentDescripcio" />:</b>
           ${pfi:processEmailURL(peticioDeFirma.remitentDescripcio)}<br />
       </c:if>
         
       <b><fmt:message key="peticioDeFirma.prioritatID" />:</b>
               <fmt:message key="prioritat.${peticioDeFirma.prioritatID}" /><br />

       <c:if test="${not empty peticioDeFirma.expedientCodi}">
           <b> <fmt:message key="peticioDeFirma.expedientCodi" />:</b>
             ${peticioDeFirma.expedientCodi}<br />
       </c:if>
         
       <c:if test="${not empty peticioDeFirma.expedientNom}">
           <b> <fmt:message key="peticioDeFirma.expedientNom" />:</b>
             ${peticioDeFirma.expedientNom}<br />
       </c:if>

       <c:if test="${not empty peticioDeFirma.expedientUrl}">
           <b> <fmt:message key="peticioDeFirma.expedientUrl" />:</b>
             ${pfi:processEmailURL(peticioDeFirma.expedientUrl)}<br />
       </c:if>
         
       <c:if test="${not empty peticioDeFirma.procedimentCodi}">
           <b> <fmt:message key="peticioDeFirma.procedimentCodi" />:</b>
             ${peticioDeFirma.procedimentCodi}<br />
       </c:if>
         
       <c:if test="${not empty peticioDeFirma.procedimentNom}">
           <b> <fmt:message key="peticioDeFirma.procedimentNom" />:</b>
             ${peticioDeFirma.procedimentNom}<br />
       </c:if>
       
       <c:if test="${not empty peticioDeFirma.informacioAddicional}">
         <b> <fmt:message key="peticioDeFirma.informacioAddicional" />:</b>
           ${pfi:processEmailURL(peticioDeFirma.informacioAddicional)}<br />      
       </c:if>
       
       <c:if test="${not empty peticioDeFirma.informacioAddicionalAvaluable}">
         <b> <fmt:message key="peticioDeFirma.informacioAddicionalAvaluable" />:</b>
           ${pfi:processEmailURL(peticioDeFirma.informacioAddicionalAvaluable)}<br />      
       </c:if>
         
     </small>
    </form>
  </div>
    
  <div>
    <style>
        .bs-docs-example-dest:after {
            content: "<fmt:message key="ROLE_DEST" />";
        }
    </style>
    <form class="bs-docs-example-dest bs-docs-example form-inline" style="margin-bottom: 5px;">
      <ul style="list-style-type:square;">
      <li>
      <small>
         ${pfi:getNom(destinatari.usuariEntitat.usuariPersona)}
         &nbsp;<img src="<c:url value="/img/fletxa.gif" />"/>&nbsp;
         <b>
           <c:choose>
             <c:when test="${empty destinatari.tipusEstatDeFirmaFinalID}">
                <fmt:message key="pendent" />
             </c:when>
             
             <c:when test="${destinatari.tipusEstatDeFirmaFinalID == Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT }">
                <fmt:message key="${traduccions[destinatari.tipusEstatDeFirmaFinalID]}" /><br/>
                <small style="color: red;">${destinatari.descripcio}</small>
             </c:when>

             <c:otherwise>
                <fmt:message key="${traduccions[destinatari.tipusEstatDeFirmaFinalID]}" /> 
             </c:otherwise>

           </c:choose>
        </b>
      </small>
      </li>
      </ul>
    </form>
    

    <c:if test="${not empty delegats}">

      <style>
          .bs-docs-example-dele:after {
          	content: "<fmt:message key="ROLE_DELE.plural" />";
          }
      </style>

      <form class="bs-docs-example-dele bs-docs-example form-inline" style="margin-bottom: 5px;">
        
        <ul style="list-style-type:square;">
          <c:forEach var="estat" items="${delegats}">
          <li> 
          <small> 
             ${pfi:getNom(estat.usuariEntitat.usuariPersona)} 
             &nbsp;<img src="<c:url value="/img/fletxa.gif" />"/>&nbsp;
             <b>               
             <c:choose>
               <c:when test="${empty estat.tipusEstatDeFirmaFinalID}">
                  <fmt:message key="pendent" />
               </c:when>
               
               <c:when test="${estat.tipusEstatDeFirmaFinalID == Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT }">
                    <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" />
                    <small style="color: red;">${estat.descripcio}</small>
               </c:when>
               
               <c:otherwise>
                  <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" /> 
               </c:otherwise>
             </c:choose>
             </b>
            </small>
            </li>
          </c:forEach>
          </ul>
      </form>
    </c:if>

    <c:if test="${not empty colaboradors}">

      <style>
        .bs-docs-example-cola:after {
        	content: "<fmt:message key="ROLE_COLA.plural" />";
        }
      </style>

      
      <form class="bs-docs-example-cola bs-docs-example form-inline">
        
        <ul style="list-style-type:square;">
           <c:forEach var="estat" items="${colaboradors}"> 
              <li>              
              <small>
              ${pfi:getNom(estat.usuariEntitat.usuariPersona)}  
              &nbsp;<img src="<c:url value="/img/fletxa.gif" />"/>&nbsp;
               <b>
               <c:choose>
                 <c:when test="${empty estat.tipusEstatDeFirmaFinalID}">
                    <fmt:message key="pendent" />
                 </c:when>
                 <c:when test="${estat.tipusEstatDeFirmaFinalID == Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT }">
                    <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" /><br/>
                    <small style="color: red;">${estat.descripcio}</small>
                    <c:set var="valorrebuig" value="${estat.descripcio}. ${valorrebuig}"/>
                 </c:when>
                 <c:otherwise>
                    <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" /> 
                 </c:otherwise>
               </c:choose>
               </b> 
               </small>              
               </li>
          </c:forEach>
        </ul>
        
        
      </form>
      
    </c:if>
    
    
    <c:if test="${not empty revisors}">

      <style>
          .bs-docs-example-revi:after {
            content: "<fmt:message key="ROLE_REVI.plural" />";
          }
      </style>

      <form class="bs-docs-example-revi bs-docs-example form-inline" style="margin-bottom: 5px;">
        
        <ul style="list-style-type:square;">
          <c:forEach var="estat" items="${revisors}">
          <li> 
          <small> 
             ${pfi:getNom(estat.usuariEntitat.usuariPersona)} 
             &nbsp;<img src="<c:url value="/img/fletxa.gif" />"/>&nbsp;
             <b>               
             <c:choose>
               <c:when test="${empty estat.tipusEstatDeFirmaFinalID}">
                  <fmt:message key="pendent" />
               </c:when>
               
               <c:when test="${estat.tipusEstatDeFirmaFinalID == Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT }">
                    <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" />
                    &nbsp; (<small style="color: red;">${estat.descripcio}</small>)
               </c:when>
               
               <c:otherwise>
                  <fmt:message key="${traduccions[estat.tipusEstatDeFirmaFinalID]}" /> 
               </c:otherwise>
             </c:choose>
             </b>
            </small>
            </li>
          </c:forEach>
          </ul>
      </form>
    </c:if>

  </div>

</div>

<!-- CONTINGUT -->
<div class="span7">

  <!--  Missatges  -->
  <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />
  
  <%--  Visor del Document a firmar i dels annexes --%>
  <jsp:include page="/WEB-INF/jsp/dest/estatFirmaDocuments.jsp" />

  <!-- FINAL DIV CONTINGUT -->
</div>

<c:if test="${empty estatDeFirma.tipusEstatDeFirmaFinalID }">
<script>


  function firmar(url) {
    url = url + "?url_user=" + encodeURIComponent(window.location.href);
    goTo(url);
  }


  function rebutjar(url) {
    var reason = prompt("<fmt:message key="motiurebutjar"/>:","${pfi:escapeJavaScript(valorrebuig)}");
    if (reason!=null) {
      document.location.href = url + "?motiu=" + encodeURIComponent(reason);
    }
  }
  
</script>
</c:if>

<div class="clearfix"></div>