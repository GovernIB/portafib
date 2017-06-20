<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioWSFields" className="es.caib.portafib.model.fields.NotificacioWSFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.NOTIFICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.NOTIFICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.TIPUSNOTIFICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.TIPUSNOTIFICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATACREACIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATAENVIAMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.DATAENVIAMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.BLOQUEJADA)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.BLOQUEJADA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.ERROR)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.ERROR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATAERROR)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.DATAERROR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.REINTENTS)}">
        <th>${pfi:getSortIcons(__theFilterForm,NotificacioWSFields.REINTENTS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

