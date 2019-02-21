<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PerfilDeFirmaFields" className="es.caib.portafib.model.fields.PerfilDeFirmaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[perfilDeFirma.usuariAplicacioPerfilID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.USUARIAPLICACIOPERFILID)}">
          <td>
          ${perfilDeFirma.usuariAplicacioPerfilID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.NOM)}">
          <td>
          ${perfilDeFirma.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CODI)}">
          <td>
          ${perfilDeFirma.codi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.DESCRIPCIO)}">
          <td>
          ${perfilDeFirma.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONDICIO)}">
          <td>
          ${perfilDeFirma.condicio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID)}">
          <td>
          <c:set var="tmp">${perfilDeFirma.configuracioDeFirma1ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID)}">
          <td>
          <c:set var="tmp">${perfilDeFirma.configuracioDeFirma2ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID)}">
          <td>
          <c:set var="tmp">${perfilDeFirma.configuracioDeFirma3ID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[perfilDeFirma.usuariAplicacioPerfilID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


