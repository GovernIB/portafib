<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0 }">
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
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.CARREC)}">
          <td>
          ${usuariEntitat.carrec}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
          <td>
          <c:set var="tmp">${usuariEntitat.usuariPersonaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariPersonaForUsuariPersonaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${usuariEntitat.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.actiu?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.EMAIL)}">
          <td>
          ${usuariEntitat.email}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
          <td>
            <c:if test="${not empty usuariEntitat.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(usuariEntitat.logoSegell)}"/>">${usuariEntitat.logoSegell.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.predeterminat?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.rebreTotsElsAvisos?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POTCUSTODIAR)}">
          <td>
            &nbsp;<c:if test="${not empty usuariEntitat.potCustodiar}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariEntitat.potCustodiar?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0 }">
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


