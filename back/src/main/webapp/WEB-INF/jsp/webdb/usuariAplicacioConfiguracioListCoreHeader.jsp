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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLESERVIDOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAAPISIMPLEWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAWS1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAASYNCREST2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB)}</th>
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.PROPIETATSTAULAFIRMES)}</th>
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioConfiguracioFields.ESDEPETICIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

