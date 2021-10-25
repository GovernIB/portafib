<%@page import="org.fundaciobit.genapp.common.web.exportdata.IDataExporter"%>
<%@page import="java.util.List"%>
<%@page import="org.fundaciobit.genapp.common.web.exportdata.DataExporterManager"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp" %>

<script type="text/javascript">

var currentActionForExporter = "";

function submitForm(action, reassign) {
  if (reassign) {  
	 currentActionForExporter = document.${formName}.action;
  }
  document.${formName}.action = action;
  if (reassign) {
    setTimeout(reassignAction, 3000);
  } 
  document.${formName}.submit();
}

function reassignAction() {
  document.${formName}.action = currentActionForExporter;
  currentActionForExporter = "";
}
</script>
<div class="row" id="${formName}_pagination" style="width:100%; text-align:center;" >

  <div class="col-4" style="float:left;" id="${formName}_pagination_left">
  <c:if test="${__theFilterForm.visibleExportList}">
      
      <%
         for(IDataExporter dataExporter : DataExporterManager.getAllDataExporters()) {
      %>
      <c:url var="exportUrl" value="${contexte}/export"/>
          <a href="#" onclick="submitForm('${exportUrl}/<%=dataExporter.getID()%>', true)">
           <img alt="<%=dataExporter.getName()%>" src="<%=request.getContextPath() + "/common/icon/" + dataExporter.getID()%>"/> 
          </a>
      <% } %>
  </c:if>
  <div class="pagination pagination-centered" id="${formName}_pagination_center">
  </div>
  </div>
  
  
  <div class="col-4">
  <c:if test="${not empty __theFilterForm.itemsPerPage}">

    <c:url var="firstUrl" value="${contexte}/list/1" 
    /><c:url var="lastUrl" value="${contexte}/list/${totalPages}" 
    /><c:url var="prevUrl" value="${contexte}/list/${currentIndex - 1}" 
    /><c:url var="nextUrl" value="${contexte}/list/${currentIndex + 1}" />
    
    
<nav aria-label="Page navigation">
  <ul class="pagination pagination-sm" id="${formName}_pagination_center">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="page-item disabled"><a class="page-link" href="#">&lt;&lt;</a></li>
                <li class="page-item disabled"><a class="page-link" href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="#" onclick="submitForm('${firstUrl}', false)">&lt;&lt;</a></li>
                <li class="page-item"><a class="page-link" href="#" onclick="submitForm('${prevUrl}', false)">&lt;</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="${contexte}/list/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="#" onclick="submitForm('${pageUrl}', false)"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li class="page-item disabled"><a class="page-link" href="#"> &gt;</a></li>
                <li class="page-item disabled"><a class="page-link" href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="#" onclick="submitForm('${nextUrl}', false)">&gt;</a></li>
                <li class="page-item"><a class="page-link" href="#" onclick="submitForm('${lastUrl}', false)">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </nav>
    
    </c:if>
    </div>

    <fmt:message var="allitems" key="genapp.form.allitems" />
    <div class="col-4" style="float:right;" id="${formName}_pagination_right" >
      <label><fmt:message key="genapp.form.itemsperpage" />:</label>
      <form:select cssClass="input-small" cssStyle="width:4em;"  onchange="document.${formName}.submit()" path="itemsPerPage" >
        <c:forEach var="num" items="${__theFilterForm.allItemsPerPage}">
           <form:option value="${num}" label="${ (num == -1)? allitems : num}"/>                 
        </c:forEach>
      </form:select>
    </div>

    </div>

</div>

