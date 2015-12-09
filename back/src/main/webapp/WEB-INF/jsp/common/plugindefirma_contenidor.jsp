<%@page import="es.caib.portafib.utils.Configuracio"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<%--
<table id="tablefull" width="100%" style="height:*;border-color: red;" border="1" cellpadding="0" cellspacing="0">
<tr valign="top">
<td>
<div style="height: 100%">
--%>
<%--  frameborder='3' --%>
<iframe src="${urlToSelectPluginPage}" style="min-height:200px" frameborder='0' width="100%" height="400px"  id="myiframe" scrolling="auto">
<p>NO IFRAME</p>
</iframe>
<%--
</div>
</td>
</tr>
</table>
--%>

<script type="text/javascript" language="javascript"> 

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
