<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.USUARIENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariEntitatID" style="display: inline;">
              <fmt:message key="usuariEntitat.usuariEntitatID" var="usuariEntitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariEntitatID" >                
                 <fmt:param value="${usuariEntitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariEntitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariEntitatID}" path="usuariEntitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.CARREC)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="carrec" style="display: inline;">
              <fmt:message key="usuariEntitat.carrec" var="carrec" />
              <fmt:message key="genapp.form.searchby" var="cercapercarrec" >                
                 <fmt:param value="${carrec}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${carrec}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercarrec}" path="carrec" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.USUARIPERSONAID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariPersonaID" style="display: inline;">
              <fmt:message key="usuariEntitat.usuariPersonaID" var="usuariPersonaID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariPersonaID" >                
                 <fmt:param value="${usuariPersonaID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariPersonaID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariPersonaID}" path="usuariPersonaID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="entitatID" style="display: inline;">
              <fmt:message key="usuariEntitat.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.ACTIU)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="usuariEntitat_actiu" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariEntitat.actiu" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="actiuDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="actiuFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.EMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="email" style="display: inline;">
              <fmt:message key="usuariEntitat.email" var="email" />
              <fmt:message key="genapp.form.searchby" var="cercaperemail" >                
                 <fmt:param value="${email}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${email}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperemail}" path="email" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.PREDETERMINAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="usuariEntitat_predeterminat" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariEntitat.predeterminat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="predeterminatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="predeterminatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="usuariEntitat_rebreTotsElsAvisos" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariEntitat.rebreTotsElsAvisos" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="rebreTotsElsAvisosDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="rebreTotsElsAvisosFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="usrentitat_politicaDePluginFirmaWeb_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariEntitat.politicaDePluginFirmaWeb" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="usrentitat_politicaDePluginFirmaWeb_select"   id="usrentitat_politicaDePluginFirmaWeb_select" path="politicaDePluginFirmaWebSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaDePluginFirmaWeb}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaDePluginFirmaWebSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#usrentitat_politicaDePluginFirmaWeb_select');
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.POLITICACUSTODIA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="usrentitat_politicaCustodia_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="usuariEntitat.politicaCustodia" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="usrentitat_politicaCustodia_select"   id="usrentitat_politicaCustodia_select" path="politicaCustodiaSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPoliticaCustodia}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.politicaCustodiaSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#usrentitat_politicaCustodia_select');
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UsuariEntitatFields.CUSTODIAINFOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="usuariEntitat_custodiaInfoID" style="display: inline;">
              <span class="add-on"><fmt:message key="usuariEntitat.custodiaInfoID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="custodiaInfoIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="custodiaInfoIDFins" />

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
  
