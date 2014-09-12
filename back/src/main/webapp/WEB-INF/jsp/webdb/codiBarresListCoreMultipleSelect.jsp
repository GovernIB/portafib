      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${codiBarres.codiBarresID}"/>
       &nbsp;
      </td>
      </c:if>

