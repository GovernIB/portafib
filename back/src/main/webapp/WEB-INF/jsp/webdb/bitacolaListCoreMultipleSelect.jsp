      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${bitacola.bitacolaID}"/>
       &nbsp;
      </td>
      </c:if>

