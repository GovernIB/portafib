<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PlantillaFluxDeFirmesFields" className="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[plantillaFluxDeFirmes.fluxDeFirmesID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}">
          <td>
          <c:set var="tmp">${plantillaFluxDeFirmes.fluxDeFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFluxDeFirmesForFluxDeFirmesID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.DESCRIPCIO)}">
          <td>
          ${plantillaFluxDeFirmes.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIENTITATID)}">
          <td>
          <c:set var="tmp">${plantillaFluxDeFirmes.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}">
          <td>
          <c:set var="tmp">${plantillaFluxDeFirmes.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.COMPARTIR)}">
          <td>
            <fmt:message key="compartirplantilla.${plantillaFluxDeFirmes.compartir}" />          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[plantillaFluxDeFirmes.fluxDeFirmesID]}" />
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


