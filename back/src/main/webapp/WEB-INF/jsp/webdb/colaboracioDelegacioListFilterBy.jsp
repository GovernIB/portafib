<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="colaboracioDelegacio_colaboracioDelegacioID" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.colaboracioDelegacioID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="colaboracioDelegacioIDDesde"  aria-labelledby="colaboracioDelegacio_colaboracioDelegacioID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="colaboracioDelegacioIDFins"  aria-labelledby="colaboracioDelegacio_colaboracioDelegacioID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DESTINATARIID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="destinatariID" style="display: inline;">
              <fmt:message key="colaboracioDelegacio.destinatariID" var="destinatariID" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatariID" >                
                 <fmt:param value="${destinatariID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatariID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatariID}" path="destinatariID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="colaboradorDelegatID" style="display: inline;">
              <fmt:message key="colaboracioDelegacio.colaboradorDelegatID" var="colaboradorDelegatID" />
              <fmt:message key="genapp.form.searchby" var="cercapercolaboradorDelegatID" >                
                 <fmt:param value="${colaboradorDelegatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${colaboradorDelegatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercolaboradorDelegatID}" path="colaboradorDelegatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.ESDELEGAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="colaboracioDelegacio_esDelegat" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.esDelegat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="esDelegatDesde"  aria-labelledby="colaboracioDelegacio_esDelegat" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="esDelegatFins"  aria-labelledby="colaboracioDelegacio_esDelegat" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.MOTIU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="motiu" style="display: inline;">
              <fmt:message key="colaboracioDelegacio.motiu" var="motiu" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiu" >                
                 <fmt:param value="${motiu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiu}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiu}" path="motiu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="descripcio" style="display: inline;">
              <fmt:message key="colaboracioDelegacio.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DATAINICI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="colaboracioDelegacio_dataInici_label" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.dataInici" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataIniciDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataIniciDesdeDiv" path="dataIniciDesde"  aria-labelledby="colaboracioDelegacio_dataInici_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataIniciDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataIniciDesdeDiv').datetimepicker({
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
                <div class="input-group date" id="dataIniciFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataIniciFinsDiv" path="dataIniciFins"  aria-labelledby="colaboracioDelegacio_dataInici_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataIniciFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataIniciFinsDiv').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DATAFI)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="colaboracioDelegacio_dataFi_label" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.dataFi" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataFiDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFiDesdeDiv" path="dataFiDesde"  aria-labelledby="colaboracioDelegacio_dataFi_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFiDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataFiDesdeDiv').datetimepicker({
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
                <div class="input-group date" id="dataFiFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFiFinsDiv" path="dataFiFins"  aria-labelledby="colaboracioDelegacio_dataFi_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFiFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataFiFinsDiv').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.ACTIVA)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="colaboracioDelegacio_activa" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.activa" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activaDesde"  aria-labelledby="colaboracioDelegacio_activa" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="activaFins"  aria-labelledby="colaboracioDelegacio_activa" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.REVISOR)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="colaboracioDelegacio_revisor" style="display: inline;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.revisor" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="revisorDesde"  aria-labelledby="colaboracioDelegacio_revisor" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="revisorFins"  aria-labelledby="colaboracioDelegacio_revisor" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="motiuDeshabilitada" style="display: inline;">
              <fmt:message key="colaboracioDelegacio.motiuDeshabilitada" var="motiuDeshabilitada" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiuDeshabilitada" >                
                 <fmt:param value="${motiuDeshabilitada}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiuDeshabilitada}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiuDeshabilitada}" path="motiuDeshabilitada" />
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
  
