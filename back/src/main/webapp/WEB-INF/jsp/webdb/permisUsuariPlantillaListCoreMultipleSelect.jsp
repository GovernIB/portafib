      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${permisUsuariPlantilla.permisUsuariPlantillaID}"/>
       &nbsp;
      </td>
      </c:if>

