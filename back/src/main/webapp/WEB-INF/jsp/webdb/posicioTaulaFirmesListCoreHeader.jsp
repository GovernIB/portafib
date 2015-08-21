<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PosicioTaulaFirmesFields" className="es.caib.portafib.model.fields.PosicioTaulaFirmesFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PosicioTaulaFirmesFields.NOM)}">
        <th>${pfi:getSortIcons(__theFilterForm,PosicioTaulaFirmesFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PosicioTaulaFirmesFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PosicioTaulaFirmesFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PosicioTaulaFirmesFields.SUPORTADA)}">
        <th>${pfi:getSortIcons(__theFilterForm,PosicioTaulaFirmesFields.SUPORTADA)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

