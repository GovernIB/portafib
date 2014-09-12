      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${roleUsuariEntitat.id}"/>
       &nbsp;
      </td>
      </c:if>

