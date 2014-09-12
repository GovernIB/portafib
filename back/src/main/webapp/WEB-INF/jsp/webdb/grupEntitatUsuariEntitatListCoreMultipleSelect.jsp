      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID}"/>
       &nbsp;
      </td>
      </c:if>

