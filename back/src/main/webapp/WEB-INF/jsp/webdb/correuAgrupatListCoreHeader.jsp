<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CorreuAgrupatFields" className="es.caib.portafib.model.fields.CorreuAgrupatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.CORREUAGRUPATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.CORREUAGRUPATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.EMAIL)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.EMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.SUBJECT)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.SUBJECT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.MESSAGE)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.MESSAGE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.HTML)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.HTML)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.DATACREACIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.ERROR)}">
        <th>${pfi:getSortIcons(__theFilterForm,CorreuAgrupatFields.ERROR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

