<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MitjaDeComunicacioFields" className="es.caib.portafib.model.fields.MitjaDeComunicacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MitjaDeComunicacioFields.MITJADECOMUNICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,MitjaDeComunicacioFields.MITJADECOMUNICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MitjaDeComunicacioFields.ACTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,MitjaDeComunicacioFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MitjaDeComunicacioFields.JAVACLASS)}">
        <th>${pfi:getSortIcons(__theFilterForm,MitjaDeComunicacioFields.JAVACLASS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MitjaDeComunicacioFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,MitjaDeComunicacioFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

