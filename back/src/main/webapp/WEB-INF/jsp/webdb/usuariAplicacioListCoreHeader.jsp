<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.portafib.model.fields.UsuariAplicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.CONTRASENYA)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.CONTRASENYA)}</th>
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
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UsuariAplicacioFields.POTCUSTODIAR)}">
        <th>${pfi:getSortIcons(__theFilterForm,UsuariAplicacioFields.POTCUSTODIAR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

