<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.portafib.model.fields.UsuariAplicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.EMAILADMIN)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.EMAILADMIN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CALLBACKVERSIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.CALLBACKVERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CALLBACKURL)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.CALLBACKURL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.ACTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.IDIOMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.IDIOMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.LOGOSEGELLID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.POLITICACUSTODIA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.POLITICACUSTODIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CUSTODIAINFOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.CUSTODIAINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CREARUSUARIS)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.CREARUSUARIS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.TIPUSREVISORS)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.TIPUSREVISORS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

