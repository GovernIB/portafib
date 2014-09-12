      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${permisGrupPlantilla.permisGrupPlantillaID}"/>
       &nbsp;
      </td>
      </c:if>

