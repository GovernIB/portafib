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
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TITOL)}">
          <td>
          ${peticioDeFirma.titol}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIO)}">
          <td>
          ${peticioDeFirma.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIU)}">
          <td>
          ${peticioDeFirma.motiu}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERAFIRMARID)}">
          <td>
            <c:if test="${not empty peticioDeFirma.fitxerAFirmar}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.fitxerAFirmar)}"/>">${peticioDeFirma.fitxerAFirmar.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}">
          <td>
            <c:if test="${not empty peticioDeFirma.firmaOriginalDetached}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.firmaOriginalDetached)}"/>">${peticioDeFirma.firmaOriginalDetached.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
          <td>
            <c:if test="${not empty peticioDeFirma.fitxerAdaptat}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.fitxerAdaptat)}"/>">${peticioDeFirma.fitxerAdaptat.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.tipusDocumentID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusDocumentForTipusDocumentID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
          <td>
          ${peticioDeFirma.descripcioTipusDocument}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.posicioTaulaFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPosicioTaulaFirmesID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATASOLICITUD)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataSolicitud}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATAFINAL)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataFinal}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATACADUCITAT)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticioDeFirma.dataCaducitat}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.tipusOperacioFirma}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusOperacioFirma[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.tipusFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusFirmaForTipusFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.algorismeDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MODEDEFIRMA)}">
          <td>
            <fmt:message key="modedefirma.${peticioDeFirma.modeDeFirma}" />          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.tipusEstatPeticioDeFirmaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIUDEREBUIG)}">
          <td>
          ${peticioDeFirma.motiuDeRebuig}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.IDIOMAID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.idiomaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfIdiomaForIdiomaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PRIORITATID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.prioritatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPrioritatForPrioritatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FLUXDEFIRMESID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.fluxDeFirmesID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFluxDeFirmesForFluxDeFirmesID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.USUARIAPLICACIOID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.usuariAplicacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioForUsuariAplicacioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTNOM)}">
          <td>
          ${peticioDeFirma.remitentNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
          <td>
          ${peticioDeFirma.remitentDescripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADICIONAL)}">
          <td>
          ${peticioDeFirma.informacioAdicional}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
          <td>
            <c:if test="${not empty peticioDeFirma.logoSegell}">
              <a target="_blank" href="<c:url value="${pfi:fileUrl(peticioDeFirma.logoSegell)}"/>">${peticioDeFirma.logoSegell.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.custodiaInfoID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfCustodiaInfoForCustodiaInfoID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.USUARIENTITATID)}">
          <td>
          <c:set var="tmp">${peticioDeFirma.usuariEntitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariEntitatForUsuariEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.AVISWEB)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${peticioDeFirma.avisWeb?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${peticioDeFirma.segellatDeTemps?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTCODI)}">
          <td>
          ${peticioDeFirma.expedientCodi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTNOM)}">
          <td>
          ${peticioDeFirma.expedientNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTURL)}">
          <td>
          ${peticioDeFirma.expedientUrl}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTCODI)}">
          <td>
          ${peticioDeFirma.procedimentCodi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTNOM)}">
          <td>
          ${peticioDeFirma.procedimentnom}
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


