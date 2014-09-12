<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>

<form:form name="estatDeFirma" cssClass="form-search"  modelAttribute="estatDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="../webdb/estatDeFirmaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
  <%@include file="../webdb/estatDeFirmaListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>
  <c:if test="${estatDeFirmaFilterForm.visibleMultipleSelection}">
      <a class="btn pull-right" role="button" data-toggle="modal"
        href="#" onclick="firmarseleccionats()"> <i class="icon-edit"></i>
        <fmt:message key="firmarseleccionats" /> 
      </a>
  </c:if>

  </div>
  <%@include file="../webdb/estatDeFirmaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="../webdb/estatDeFirmaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty estatDeFirmaItems}">
          <%@include file="../webdb/estatDeFirmaListEmpty.jsp" %>
  </c:if>
  
  <c:if test="${not empty estatDeFirmaItems}">


  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="../webdb/estatDeFirmaListCoreHeaderMultipleSelect.jsp" %>
<%-- 
          <c:if test="${estatDeFirmaFilterForm.visibleMultipleSelection}">
          <th>
             <input type="checkbox" onClick="selectUnselectCheckBoxes(this)" />
          </th>
          </c:if>
--%>
          <th><fmt:message key="peticioDeFirma.peticioDeFirma"/></th>

          <%@include file="../webdb/estatDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>
          <c:if test="${pipella eq Constants.ROLE_DEST || pipella eq Constants.ROLE_DELE}">
          <th>
           <fmt:message key="peticioDeFirma.remitentNom"/>
          </th>
          </c:if> 

          <c:if test="${pipella eq Constants.ROLE_COLA}">
          <th>
            <fmt:message key="colaboracioDelegacio.destinatariID"/>
          </th>
          </c:if>

          <c:if test="${pipella eq Constants.ROLE_DEST}">
          <th><fmt:message key="ROLE_DELE.plural" /></th>
          </c:if>

<c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DESTINATARIID)}">
          <c:if test="${pipella eq Constants.ROLE_DEST || pipella eq Constants.ROLE_DELE}">
          <th><fmt:message key="colaborador.short" /></th>
          </c:if>
