      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${modulDeFirmaPerTipusDeDocument.ID}"/>
       &nbsp;
      </td>
      </c:if>

