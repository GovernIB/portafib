<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.portafib.model.fields.EntitatFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="well formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="pull-right">

           <a class="pull-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="icon-remove"></i></a>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-primary pull-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
<c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
<c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
<div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
  <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
  <fmt:message key="genapp.form.searchby" var="cercaperAF" >
    <fmt:param>
      <fmt:message key="${__entry.value.codeName}" />
    </fmt:param>
  </fmt:message>
  <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
</div>
</c:if>
</c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ACTIVA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.activa" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activaDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="activaFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.WEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.web" var="web" />
              <fmt:message key="genapp.form.searchby" var="cercaperweb" >                
                 <fmt:param value="${web}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${web}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperweb}" path="web" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ADREZAHTML)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.adrezaHtml" var="adrezaHtml" />
              <fmt:message key="genapp.form.searchby" var="cercaperadrezaHtml" >                
                 <fmt:param value="${adrezaHtml}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${adrezaHtml}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperadrezaHtml}" path="adrezaHtml" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.FILTRECERTIFICATS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.filtreCertificats" var="filtreCertificats" />
              <fmt:message key="genapp.form.searchby" var="cercaperfiltreCertificats" >                
                 <fmt:param value="${filtreCertificats}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${filtreCertificats}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfiltreCertificats}" path="filtreCertificats" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTTELEFON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportTelefon" var="suportTelefon" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportTelefon" >                
                 <fmt:param value="${suportTelefon}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportTelefon}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportTelefon}" path="suportTelefon" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportWeb" var="suportWeb" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportWeb" >                
                 <fmt:param value="${suportWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportWeb}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportWeb}" path="suportWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportEmail" var="suportEmail" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportEmail" >                
                 <fmt:param value="${suportEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportEmail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportEmail}" path="suportEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.USUARIAPLICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.usuariAplicacioID" var="usuariAplicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariAplicacioID" >                
                 <fmt:param value="${usuariAplicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariAplicacioID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariAplicacioID}" path="usuariAplicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXUPLOADSIZE)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.maxUploadSize" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxUploadSizeDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="maxUploadSizeFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXSIZEFITXERADAPTAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.maxSizeFitxerAdaptat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxSizeFitxerAdaptatDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="maxSizeFitxerAdaptatFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.maxFilesToSignAtSameTime" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxFilesToSignAtSameTimeDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="maxFilesToSignAtSameTimeFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIER)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.policyIdentifier" var="policyIdentifier" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifier" >                
                 <fmt:param value="${policyIdentifier}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifier}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifier}" path="policyIdentifier" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIERHASH)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.policyIdentifierHash" var="policyIdentifierHash" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHash" >                
                 <fmt:param value="${policyIdentifierHash}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHash}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHash}" path="policyIdentifierHash" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.policyIdentifierHashAlgorithm" var="policyIdentifierHashAlgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHashAlgorithm" >                
                 <fmt:param value="${policyIdentifierHashAlgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHashAlgorithm}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHashAlgorithm}" path="policyIdentifierHashAlgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYURLDOCUMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.policyUrlDocument" var="policyUrlDocument" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyUrlDocument" >                
                 <fmt:param value="${policyUrlDocument}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyUrlDocument}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyUrlDocument}" path="policyUrlDocument" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MOTIUDELEGACIOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.motiuDelegacioID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="motiuDelegacioIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="motiuDelegacioIDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.FIRMATPERFORMATID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.firmatPerFormatID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmatPerFormatIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="firmatPerFormatIDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ALGORISMEDEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.algorismeDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="algorismeDeFirmaIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="algorismeDeFirmaIDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.COMPROVARCERTIFICATCLIENTCERT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.comprovarCertificatClientCert" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="comprovarCertificatClientCertDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="comprovarCertificatClientCertFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.COMPROVARNIFFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.comprovarNifFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="comprovarNifFirmaDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="comprovarNifFirmaFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CUSTODIAINFOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.custodiaInfoID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />
              
            </div>


        </c:if>

<c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
<c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
<div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
  <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
  <fmt:message key="genapp.form.searchby" var="cercaperAF" >
    <fmt:param>
      <fmt:message key="${__entry.value.codeName}" />
    </fmt:param>
  </fmt:message>
  <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
</div>
</c:if>
</c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
