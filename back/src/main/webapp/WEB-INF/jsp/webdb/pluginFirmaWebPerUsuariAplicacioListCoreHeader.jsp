<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariAplicacioFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBPERUSRAPPID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBPERUSRAPPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

