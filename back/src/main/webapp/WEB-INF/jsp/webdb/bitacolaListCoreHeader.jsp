<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.BITACOLAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.BITACOLAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DATA)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.DATA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,BitacolaFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,BitacolaFields.USUARIENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

