<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaUsuariEntitatFields" className="es.caib.portafib.model.fields.PeticioDeFirmaUsuariEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaUsuariEntitatFields.PETICIODEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaUsuariEntitatFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaUsuariEntitatFields.MITJADECOMUNICACIOADREZA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}">
        <th>${pfi:getSortIcons(__theFilterForm,PeticioDeFirmaUsuariEntitatFields.AVISWEB)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

