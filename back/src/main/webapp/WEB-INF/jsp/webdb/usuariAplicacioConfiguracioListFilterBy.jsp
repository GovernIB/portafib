<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usuariAplicacioConfigID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usuariAplicacioConfigIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="usuariAplicacioConfigIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.usuariAplicacioID" var="usuariAplicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariAplicacioID" >                
                 <fmt:param value="${usuariAplicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariAplicacioID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariAplicacioID}" path="usuariAplicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usPoliticaDeTirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usPoliticaDeTirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="usPoliticaDeTirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifier" var="policyIdentifier" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifier" >                
                 <fmt:param value="${policyIdentifier}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifier}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifier}" path="policyIdentifier" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifierHash" var="policyIdentifierHash" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHash" >                
                 <fmt:param value="${policyIdentifierHash}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHash}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHash}" path="policyIdentifierHash" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" var="policyIdentifierHashAlgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHashAlgorithm" >                
                 <fmt:param value="${policyIdentifierHashAlgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHashAlgorithm}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHashAlgorithm}" path="policyIdentifierHashAlgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.policyUrlDocument" var="policyUrlDocument" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyUrlDocument" >                
                 <fmt:param value="${policyUrlDocument}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyUrlDocument}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyUrlDocument}" path="policyUrlDocument" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.filtreCertificats" var="filtreCertificats" />
              <fmt:message key="genapp.form.searchby" var="cercaperfiltreCertificats" >                
                 <fmt:param value="${filtreCertificats}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${filtreCertificats}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfiltreCertificats}" path="filtreCertificats" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.tipusOperacioFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusOperacioFirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusOperacioFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.tipusFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.algorismeDeFirmaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="algorismeDeFirmaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="algorismeDeFirmaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.modeDeFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="modeDeFirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="modeDeFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.motiuDelegacioID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="motiuDelegacioIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="motiuDelegacioIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.firmatPerFormatID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmatPerFormatIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="firmatPerFormatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.custodiaInfoID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.posicioTaulaFirmesID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="posicioTaulaFirmesIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="posicioTaulaFirmesIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.pluginSegellatID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginSegellatIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="pluginSegellatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.pluginFirmaServidorID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginFirmaServidorIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="pluginFirmaServidorIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb" var="htmlPerLlistarPluginsFirmaWeb" />
              <fmt:message key="genapp.form.searchby" var="cercaperhtmlPerLlistarPluginsFirmaWeb" >                
                 <fmt:param value="${htmlPerLlistarPluginsFirmaWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${htmlPerLlistarPluginsFirmaWeb}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperhtmlPerLlistarPluginsFirmaWeb}" path="htmlPerLlistarPluginsFirmaWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.comprovarNifFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="comprovarNifFirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="comprovarNifFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.checkCanviatDocFirmat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="checkCanviatDocFirmatDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="checkCanviatDocFirmatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.validarFirma" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="validarFirmaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="validarFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.validarCertificat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="validarCertificatDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="validarCertificatFins" />

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
  
