<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioWSFields" className="es.caib.portafib.model.fields.NotificacioWSFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[notificacioWS.notificacioID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.NOTIFICACIOID)}">
          <td>
          ${notificacioWS.notificacioID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.PETICIODEFIRMAID)}">
          <td>
          <c:set var="tmp">${notificacioWS.peticioDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPeticioDeFirmaForPeticioDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.TIPUSNOTIFICACIOID)}">
          <td>
          <c:set var="tmp">${notificacioWS.tipusNotificacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusNotificacioForTipusNotificacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${notificacioWS.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATAENVIAMENT)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${notificacioWS.dataEnviament}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DESCRIPCIO)}">
          <td>
          ${notificacioWS.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.BLOQUEJADA)}">
          <td>
            &nbsp;<c:if test="${not empty notificacioWS.bloquejada}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${notificacioWS.bloquejada?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.ERROR)}">
          <td>
          ${notificacioWS.error}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.DATAERROR)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${notificacioWS.dataError}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioWSFields.REINTENTS)}">
          <td>
          ${notificacioWS.reintents}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[notificacioWS.notificacioID]}" />
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


