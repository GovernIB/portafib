      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${usuariEntitat.usuariEntitatID}"/>
       &nbsp;
      </td>
      </c:if>

