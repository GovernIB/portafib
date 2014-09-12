      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${usuariAplicacio.usuariAplicacioID}"/>
       &nbsp;
      </td>
      </c:if>

