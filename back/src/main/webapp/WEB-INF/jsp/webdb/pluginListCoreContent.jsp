<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.portafib.model.fields.PluginFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[plugin.pluginID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PLUGINID)}">
        <td>
          ${plugin.pluginID}
             <c:if test="${empty plugin.pluginID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CODI)}">
        <td>
          ${plugin.codi}
             <c:if test="${empty plugin.codi}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.NOMID)}">
        <td>
          <c:set var="tmp">${plugin.nomID}</c:set>
          <c:if test="${not empty tmp}">
          ${plugin.nom.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty plugin.nom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <td>
          <c:set var="tmp">${plugin.descripcioCurtaID}</c:set>
          <c:if test="${not empty tmp}">
          ${plugin.descripcioCurta.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty plugin.descripcioCurta}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CLASSE)}">
        <td>
          ${plugin.classe}
             <c:if test="${empty plugin.classe}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ORDRE)}">
        <td>
          ${plugin.ordre}
             <c:if test="${empty plugin.ordre}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.TIPUS)}">
        <td>
          <c:set var="tmp">${plugin.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
             <c:if test="${empty plugin.tipus}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <td>
          ${plugin.propertiesAdmin}
             <c:if test="${empty plugin.propertiesAdmin}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIESENTITAT)}">
        <td>
          ${plugin.propertiesEntitat}
             <c:if test="${empty plugin.propertiesEntitat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.POLITICADEUS)}">
        <td>
          <c:set var="tmp">${plugin.politicaDeUs}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaDeUs[tmp]}
          </c:if>
             <c:if test="${empty plugin.politicaDeUs}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${plugin.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty plugin.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ACTIU)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${plugin.actiu?'success':'error'}.png"/>" alt="${plugin.actiu}" >
             <c:if test="${empty plugin.actiu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.POLITICAMOSTRARPROPIETATS)}">
        <td>
          <c:set var="tmp">${plugin.politicaMostrarPropietats}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaMostrarPropietats[tmp]}
          </c:if>
             <c:if test="${empty plugin.politicaMostrarPropietats}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ICONAID)}">
        <td>
            <c:if test="${not empty plugin.icona}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(plugin.icona)}"/>">${plugin.icona.nom}</a>
            </c:if>
             <c:if test="${empty plugin.icona}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[plugin.pluginID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


