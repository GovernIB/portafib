<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.portafib.model.fields.UsuariAplicacioFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacio.usuariAplicacioID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <td>
          ${usuariAplicacio.usuariAplicacioID}
             <c:if test="${empty usuariAplicacio.usuariAplicacioID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.EMAILADMIN)}">
        <td>
          ${usuariAplicacio.emailAdmin}
             <c:if test="${empty usuariAplicacio.emailAdmin}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CALLBACKVERSIO)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.callbackVersio}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForCallbackVersio[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.callbackVersio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CALLBACKURL)}">
        <td>
          ${usuariAplicacio.callbackURL}
             <c:if test="${empty usuariAplicacio.callbackURL}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.ACTIU)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacio.actiu?'success':'error'}.png"/>" alt="${usuariAplicacio.actiu}" >
             <c:if test="${empty usuariAplicacio.actiu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.IDIOMAID)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.idiomaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfIdiomaForIdiomaID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.idiomaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.DESCRIPCIO)}">
        <td>
          ${usuariAplicacio.descripcio}
             <c:if test="${empty usuariAplicacio.descripcio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.LOGOSEGELLID)}">
        <td>
            <c:if test="${not empty usuariAplicacio.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(usuariAplicacio.logoSegell)}"/>">${usuariAplicacio.logoSegell.nom}</a>
            </c:if>
             <c:if test="${empty usuariAplicacio.logoSegell}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.politicaDePluginFirmaWeb}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaDePluginFirmaWeb[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.politicaDePluginFirmaWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.POLITICACUSTODIA)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.politicaCustodia}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaCustodia[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.politicaCustodia}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CUSTODIAINFOID)}">
        <td>
          <c:set var="tmp">${usuariAplicacio.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacio.custodiaInfoID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CREARUSUARIS)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacio.crearUsuaris?'success':'error'}.png"/>" alt="${usuariAplicacio.crearUsuaris}" >
             <c:if test="${empty usuariAplicacio.crearUsuaris}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.TIPUSREVISORS)}">
        <td>
            <fmt:message key="tipusrevisors.${usuariAplicacio.tipusRevisors}" />             <c:if test="${empty usuariAplicacio.tipusRevisors}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacio.usuariAplicacioID]}" />
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


