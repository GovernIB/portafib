<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariEntitat.usuariEntitatID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.USUARIENTITATID)}">
        <td>
          ${usuariEntitat.usuariEntitatID}
             <c:if test="${empty usuariEntitat.usuariEntitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.CARREC)}">
        <td>
          ${usuariEntitat.carrec}
             <c:if test="${empty usuariEntitat.carrec}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
        <td>
          <c:set var="tmp">${usuariEntitat.usuariPersonaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariPersonaForUsuariPersonaID[tmp]}
          </c:if>
             <c:if test="${empty usuariEntitat.usuariPersonaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${usuariEntitat.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty usuariEntitat.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.actiu?'success':'error'}.png"/>" alt="${usuariEntitat.actiu}" >
             <c:if test="${empty usuariEntitat.actiu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.EMAIL)}">
        <td>
          ${usuariEntitat.email}
             <c:if test="${empty usuariEntitat.email}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
        <td>
            <c:if test="${not empty usuariEntitat.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(usuariEntitat.logoSegell)}"/>">${usuariEntitat.logoSegell.nom}</a>
            </c:if>
             <c:if test="${empty usuariEntitat.logoSegell}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.predeterminat?'success':'error'}.png"/>" alt="${usuariEntitat.predeterminat}" >
             <c:if test="${empty usuariEntitat.predeterminat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.rebreTotsElsAvisos?'success':'error'}.png"/>" alt="${usuariEntitat.rebreTotsElsAvisos}" >
             <c:if test="${empty usuariEntitat.rebreTotsElsAvisos}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}">
        <td>
          <c:set var="tmp">${usuariEntitat.politicaDePluginFirmaWeb}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaDePluginFirmaWeb[tmp]}
          </c:if>
             <c:if test="${empty usuariEntitat.politicaDePluginFirmaWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POLITICACUSTODIA)}">
        <td>
          <c:set var="tmp">${usuariEntitat.politicaCustodia}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaCustodia[tmp]}
          </c:if>
             <c:if test="${empty usuariEntitat.politicaCustodia}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.CUSTODIAINFOID)}">
        <td>
          <c:set var="tmp">${usuariEntitat.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
             <c:if test="${empty usuariEntitat.custodiaInfoID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariEntitat.usuariEntitatID]}" />
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


