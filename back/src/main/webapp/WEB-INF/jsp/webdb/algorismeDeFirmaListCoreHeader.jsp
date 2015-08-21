<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AlgorismeDeFirmaFields" className="es.caib.portafib.model.fields.AlgorismeDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AlgorismeDeFirmaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,AlgorismeDeFirmaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AlgorismeDeFirmaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,AlgorismeDeFirmaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AlgorismeDeFirmaFields.SUPORTAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,AlgorismeDeFirmaFields.SUPORTAT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

