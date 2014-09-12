<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[custodiaInfo.custodiaInfoID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAINFOID)}">
          <td>
          ${custodiaInfo.custodiaInfoID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINID)}">
          <td>
          ${custodiaInfo.custodiaPluginID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINCLASSID)}">
          <td>
          ${custodiaInfo.custodiaPluginClassID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
          <td>
          ${custodiaInfo.custodiaPluginParameters}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
          <td>
          ${custodiaInfo.nomPlantilla}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${custodiaInfo.custodiar?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
          <td>
          ${custodiaInfo.urlFitxerCustodiat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
          <td>
          ${custodiaInfo.pagines}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
          <td>
          ${custodiaInfo.missatge}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.missatgePosicioPaginaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPosicioPaginaForMissatgePosicioPaginaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.codiBarresID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCodiBarresForCodiBarresID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.codiBarresPosicioPaginaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPosicioPaginaForCodiBarresPosicioPaginaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
          <td>
          ${custodiaInfo.codiBarresText}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIENTITATID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIAPLICACIOID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.TITOLPETICIO)}">
          <td>
          ${custodiaInfo.titolPeticio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.DATACUSTODIA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${custodiaInfo.dataCustodia}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.EDITABLE)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${custodiaInfo.editable?'success':'error'}.png"/>">
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0 }">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[custodiaInfo.custodiaInfoID]}" />
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


