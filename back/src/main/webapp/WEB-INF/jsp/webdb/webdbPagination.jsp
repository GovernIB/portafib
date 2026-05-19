<%@page
    import="org.fundaciobit.genapp.common.web.exportdata.IDataExporter"%>
<%@page import="java.util.List"%>
<%@page
    import="org.fundaciobit.genapp.common.web.exportdata.DataExporterManager"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

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
<div class="row" id="${formName}_pagination"
    style="width: 100%;">

    <div class="col" style="text-align: left;"
        id="${formName}_pagination_left">
        <c:if test="${__theFilterForm.visibleExportList}">

            <%
            for (IDataExporter dataExporter : DataExporterManager.getAllDataExporters()) {
            %>
            <c:url var="exportUrl" value="${contexte}/export" />
            <a href="#"
                onclick="submitForm('${exportUrl}/<%=dataExporter.getID()%>', true)">
                <img alt="<%=dataExporter.getName()%>"
                src="<%=request.getContextPath() + "/common/icon/" + dataExporter.getID()%>" />
            </a>
            <%
            }
            %>
        </c:if>
    </div>

    <div class="col-md-auto text-center"
        id="${formName}_pagination_center">
        <c:if test="${not empty __theFilterForm.itemsPerPage}">
          <%@include file="webdbPaginationOnlyBar.jsp" %>
        </c:if>
    </div>

    <fmt:message var="allitems" key="genapp.form.allitems" />
    <div class="col" style="text-align: right"
        id="${formName}_pagination_right">
        <div class="row float-right">
        <label><fmt:message key="genapp.form.itemsperpage" />:</label>
        <form:select cssClass="input-small" cssStyle="width:4em;"
            onchange="document.${formName}.submit()"
            path="itemsPerPage">
            <c:forEach var="num"
                items="${__theFilterForm.allItemsPerPage}">
                <form:option value="${num}"
                    label="${ (num == -1)? allitems : num}" />
            </c:forEach>
        </form:select>
        </div>
    </div>


</div>

</div>

