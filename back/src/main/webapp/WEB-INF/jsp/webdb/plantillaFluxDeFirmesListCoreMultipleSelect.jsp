      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${plantillaFluxDeFirmes.fluxDeFirmesID}"/>
       &nbsp;
      </td>
      </c:if>

