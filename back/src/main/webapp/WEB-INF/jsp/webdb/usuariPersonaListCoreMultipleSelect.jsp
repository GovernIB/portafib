      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${usuariPersona.usuariPersonaID}"/>
       &nbsp;
      </td>
      </c:if>

