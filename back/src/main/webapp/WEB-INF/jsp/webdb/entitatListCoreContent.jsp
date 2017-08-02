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
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
          <td>
          ${entitat.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
          <td>
          ${entitat.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.activa?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEB)}">
          <td>
          ${entitat.web}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FAVICONID)}">
          <td>
            <c:if test="${not empty entitat.favicon}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.favicon)}"/>">${entitat.favicon.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBID)}">
          <td>
            <c:if test="${not empty entitat.logoWeb}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoWeb)}"/>">${entitat.logoWeb.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
          <td>
            <c:if test="${not empty entitat.logoWebPeu}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoWebPeu)}"/>">${entitat.logoWebPeu.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
          <td>
            <c:if test="${not empty entitat.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.logoSegell)}"/>">${entitat.logoSegell.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ADREZAHTML)}">
          <td>
          ${entitat.adrezaHtml}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FILTRECERTIFICATS)}">
          <td>
          ${entitat.filtreCertificats}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PDFAUTORITZACIODELEGACIOID)}">
          <td>
            <c:if test="${not empty entitat.pdfAutoritzacioDelegacio}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(entitat.pdfAutoritzacioDelegacio)}"/>">${entitat.pdfAutoritzacioDelegacio.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
          <td>
          ${entitat.suportTelefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
          <td>
          ${entitat.suportWeb}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
          <td>
          ${entitat.suportEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.USUARIAPLICACIOID)}">
          <td>
          <c:set var="tmp">${entitat.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXUPLOADSIZE)}">
          <td>
          ${entitat.maxUploadSize}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXSIZEFITXERADAPTAT)}">
          <td>
          ${entitat.maxSizeFitxerAdaptat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
          <td>
          ${entitat.maxFilesToSignAtSameTime}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIER)}">
          <td>
          ${entitat.policyIdentifier}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASH)}">
          <td>
          ${entitat.policyIdentifierHash}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
          <td>
          ${entitat.policyIdentifierHashAlgorithm}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYURLDOCUMENT)}">
          <td>
                       <c:if test="${ not empty entitat.policyUrlDocument}">
               <a href="${entitat.policyUrlDocument}" target="_blank">${entitat.policyUrlDocument}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
          <td>
          <c:set var="tmp">${entitat.motiuDelegacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.motiuDelegacio.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FIRMATPERFORMATID)}">
          <td>
          <c:set var="tmp">${entitat.firmatPerFormatID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.firmatPerFormat.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ALGORISMEDEFIRMAID)}">
          <td>
          <c:set var="tmp">${entitat.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COMPROVARNIFFIRMA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.comprovarNifFirma?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CUSTODIAINFOID)}">
          <td>
          <c:set var="tmp">${entitat.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINID)}">
          <td>
          <c:set var="tmp">${entitat.pluginID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SEGELLDETEMPSVIAWEB)}">
          <td>
          <c:set var="tmp">${entitat.segellDeTempsViaWeb}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForSegellDeTempsViaWeb[tmp]}
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


