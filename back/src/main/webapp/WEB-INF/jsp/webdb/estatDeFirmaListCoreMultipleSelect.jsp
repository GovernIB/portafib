      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estatDeFirma.estatDeFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

