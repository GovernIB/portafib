      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusDocumentColaboracioDelegacio.id}"/>
       &nbsp;
      </td>
      </c:if>

