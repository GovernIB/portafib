<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.NOMPLANTILLA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CUSTODIADOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PLUGINID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CUSTODIAR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.URLFITXERCUSTODIAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.PAGINES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.MISSATGE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CODIBARRESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.CODIBARRESTEXT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.TITOLPETICIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.TITOLPETICIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.DATACUSTODIA)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.DATACUSTODIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CustodiaInfoFields.EDITABLE)}">
        <th>${pfi:getSortIcons(__theFilterForm,CustodiaInfoFields.EDITABLE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

