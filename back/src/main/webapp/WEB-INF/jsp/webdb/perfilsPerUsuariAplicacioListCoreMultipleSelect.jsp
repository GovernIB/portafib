      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${perfilsPerUsuariAplicacio.perfilsPerUsrAppID}"/>
       &nbsp;
      </td>
      </c:if>

