<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />

<div class="navbar">
  <div class="navbar-inner">
    <div class="left">
      <a href="#" class="link" onClick="goBack()">
        <i class="icon icon-back"></i>
        <span> </span>
       </a>
    </div>
    <div class="center">
    	Informació Sol·licitud
  	</div>
    <div class="right">
        <a href="#" class="link open-panel"><i class="icon f7-icons">bars</i></a>
    </div>
  </div>
</div>

<div class="pages navbar-through toolbar-through">
    <!-- Page, "data-page" contains page name -->
    <div data-page="estatDeFirmaFullView" class="page">
      <!-- Scrollable page content -->
      <div class="page-content">
		<div class="content-block">
			<!-- CONTENT -->
	  		<div class="row">
			    <div class="col-100">
				  <iframe scrolling="auto"
				    src="<c:url value="/${rolecontext}/plantilla/viewonlyflux/${peticioDeFirma.fluxDeFirmesID}?readOnly=true"/>"
				    style="width: 100%; height: 375px;"></iframe>
			    </div>
			</div>
			<br/>
			
			
			<div class="block">
			    <c:forEach var="fitxer" items="${fitxers}" varStatus="theCount">
			    	 <c:url var="urlfile" value="${pfi:fileUrl(fitxer.key)}"/>
   					 <c:set var="nomfile" value="${fitxer.key.nom}"/>
   					 <c:set var="idFile" value="${fitxer.key.fitxerID}"/>
   					 
   					 <p class="row mall-vert-margins" onclick="javascript:var win = window.open('<c:url value="${contexte}/docVista/${idFile}"/>', '_blank'); win.focus();">
					    <a href="#" target="_blank" class="col-80 button button-fill color-orange">${nomfile}</a>
					 </p>
   					 
			    </c:forEach>
		    </div>
			
			<br/>
		
		    <c:set var="peticioID" value="${peticioDeFirma.peticioDeFirmaID}" />
		    <c:set var="estatID" value="${estatDeFirma.estatDeFirmaID}" />
		   
		    <c:if test="${empty estatDeFirma.tipusEstatDeFirmaFinalID }">
			  <div class="row">
			    <div class="col-100">
				  	<div class="block">
					  <p class="row">
					    <a class="col-40 button button-fill button-big color-green" href="#" onclick="goTo('<c:url value="${contexte}/firmar/${estatID}/${peticioID}"/>')"> 
					      <i class="f7-icons">compose</i> 
					      <fmt:message key="firmar" />
					    </a> 
					    <a class="col-40 button button-fill button-big color-red" href="#" onclick="rebutjar('<c:url value="${contexte}/rebutjar/${estatID}/${peticioID}"/>')">
					      <i class="f7-icons">close_round</i>
					      <fmt:message key="rebutjar" />
					    </a>
					  </p>
					</div>
			  	</div>
			  </div>
		    </c:if>
			<div class="row">
			    <div class="col-100">
			    	<div>
				    <style>
				        .bs-docs-example:after {
				        	content: "<fmt:message key="peticioDeFirma.peticioDeFirma" />";
				        }
				    </style>
				    <form class="bs-docs-example bs-docs-example form-inline" style="margin-bottom: 5px;">
				      <small>
				       <b> <fmt:message key="peticioDeFirma.titol" />:</b> 
				           <c:out  value="${peticioDeFirma.titol}" /><br /> 
				
				       <b> <fmt:message key="peticioDeFirma.motiu" />:</b>
				           <c:out value="${peticioDeFirma.motiu}" /><br />
				
				       <b> <fmt:message key="peticioDeFirma.descripcio" />:</b>
				           <c:out  value="${peticioDeFirma.descripcio}" /><br />
				
				       <b> <fmt:message key="peticioDeFirma.remitentNom" />:</b>
				           <c:out  value="${peticioDeFirma.remitentNom}" /><br />
				       <c:if test="${not empty peticioDeFirma.informacioAdicional}">
				       <b> <fmt:message key="peticioDeFirma.remitentDescripcio" />:</b>
				           ${pfi:processEmailURL(peticioDeFirma.remitentDescripcio)}<br />
				       </c:if>  
				       <c:if test="${not empty peticioDeFirma.informacioAdicional}">
				         <b> <fmt:message key="expedient.informacio" />:</b>
				           ${pfi:processEmailURL(peticioDeFirma.informacioAdicional)}<br />      
				        </c:if> 
				        <c:choose>
				          <c:when test="${peticioDeFirma.prioritatID <= 2}">
				             <c:set var="title_priority" value="prioritat.baixa"/>
				          </c:when>
				          <c:when test="${peticioDeFirma.prioritatID >= 7}">
				              <c:set var="title_priority" value="prioritat.alta"/>
				          </c:when>
				          <c:otherwise>
				              <c:set var="title_priority" value="prioritat.normal"/>
				          </c:otherwise>
				         </c:choose>
				         <b><fmt:message key="peticioDeFirma.prioritatID" />:</b>
				               <fmt:message key="${title_priority}" />
				
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
				
				  </div>
			    </div>
			</div>
		</div>
     	<!-- END CONTENT -->
    </div>
  </div>
</div>

<c:if test="${empty estatDeFirma.tipusEstatDeFirmaFinalID }">
<script>

  function rebutjar(url) {
    var x;
    
    var reason = prompt("<fmt:message key="motiurebutjar"/>:","${pfi:escapeJavaScript(valorrebuig)}");
    
    if (reason!=null) {
      document.location.href = url + "?motiu=" + encodeURIComponent(reason);
    }
  }
  
</script>
</c:if>