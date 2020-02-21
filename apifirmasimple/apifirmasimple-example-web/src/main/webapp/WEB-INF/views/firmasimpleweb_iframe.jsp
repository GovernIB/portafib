<%@ page language="java" contentType="text/html;charset=UTF-8"%><%@ include file="/WEB-INF/views/include.jsp"%>
<tags:template>
<jsp:attribute name="head">  
<style>
body {
    background-image: url("<c:url value="/ img/ bg.png "/>");
    background-repeat: repeat;
}
</style>
</jsp:attribute>

<jsp:body>
<br />
<br />
<center>
  <iframe id="iframediv" src="${urlToIFrameCode}" width="600px" height="700px" />
</center>
</jsp:body>
</tags:template>
