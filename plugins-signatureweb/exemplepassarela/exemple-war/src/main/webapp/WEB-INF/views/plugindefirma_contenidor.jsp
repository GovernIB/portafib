<%@ include file="/WEB-INF/views/html_header.jsp"%>

<style>
    body{
    //background-color: red;
       background-image: url("<c:url value="/img/bg.png"/>");
         background-repeat: repeat;
    }
</style>

<br/><br/>


<%--
<table style="width:600px; background: transparent; border: 4px solid red;   padding:40px 40px 40px 40px;">
<tr>
<td valign="middle">
<center>

--%>

<%--
<table id="tablefull" width="100%" style="height:*;border-color: red;" border="1" cellpadding="0" cellspacing="0">
<tr valign="top">
<td>
<div style="height: 100%">
--%>
<%--  frameborder='3' --%>
<iframe src="${urlToSelectPluginPage}" style="background-color: white; min-height:200px" frameborder='0' width="100%" height="400px"  id="myiframe" scrolling="auto">
<p>NO IFRAME</p>
</iframe>
<%--
</div>
</td>
</tr>
</table>
--%>
<%--
</center>
</td>
</tr>
</table>
 --%>
<script type="text/javascript"> 

    var lastSize = 0;

    function checkIframeSize() {
        
        setTimeout(checkIframeSize, 1000);
                
        var iframe = document.getElementById('myiframe');
        
        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;

        var h1 = $(iframeDocument.body).height();
        var h2 = iframeDocument.body.scrollHeight;
        //var h3 = $("#tablefull").height();

        var h = Math.max(h1,h2);

        var log = false;

        var d = new Date();
        if (log) {
            console.log("================ " + d + " (H = " + h +" | H1= " + h1 + " | H2= " + h2 + ") ===================");
        }

        if (h != lastSize) {
            h = h + 100;
            lastSize = h;
            if (log) {
              console.log(" checkIframeSize()::iframeDocument.body.scrollHeight = " + iframeDocument.body.scrollHeight);
              console.log(" checkIframeSize()::$(iframeDocument.body).height() = " + $(iframeDocument.body).height());
              console.log(" checkIframeSize()::$(TABLE).height() = " + $("#tablefull").height());
              console.log(" checkIframeSize():: SET " + h);
            }
            document.getElementById('myiframe').style.height=h + "px";
            lastSize =  Math.max($(iframeDocument.body).height(),iframeDocument.body.scrollHeight); <%--  $("#tablefull").height() --%>
            if (log) {
              console.log(" checkIframeSize():: GET " + lastSize);
            }
        }
    }
    
    $(document).ready(function ()  {
        setTimeout(checkIframeSize, 1000);
      });

</script>


<%@ include file="/WEB-INF/views/html_footer.jsp"%>
