<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariPersonaFields" className="es.caib.portafib.model.fields.UsuariPersonaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.USUARIPERSONAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.USUARIPERSONAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.LLINATGES)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.LLINATGES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.EMAIL)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.EMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.NIF)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.IDIOMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.IDIOMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariPersonaFields.RUBRICAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariPersonaFields.RUBRICAID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

