<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${not empty __theFilterForm.titleCode}">
      <fmt:message key="${__theFilterForm.titleCode}">
        <fmt:param value="${__theFilterForm.titleParam}" />
      </fmt:message>
  </c:if>
  <c:if test="${empty __theFilterForm.titleCode}">
    <fmt:message key="genapp.listtitle">
      <fmt:param value="${entitynameplural}"/>
    </fmt:message>
  </c:if>

      <%-- AGRUPAR PER BOTO - INICI  --%>
  <c:if test="${fn:length(groupby_items) > 0}">
      <c:set var="displayGroupBut" value="${__theFilterForm.visibleGroupBy?'display:none;':''}" />
      <a id="GroupButton" style="${displayGroupBut}" title="<fmt:message key="genapp.form.groupby"/>" onclick="document.getElementById('GroupDiv').style.display = 'inherit'; document.getElementById('GroupButton').style.display = 'none';" class="btn" role="button" data-toggle="modal">
         <img src="<c:url value="/img/treeicon.png"/>"/>
      </a>
  </c:if>
      <%-- AGRUPAR PER BOTO - FINAL  --%>

      <%-- FILTRAR PER BOTO - INICI  --%>
      <c:if test="${fn:length(__theFilterForm.filterByFields) > 0}">
      <c:set var="displayFilterBut" value="${__theFilterForm.visibleFilterBy?'display:none;':''}" />
      <a id="FilterButton" style="${displayFilterBut}" title="<fmt:message key="genapp.form.filterby"/>" onclick="document.getElementById('FilterDiv').style.display = 'inherit'; document.getElementById('FilterButton').style.display = 'none';" class="btn" role="button" data-toggle="modal">
         <i class="icon-search"></i>
      </a>
      </c:if>
      <%-- FILTRAR PER BOTO - FINAL  --%>
     
    <c:if test="${__theFilterForm.addButtonVisible}">
      <a class="btn btn-small pull-right" role="button" data-toggle="modal"
        href="<c:url value="${contexte}/new"/>"> <i class="icon-plus-sign"></i>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
      </a>
    </c:if>
    <c:if test="${__theFilterForm.deleteSelectedButtonVisible && __theFilterForm.visibleMultipleSelection && not empty posicioTaulaFirmesItems}">
      <a class="btn btn-danger btn-small pull-right" style="color: white;" href="#myModal"
        onclick="openModalSubmit('<c:url value="${contexte}/deleteSelected"/>','show', 'posicioTaulaFirmes');"
        title="<fmt:message key="genapp.delete"/>">
        <i class="icon-trash icon-white"></i>
        <fmt:message key="genapp.delete.selected" />
      </a>
    </c:if>
    <c:forEach var="button" items="${__theFilterForm.additionalButtons}">
      <c:set var="thelink" value="${button.link}" />
      <c:if test="${!fn:startsWith(thelink,'javascript:')}">
        <c:url var="thelink" value="${thelink}"/>
        <c:set var="thelink" value="goTo('${thelink}')"/>
      </c:if>
      <a class="btn ${button.type} btn-small pull-right" href="#" 
         onclick="${thelink}" 
         title="<fmt:message key="${button.codeText}"/>">
         <i class="${button.icon}"></i>
         <fmt:message key="${button.codeText}"/>
      </a>
    </c:forEach>


  
