<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usuariAplicacioConfigID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usuariAplicacioConfigID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usuariAplicacioConfigIDDesde"  aria-labelledby="usuariAplicacioConfiguracio_usuariAplicacioConfigID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usuariAplicacioConfigIDFins"  aria-labelledby="usuariAplicacioConfiguracio_usuariAplicacioConfigID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="nom" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="entitatID" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaApiSimpleServidorDesde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaApiSimpleServidorFins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaApiSimpleServidor" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaApiSimpleWebDesde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaApiSimpleWebFins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaApiSimpleWeb" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaWeb" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaWeb" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaWebDesde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaWeb" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaWebFins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaWeb" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaWS1" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaWS1" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaWS1Desde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaWS1" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaWS1Fins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaWS1" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaAsyncRest2" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaAsyncRest2Desde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaAsyncRest2Fins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaAsyncRest2" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaPassarelaServidorDesde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaPassarelaServidorFins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaPassarelaServidor" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usEnFirmaPassarelaWebDesde"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usEnFirmaPassarelaWebFins"  aria-labelledby="usuariAplicacioConfiguracio_usEnFirmaPassarelaWeb" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="filtreCertificats" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.filtreCertificats" var="filtreCertificats" />
              <fmt:message key="genapp.form.searchby" var="cercaperfiltreCertificats" >                
                 <fmt:param value="${filtreCertificats}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${filtreCertificats}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfiltreCertificats}" path="filtreCertificats" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_tipusOperacioFirma_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.tipusOperacioFirma" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_tipusOperacioFirma_select" path="tipusOperacioFirmaSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipusOperacioFirma}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusOperacioFirmaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_tipusOperacioFirma_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_tipusOperacioFirma_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_tipusOperacioFirma_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_tipusFirmaID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.tipusFirmaID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_tipusFirmaID_select" path="tipusFirmaIDSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipusFirmaID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusFirmaIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_tipusFirmaID_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_tipusFirmaID_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_tipusFirmaID_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_algorismeDeFirmaID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.algorismeDeFirmaID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_algorismeDeFirmaID_select" path="algorismeDeFirmaIDSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForAlgorismeDeFirmaID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.algorismeDeFirmaIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_algorismeDeFirmaID_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_algorismeDeFirmaID_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_algorismeDeFirmaID_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_modeDeFirma_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.modeDeFirma" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_modeDeFirma_select" path="modeDeFirmaSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForModeDeFirma}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.modeDeFirmaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_modeDeFirma_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_modeDeFirma_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_modeDeFirma_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_usPoliticaDeFirma_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.usPoliticaDeFirma" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_usPoliticaDeFirma_select" path="usPoliticaDeFirmaSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForUsPoliticaDeFirma}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.usPoliticaDeFirmaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_usPoliticaDeFirma_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_usPoliticaDeFirma_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_usPoliticaDeFirma_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifier" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifier" var="policyIdentifier" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifier" >                
                 <fmt:param value="${policyIdentifier}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifier}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifier}" path="policyIdentifier" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifierHash" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifierHash" var="policyIdentifierHash" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHash" >                
                 <fmt:param value="${policyIdentifierHash}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHash}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHash}" path="policyIdentifierHash" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyIdentifierHashAlgorithm" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" var="policyIdentifierHashAlgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyIdentifierHashAlgorithm" >                
                 <fmt:param value="${policyIdentifierHashAlgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyIdentifierHashAlgorithm}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyIdentifierHashAlgorithm}" path="policyIdentifierHashAlgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="policyUrlDocument" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.policyUrlDocument" var="policyUrlDocument" />
              <fmt:message key="genapp.form.searchby" var="cercaperpolicyUrlDocument" >                
                 <fmt:param value="${policyUrlDocument}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${policyUrlDocument}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpolicyUrlDocument}" path="policyUrlDocument" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_motiuDelegacioID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.motiuDelegacioID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="motiuDelegacioIDDesde"  aria-labelledby="usuariAplicacioConfiguracio_motiuDelegacioID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="motiuDelegacioIDFins"  aria-labelledby="usuariAplicacioConfiguracio_motiuDelegacioID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_politicaTaulaFirmes_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.politicaTaulaFirmes" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_politicaTaulaFirmes_select" path="politicaTaulaFirmesSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaTaulaFirmes}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaTaulaFirmesSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_politicaTaulaFirmes_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_politicaTaulaFirmes_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_politicaTaulaFirmes_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_posicioTaulaFirmesID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.posicioTaulaFirmesID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_posicioTaulaFirmesID_select" path="posicioTaulaFirmesIDSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPosicioTaulaFirmesID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.posicioTaulaFirmesIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_posicioTaulaFirmesID_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_posicioTaulaFirmesID_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_posicioTaulaFirmesID_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_firmatPerFormatID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.firmatPerFormatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="firmatPerFormatIDDesde"  aria-labelledby="usuariAplicacioConfiguracio_firmatPerFormatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="firmatPerFormatIDFins"  aria-labelledby="usuariAplicacioConfiguracio_firmatPerFormatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="propietatsTaulaFirmes" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.propietatsTaulaFirmes" var="propietatsTaulaFirmes" />
              <fmt:message key="genapp.form.searchby" var="cercaperpropietatsTaulaFirmes" >                
                 <fmt:param value="${propietatsTaulaFirmes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${propietatsTaulaFirmes}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpropietatsTaulaFirmes}" path="propietatsTaulaFirmes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_politicaSegellatDeTemps_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.politicaSegellatDeTemps" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_politicaSegellatDeTemps_select" path="politicaSegellatDeTempsSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaSegellatDeTempsSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_politicaSegellatDeTemps_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_politicaSegellatDeTemps_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_politicaSegellatDeTemps_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_pluginSegellatID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.pluginSegellatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginSegellatIDDesde"  aria-labelledby="usuariAplicacioConfiguracio_pluginSegellatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginSegellatIDFins"  aria-labelledby="usuariAplicacioConfiguracio_pluginSegellatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="htmlPerLlistarPluginsFirmaWeb" style="display: inline;">
              <fmt:message key="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb" var="htmlPerLlistarPluginsFirmaWeb" />
              <fmt:message key="genapp.form.searchby" var="cercaperhtmlPerLlistarPluginsFirmaWeb" >                
                 <fmt:param value="${htmlPerLlistarPluginsFirmaWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${htmlPerLlistarPluginsFirmaWeb}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperhtmlPerLlistarPluginsFirmaWeb}" path="htmlPerLlistarPluginsFirmaWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_pluginFirmaServidorID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.pluginFirmaServidorID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginFirmaServidorIDDesde"  aria-labelledby="usuariAplicacioConfiguracio_pluginFirmaServidorID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginFirmaServidorIDFins"  aria-labelledby="usuariAplicacioConfiguracio_pluginFirmaServidorID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="confapp_upgradeSignFormat_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.upgradeSignFormat" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="confapp_upgradeSignFormat_select" path="upgradeSignFormatSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForUpgradeSignFormat}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.upgradeSignFormatSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#confapp_upgradeSignFormat_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "confapp_upgradeSignFormat_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "confapp_upgradeSignFormat_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="usuariAplicacioConfiguracio_esDePeticio" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariAplicacioConfiguracio.esDePeticio" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="esDePeticioDesde"  aria-labelledby="usuariAplicacioConfiguracio_esDePeticio" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="esDePeticioFins"  aria-labelledby="usuariAplicacioConfiguracio_esDePeticio" />

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
  
