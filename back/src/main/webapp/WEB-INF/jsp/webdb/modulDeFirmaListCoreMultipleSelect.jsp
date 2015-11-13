      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${modulDeFirma.modulDeFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

