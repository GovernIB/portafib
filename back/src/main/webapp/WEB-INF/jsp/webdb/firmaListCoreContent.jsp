<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FirmaFields" className="es.caib.portafib.model.fields.FirmaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[firma.firmaID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.FIRMAID)}">
          <td>
          ${firma.firmaID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.DESTINATARIID)}">
          <td>
          <c:set var="tmp">${firma.destinatariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForDestinatariID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.BLOCDEFIRMAID)}">
          <td>
          <c:set var="tmp">${firma.blocDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfBlocDeFirmesForBlocDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.OBLIGATORI)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.obligatori?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.FITXERFIRMATID)}">
          <td>
            <c:if test="${not empty firma.fitxerFirmat}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(firma.fitxerFirmat)}"/>">${firma.fitxerFirmat.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMFIRMADOCUMENT)}">
          <td>
          ${firma.numFirmaDocument}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAPAGINA)}">
          <td>
          ${firma.caixaPagina}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAX)}">
          <td>
          ${firma.caixaX}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAY)}">
          <td>
          ${firma.caixaY}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAAMPLE)}">
          <td>
          ${firma.caixaAmple}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAALT)}">
          <td>
          ${firma.caixaAlt}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMEROSERIECERTIFICAT)}">
          <td>
          ${firma.numeroSerieCertificat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.EMISSORCERTIFICAT)}">
          <td>
          ${firma.emissorCertificat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NOMCERTIFICAT)}">
          <td>
          ${firma.nomCertificat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
          <td>
          <c:set var="tmp">${firma.tipusEstatDeFirmaFinalID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusEstatDeFirmaFinalID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOSTRARRUBRICA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.mostrarRubrica?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOTIU)}">
          <td>
          ${firma.motiu}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MINIMDEREVISORS)}">
          <td>
          ${firma.minimDeRevisors}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
          <td>
            &nbsp;<c:if test="${not empty firma.checkAdministrationIdOfSigner}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkAdministrationIdOfSigner?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}">
          <td>
            &nbsp;<c:if test="${not empty firma.checkDocumentModifications}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkDocumentModifications?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKVALIDATIONSIGNATURE)}">
          <td>
            &nbsp;<c:if test="${not empty firma.checkValidationSignature}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkValidationSignature?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.PERFILDEFIRMA)}">
          <td>
          ${firma.perfilDeFirma}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNNOM)}">
          <td>
          ${firma.usuariExternNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNLLINATGES)}">
          <td>
          ${firma.usuariExternLlinatges}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNEMAIL)}">
          <td>
          ${firma.usuariExternEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNIDIOMA)}">
          <td>
          ${firma.usuariExternIdioma}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNTOKEN)}">
          <td>
          ${firma.usuariExternToken}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}">
          <td>
          <c:set var="tmp">${firma.usuariExternNivellSeguretat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsuariExternNivellSeguretat[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[firma.firmaID]}" />
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


