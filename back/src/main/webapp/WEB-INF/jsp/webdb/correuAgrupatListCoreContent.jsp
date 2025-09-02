<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CorreuAgrupatFields" className="es.caib.portafib.model.fields.CorreuAgrupatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[correuAgrupat.correuAgrupatID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.CORREUAGRUPATID)}">
          <td>
          ${correuAgrupat.correuAgrupatID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.EMAIL)}">
          <td>
          ${correuAgrupat.email}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.SUBJECT)}">
          <td>
          ${correuAgrupat.subject}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.MESSAGE)}">
          <td>
          ${correuAgrupat.message}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.HTML)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${correuAgrupat.html?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.USUARIENTITATID)}">
          <td>
          <c:set var="tmp">${correuAgrupat.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${correuAgrupat.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CorreuAgrupatFields.ERROR)}">
          <td>
          ${correuAgrupat.error}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[correuAgrupat.correuAgrupatID]}" />
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


