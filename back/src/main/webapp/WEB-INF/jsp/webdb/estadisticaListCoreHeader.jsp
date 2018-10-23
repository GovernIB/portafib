<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.portafib.model.fields.EstadisticaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.ESTADISTICAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.ESTADISTICAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.DATA)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.DATA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.VALOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.VALOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.PARAMETRES)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstadisticaFields.PARAMETRES)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

