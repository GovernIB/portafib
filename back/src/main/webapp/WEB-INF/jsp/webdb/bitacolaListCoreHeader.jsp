<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.BITACOLAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.BITACOLAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.USUARIID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.USUARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DATA)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.DATA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.TIPUSOBJECTE)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.TIPUSOBJECTE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.OBJECTEID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.OBJECTEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.TIPUSOPERACIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.TIPUSOPERACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.OBJECTESERIALITZAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.OBJECTESERIALITZAT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

