      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" aria-label="Seleccionar" value="${annexFirmat.annexfirmatID}"/>
       &nbsp;
      </td>
      </c:if>

