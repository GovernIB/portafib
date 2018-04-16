      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${pluginCridada.pluginCridadaID}"/>
       &nbsp;
      </td>
      </c:if>

