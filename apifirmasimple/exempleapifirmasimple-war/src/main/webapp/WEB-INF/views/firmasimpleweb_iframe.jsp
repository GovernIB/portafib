<%@ include file="/WEB-INF/views/html_header.jsp"%>

<style>
    body{
       background-image: url("<c:url value="/img/bg.png"/>");
         background-repeat: repeat;
    }
</style>

<br/><br/>

<div id="iframediv"></div>
<script type="text/javascript"> 
$("#iframediv").load("${urlToIFrameCode}")
</script>


<%@ include file="/WEB-INF/views/html_footer.jsp"%>
