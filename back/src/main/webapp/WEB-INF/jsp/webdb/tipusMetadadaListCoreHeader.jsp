<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusMetadadaFields" className="es.caib.portafib.model.fields.TipusMetadadaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusMetadadaFields.TIPUSMETADADAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusMetadadaFields.TIPUSMETADADAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusMetadadaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusMetadadaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusMetadadaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusMetadadaFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

