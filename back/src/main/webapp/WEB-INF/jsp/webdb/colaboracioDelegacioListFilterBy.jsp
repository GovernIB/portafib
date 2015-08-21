<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.colaboracioDelegacioID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="colaboracioDelegacioIDDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="colaboracioDelegacioIDFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DESTINATARIID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="colaboracioDelegacio.destinatariID" var="destinatariID" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatariID" >                
                 <fmt:param value="${destinatariID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatariID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatariID}" path="destinatariID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="colaboracioDelegacio.colaboradorDelegatID" var="colaboradorDelegatID" />
              <fmt:message key="genapp.form.searchby" var="cercapercolaboradorDelegatID" >                
                 <fmt:param value="${colaboradorDelegatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${colaboradorDelegatID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercolaboradorDelegatID}" path="colaboradorDelegatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.ESDELEGAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.esDelegat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="esDelegatDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="esDelegatFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.MOTIU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="colaboracioDelegacio.motiu" var="motiu" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiu" >                
                 <fmt:param value="${motiu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiu}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiu}" path="motiu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="colaboracioDelegacio.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DATAINICI)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.dataInici" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataIniciDesde" class="input-append">
                <form:input cssClass="input-large" path="dataIniciDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataIniciDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataIniciFins" class="input-append">
                <form:input cssClass="input-large" path="dataIniciFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataIniciFins').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.DATAFI)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.dataFi" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataFiDesde" class="input-append">
                <form:input cssClass="input-large" path="dataFiDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFiDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataFiFins" class="input-append">
                <form:input cssClass="input-large" path="dataFiFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFiFins').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.ACTIVA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.activa" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activaDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="activaFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.REVISOR)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="colaboracioDelegacio.revisor" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="revisorDesde" />
                                       
              
              <span class="add-on"><fmt:message key="genapp.to" /></span>
              
              <form:input cssClass="input-append input-small search-query" path="revisorFins" />
              
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="colaboracioDelegacio.motiuDeshabilitada" var="motiuDeshabilitada" />
              <fmt:message key="genapp.form.searchby" var="cercapermotiuDeshabilitada" >                
                 <fmt:param value="${motiuDeshabilitada}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${motiuDeshabilitada}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermotiuDeshabilitada}" path="motiuDeshabilitada" />
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
  
