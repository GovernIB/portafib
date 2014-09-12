      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusNotificacio.tipusNotificacioID}"/>
       &nbsp;
      </td>
      </c:if>

