<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.portafib.model.fields.EntitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
        <td>
          ${entitat.entitatID}
             <c:if test="${empty entitat.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
        <td>
          ${entitat.nom}
             <c:if test="${empty entitat.nom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
        <td>
          ${entitat.descripcio}
             <c:if test="${empty entitat.descripcio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.activa?'success':'error'}.png"/>" alt="${entitat.activa}" >
             <c:if test="${empty entitat.activa}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEB)}">
        <td>
          ${entitat.web}
             <c:if test="${empty entitat.web}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FAVICONID)}">
        <td>
            <c:if test="${not empty entitat.favicon}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.favicon)}"/>">${entitat.favicon.nom}</a>
            </c:if>
             <c:if test="${empty entitat.favicon}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBID)}">
        <td>
            <c:if test="${not empty entitat.logoWeb}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoWeb)}"/>">${entitat.logoWeb.nom}</a>
            </c:if>
             <c:if test="${empty entitat.logoWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
        <td>
            <c:if test="${not empty entitat.logoWebPeu}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoWebPeu)}"/>">${entitat.logoWebPeu.nom}</a>
            </c:if>
             <c:if test="${empty entitat.logoWebPeu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
        <td>
            <c:if test="${not empty entitat.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoSegell)}"/>">${entitat.logoSegell.nom}</a>
            </c:if>
             <c:if test="${empty entitat.logoSegell}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ADREZAHTML)}">
        <td>
          ${entitat.adrezaHtml}
             <c:if test="${empty entitat.adrezaHtml}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FILTRECERTIFICATS)}">
        <td>
          ${entitat.filtreCertificats}
             <c:if test="${empty entitat.filtreCertificats}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PDFAUTORITZACIODELEGACIOID)}">
        <td>
            <c:if test="${not empty entitat.pdfAutoritzacioDelegacio}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.pdfAutoritzacioDelegacio)}"/>">${entitat.pdfAutoritzacioDelegacio.nom}</a>
            </c:if>
             <c:if test="${empty entitat.pdfAutoritzacioDelegacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <td>
          ${entitat.suportTelefon}
             <c:if test="${empty entitat.suportTelefon}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <td>
                       <c:if test="${ not empty entitat.suportWeb}">
               <a href="${entitat.suportWeb}" target="_blank">${entitat.suportWeb}</a>
             </c:if>

             <c:if test="${empty entitat.suportWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <td>
          ${entitat.suportEmail}
             <c:if test="${empty entitat.suportEmail}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.USUARIAPLICACIOID)}">
        <td>
          <c:set var="tmp">${entitat.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
             <c:if test="${empty entitat.usuariAplicacioID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXUPLOADSIZE)}">
        <td>
          ${entitat.maxUploadSize}
             <c:if test="${empty entitat.maxUploadSize}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXSIZEFITXERADAPTAT)}">
        <td>
          ${entitat.maxSizeFitxerAdaptat}
             <c:if test="${empty entitat.maxSizeFitxerAdaptat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
        <td>
          ${entitat.maxFilesToSignAtSameTime}
             <c:if test="${empty entitat.maxFilesToSignAtSameTime}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.USPOLITICADEFIRMA)}">
        <td>
          <c:set var="tmp">${entitat.usPoliticaDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsPoliticaDeFirma[tmp]}
          </c:if>
             <c:if test="${empty entitat.usPoliticaDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIER)}">
        <td>
          ${entitat.policyIdentifier}
             <c:if test="${empty entitat.policyIdentifier}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASH)}">
        <td>
          ${entitat.policyIdentifierHash}
             <c:if test="${empty entitat.policyIdentifierHash}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <td>
          ${entitat.policyIdentifierHashAlgorithm}
             <c:if test="${empty entitat.policyIdentifierHashAlgorithm}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYURLDOCUMENT)}">
        <td>
                       <c:if test="${ not empty entitat.policyUrlDocument}">
               <a href="${entitat.policyUrlDocument}" target="_blank">${entitat.policyUrlDocument}</a>
             </c:if>

             <c:if test="${empty entitat.policyUrlDocument}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
        <td>
          <c:set var="tmp">${entitat.motiuDelegacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.motiuDelegacio.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty entitat.motiuDelegacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FIRMATPERFORMATID)}">
        <td>
          <c:set var="tmp">${entitat.firmatPerFormatID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.firmatPerFormat.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty entitat.firmatPerFormat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ALGORISMEDEFIRMAID)}">
        <td>
          <c:set var="tmp">${entitat.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForAlgorismeDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty entitat.algorismeDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLITICACUSTODIA)}">
        <td>
          <c:set var="tmp">${entitat.politicaCustodia}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaCustodia[tmp]}
          </c:if>
             <c:if test="${empty entitat.politicaCustodia}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CUSTODIAINFOID)}">
        <td>
          <c:set var="tmp">${entitat.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
             <c:if test="${empty entitat.custodiaInfoID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLITICATAULAFIRMES)}">
        <td>
          <c:set var="tmp">${entitat.politicaTaulaFirmes}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaTaulaFirmes[tmp]}
          </c:if>
             <c:if test="${empty entitat.politicaTaulaFirmes}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POSICIOTAULAFIRMES)}">
        <td>
          <c:set var="tmp">${entitat.posicioTaulaFirmes}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPosicioTaulaFirmes[tmp]}
          </c:if>
             <c:if test="${empty entitat.posicioTaulaFirmes}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PROPIETATSTAULAFIRMES)}">
        <td>
          ${entitat.propietatsTaulaFirmes}
             <c:if test="${empty entitat.propietatsTaulaFirmes}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLITICASEGELLATDETEMPS)}">
        <td>
          <c:set var="tmp">${entitat.politicaSegellatDeTemps}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps[tmp]}
          </c:if>
             <c:if test="${empty entitat.politicaSegellatDeTemps}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINSEGELLTEMPSID)}">
        <td>
          <c:set var="tmp">${entitat.pluginSegellTempsID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginSegellTempsID[tmp]}
          </c:if>
             <c:if test="${empty entitat.pluginSegellTempsID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINRUBRICAID)}">
        <td>
          <c:set var="tmp">${entitat.pluginRubricaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginRubricaID[tmp]}
          </c:if>
             <c:if test="${empty entitat.pluginRubricaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.VALIDARFIRMA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.validarfirma?'success':'error'}.png"/>" alt="${entitat.validarfirma}" >
             <c:if test="${empty entitat.validarfirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COMPROVARNIFFIRMA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.comprovarNifFirma?'success':'error'}.png"/>" alt="${entitat.comprovarNifFirma}" >
             <c:if test="${empty entitat.comprovarNifFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.checkCanviatDocFirmat?'success':'error'}.png"/>" alt="${entitat.checkCanviatDocFirmat}" >
             <c:if test="${empty entitat.checkCanviatDocFirmat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINVALIDAFIRMESID)}">
        <td>
          <c:set var="tmp">${entitat.pluginValidaFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginValidaFirmesID[tmp]}
          </c:if>
             <c:if test="${empty entitat.pluginValidaFirmesID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINVALIDACERTIFICATID)}">
        <td>
          <c:set var="tmp">${entitat.pluginValidaCertificatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginValidaCertificatID[tmp]}
          </c:if>
             <c:if test="${empty entitat.pluginValidaCertificatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatID]}" />
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


