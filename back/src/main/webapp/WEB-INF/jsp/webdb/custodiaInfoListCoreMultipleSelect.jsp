      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${custodiaInfo.custodiaInfoID}"/>
       &nbsp;
      </td>
      </c:if>

