      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID}"/>
       &nbsp;
      </td>
      </c:if>

