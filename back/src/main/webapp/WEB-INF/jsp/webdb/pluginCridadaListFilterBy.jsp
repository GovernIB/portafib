<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginCridadaFields" className="es.caib.portafib.model.fields.PluginCridadaFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.PLUGINCRIDADAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="pluginCridada.pluginCridadaID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginCridadaIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="pluginCridadaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.ENTITATID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="pluginCridada.entitatID" var="entitatID" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatID" >                
                 <fmt:param value="${entitatID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatID}" path="entitatID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.DATA)}">
            <%-- FILTRE DATE --%>
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="pluginCridada.data" />:</span>
              <span class="add-on"><fmt:message key="genapp.from" /></span>
              <div id="dataDesde" class="input-append">
                <form:input cssClass="input-large" path="dataDesde" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataDesde').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
              <span class="add-on"><fmt:message key="genapp.to" /></span>              
              <div id="dataFins" class="input-append">
                <form:input cssClass="input-large" path="dataFins" />
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFins').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.TIPUSPLUGIN)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="pluginCridada.tipusPlugin" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusPluginDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusPluginFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.DADESPLUGIN)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="pluginCridada.dadesPlugin" var="dadesPlugin" />
              <fmt:message key="genapp.form.searchby" var="cercaperdadesPlugin" >                
                 <fmt:param value="${dadesPlugin}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${dadesPlugin}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdadesPlugin}" path="dadesPlugin" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.METODEPLUGIN)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="pluginCridada.metodePlugin" var="metodePlugin" />
              <fmt:message key="genapp.form.searchby" var="cercapermetodePlugin" >                
                 <fmt:param value="${metodePlugin}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${metodePlugin}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermetodePlugin}" path="metodePlugin" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.DADESCRIDADA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="pluginCridada.dadesCridada" var="dadesCridada" />
              <fmt:message key="genapp.form.searchby" var="cercaperdadesCridada" >                
                 <fmt:param value="${dadesCridada}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${dadesCridada}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdadesCridada}" path="dadesCridada" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.TIPUSTESULTAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="pluginCridada.tipusTesultat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusTesultatDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tipusTesultatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.RESULTAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="pluginCridada.resultat" var="resultat" />
              <fmt:message key="genapp.form.searchby" var="cercaperresultat" >                
                 <fmt:param value="${resultat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${resultat}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperresultat}" path="resultat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PluginCridadaFields.TEMPSEXECUCIO)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="pluginCridada.tempsExecucio" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tempsExecucioDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="tempsExecucioFins" />

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
  
