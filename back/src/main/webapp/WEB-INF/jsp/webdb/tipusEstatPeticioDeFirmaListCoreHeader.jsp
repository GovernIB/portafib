<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatPeticioDeFirmaFields" className="es.caib.portafib.model.fields.TipusEstatPeticioDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatPeticioDeFirmaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatPeticioDeFirmaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatPeticioDeFirmaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatPeticioDeFirmaFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

