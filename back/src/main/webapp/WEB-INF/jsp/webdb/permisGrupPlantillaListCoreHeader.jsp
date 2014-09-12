<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisGrupPlantillaFields" className="es.caib.portafib.model.fields.PermisGrupPlantillaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisGrupPlantillaFields.PERMISGRUPPLANTILLAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisGrupPlantillaFields.PERMISGRUPPLANTILLAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisGrupPlantillaFields.GRUPENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisGrupPlantillaFields.GRUPENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

