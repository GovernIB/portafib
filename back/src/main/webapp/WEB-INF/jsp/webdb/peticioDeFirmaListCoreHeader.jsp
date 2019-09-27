<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TITOL)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TITOL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.MOTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERAFIRMARID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.FITXERAFIRMARID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.FITXERADAPTATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATASOLICITUD)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DATASOLICITUD)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATAFINAL)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DATAFINAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DATACADUCITAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DATACADUCITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MODEDEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.MODEDEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MOTIUDEREBUIG)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.MOTIUDEREBUIG)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.IDIOMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.IDIOMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PRIORITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.PRIORITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FLUXDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.FLUXDEFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTNOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.REMITENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTCODI)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.EXPEDIENTCODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTNOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.EXPEDIENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTURL)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.EXPEDIENTURL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTCODI)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.PROCEDIMENTCODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTNOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.PROCEDIMENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONAL)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.INFORMACIOADDICIONAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.AVISWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.AVISWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SEGELLATDETEMPS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

