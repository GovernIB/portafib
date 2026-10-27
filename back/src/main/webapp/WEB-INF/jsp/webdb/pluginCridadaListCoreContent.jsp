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
             <c:if test="${empty pluginCridada.pluginCridadaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${pluginCridada.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty pluginCridada.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.DATA)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${pluginCridada.data}" />
             <c:if test="${empty pluginCridada.data}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.PLUGINID)}">
        <td>
          <c:set var="tmp">${pluginCridada.pluginID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginID[tmp]}
          </c:if>
             <c:if test="${empty pluginCridada.pluginID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.METODEPLUGIN)}">
        <td>
          ${pluginCridada.metodePlugin}
             <c:if test="${empty pluginCridada.metodePlugin}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.PARAMETRESTEXT)}">
        <td>
          ${pluginCridada.parametresText}
             <c:if test="${empty pluginCridada.parametresText}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.PARAMETRESFITXERID)}">
        <td>
            <c:if test="${not empty pluginCridada.parametresFitxer}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(pluginCridada.parametresFitxer)}"/>">${pluginCridada.parametresFitxer.nom}</a>
            </c:if>
             <c:if test="${empty pluginCridada.parametresFitxer}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.RETORNTEXT)}">
        <td>
          ${pluginCridada.retornText}
             <c:if test="${empty pluginCridada.retornText}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.RETORNFITXERID)}">
        <td>
            <c:if test="${not empty pluginCridada.retornFitxer}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(pluginCridada.retornFitxer)}"/>">${pluginCridada.retornFitxer.nom}</a>
            </c:if>
             <c:if test="${empty pluginCridada.retornFitxer}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.TIPUSTESULTAT)}">
        <td>
          ${pluginCridada.tipusTesultat}
             <c:if test="${empty pluginCridada.tipusTesultat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginCridadaFields.TEMPSEXECUCIO)}">
        <td>
          ${pluginCridada.tempsExecucio}
             <c:if test="${empty pluginCridada.tempsExecucio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
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


