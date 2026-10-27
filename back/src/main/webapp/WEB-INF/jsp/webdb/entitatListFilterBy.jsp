<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.portafib.model.fields.EntitatFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#" title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline'; return false;"> <i aria-hidden="true" class="far fa-window-close"></i><span class="sr-only"><fmt:message key="genapp.form.hidefilter"/></span></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="entitatID" style="display: inline;">
              <fmt:message key="entitat.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="nom" style="display: inline;">
              <fmt:message key="entitat.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="descripcio" style="display: inline;">
              <fmt:message key="entitat.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ACTIVA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_activa" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.activa" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activaDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="activaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.WEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="web" style="display: inline;">
              <fmt:message key="entitat.web" var="web" />
              <fmt:message key="genapp.form.searchby" var="cercaperweb" >                
                 <fmt:param value="${web}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${web}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperweb}" path="web" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ADREZAHTML)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="adrezaHtml" style="display: inline;">
              <fmt:message key="entitat.adrezaHtml" var="adrezaHtml" />
              <fmt:message key="genapp.form.searchby" var="cercaperadrezaHtml" >                
                 <fmt:param value="${adrezaHtml}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${adrezaHtml}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperadrezaHtml}" path="adrezaHtml" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.FILTRECERTIFICATS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="filtreCertificats" style="display: inline;">
              <fmt:message key="entitat.filtreCertificats" var="filtreCertificats" />
              <fmt:message key="genapp.form.searchby" var="cercaperfiltreCertificats" >                
                 <fmt:param value="${filtreCertificats}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${filtreCertificats}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfiltreCertificats}" path="filtreCertificats" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTTELEFON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="suportTelefon" style="display: inline;">
              <fmt:message key="entitat.suportTelefon" var="suportTelefon" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportTelefon" >                
                 <fmt:param value="${suportTelefon}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportTelefon}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportTelefon}" path="suportTelefon" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="suportWeb" style="display: inline;">
              <fmt:message key="entitat.suportWeb" var="suportWeb" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportWeb" >                
                 <fmt:param value="${suportWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportWeb}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportWeb}" path="suportWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="suportEmail" style="display: inline;">
              <fmt:message key="entitat.suportEmail" var="suportEmail" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportEmail" >                
                 <fmt:param value="${suportEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportEmail}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportEmail}" path="suportEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.USUARIAPLICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariAplicacioID" style="display: inline;">
              <fmt:message key="entitat.usuariAplicacioID" var="usuariAplicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariAplicacioID" >                
                 <fmt:param value="${usuariAplicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariAplicacioID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariAplicacioID}" path="usuariAplicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXUPLOADSIZE)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_maxUploadSize" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.maxUploadSize" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxUploadSizeDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="maxUploadSizeFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXSIZEFITXERADAPTAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_maxSizeFitxerAdaptat" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.maxSizeFitxerAdaptat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxSizeFitxerAdaptatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="maxSizeFitxerAdaptatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_maxFilesToSignAtSameTime" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.maxFilesToSignAtSameTime" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="maxFilesToSignAtSameTimeDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="maxFilesToSignAtSameTimeFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.USPOLITICADEFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_usPoliticaDeFirma_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.usPoliticaDeFirma" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_usPoliticaDeFirma_select"   id="entitat_usPoliticaDeFirma_select" path="usPoliticaDeFirmaSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForUsPoliticaDeFirma}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.usPoliticaDeFirmaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_usPoliticaDeFirma_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIER)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifier" style="display: inline;">
              <fmt:message key="entitat.policyIdentifier" var="policyIdentifier" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifier" >                
                 <fmt:param value="${policyIdentifier}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifier}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifier}" path="policyIdentifier" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIERHASH)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifierHash" style="display: inline;">
              <fmt:message key="entitat.policyIdentifierHash" var="policyIdentifierHash" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHash" >                
                 <fmt:param value="${policyIdentifierHash}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHash}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHash}" path="policyIdentifierHash" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifierHashAlgorithm" style="display: inline;">
              <fmt:message key="entitat.policyIdentifierHashAlgorithm" var="policyIdentifierHashAlgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHashAlgorithm" >                
                 <fmt:param value="${policyIdentifierHashAlgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHashAlgorithm}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHashAlgorithm}" path="policyIdentifierHashAlgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLICYURLDOCUMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyUrlDocument" style="display: inline;">
              <fmt:message key="entitat.policyUrlDocument" var="policyUrlDocument" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyUrlDocument" >                
                 <fmt:param value="${policyUrlDocument}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyUrlDocument}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyUrlDocument}" path="policyUrlDocument" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.MOTIUDELEGACIOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_motiuDelegacioID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.motiuDelegacioID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="motiuDelegacioIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="motiuDelegacioIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.FIRMATPERFORMATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_firmatPerFormatID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.firmatPerFormatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmatPerFormatIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="firmatPerFormatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ALGORISMEDEFIRMAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_algorismeDeFirmaID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.algorismeDeFirmaID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_algorismeDeFirmaID_select"   id="entitat_algorismeDeFirmaID_select" path="algorismeDeFirmaIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForAlgorismeDeFirmaID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.algorismeDeFirmaIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_algorismeDeFirmaID_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLITICACUSTODIA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_politicaCustodia_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.politicaCustodia" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_politicaCustodia_select"   id="entitat_politicaCustodia_select" path="politicaCustodiaSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaCustodia}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaCustodiaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_politicaCustodia_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CUSTODIAINFOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_custodiaInfoID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.custodiaInfoID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLITICATAULAFIRMES)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_politicaTaulaFirmes_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.politicaTaulaFirmes" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_politicaTaulaFirmes_select"   id="entitat_politicaTaulaFirmes_select" path="politicaTaulaFirmesSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaTaulaFirmes}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaTaulaFirmesSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_politicaTaulaFirmes_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POSICIOTAULAFIRMES)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_posicioTaulaFirmes_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.posicioTaulaFirmes" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_posicioTaulaFirmes_select"   id="entitat_posicioTaulaFirmes_select" path="posicioTaulaFirmesSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPosicioTaulaFirmes}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.posicioTaulaFirmesSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_posicioTaulaFirmes_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PROPIETATSTAULAFIRMES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="propietatsTaulaFirmes" style="display: inline;">
              <fmt:message key="entitat.propietatsTaulaFirmes" var="propietatsTaulaFirmes" />
              <fmt:message key="genapp.form.searchby" var="cercaperpropietatsTaulaFirmes" >                
                 <fmt:param value="${propietatsTaulaFirmes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${propietatsTaulaFirmes}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpropietatsTaulaFirmes}" path="propietatsTaulaFirmes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.POLITICASEGELLATDETEMPS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="entitat_politicaSegellatDeTemps_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="entitat.politicaSegellatDeTemps" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="entitat_politicaSegellatDeTemps_select"   id="entitat_politicaSegellatDeTemps_select" path="politicaSegellatDeTempsSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaSegellatDeTempsSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#entitat_politicaSegellatDeTemps_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PLUGINSEGELLTEMPSID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_pluginSegellTempsID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.pluginSegellTempsID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginSegellTempsIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginSegellTempsIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PLUGINRUBRICAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_pluginRubricaID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.pluginRubricaID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginRubricaIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginRubricaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.VALIDARFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_validarfirma" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.validarfirma" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="validarfirmaDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="validarfirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.COMPROVARNIFFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_comprovarNifFirma" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.comprovarNifFirma" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="comprovarNifFirmaDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="comprovarNifFirmaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_checkCanviatDocFirmat" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.checkCanviatDocFirmat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="checkCanviatDocFirmatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="checkCanviatDocFirmatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PLUGINVALIDAFIRMESID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_pluginValidaFirmesID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.pluginValidaFirmesID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginValidaFirmesIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginValidaFirmesIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PLUGINVALIDACERTIFICATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="entitat_pluginValidaCertificatID" style="display: inline;">
              <span class="add-on"><fmt:message key="entitat.pluginValidaCertificatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginValidaCertificatIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginValidaCertificatIDFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
