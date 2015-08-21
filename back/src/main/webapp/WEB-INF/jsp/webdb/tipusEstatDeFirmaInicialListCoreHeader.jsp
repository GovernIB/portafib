<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatDeFirmaInicialFields" className="es.caib.portafib.model.fields.TipusEstatDeFirmaInicialFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaInicialFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaInicialFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TipusEstatDeFirmaInicialFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,TipusEstatDeFirmaInicialFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

