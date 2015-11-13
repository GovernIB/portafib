<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModulDeFirmaFields" className="es.caib.portafib.model.fields.ModulDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.MODULDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.MODULDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.NOMID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.DESCRIPCIOCURTAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.DESCRIPCIOCURTAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.CLASSE)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.CLASSE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.PROPERTIESADMIN)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.PROPERTIESADMIN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.PROPERTIESENTITAT)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.PROPERTIESENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.ENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ModulDeFirmaFields.ACTIU)}">
        <th>${pfi:getSortIcons(__theFilterForm,ModulDeFirmaFields.ACTIU)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

