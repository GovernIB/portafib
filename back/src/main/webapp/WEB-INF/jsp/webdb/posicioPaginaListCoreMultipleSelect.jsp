      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${posicioPagina.posicioPaginaID}"/>
       &nbsp;
      </td>
      </c:if>

