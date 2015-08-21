<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.COLABORACIODELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DESTINATARIID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.DESTINATARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.COLABORADORDELEGATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.COLABORADORDELEGATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.ESDELEGAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.ESDELEGAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.MOTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.MOTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DATAINICI)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.DATAFI)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.ACTIVA)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.REVISOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.REVISOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

