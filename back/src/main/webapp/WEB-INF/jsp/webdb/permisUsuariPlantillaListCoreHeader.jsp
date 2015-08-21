<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PermisUsuariPlantillaFields" className="es.caib.portafib.model.fields.PermisUsuariPlantillaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisUsuariPlantillaFields.PERMISUSUARIPLANTILLAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisUsuariPlantillaFields.PERMISUSUARIPLANTILLAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisUsuariPlantillaFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisUsuariPlantillaFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

