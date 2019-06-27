<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FirmaFields" className="es.caib.portafib.model.fields.FirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.FIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.FIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.DESTINATARIID)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.DESTINATARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.BLOCDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.BLOCDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.OBLIGATORI)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.OBLIGATORI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.FITXERFIRMATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.FITXERFIRMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMFIRMADOCUMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.NUMFIRMADOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAPAGINA)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CAIXAPAGINA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAX)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CAIXAX)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAY)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CAIXAY)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAAMPLE)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CAIXAAMPLE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CAIXAALT)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CAIXAALT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NUMEROSERIECERTIFICAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.NUMEROSERIECERTIFICAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.EMISSORCERTIFICAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.EMISSORCERTIFICAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.NOMCERTIFICAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.NOMCERTIFICAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.TIPUSESTATDEFIRMAFINALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOSTRARRUBRICA)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.MOSTRARRUBRICA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MOTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.MOTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.MINIMDEREVISORS)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.MINIMDEREVISORS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.CHECKVALIDATIONSIGNATURE)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.CHECKVALIDATIONSIGNATURE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FirmaFields.PERFILDEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,FirmaFields.PERFILDEFIRMA)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

