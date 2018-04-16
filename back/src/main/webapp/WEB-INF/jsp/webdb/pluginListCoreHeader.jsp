<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.portafib.model.fields.PluginFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PLUGINID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CODI)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.NOMID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.DESCRIPCIOCURTAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CLASSE)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.CLASSE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ORDRE)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.ORDRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.TIPUS)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.PROPERTIESADMIN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIESENTITAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.PROPERTIESENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.POLITICADEUS)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.POLITICADEUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ACTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.POLITICAMOSTRARPROPIETATS)}">
        <th>${pfi:getSortIcons(__theFilterForm,PluginFields.POLITICAMOSTRARPROPIETATS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

