<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatDeFirmaFinalFields" className="es.caib.portafib.model.fields.TipusEstatDeFirmaFinalFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaFinalFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaFinalFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaFinalFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaFinalFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

