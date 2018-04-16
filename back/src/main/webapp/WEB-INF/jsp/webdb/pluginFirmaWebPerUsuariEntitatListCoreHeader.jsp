<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariEntitatFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBPERUSRENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBPERUSRENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

