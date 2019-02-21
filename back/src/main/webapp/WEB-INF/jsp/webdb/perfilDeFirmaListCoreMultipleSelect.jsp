      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${perfilDeFirma.usuariAplicacioPerfilID}"/>
       &nbsp;
      </td>
      </c:if>

