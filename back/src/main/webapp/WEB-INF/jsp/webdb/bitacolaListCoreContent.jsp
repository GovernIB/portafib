<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[bitacola.bitacolaID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.BITACOLAID)}">
          <td>
          ${bitacola.bitacolaID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DATA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${bitacola.data}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
          <td>
          ${bitacola.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.PETICIODEFIRMAID)}">
          <td>
          <c:set var="tmp">${bitacola.peticioDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPeticioDeFirmaForPeticioDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.USUARIENTITATID)}">
          <td>
          <c:set var="tmp">${bitacola.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[bitacola.bitacolaID]}" />
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

