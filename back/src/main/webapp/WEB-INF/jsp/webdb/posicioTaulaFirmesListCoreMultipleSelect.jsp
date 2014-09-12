      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${posicioTaulaFirmes.posicioTaulaFirmesID}"/>
       &nbsp;
      </td>
      </c:if>

