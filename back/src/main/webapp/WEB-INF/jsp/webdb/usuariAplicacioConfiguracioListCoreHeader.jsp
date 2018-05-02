<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICACUSTODIA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLITICACUSTODIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

