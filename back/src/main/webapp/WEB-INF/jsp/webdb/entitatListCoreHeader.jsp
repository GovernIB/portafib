<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.portafib.model.fields.EntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.WEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FAVICONID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.FAVICONID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.LOGOWEBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.LOGOWEBPEUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ADREZAHTML)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.ADREZAHTML)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FILTRECERTIFICATS)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.FILTRECERTIFICATS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PDFAUTORITZACIODELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.PDFAUTORITZACIODELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.SUPORTTELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.SUPORTWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.SUPORTEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXUPLOADSIZE)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.MAXUPLOADSIZE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXSIZEFITXERADAPTAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.MAXSIZEFITXERADAPTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MAXFILESTOSIGNATSAMETIME)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.MAXFILESTOSIGNATSAMETIME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIER)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLICYIDENTIFIER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASH)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLICYIDENTIFIERHASH)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLICYIDENTIFIERHASHALGORITHM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLICYURLDOCUMENT)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLICYURLDOCUMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.MOTIUDELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FIRMATPERFORMATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.FIRMATPERFORMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ALGORISMEDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.ALGORISMEDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLITICACUSTODIA)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLITICACUSTODIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POLITICATAULAFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POLITICATAULAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.POSICIOTAULAFIRMES)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.POSICIOTAULAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SEGELLDETEMPSVIAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.SEGELLDETEMPSVIAWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINRUBRICAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.PLUGINRUBRICAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COMPROVARNIFFIRMA)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.COMPROVARNIFFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.CHECKCANVIATDOCFIRMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINVALIDAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.PLUGINVALIDAFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINVALIDACERTIFICATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EntitatFields.PLUGINVALIDACERTIFICATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

