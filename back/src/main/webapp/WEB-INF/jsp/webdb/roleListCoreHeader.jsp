<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleFields" className="es.caib.portafib.model.fields.RoleFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleFields.ROLEID)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleFields.ROLEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RoleFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,RoleFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

