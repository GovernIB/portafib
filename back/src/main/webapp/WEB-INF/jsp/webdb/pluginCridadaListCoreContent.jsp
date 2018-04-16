<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginCridadaFields" className="es.caib.portafib.model.fields.PluginCridadaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[pluginCridada.pluginCridadaID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.PLUGINCRIDADAID)}">
          <td>
          ${pluginCridada.pluginCridadaID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${pluginCridada.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.DATA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${pluginCridada.data}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.TIPUSPLUGIN)}">
          <td>
          <c:set var="tmp">${pluginCridada.tipusPlugin}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusPlugin[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.DADESPLUGIN)}">
          <td>
          ${pluginCridada.dadesPlugin}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.METODEPLUGIN)}">
          <td>
          ${pluginCridada.metodePlugin}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.DADESCRIDADA)}">
          <td>
          ${pluginCridada.dadesCridada}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.TIPUSTESULTAT)}">
          <td>
          ${pluginCridada.tipusTesultat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.RESULTAT)}">
          <td>
          ${pluginCridada.resultat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.TEMPSEXECUCIO)}">
          <td>
          ${pluginCridada.tempsExecucio}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[pluginCridada.pluginCridadaID]}" />
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


