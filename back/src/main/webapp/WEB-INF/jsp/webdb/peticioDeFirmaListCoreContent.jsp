<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[peticioDeFirma.peticioDeFirmaID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PETICIODEFIRMAID)}">
        <td>
          ${peticioDeFirma.peticioDeFirmaID}
             <c:if test="${empty peticioDeFirma.peticioDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TITOL)}">
        <td>
          ${peticioDeFirma.titol}
             <c:if test="${empty peticioDeFirma.titol}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIO)}">
        <td>
          ${peticioDeFirma.descripcio}
             <c:if test="${empty peticioDeFirma.descripcio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIU)}">
        <td>
          ${peticioDeFirma.motiu}
             <c:if test="${empty peticioDeFirma.motiu}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERAFIRMARID)}">
        <td>
            <c:if test="${not empty peticioDeFirma.fitxerAFirmar}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.fitxerAFirmar)}"/>">${peticioDeFirma.fitxerAFirmar.nom}</a>
            </c:if>
             <c:if test="${empty peticioDeFirma.fitxerAFirmar}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}">
        <td>
            <c:if test="${not empty peticioDeFirma.firmaOriginalDetached}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.firmaOriginalDetached)}"/>">${peticioDeFirma.firmaOriginalDetached.nom}</a>
            </c:if>
             <c:if test="${empty peticioDeFirma.firmaOriginalDetached}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
        <td>
            <c:if test="${not empty peticioDeFirma.fitxerAdaptat}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.fitxerAdaptat)}"/>">${peticioDeFirma.fitxerAdaptat.nom}</a>
            </c:if>
             <c:if test="${empty peticioDeFirma.fitxerAdaptat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.tipusDocumentID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusDocumentForTipusDocumentID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.tipusDocumentID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
        <td>
          ${peticioDeFirma.descripcioTipusDocument}
             <c:if test="${empty peticioDeFirma.descripcioTipusDocument}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATASOLICITUD)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataSolicitud}" />
             <c:if test="${empty peticioDeFirma.dataSolicitud}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATAFINAL)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataFinal}" />
             <c:if test="${empty peticioDeFirma.dataFinal}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATACADUCITAT)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataCaducitat}" />
             <c:if test="${empty peticioDeFirma.dataCaducitat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.tipusOperacioFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.tipusFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusFirmaID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.tipusFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForAlgorismeDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.algorismeDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MODEDEFIRMA)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.modeDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForModeDeFirma[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.modeDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.posicioTaulaFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPosicioTaulaFirmesID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.posicioTaulaFirmesID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.tipusEstatPeticioDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusEstatPeticioDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.tipusEstatPeticioDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIUDEREBUIG)}">
        <td>
          ${peticioDeFirma.motiuDeRebuig}
             <c:if test="${empty peticioDeFirma.motiuDeRebuig}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.IDIOMAID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.idiomaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfIdiomaForIdiomaID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.idiomaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PRIORITATID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.prioritatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPrioritatID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.prioritatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FLUXDEFIRMESID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.fluxDeFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFluxDeFirmesForFluxDeFirmesID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.fluxDeFirmesID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.solicitantUsuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForSolicitantUsuariAplicacioID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.solicitantUsuariAplicacioID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTNOM)}">
        <td>
          ${peticioDeFirma.remitentNom}
             <c:if test="${empty peticioDeFirma.remitentNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
        <td>
          ${peticioDeFirma.remitentDescripcio}
             <c:if test="${empty peticioDeFirma.remitentDescripcio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTCODI)}">
        <td>
          ${peticioDeFirma.expedientCodi}
             <c:if test="${empty peticioDeFirma.expedientCodi}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTNOM)}">
        <td>
          ${peticioDeFirma.expedientNom}
             <c:if test="${empty peticioDeFirma.expedientNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTURL)}">
        <td>
          ${peticioDeFirma.expedientUrl}
             <c:if test="${empty peticioDeFirma.expedientUrl}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTCODI)}">
        <td>
          ${peticioDeFirma.procedimentCodi}
             <c:if test="${empty peticioDeFirma.procedimentCodi}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTNOM)}">
        <td>
          ${peticioDeFirma.procedimentNom}
             <c:if test="${empty peticioDeFirma.procedimentNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONAL)}">
        <td>
          ${peticioDeFirma.informacioAddicional}
             <c:if test="${empty peticioDeFirma.informacioAddicional}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)}">
        <td>
          ${peticioDeFirma.informacioAddicionalAvaluable}
             <c:if test="${empty peticioDeFirma.informacioAddicionalAvaluable}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
        <td>
            <c:if test="${not empty peticioDeFirma.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.logoSegell)}"/>">${peticioDeFirma.logoSegell.nom}</a>
            </c:if>
             <c:if test="${empty peticioDeFirma.logoSegell}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.custodiaInfoID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.solicitantUsuariEntitat1ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForSolicitantUsuariEntitat1ID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.solicitantUsuariEntitat1ID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.solicitantUsuariEntitat2ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForSolicitantUsuariEntitat2ID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.solicitantUsuariEntitat2ID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.solicitantUsuariEntitat3ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForSolicitantUsuariEntitat3ID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.solicitantUsuariEntitat3ID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.AVISWEB)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${peticioDeFirma.avisWeb?'success':'error'}.png"/>" alt="${peticioDeFirma.avisWeb}" >
             <c:if test="${empty peticioDeFirma.avisWeb}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
        <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${peticioDeFirma.segellatDeTemps?'success':'error'}.png"/>" alt="${peticioDeFirma.segellatDeTemps}" >
             <c:if test="${empty peticioDeFirma.segellatDeTemps}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.origenPeticioDeFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForOrigenPeticioDeFirma[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.origenPeticioDeFirma}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}">
        <td>
          <c:set var="tmp">${peticioDeFirma.configuracioDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID[tmp]}
          </c:if>
             <c:if test="${empty peticioDeFirma.configuracioDeFirmaID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[peticioDeFirma.peticioDeFirmaID]}" />
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


