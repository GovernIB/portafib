<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatUsuariEntitatFields" className="es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GrupEntitatUsuariEntitatFields.GRUPENTITATUSUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,GrupEntitatUsuariEntitatFields.GRUPENTITATUSUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,GrupEntitatUsuariEntitatFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,GrupEntitatUsuariEntitatFields.GRUPENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

