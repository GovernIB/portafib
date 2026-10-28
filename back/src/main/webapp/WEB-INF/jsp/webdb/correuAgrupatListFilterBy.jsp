<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CorreuAgrupatFields" className="es.caib.portafib.model.fields.CorreuAgrupatFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.CORREUAGRUPATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="correuAgrupat_correuAgrupatID" style="display: inline;">
              <span class="add-on"><fmt:message key="correuAgrupat.correuAgrupatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="correuAgrupatIDDesde"  aria-labelledby="correuAgrupat_correuAgrupatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="correuAgrupatIDFins"  aria-labelledby="correuAgrupat_correuAgrupatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.EMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="email" style="display: inline;">
              <fmt:message key="correuAgrupat.email" var="email" />
              <fmt:message key="genapp.form.searchby" var="cercaperemail" >                
                 <fmt:param value="${email}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${email}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperemail}" path="email" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.SUBJECT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="subject" style="display: inline;">
              <fmt:message key="correuAgrupat.subject" var="subject" />
              <fmt:message key="genapp.form.searchby" var="cercapersubject" >                
                 <fmt:param value="${subject}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${subject}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersubject}" path="subject" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.MESSAGE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="message" style="display: inline;">
              <fmt:message key="correuAgrupat.message" var="message" />
              <fmt:message key="genapp.form.searchby" var="cercapermessage" >                
                 <fmt:param value="${message}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${message}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermessage}" path="message" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.HTML)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="correuAgrupat_html" style="display: inline;">
              <span class="add-on"><fmt:message key="correuAgrupat.html" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="htmlDesde"  aria-labelledby="correuAgrupat_html" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="htmlFins"  aria-labelledby="correuAgrupat_html" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.USUARIENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="usuariEntitatID" style="display: inline;">
              <fmt:message key="correuAgrupat.usuariEntitatID" var="usuariEntitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperusuariEntitatID" >                
                 <fmt:param value="${usuariEntitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${usuariEntitatID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperusuariEntitatID}" path="usuariEntitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="correuAgrupat_dataCreacio_label" style="display: inline;">
              <span class="add-on"><fmt:message key="correuAgrupat.dataCreacio" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesdeDiv" path="dataCreacioDesde"  aria-labelledby="correuAgrupat_dataCreacio_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioDesdeDiv').datetimepicker({
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
                <div class="input-group date" id="dataCreacioFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFinsDiv" path="dataCreacioFins"  aria-labelledby="correuAgrupat_dataCreacio_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioFinsDiv').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CorreuAgrupatFields.ERROR)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="error" style="display: inline;">
              <fmt:message key="correuAgrupat.error" var="error" />
              <fmt:message key="genapp.form.searchby" var="cercapererror" >                
                 <fmt:param value="${error}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${error}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapererror}" path="error" />
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
  
