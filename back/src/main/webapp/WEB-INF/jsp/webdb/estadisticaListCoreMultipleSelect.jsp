      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${estadistica.estadisticaID}"/>
       &nbsp;
      </td>
      </c:if>

