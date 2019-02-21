<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilsPerUsuariAplicacioFields" className="es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilsPerUsuariAplicacioFields.PERFILSPERUSRAPPID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PerfilsPerUsuariAplicacioFields.PERFILSPERUSRAPPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

