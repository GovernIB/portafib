      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${peticioDeFirmaUsuariEntitatFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${peticioDeFirmaUsuariEntitat.peticioDeFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

