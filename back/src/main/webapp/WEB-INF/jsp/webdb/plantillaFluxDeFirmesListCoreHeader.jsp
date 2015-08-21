<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PlantillaFluxDeFirmesFields" className="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PlantillaFluxDeFirmesFields.FLUXDEFIRMESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,PlantillaFluxDeFirmesFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PlantillaFluxDeFirmesFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,PlantillaFluxDeFirmesFields.USUARIAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PlantillaFluxDeFirmesFields.COMPARTIR)}">
        <th>${pfi:getSortIcons(__theFilterForm,PlantillaFluxDeFirmesFields.COMPARTIR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

