<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PropietatGlobalFields" className="es.caib.portafib.model.fields.PropietatGlobalFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PropietatGlobalFields.PROPIETATGLOBALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PropietatGlobalFields.PROPIETATGLOBALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PropietatGlobalFields.CLAU)}">
        <th>${pfi:getSortIcons(__theFilterForm,PropietatGlobalFields.CLAU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PropietatGlobalFields.VALOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,PropietatGlobalFields.VALOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PropietatGlobalFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PropietatGlobalFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PropietatGlobalFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PropietatGlobalFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

