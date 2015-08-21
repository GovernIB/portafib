<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CodiBarresFields" className="es.caib.portafib.model.fields.CodiBarresFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CodiBarresFields.CODIBARRESID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="codiBarres.codiBarresID" var="codiBarresID" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiBarresID" >                
                 <fmt:param value="${codiBarresID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiBarresID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiBarresID}" path="codiBarresID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CodiBarresFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="codiBarres.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,CodiBarresFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="codiBarres.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" />
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
  
