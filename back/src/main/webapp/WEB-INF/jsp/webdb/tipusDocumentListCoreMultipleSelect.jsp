      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusDocument.tipusDocumentID}"/>
       &nbsp;
      </td>
      </c:if>

