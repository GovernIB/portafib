<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacioConfiguracio.usuariAplicacioConfigID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
        <td>
          ${usuariAplicacioConfiguracio.usuariAplicacioConfigID}
             <c:if test="${empty usuariAplicacioConfiguracio.usuariAplicacioConfigID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <td>
          ${usuariAplicacioConfiguracio.nom}
             <c:if test="${empty usuariAplicacioConfiguracio.nom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ENTITATID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.entitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaApiSimpleServidor}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaApiSimpleWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaWeb?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaWeb}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaWS1?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaWS1}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaWS1}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaAsyncRest2?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaAsyncRest2}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaAsyncRest2}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaPassarelaServidor}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb}" >
             <c:if test="${empty usuariAplicacioConfiguracio.usEnFirmaPassarelaWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
        <td>
          ${usuariAplicacioConfiguracio.filtreCertificats}
             <c:if test="${empty usuariAplicacioConfiguracio.filtreCertificats}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.tipusOperacioFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusFirmaID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.tipusFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForAlgorismeDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.algorismeDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.modeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForModeDeFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.modeDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.usPoliticaDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsPoliticaDeFirma[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.usPoliticaDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifier}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifier}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHash}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifierHash}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}
             <c:if test="${empty usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <td>
          ${usuariAplicacioConfiguracio.policyUrlDocument}
             <c:if test="${empty usuariAplicacioConfiguracio.policyUrlDocument}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.motiuDelegacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${usuariAplicacioConfiguracio.motiuDelegacio.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.motiuDelegacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.politicaTaulaFirmes}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaTaulaFirmes[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.politicaTaulaFirmes}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.posicioTaulaFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPosicioTaulaFirmesID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.posicioTaulaFirmesID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.firmatPerFormatID}</c:set>
          <c:if test="${not empty tmp}">
          ${usuariAplicacioConfiguracio.firmatPerFormat.traduccions[lang].valor}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.firmatPerFormat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}">
        <td>
          ${usuariAplicacioConfiguracio.propietatsTaulaFirmes}
             <c:if test="${empty usuariAplicacioConfiguracio.propietatsTaulaFirmes}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.politicaSegellatDeTemps}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPoliticaSegellatDeTemps[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.politicaSegellatDeTemps}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginSegellatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginSegellatID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.pluginSegellatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
        <td>
          ${usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb}
             <c:if test="${empty usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginFirmaServidorID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginFirmaServidorID[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.pluginFirmaServidorID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.upgradeSignFormat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUpgradeSignFormat[tmp]}
          </c:if>
             <c:if test="${empty usuariAplicacioConfiguracio.upgradeSignFormat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.validarFirma}" />             <c:if test="${empty usuariAplicacioConfiguracio.validarFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.checkCanviatDocFirmat}" />             <c:if test="${empty usuariAplicacioConfiguracio.checkCanviatDocFirmat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.comprovarNifFirma}" />             <c:if test="${empty usuariAplicacioConfiguracio.comprovarNifFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
        <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.validarCertificat}" />             <c:if test="${empty usuariAplicacioConfiguracio.validarCertificat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${usuariAplicacioConfiguracio.esDePeticio?'success':'error'}.png"/>" alt="${usuariAplicacioConfiguracio.esDePeticio}" >
             <c:if test="${empty usuariAplicacioConfiguracio.esDePeticio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[usuariAplicacioConfiguracio.usuariAplicacioConfigID]}" />
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


