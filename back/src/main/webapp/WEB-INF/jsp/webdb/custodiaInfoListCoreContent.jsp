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
          ${custodiaInfo.custodiaInfoID}
             <c:if test="${empty custodiaInfo.custodiaInfoID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
        <td>
          ${custodiaInfo.nomPlantilla}
             <c:if test="${empty custodiaInfo.nomPlantilla}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
        <td>
          ${custodiaInfo.custodiaDocumentID}
             <c:if test="${empty custodiaInfo.custodiaDocumentID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PLUGINID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.pluginID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.pluginID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
        <td>
          ${custodiaInfo.custodiaPluginParameters}
             <c:if test="${empty custodiaInfo.custodiaPluginParameters}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${custodiaInfo.custodiar?'success':'error'}.png"/>" alt="${custodiaInfo.custodiar}" >
             <c:if test="${empty custodiaInfo.custodiar}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
        <td>
          ${custodiaInfo.pagines}
             <c:if test="${empty custodiaInfo.pagines}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
        <td>
          ${custodiaInfo.missatge}
             <c:if test="${empty custodiaInfo.missatge}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.missatgePosicioPaginaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForMissatgePosicioPaginaID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.missatgePosicioPaginaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.codiBarresID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCodiBarresForCodiBarresID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.codiBarresID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.codiBarresPosicioPaginaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForCodiBarresPosicioPaginaID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.codiBarresPosicioPaginaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
        <td>
          ${custodiaInfo.codiBarresText}
             <c:if test="${empty custodiaInfo.codiBarresText}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIENTITATID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.usuariEntitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIAPLICACIOID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.usuariAplicacioID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${custodiaInfo.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty custodiaInfo.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.TITOLPETICIO)}">
        <td>
          ${custodiaInfo.titolPeticio}
             <c:if test="${empty custodiaInfo.titolPeticio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.DATACUSTODIA)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${custodiaInfo.dataCustodia}" />
             <c:if test="${empty custodiaInfo.dataCustodia}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.EDITABLE)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${custodiaInfo.editable?'success':'error'}.png"/>" alt="${custodiaInfo.editable}" >
             <c:if test="${empty custodiaInfo.editable}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSV)}">
        <td>
          ${custodiaInfo.csv}
             <c:if test="${empty custodiaInfo.csv}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSVVALIDATIONWEB)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.csvValidationWeb}">
               <a href="${custodiaInfo.csvValidationWeb}" target="_blank">${custodiaInfo.csvValidationWeb}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.csvValidationWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CSVGENERATIONDEFINITION)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.csvGenerationDefinition}">
               <a href="${custodiaInfo.csvGenerationDefinition}" target="_blank">${custodiaInfo.csvGenerationDefinition}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.csvGenerationDefinition}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.urlFitxerCustodiat}">
               <a href="${custodiaInfo.urlFitxerCustodiat}" target="_blank">${custodiaInfo.urlFitxerCustodiat}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.urlFitxerCustodiat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ORIGINALFILEDIRECTURL)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.originalFileDirectUrl}">
               <a href="${custodiaInfo.originalFileDirectUrl}" target="_blank">${custodiaInfo.originalFileDirectUrl}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.originalFileDirectUrl}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.printableFileDirectUrl}">
               <a href="${custodiaInfo.printableFileDirectUrl}" target="_blank">${custodiaInfo.printableFileDirectUrl}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.printableFileDirectUrl}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ENIFILEDIRECTURL)}">
        <td>
                       <c:if test="${ not empty custodiaInfo.eniFileDirectUrl}">
               <a href="${custodiaInfo.eniFileDirectUrl}" target="_blank">${custodiaInfo.eniFileDirectUrl}</a>
             </c:if>

             <c:if test="${empty custodiaInfo.eniFileDirectUrl}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.EXPEDIENTARXIUID)}">
        <td>
          ${custodiaInfo.expedientArxiuId}
             <c:if test="${empty custodiaInfo.expedientArxiuId}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.DOCUMENTARXIUID)}">
        <td>
          ${custodiaInfo.documentArxiuId}
             <c:if test="${empty custodiaInfo.documentArxiuId}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
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


