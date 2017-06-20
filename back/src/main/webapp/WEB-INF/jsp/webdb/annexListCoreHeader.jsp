<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFields" className="es.caib.portafib.model.fields.AnnexFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFields.ANNEXID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFields.ANNEXID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFields.FITXERID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFields.ADJUNTAR)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFields.ADJUNTAR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFields.FIRMAR)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFields.FIRMAR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

