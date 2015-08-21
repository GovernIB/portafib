<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BlocDeFirmesFields" className="es.caib.portafib.model.fields.BlocDeFirmesFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BlocDeFirmesFields.BLOCDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BlocDeFirmesFields.BLOCDEFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BlocDeFirmesFields.ORDRE)}">
        <th>${pfi:getSortIcons(__theFilterForm,BlocDeFirmesFields.ORDRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BlocDeFirmesFields.DATAFINALITZACIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,BlocDeFirmesFields.DATAFINALITZACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BlocDeFirmesFields.FLUXDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BlocDeFirmesFields.FLUXDEFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BlocDeFirmesFields.MINIMDEFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,BlocDeFirmesFields.MINIMDEFIRMES)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

