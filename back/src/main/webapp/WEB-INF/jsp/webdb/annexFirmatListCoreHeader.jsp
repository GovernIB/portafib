<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFirmatFields" className="es.caib.portafib.model.fields.AnnexFirmatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFirmatFields.ANNEXFIRMATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFirmatFields.ANNEXFIRMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFirmatFields.FITXERID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFirmatFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFirmatFields.ANNEXID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFirmatFields.ANNEXID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AnnexFirmatFields.FIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,AnnexFirmatFields.FIRMAID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        <fmt:message key="${__entry.value.codeName}" />
        </th>
        </c:if>
        </c:forEach>

