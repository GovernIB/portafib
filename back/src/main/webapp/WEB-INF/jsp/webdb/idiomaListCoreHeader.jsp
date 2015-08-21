<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IdiomaFields" className="es.caib.portafib.model.fields.IdiomaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.IDIOMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,IdiomaFields.IDIOMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,IdiomaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.SUPORTAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,IdiomaFields.SUPORTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,IdiomaFields.ORDRE)}">
        <th>${pfi:getSortIcons(__theFilterForm,IdiomaFields.ORDRE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

