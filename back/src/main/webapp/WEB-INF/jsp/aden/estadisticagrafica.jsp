<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h2>${categoria}</h2>
<h4>${grupLabel}${rang}</h4>

<form method="get" class="form-inline">
    <label for="grupSelect">Grups:</label> <select id="grupSelect" name="grup" class="form-control">
    <%-- Recorrer sobre un MAP "grups" on la clau és el "value" del select i l'etiqueta del select és el value del map--%>
    <option value="">Tots</option>
    <c:forEach var="entry" items="${grups}">
        <option value="${entry.key}" <c:if test="${selectedGrup == entry.key}"> selected="selected"</c:if>>${entry.value}</option>
    </c:forEach>
    </select> &nbsp;&nbsp; 
    
  
    <label for="usuariAplicacioSelect">Aplicacions:</label>
     <select id="usuariAplicacioSelect" name="usuariaplicacio" class="form-control">
        <option value="">Totes</option>
        <c:forEach var="usuariApp" items="${aplicacions}">
            <option value="${usuariApp}" <c:if test="${selectedUsuariAplicacio == usuariApp}"> selected="selected"</c:if>>${usuariApp}</option>
        </c:forEach>
    </select> &nbsp;&nbsp;
    
     <label for="rangeSelect">Rang:</label>
      <select id="rangeSelect" name="rang" class="form-control">
        <option value="1" <c:if test="${selectedRange == 1}"> selected="selected"</c:if>>Any</option>
        <option value="2" <c:if test="${selectedRange == 2}"> selected="selected"</c:if>>Mes</option>
        <option value="3" <c:if test="${selectedRange == 3}"> selected="selected"</c:if>>Dia</option>
    </select> &nbsp;&nbsp; <label for="dateInput">Data:</label> <input type="date" class="form-control" id="dateInput" name="date"
        value="${selectedDate}" /> &nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">Aplicar</button>
    &nbsp;&nbsp;
    <button type="button" class="btn btn-secondary" onclick="exportToCSV()">Exportar CSV</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<canvas id="barChart" style="padding: 20px"></canvas>
<script>
  const ctx = document.getElementById('barChart').getContext('2d');
  const labels = [
      <c:forEach var="entry" items="${labels}">'${entry}',</c:forEach>]; // or hours/days of month
  <c:if test="${not empty valuesCreate}">
  const dataCreate = [ <c:forEach var="entry" items="${valuesCreate}">${entry},</c:forEach>];
  </c:if>     
  <c:if test="${not empty valuesFirmes}">
  const dataFirmes = [ <c:forEach var="entry" items="${valuesFirmes}">${entry},</c:forEach>];
  </c:if>     
      
  const dataOk = [ <c:forEach var="entry" items="${valuesOK}">${entry},</c:forEach>];
  <c:if test="${not empty valuesCancel}">
  const dataCancel = [ <c:forEach var="entry" items="${valuesCancel}">${entry},</c:forEach>];
  </c:if>
  const dataError = [ <c:forEach var="entry" items="${valuesError}">${entry},</c:forEach>];

  function exportToCSV() {
    const csvRows = [];
    // Capçaleres
    csvRows.push(['Etiqueta', <c:if test="${not empty valuesCreate}">'${titleCreate}',</c:if> <c:if test="${not empty valuesFirmes}">'${titleFirmes}',</c:if> '${titleOK}', <c:if test="${not empty valuesCancel}">'${titleCancel}',</c:if>'${titleError}'].join(','));
    
    // Recórrer les dades
    for (let i = 0; i < labels.length; i++) {
        const row = [
            labels[i],
            <c:if test="${not empty valuesCreate}">(typeof dataCreate !== 'undefined' ? dataCreate[i] : 0),</c:if>
            <c:if test="${not empty valuesFirmes}">(typeof dataFirmes !== 'undefined' ? dataFirmes[i] : 0),</c:if>
            dataOk[i] || 0,
            <c:if test="${not empty valuesCancel}">(typeof dataCancel !== 'undefined' ? dataCancel[i] : 0),</c:if>
            dataError[i] || 0
        ];
        csvRows.push(row.join(','));
    }
    
    // Crear el fitxer i descarregar
    const blob = new Blob([csvRows.join('\n')], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.setAttribute("href", url);
    link.setAttribute("download", "estadistiques.csv");
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [
          
      <c:if test="${not empty valuesCreate}">
      {
          label: '${titleCreate}',
          data: dataCreate,
          backgroundColor: 'rgba(0, 0, 0, 0.8)'
       },
      </c:if>
       
       <c:if test="${not empty valuesFirmes}">
       {
           label: '${titleFirmes}',
           data: dataFirmes,
           backgroundColor: 'rgba(17, 150, 255, 0.8)'
        },
       </c:if>
          
      {
        label: '${titleOK}',
        data: dataOk,
        backgroundColor: 'rgba(68, 219, 68, 0.8)'
      },
      
      <c:if test="${not empty valuesCancel}">
      {
          label: '${titleCancel}',
          data: dataCancel,
          backgroundColor: 'rgba(250, 250, 0, 0.8)'
      },
      </c:if>
      
      {
        label: '${titleError}',
        data: dataError,
        backgroundColor: 'rgba(242, 50, 52, 0.8)'
      }]
    },
    options: {
        precision: 0,
        responsive: true,
        scales: { y: { beginAtZero: true } }
    }
  });
</script>