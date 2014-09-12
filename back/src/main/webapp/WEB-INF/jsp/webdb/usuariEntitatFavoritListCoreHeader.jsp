<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFavoritFields" className="es.caib.portafib.model.fields.UsuariEntitatFavoritFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFavoritFields.ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFavoritFields.ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFavoritFields.ORIGENID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFavoritFields.ORIGENID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFavoritFields.FAVORITID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFavoritFields.FAVORITID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

