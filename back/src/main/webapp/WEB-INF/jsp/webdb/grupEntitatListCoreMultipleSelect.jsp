      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${grupEntitat.grupEntitatID}"/>
       &nbsp;
      </td>
      </c:if>

