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
             <c:if test="${empty firma.firmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.DESTINATARIID)}">
        <td>
          <c:set var="tmp">${firma.destinatariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForDestinatariID[tmp]}
          </c:if>
             <c:if test="${empty firma.destinatariID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.BLOCDEFIRMAID)}">
        <td>
          <c:set var="tmp">${firma.blocDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfBlocDeFirmesForBlocDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty firma.blocDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.OBLIGATORI)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.obligatori?'success':'error'}.png"/>" alt="${firma.obligatori}" >
             <c:if test="${empty firma.obligatori}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.FITXERFIRMATID)}">
        <td>
            <c:if test="${not empty firma.fitxerFirmat}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(firma.fitxerFirmat)}"/>">${firma.fitxerFirmat.nom}</a>
            </c:if>
             <c:if test="${empty firma.fitxerFirmat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMFIRMADOCUMENT)}">
        <td>
          ${firma.numFirmaDocument}
             <c:if test="${empty firma.numFirmaDocument}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAPAGINA)}">
        <td>
          ${firma.caixaPagina}
             <c:if test="${empty firma.caixaPagina}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAX)}">
        <td>
          ${firma.caixaX}
             <c:if test="${empty firma.caixaX}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAY)}">
        <td>
          ${firma.caixaY}
             <c:if test="${empty firma.caixaY}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAAMPLE)}">
        <td>
          ${firma.caixaAmple}
             <c:if test="${empty firma.caixaAmple}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAALT)}">
        <td>
          ${firma.caixaAlt}
             <c:if test="${empty firma.caixaAlt}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMEROSERIECERTIFICAT)}">
        <td>
          ${firma.numeroSerieCertificat}
             <c:if test="${empty firma.numeroSerieCertificat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.EMISSORCERTIFICAT)}">
        <td>
          ${firma.emissorCertificat}
             <c:if test="${empty firma.emissorCertificat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NOMCERTIFICAT)}">
        <td>
          ${firma.nomCertificat}
             <c:if test="${empty firma.nomCertificat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <td>
          <c:set var="tmp">${firma.tipusEstatDeFirmaFinalID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusEstatDeFirmaFinalID[tmp]}
          </c:if>
             <c:if test="${empty firma.tipusEstatDeFirmaFinalID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOSTRARRUBRICA)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.mostrarRubrica?'success':'error'}.png"/>" alt="${firma.mostrarRubrica}" >
             <c:if test="${empty firma.mostrarRubrica}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOTIU)}">
        <td>
          ${firma.motiu}
             <c:if test="${empty firma.motiu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MINIMDEREVISORS)}">
        <td>
          ${firma.minimDeRevisors}
             <c:if test="${empty firma.minimDeRevisors}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <td>
            &nbsp;<c:if test="${not empty firma.checkAdministrationIdOfSigner}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkAdministrationIdOfSigner?'success':'error'}.png"/>" alt="${firma.checkAdministrationIdOfSigner}" >
            </c:if>
             <c:if test="${empty firma.checkAdministrationIdOfSigner}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <td>
            &nbsp;<c:if test="${not empty firma.checkDocumentModifications}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkDocumentModifications?'success':'error'}.png"/>" alt="${firma.checkDocumentModifications}" >
            </c:if>
             <c:if test="${empty firma.checkDocumentModifications}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKVALIDATIONSIGNATURE)}">
        <td>
            &nbsp;<c:if test="${not empty firma.checkValidationSignature}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${firma.checkValidationSignature?'success':'error'}.png"/>" alt="${firma.checkValidationSignature}" >
            </c:if>
             <c:if test="${empty firma.checkValidationSignature}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.PERFILDEFIRMA)}">
        <td>
          ${firma.perfilDeFirma}
             <c:if test="${empty firma.perfilDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNNOM)}">
        <td>
          ${firma.usuariExternNom}
             <c:if test="${empty firma.usuariExternNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNLLINATGES)}">
        <td>
          ${firma.usuariExternLlinatges}
             <c:if test="${empty firma.usuariExternLlinatges}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNEMAIL)}">
        <td>
          ${firma.usuariExternEmail}
             <c:if test="${empty firma.usuariExternEmail}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNIDIOMA)}">
        <td>
          ${firma.usuariExternIdioma}
             <c:if test="${empty firma.usuariExternIdioma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNTOKEN)}">
        <td>
          ${firma.usuariExternToken}
             <c:if test="${empty firma.usuariExternToken}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}">
        <td>
          <c:set var="tmp">${firma.usuariExternNivellSeguretat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForUsuariExternNivellSeguretat[tmp]}
          </c:if>
             <c:if test="${empty firma.usuariExternNivellSeguretat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.SIGNATUREPLUGINID)}">
        <td>
          ${firma.signaturePluginId}
             <c:if test="${empty firma.signaturePluginId}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
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


