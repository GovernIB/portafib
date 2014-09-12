<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Utils" className="es.caib.portafib.utils.Constants"/>

<style>

.a_item {
  text-align: left;
  margin:5px;
}

</style>


<c:if test="${fn:startsWith(peticioDeFirmaFilterForm.contexte,'/aden/')}" >
    <c:set var="context_role" value="aden" />
</c:if>

<c:if test="${fn:startsWith(peticioDeFirmaFilterForm.contexte,'/soli/')}" >
    <c:set var="context_role" value="soli" />
</c:if>


<form:form name="peticioDeFirma" cssClass="form-search"  modelAttribute="peticioDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="../webdb/peticioDeFirmaListCommon.jsp" %>
  
  <div class="filterLine lead">
  <%@include file="../webdb/peticioDeFirmaListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>
  
  <c:if test="${isSolicitantUsuariEntitat == true}">
    <a class="btn pull-right btn-small" role="button" data-toggle="modal"
      href="<c:url value="${contexte}/new"/>"> <i class="icon-plus-sign"></i>
     <fmt:message key="genapp.createtitle" >
       <fmt:param><fmt:message key="peticioDeFirma.peticioDeFirma"/></fmt:param>
     </fmt:message>
    </a>
  </c:if>
  
  <%-- Boto per crear una petició a partir d'un usuari-aplicació --%>
  <c:if test="${isSolicitantUsuariEntitat == false && not empty listOfUsuariAplicacio}">

  <div class="btn-group pull-right">
    <button class="btn btn-small dropdown-toggle" data-toggle="dropdown">
      <i class="icon-plus-sign"></i>
      <fmt:message key="genapp.createtitle" >
        <fmt:param value="${entityname}"/>
      </fmt:message>
      <span class="caret">
    </span></button>
    <ul class="dropdown-menu">
      <c:forEach items="${listOfUsuariAplicacio}" var="tmp">
      <li><a href="<c:url value="${contexte}/selectflux?usuariAplicacioID=${tmp}"/>">${tmp}</a></li>
      </c:forEach>
    </ul>
  </div>  
  </c:if>

  </div>
  <%@include file="../webdb/peticioDeFirmaListSubtitle.jsp" %>
  <%@include file="../webdb/peticioDeFirmaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="../webdb/peticioDeFirmaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty peticioDeFirmaItems}">
  <div class="alert alert-block">
    <fmt:message key="genapp.emptylist" >
      <fmt:param value="${entitynameplural}"/>
    </fmt:message>
  </div>
  </c:if>
  
  <c:if test="${not empty peticioDeFirmaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>
          <%@include file="../webdb/peticioDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="../webdb/peticioDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>
          
          <th><fmt:message key="genapp.actions" /></th>

          <c:if test="${peticioDeFirmaFilterForm.editButtonVisible || peticioDeFirmaFilterForm.deleteButtonVisible || not empty peticioDeFirmaFilterForm.additionalButtons}">
          <th><fmt:message key="genapp.actions" /></th>
          </c:if>
      </tr>
    </thead>
    <tbody>

      <c:forEach var="peticioDeFirma" items="${peticioDeFirmaItems}">

        <tr>
          <%@include file="../webdb/peticioDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="../webdb/peticioDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>
          <td>
          
                  
          <%!
          /*
          Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT = 0;
          Utils.TIPUSESTATPETICIODEFIRMA_ENPROCESS = 1;
          Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT = 2;
          Utils.TIPUSESTATPETICIODEFIRMA_REBUTJAT = 3;
          Utils.TIPUSESTATPETICIODEFIRMA_FIRMAT = 4;
          */
          %> 
          <c:if test="${isSolicitantUsuariEntitat}">
          <c:set var="avisweb" value="${gen:contains(peticionsIDsAmbAvis,peticioDeFirma.peticioDeFirmaID)}" />
          </c:if>
          <c:if test="${not isSolicitantUsuariEntitat}">
          <c:set var="avisweb" value="${false}"/>
          </c:if>
          <c:set var="estat" value="${peticioDeFirma.tipusEstatPeticioDeFirmaID}" />
          
          <c:if test="${avisweb == 'true'}" >
            <c:set var="botomenu" value="btn-warning" />
          </c:if>
          <c:if test="${avisweb == 'false'}" >
          <c:choose>
              <c:when test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT || estat == Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT}">
                 <c:set var="botomenu" value="" /> <!--  BLANC -->
              </c:when>
              <c:when test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_ENPROCES}">
                  <c:set var="botomenu" value="btn-primary" /> <%-- BLAU --%>
              </c:when>
              
              <c:when test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_REBUTJAT}">
                  <c:set var="botomenu" value="btn-danger" />
              </c:when>
              
              <c:when test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_FIRMAT}">
                  <c:set var="botomenu" value="btn-success" />
              </c:when>
          
              <c:otherwise>
                  <c:set var="botomenu" value="btn-inverse" />
              </c:otherwise>
          </c:choose>
          </c:if>

     
     
          <div class="btn-group">
            
             <a class="btn btn-small ${botomenu}" href="#" style="${(empty botomenu)? '' : 'color: white;'}"><i class="icon-list ${(empty botomenu)? '' : 'icon-white'}"></i> <fmt:message key="genapp.actions" /></a>
             <a class="btn btn-small ${botomenu} dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;<span class="caret"> </span></a>
 
             <ul class="dropdown-menu pull-right" style="min-width:75px;margin:0px;font-size: 12px" >
    
               <c:if test="${avisweb}">
                 <li>
                   <a class="btn btn-warning btn-small a_item" style="color:white;" href="<c:url value="${contexte}/revisat/${peticioDeFirma.peticioDeFirmaID}"/>" >
                    <i class="icon-check icon-white"></i> <fmt:message key="revisat"/>
                   </a>
                 </li>
               </c:if>
              
               <li>
                 <a class="btn btn-info btn-small a_item" style="color:white;" href="<c:url value="${contexte}/docfirmat/${peticioDeFirma.peticioDeFirmaID}"/>" 
                  target="_blank" >
                  <i class="icon-file icon-white"></i> <fmt:message key="veuredoc"/>
                 </a>
               </li>
    
              <c:if test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT}" >
                <li>
                <a class="btn btn-warning btn-small a_item" href="#" style="color:white;"
                  onclick="goTo('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/edit"/>')">
                    <i class="icon-pencil icon-white"></i>
                    <fmt:message key="peticiodefirma.editar"/>
                </a>
                </li>
                <li>
                <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="/${context_role}/fluxdefirmes/${peticioDeFirma.fluxDeFirmesID}/edit?redirectOnModify=${contexte}/list"/>')">
                  <img src="<c:url value="/img/fluxicon.png"/>"/>
                   <fmt:message key="fluxDeFirmes.editar"/>
                </a>
                </li> 
                </c:if>


            <%-- CUSTODIA --%>
            <c:if test="${potCustodiar[peticioDeFirma.peticioDeFirmaID]}" >            
                  
              <c:if test="${estat != Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT}" >
                <c:if test="${not empty peticioDeFirma.custodiaInfoID}">
                <li>
                <a class="btn btn-info btn-small a_item" href="#" style="color:white;"
                  onclick="goTo('<c:url value="/${context_role}/peticio/custodiainfo/view/${peticioDeFirma.custodiaInfoID}"/>')">                                   
                    <img src="<c:url value="/img/custodia.png"/>"/>
                    <fmt:message key="genapp.viewtitle">
                      <fmt:param><fmt:message key="custodia"/></fmt:param>
                    </fmt:message>
                </a>
                </li>
                </c:if>
              </c:if>
                
              <c:if test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT}" >
                <c:if test="${empty peticioDeFirma.custodiaInfoID}" >
                    <li>
                    <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
                      onclick="goTo('<c:url value="/${contexte}/afegircustodiainfo/${peticioDeFirma.peticioDeFirmaID}"/>')">
                      <img src="<c:url value="/img/custodia.png"/>"/>
                      <fmt:message key="genapp.createtitle">
                        <fmt:param><fmt:message key="custodia"/></fmt:param>
                      </fmt:message>
                    </a>
                    </li> 
                </c:if>

                <c:if test="${not empty peticioDeFirma.custodiaInfoID}" >
                <li>
                <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="/${context_role}/peticio/custodiainfo/${peticioDeFirma.custodiaInfoID}/edit"/>')">
                  <img src="<c:url value="/img/custodia.png"/>"/>
                    <fmt:message key="genapp.edittitle">
                      <fmt:param><fmt:message key="custodia"/></fmt:param>
                    </fmt:message>
                </a>
                </li>
                </c:if>
              </c:if>
                    
            </c:if>
            <%-- FINAL CUSTODIA --%>


              <c:if test="${estat != Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT}" >
                <li>
                <a class="btn btn-info btn-small a_item" href="#" style="color:white;"
                  onclick="goTo('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/edit"/>')">                                    
                    <i class="icon-eye-open icon-white"></i>
                    <fmt:message key="peticiodefirma.veuredetalls"/>
                </a>
                </li> 
                <li>
                <a class="btn btn-info btn-small a_item" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="/${context_role}/fluxdefirmes/view/${peticioDeFirma.fluxDeFirmesID}?redirectOnModify=${contexte}/list&readOnly=true"/>')">
                   <img src="<c:url value="/img/fluxicon.png"/>"/> <fmt:message key="fluxDeFirmes.fluxDeFirmes"/>
                </a> 
                </li>
              </c:if>

              <c:if test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT || estat == Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT}" >
                <li>
                <a class="btn btn-success btn-small a_item" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="${contexte}/iniciar/${peticioDeFirma.peticioDeFirmaID}"/>')">
                  <i class="icon-play icon-white"></i> <fmt:message key="iniciar"/>
                </a>
                </li> 
              </c:if>

              <c:if test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_ENPROCES }" >
                 <li>
                 <a class="btn btn-warning btn-small a_item" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="${contexte}/pausar/${peticioDeFirma.peticioDeFirmaID}"/>')">
                  <i class="icon-pause icon-white"></i> <fmt:message key="pausar"/>
                </a> 
                </li>
              </c:if>

              <c:if test="${estat != Utils.TIPUSESTATPETICIODEFIRMA_ENPROCES}" >         
                <li>
                <a class="btn btn-danger btn-small a_item" style="color:white;" href="#myModal"
                   onclick="openModal('<c:url value="${contexte}/${peticioDeFirma.peticioDeFirmaID}/delete"/>','show');">
                   <i class="icon-trash icon-white"></i> <fmt:message key="genapp.delete"/>
                </a>
                </li>
              </c:if>

              <li>
                <a class="btn btn-small a_item" href="#" 
                  onclick="goTo('<c:url value="${contexte}/clonar/${peticioDeFirma.peticioDeFirmaID}"/>')">
                  <i class="icon-random"></i> <fmt:message key="clonar"/>
                </a> 
              </li>

              <c:if test="${estat == Utils.TIPUSESTATPETICIODEFIRMA_FIRMAT || estat == Utils.TIPUSESTATPETICIODEFIRMA_REBUTJAT || estat == Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT}" >
              <li>
                <a class="btn btn-small a_item btn-danger" style="color:white;" href="#" 
                  onclick="goTo('<c:url value="${contexte}/reinicialitzar/${peticioDeFirma.peticioDeFirmaID}"/>')">
                  <i class=" icon-repeat icon-white"></i> <fmt:message key="reinicialitzar"/>
                </a> 
              </li>
              </c:if>

              </ul>    
          </div>
    
        </td> 

          <%@include file="../webdb/peticioDeFirmaListButtons.jsp" %>

        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty peticioDeFirmaItems}">
          <%@include file="../webdb/webdbPagination.jsp" %>
  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
          <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 

