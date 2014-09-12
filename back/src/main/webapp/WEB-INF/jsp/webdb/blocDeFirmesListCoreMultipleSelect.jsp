      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${blocDeFirmes.blocDeFirmesID}"/>
       &nbsp;
      </td>
      </c:if>

