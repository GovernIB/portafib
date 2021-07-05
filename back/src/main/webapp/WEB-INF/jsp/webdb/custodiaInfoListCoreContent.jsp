<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
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
          <c:out value="${custodiaInfo.custodiaInfoID}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
          <td>
          <c:out value="${custodiaInfo.nomPlantilla}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
          <td>
          <c:out value="${custodiaInfo.custodiaDocumentID}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PLUGINID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.pluginID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
          <td>
          <c:out value="${custodiaInfo.custodiaPluginParameters}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${custodiaInfo.custodiar?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
          <td>
          <c:out value="${custodiaInfo.pagines}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
          <td>
          <c:out value="${custodiaInfo.missatge}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
          <td>
          <c:set var="tmp">${custodiaInfo.missatgePosicioPaginaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForMissatgePosicioPaginaID[tmp]}
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
          ${__theFilterForm.mapOfValuesForCodiBarresPosicioPaginaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
          <td>
          <c:out value="${custodiaInfo.codiBarresText}" />
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
          <c:out value="${custodiaInfo.titolPeticio}" />
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSV)}">
          <td>
          <c:out value="${custodiaInfo.csv}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSVVALIDATIONWEB)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.csvValidationWeb}">
               <a href="${custodiaInfo.csvValidationWeb}" target="_blank">${custodiaInfo.csvValidationWeb}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSVGENERATIONDEFINITION)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.csvGenerationDefinition}">
               <a href="${custodiaInfo.csvGenerationDefinition}" target="_blank">${custodiaInfo.csvGenerationDefinition}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.urlFitxerCustodiat}">
               <a href="${custodiaInfo.urlFitxerCustodiat}" target="_blank">${custodiaInfo.urlFitxerCustodiat}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ORIGINALFILEDIRECTURL)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.originalFileDirectUrl}">
               <a href="${custodiaInfo.originalFileDirectUrl}" target="_blank">${custodiaInfo.originalFileDirectUrl}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.printableFileDirectUrl}">
               <a href="${custodiaInfo.printableFileDirectUrl}" target="_blank">${custodiaInfo.printableFileDirectUrl}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ENIFILEDIRECTURL)}">
          <td>
                       <c:if test="${ not empty custodiaInfo.eniFileDirectUrl}">
               <a href="${custodiaInfo.eniFileDirectUrl}" target="_blank">${custodiaInfo.eniFileDirectUrl}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.EXPEDIENTARXIUID)}">
          <td>
          <c:out value="${custodiaInfo.expedientArxiuId}" />
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.DOCUMENTARXIUID)}">
          <td>
          <c:out value="${custodiaInfo.documentArxiuId}" />
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
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


