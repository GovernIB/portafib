      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${usuariEntitatRevisor.usuariEntitatRevisorId}"/>
       &nbsp;
      </td>
      </c:if>

