<%@ include file="/WEB-INF/views/include.jsp"%>


<%@ include file="/WEB-INF/views/html_header.jsp"%>

<br>
<br>
<br>
<br>
<br>
<br>

<center>
<fmt:message key="autofirma.final.msg1"/><br>
<fmt:message key="autofirma.final.msg2"/><br>
<c:if test="${not empty id}" >
<a href="<c:url value="/common/autofirma/download/${id}/${signType}" />" target="_blank" class="btn btn-primary" style="color:white;">
 <fmt:message key="descarregardocumentfirmat"/>
</a> &nbsp; 
</c:if>
<a href="<c:url value="/" />" class="btn"><fmt:message key="tornar"/></a>

<%--  common/autofirma/form.html --%>

</center>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>