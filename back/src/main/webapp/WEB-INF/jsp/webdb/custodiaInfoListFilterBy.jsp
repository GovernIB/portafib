<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CUSTODIAINFOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="custodiaInfo_custodiaInfoID" style="display: inline;">
              <span class="add-on"><fmt:message key="custodiaInfo.custodiaInfoID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.NOMPLANTILLA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="nomPlantilla" style="display: inline;">
              <fmt:message key="custodiaInfo.nomPlantilla" var="nomPlantilla" />
              <fmt:message key="genapp.form.searchby" var="cercapernomPlantilla" >                
                 <fmt:param value="${nomPlantilla}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nomPlantilla}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernomPlantilla}" path="nomPlantilla" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="custodiaDocumentID" style="display: inline;">
              <fmt:message key="custodiaInfo.custodiaDocumentID" var="custodiaDocumentID" />
              <fmt:message key="genapp.form.searchby" var="cercapercustodiaDocumentID" >                
                 <fmt:param value="${custodiaDocumentID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${custodiaDocumentID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercustodiaDocumentID}" path="custodiaDocumentID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.PLUGINID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="custodiaInfo_pluginID" style="display: inline;">
              <span class="add-on"><fmt:message key="custodiaInfo.pluginID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="pluginIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="custodiaPluginParameters" style="display: inline;">
              <fmt:message key="custodiaInfo.custodiaPluginParameters" var="custodiaPluginParameters" />
              <fmt:message key="genapp.form.searchby" var="cercapercustodiaPluginParameters" >                
                 <fmt:param value="${custodiaPluginParameters}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${custodiaPluginParameters}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercustodiaPluginParameters}" path="custodiaPluginParameters" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CUSTODIAR)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="custodiaInfo_custodiar" style="display: inline;">
              <span class="add-on"><fmt:message key="custodiaInfo.custodiar" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiarDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="custodiarFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.PAGINES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="pagines" style="display: inline;">
              <fmt:message key="custodiaInfo.pagines" var="pagines" />
              <fmt:message key="genapp.form.searchby" var="cercaperpagines" >                
                 <fmt:param value="${pagines}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${pagines}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpagines}" path="pagines" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.MISSATGE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="missatge" style="display: inline;">
              <fmt:message key="custodiaInfo.missatge" var="missatge" />
              <fmt:message key="genapp.form.searchby" var="cercapermissatge" >                
                 <fmt:param value="${missatge}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${missatge}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermissatge}" path="missatge" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="custodia_missatgePosicioPaginaID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="custodiaInfo.missatgePosicioPaginaID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="custodia_missatgePosicioPaginaID_select"   id="custodia_missatgePosicioPaginaID_select" path="missatgePosicioPaginaIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForMissatgePosicioPaginaID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.missatgePosicioPaginaIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#custodia_missatgePosicioPaginaID_select');
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CODIBARRESID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codiBarresID" style="display: inline;">
              <fmt:message key="custodiaInfo.codiBarresID" var="codiBarresID" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiBarresID" >                
                 <fmt:param value="${codiBarresID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiBarresID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiBarresID}" path="codiBarresID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="custodia_codiBarresPosicioPaginaID_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="custodiaInfo.codiBarresPosicioPaginaID" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="custodia_codiBarresPosicioPaginaID_select"   id="custodia_codiBarresPosicioPaginaID_select" path="codiBarresPosicioPaginaIDSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForCodiBarresPosicioPaginaID}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.codiBarresPosicioPaginaIDSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#custodia_codiBarresPosicioPaginaID_select');
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CODIBARRESTEXT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codiBarresText" style="display: inline;">
              <fmt:message key="custodiaInfo.codiBarresText" var="codiBarresText" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiBarresText" >                
                 <fmt:param value="${codiBarresText}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiBarresText}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiBarresText}" path="codiBarresText" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.USUARIENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariEntitatID" style="display: inline;">
              <fmt:message key="custodiaInfo.usuariEntitatID" var="usuariEntitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariEntitatID" >                
                 <fmt:param value="${usuariEntitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariEntitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariEntitatID}" path="usuariEntitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.USUARIAPLICACIOID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariAplicacioID" style="display: inline;">
              <fmt:message key="custodiaInfo.usuariAplicacioID" var="usuariAplicacioID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariAplicacioID" >                
                 <fmt:param value="${usuariAplicacioID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariAplicacioID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariAplicacioID}" path="usuariAplicacioID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="entitatID" style="display: inline;">
              <fmt:message key="custodiaInfo.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.TITOLPETICIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="titolPeticio" style="display: inline;">
              <fmt:message key="custodiaInfo.titolPeticio" var="titolPeticio" />
              <fmt:message key="genapp.form.searchby" var="cercapertitolPeticio" >                
                 <fmt:param value="${titolPeticio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${titolPeticio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertitolPeticio}" path="titolPeticio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.DATACUSTODIA)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="custodiaInfo_dataCustodia_label" style="display: inline;">
              <span class="add-on"><fmt:message key="custodiaInfo.dataCustodia" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCustodiaDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCustodiaDesdeDiv" path="dataCustodiaDesde"  aria-labelledby="custodiaInfo_dataCustodia_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCustodiaDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCustodiaDesdeDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCustodiaFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCustodiaFinsDiv" path="dataCustodiaFins"  aria-labelledby="custodiaInfo_dataCustodia_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCustodiaFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCustodiaFinsDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.EDITABLE)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="custodiaInfo_editable" style="display: inline;">
              <span class="add-on"><fmt:message key="custodiaInfo.editable" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="editableDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="editableFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CSV)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="csv" style="display: inline;">
              <fmt:message key="custodiaInfo.csv" var="csv" />
              <fmt:message key="genapp.form.searchby" var="cercapercsv" >                
                 <fmt:param value="${csv}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csv}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsv}" path="csv" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CSVVALIDATIONWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="csvValidationWeb" style="display: inline;">
              <fmt:message key="custodiaInfo.csvValidationWeb" var="csvValidationWeb" />
              <fmt:message key="genapp.form.searchby" var="cercapercsvValidationWeb" >                
                 <fmt:param value="${csvValidationWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csvValidationWeb}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsvValidationWeb}" path="csvValidationWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.CSVGENERATIONDEFINITION)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="csvGenerationDefinition" style="display: inline;">
              <fmt:message key="custodiaInfo.csvGenerationDefinition" var="csvGenerationDefinition" />
              <fmt:message key="genapp.form.searchby" var="cercapercsvGenerationDefinition" >                
                 <fmt:param value="${csvGenerationDefinition}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csvGenerationDefinition}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsvGenerationDefinition}" path="csvGenerationDefinition" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="urlFitxerCustodiat" style="display: inline;">
              <fmt:message key="custodiaInfo.urlFitxerCustodiat" var="urlFitxerCustodiat" />
              <fmt:message key="genapp.form.searchby" var="cercaperurlFitxerCustodiat" >                
                 <fmt:param value="${urlFitxerCustodiat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${urlFitxerCustodiat}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperurlFitxerCustodiat}" path="urlFitxerCustodiat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.ORIGINALFILEDIRECTURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="originalFileDirectUrl" style="display: inline;">
              <fmt:message key="custodiaInfo.originalFileDirectUrl" var="originalFileDirectUrl" />
              <fmt:message key="genapp.form.searchby" var="cercaperoriginalFileDirectUrl" >                
                 <fmt:param value="${originalFileDirectUrl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${originalFileDirectUrl}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperoriginalFileDirectUrl}" path="originalFileDirectUrl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="printableFileDirectUrl" style="display: inline;">
              <fmt:message key="custodiaInfo.printableFileDirectUrl" var="printableFileDirectUrl" />
              <fmt:message key="genapp.form.searchby" var="cercaperprintableFileDirectUrl" >                
                 <fmt:param value="${printableFileDirectUrl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${printableFileDirectUrl}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprintableFileDirectUrl}" path="printableFileDirectUrl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.ENIFILEDIRECTURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="eniFileDirectUrl" style="display: inline;">
              <fmt:message key="custodiaInfo.eniFileDirectUrl" var="eniFileDirectUrl" />
              <fmt:message key="genapp.form.searchby" var="cercapereniFileDirectUrl" >                
                 <fmt:param value="${eniFileDirectUrl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniFileDirectUrl}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniFileDirectUrl}" path="eniFileDirectUrl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.EXPEDIENTARXIUID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="expedientArxiuId" style="display: inline;">
              <fmt:message key="custodiaInfo.expedientArxiuId" var="expedientArxiuId" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpedientArxiuId" >                
                 <fmt:param value="${expedientArxiuId}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expedientArxiuId}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpedientArxiuId}" path="expedientArxiuId" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CustodiaInfoFields.DOCUMENTARXIUID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="documentArxiuId" style="display: inline;">
              <fmt:message key="custodiaInfo.documentArxiuId" var="documentArxiuId" />
              <fmt:message key="genapp.form.searchby" var="cercaperdocumentArxiuId" >                
                 <fmt:param value="${documentArxiuId}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${documentArxiuId}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdocumentArxiuId}" path="documentArxiuId" />
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
  
