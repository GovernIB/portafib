<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MetadadaFields" className="es.caib.portafib.model.fields.MetadadaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.METADADAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.METADADAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.VALOR)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.VALOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MetadadaFields.TIPUSMETADADAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,MetadadaFields.TIPUSMETADADAID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

