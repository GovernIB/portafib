<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.CARREC)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.CARREC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.USUARIPERSONAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.EMAIL)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.EMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.PREDETERMINAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.REBRETOTSELSAVISOS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POTCUSTODIAR)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.POTCUSTODIAR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POLITICACUSTODIA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.POLITICACUSTODIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

