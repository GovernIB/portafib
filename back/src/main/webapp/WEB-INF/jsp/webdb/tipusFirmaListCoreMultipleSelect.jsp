      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusFirma.tipusFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

