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
     
      <%-- BOTO DE NOU ELEMENT EN LLISTAT  --%>
    <c:if test="${__theFilterForm.addButtonVisible}">
      <a class="btn btn-small pull-right" role="button" data-toggle="modal"
        href="<c:url value="${contexte}/new"/>"> <i class="icon-plus-sign"></i>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
      </a>
    </c:if>
      <%-- BOTO DE ESBORRAT MULTIPLE  --%>
    <c:if test="${__theFilterForm.deleteSelectedButtonVisible && __theFilterForm.visibleMultipleSelection && not empty usuariPersonaItems}">
      <a class="btn btn-danger btn-small pull-right" style="color: white;" href="#myModal"
        onclick="openModalSubmit('<c:url value="${contexte}/deleteSelected"/>','show', 'usuariPersona');"
        title="<fmt:message key="genapp.delete"/>">
        <i class="icon-trash icon-white"></i>
        <fmt:message key="genapp.delete.selected" />
      </a>
    </c:if>
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
  