</c:if>
          <th>
            <i title="<fmt:message key="peticioDeFirma.prioritatID"/>" class="icon-warning-sign"></i>
          </th>

          <th><fmt:message key="genapp.actions" /></th>

          <%@include file="../webdb/estatDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatDeFirma" items="${estatDeFirmaItems}">
        <tr>

          <c:set var="estatID" value="${estatDeFirma.estatDeFirmaID}"/>
          <c:set var="peticioDeFirma" value="${peticionsByEstat[estatDeFirma.estatDeFirmaID]}"/>
          <c:set var="peticioID" value="${peticioDeFirma.peticioDeFirmaID}"/>
  
          <%--  CHECK DE SELECCIO MULTIPLE  --%>
           <c:if test="${estatDeFirmaFilterForm.visibleMultipleSelection}">
           <td>
             <c:if test="${empty estatDeFirma.dataFi }">
            <form:checkbox path="selectedItems" value="${estatID}/${peticioID}"/>
            </c:if>
            &nbsp;
           </td>
           </c:if>
            
           <td>
              ${peticioDeFirma.titol}          
           </td>
        
        
        
          <%@include file="../webdb/estatDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>
          <c:if test="${pipella eq Constants.ROLE_DEST || pipella eq Constants.ROLE_DELE}">
          <td>
             <small title="${peticioDeFirma.remitentDescripcio}" >${peticioDeFirma.remitentNom}</small>          
          </td>
          </c:if>
          
          <c:if test="${pipella eq Constants.ROLE_COLA}">
             <td>
               <small>
                 ${infoDestByEstat[estatDeFirma.estatDeFirmaID]}
               </small>          
            </td>
          </c:if>

          <%--  DELEGATS  --%>
          <c:if test="${pipella eq Constants.ROLE_DEST}">
             
             <td> <%-- DELEGATS --%>
               <c:set var="valors" value="${infoDelegatsByEstat[estatDeFirma.estatDeFirmaID]}"/>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2] != 0}">
                  <small><fmt:message key="tipusestatdefirmafinal.FIRMAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2] != 0}">
                  <small><fmt:message key="tipusestatdefirmafinal.REBUTJAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0}">             
                  <small><fmt:message key="tipusestatdefirmafinal.DESCARTAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[1] != 0}">
                  <small><fmt:message key="pendent"/>: ${valors[1]}/${valors[0]}</small><br/>
               </c:if>
             </td>
          </c:if>
  
          <%--  COL.LABORADORS  --%>
          <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DESTINATARIID)}">
          <c:if test="${pipella eq Constants.ROLE_DEST || pipella eq Constants.ROLE_DELE}">
             <td> <%-- COLABORADORS --%>
               <c:set var="valors" value="${infoColaboradorsByEstat[estatDeFirma.estatDeFirmaID]}"/>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2] != 0}">
                  <small><fmt:message key="tipusestatdefirmafinal.VALIDAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] != 0}">
                  <small><fmt:message key="tipusestatdefirmafinal.INVALIDAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0}">             
                  <small><fmt:message key="tipusestatdefirmafinal.DESCARTAT"/>: ${valors[Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT+ 2]}/${valors[0]}</small><br/>
               </c:if>
               <c:if test="${valors[1] != 0}">
                  <small><fmt:message key="pendent"/>: ${valors[1]}/${valors[0]}</small><br/>
               </c:if>           
             </td>
             
          </c:if>
          </c:if>
          
          <%--  PRIORITAT  --%>
          <td>
            <c:choose>
                <c:when test="${peticioDeFirma.prioritatID <= 2}">
                   <c:set var="color_priority" value="btn-success"/>
                   <c:set var="title_priority" value="prioritat.baixa"/>
                </c:when>
                <c:when test="${peticioDeFirma.prioritatID >= 7}">
                    <c:set var="color_priority" value="btn-danger"/>
                    <c:set var="title_priority" value="prioritat.alta"/>
                </c:when>
                <c:otherwise>
                    <c:set var="color_priority" value="btn-warning"/>
                    <c:set var="title_priority" value="prioritat.normal"/>
                </c:otherwise>
            </c:choose>
          
            <button title="<fmt:message key="${title_priority}"/>" class="btn btn-mini ${color_priority}" type="button">&nbsp;</button>
         
          </td>
          
            
          <%--  ACCCIONS  --%>
          <td>
          
            <div class="btn-group" data-toggle="buttons-checkbox">
            <%--
            // TODO aqui em de veure si el fitxer s'ha firma mirant si la Firma associada té fitxerFirmatID 
             --%>
            <a class="btn btn-info" href="<c:url value="${contexte}/docfirmat/${peticioID}"/>" target="_blank"   title="<fmt:message key="veuredoc"/>">
              <i class="icon-file"></i>
            </a>
            
            <c:set var="estat" value="${estatDeFirma.tipusEstatDeFirmaInicialID }"/>
            
            <c:if test="${estat eq Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR }">
              <a class="btn btn-info" href="#" 
                onclick="goTo('<c:url value="${contexte}/fullView/${estatID}/${peticioID}"/>')"
                title="<fmt:message key="vistacompleta"/>">
                <i class="icon-eye-open"></i>
              </a>
            </c:if>
            
           <c:if test="${empty estatDeFirma.tipusEstatDeFirmaFinalID}">  
  
            <c:if test="${estat eq Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR }">
              
              <a class="btn btn-success" href="#" 
                onclick="goTo('<c:url value="${contexte}/firmar/${estatID}/${peticioID}"/>')"
                title="<fmt:message key="firmar"/>">
                <i class="icon-edit"></i>
              </a>
              
              
              <c:url var="_tmpUrl" value="${contexte}/rebutjar/${estatID}/${peticioID}"/>
              
              <c:set var="_senseEscapar" value="rebutjar('${pfi:escapeJavaScript(_tmpUrl)}', ${estatID})" />
              
              <a class="btn btn-danger" href="#"
                onclick="${_senseEscapar}"
                title="<fmt:message key="rebutjar"/>">
                <i class="icon-remove"></i>
              </a>
  
            </c:if>
            
                      
            <c:if test="${estat eq Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR }">
              <a class="btn btn-success" href="#" 
                onclick="goTo('<c:url value="${contexte}/marcarrevisant/${estatID}/${peticioID}"/>')"
                title="<fmt:message key="marcarrevisant"/>">
                <i class="icon-flag"></i>
              </a>
            </c:if>
            
            <c:if test="${estat eq Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR || estat eq Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR }">
              <a class="btn btn-success" href="#" 
                onclick="goTo('<c:url value="${contexte}/validar/${estatID}/${peticioID}"/>')"
                title="<fmt:message key="validar"/>">
                <i class="icon-check"></i>
              </a>
              <a class="btn btn-danger" href="#" 
                onclick="invalidar('<c:url value="${contexte}/invalidar/${estatID}/${peticioID}"/>')"
                title="<fmt:message key="invalidar"/>">
                <i class="icon-remove"></i>
              </a>
            </c:if>
            
            </c:if>
            
            </div>
          
           </td>

          <%@include file="../webdb/estatDeFirmaListButtons.jsp" %>
            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty estatDeFirmaItems}">
          <%@include file="../webdb/webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 


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


<form name="motiuform" action="" method="post">
  <input type="hidden" id="motiu" name="motiu"/>
</form>


