<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleUsuariEntitatFields" className="es.caib.portafib.model.fields.RoleUsuariEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleUsuariEntitatFields.ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleUsuariEntitatFields.ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleUsuariEntitatFields.ROLEID)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleUsuariEntitatFields.ROLEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleUsuariEntitatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleUsuariEntitatFields.USUARIENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

