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
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.usPoliticaDeTirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsPoliticaDeTirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifier}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHash}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
          <td>
          ${usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
          <td>
          ${usuariAplicacioConfiguracio.policyUrlDocument}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
          <td>
          ${usuariAplicacioConfiguracio.filtreCertificats}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.tipusFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusFirmaForTipusFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
          <td>
            <fmt:message key="modefirma.${usuariAplicacioConfiguracio.modeDeFirma}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.motiuDelegacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${usuariAplicacioConfiguracio.motiuDelegacio.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.firmatPerFormatID}</c:set>
          <c:if test="${not empty tmp}">
          ${usuariAplicacioConfiguracio.firmatPerFormat.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.posicioTaulaFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPosicioTaulaFirmesForPosicioTaulaFirmesID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginSegellatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginSegellatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
          <td>
          <c:set var="tmp">${usuariAplicacioConfiguracio.pluginFirmaServidorID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginFirmaServidorID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
          <td>
          ${usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}">
          <td>
            <c:if test="${not empty usuariAplicacioConfiguracio.loginCertificate}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(usuariAplicacioConfiguracio.loginCertificate)}"/>">${usuariAplicacioConfiguracio.loginCertificate.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
          <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.comprovarNifFirma}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
          <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.checkCanviatDocFirmat}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
          <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.validarFirma}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
          <td>
            <fmt:message key="definitenentitat.${usuariAplicacioConfiguracio.validarCertificat}" />          </td>
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


