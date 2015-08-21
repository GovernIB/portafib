<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatDeFirmaFields" className="es.caib.portafib.model.fields.EstatDeFirmaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.ESTATDEFIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.ESTATDEFIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.FIRMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.USUARIENTITATID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.USUARIENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAINICI)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.COLABORACIODELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
        <th>${pfi:getSortIcons(__theFilterForm,EstatDeFirmaFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 }">
        <th>
        ${pfi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

