      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
        <th>
        <form:checkbox path="selectedItems" aria-label="Seleccionar" value="${notificacioWS.notificacioID}"/>
            <span class="sr-only">Seleccionar fila</span>
        </th>
      </c:if>

