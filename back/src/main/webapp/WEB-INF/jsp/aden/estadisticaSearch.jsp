<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="estadistica" cssClass="form-horizontal"  modelAttribute="estadisticaFilterForm"
        method="${method}" enctype="multipart/form-data">

  <c:set var="contexte" value="${estadisticaFilterForm.contexte}"/>
  <c:set var="__theFilterForm" value="${estadisticaFilterForm}" />

  <%-- HIDDEN PARAMS: ORDER BY --%>
  <form:hidden id="orderBy" path="orderBy"/>
  <form:hidden id="orderAsc" path="orderAsc"/>
  <form:hidden path="nou" value="false"/>

  <input type="hidden" id="agruparPerCamp" name="groupBy" value="tipus" />

  <div id="estadistica_listheader" class="filterLine lead" style="margin-bottom:10px">
    <fmt:message key="estadistica.estadistica"/>

    <c:forEach var="button" items="${__theFilterForm.additionalButtons}">
      <c:set var="thelink" value="${button.link}" />
      <c:set var="thehref" value="#"/>
      <c:if test="${!fn:startsWith(thelink,'javascript:')}">
          <c:url var="thehref" value="${thelink}"/>
          <c:url var="thelink" value=""/>
      </c:if>
      <a class="btn btn-small ${button.type} pull-right" style="${(empty button.type)? '' : 'color: white;'}"  href="${thehref}" onclick="${thelink}" title="<fmt:message key="${button.codeText}"/>">
          <i class="${button.icon}"></i>
          <fmt:message key="${button.codeText}"/>
      </a>
    </c:forEach>
  </div>

    <div class="control-group">
        <label class="control-label"><fmt:message key="genapp.from" /></label>
        <div class="controls">
            <div id="dataDesde" class="input-append">
                <form:input cssClass="input-large" path="dataDesde" id="dataDesteInput" />
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
        </div>
    </div>

    <div class="control-group">
        <label class="control-label"><fmt:message key="genapp.to" /></label>
        <div class="controls">
            <div id="dataFins" class="input-append">
                <form:input cssClass="input-large" path="dataFins" id="dataFinsInput" />
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
    </div>

    <div class="control-group">
        <label class="control-label"><fmt:message key="estadistiques.tipususuari" /></label>
        <div class="controls">
            <label class="radio"><fmt:message key="estadistiques.tipusaplicacio" />
                <form:radiobutton path="aplicacio" value="true" onchange="this.form.submit()" />
            </label>
            <label class="radio"><fmt:message key="estadistiques.tipuspersona" />
                <form:radiobutton path="aplicacio" value="false" onchange="this.form.submit()" />&nbsp;
            </label>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label"><fmt:message key="estadistiques.estatpeticio" /></label>
        <div class="controls">
            <form:select id="agruparPerValor" path="groupValue">
                <c:forEach  var="groupby_item" items="${groupby_items}">
                    <c:if test="${groupby_item.value == 'tipus'}">
                        <c:forEach  var="groupbyvalue_item"  items="${groupby_item.values}">
                            <form:option value="${groupbyvalue_item.value}">${groupbyvalue_item.codeLabel}</form:option>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </form:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label"><fmt:message key="estadistiques.format" /></label>
        <div class="controls">
            <c:forEach items="${dataExporters}" var="dataExporter">
                <form:radiobutton path="exporter" value="${dataExporter.ID}" />
                &nbsp;${dataExporter.name}
                <img alt="${dataExporter.name}" src="<c:url value="/common/icon/${dataExporter.ID}" />" />
            </c:forEach>
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <button type="button" class="btn" onclick="submitForm(true)"><fmt:message key="descarregar" /></button>
        </div>
    </div>

    <script type="text/javascript">
        var currentActionForExporter = "";

        function submitForm(reassign) {
            if (reassign) {
                currentActionForExporter = document.estadistica.action;
            }
            document.estadistica.action = '<c:url value="${contexte}/export/" />' + document.estadistica.exporter.value;
            if (reassign) {
                setTimeout(reassignAction, 3000);
            }
            document.estadistica.submit();
        }

        function reassignAction() {
            document.estadistica.action = currentActionForExporter;
            currentActionForExporter = "";
        }
    </script>

</form:form> 
    

