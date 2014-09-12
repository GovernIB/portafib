      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${fluxDeFirmes.fluxDeFirmesID}"/>
       &nbsp;
      </td>
      </c:if>

