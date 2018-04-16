      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${pluginFirmaWebPerUsuariAplicacio.pluginfirmawebperusrappid}"/>
       &nbsp;
      </td>
      </c:if>

