<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.FITXERADAPTATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}</th>
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.TIPUSFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.MODEDEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.MODEDEFIRMA)}</th>
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTNOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.REMITENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADICIONAL)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.INFORMACIOADICIONAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.AVISWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.AVISWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaFields.SEGELLATDETEMPS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

