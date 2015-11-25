<%@page import="es.caib.portafib.utils.Configuracio"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<%-- XYZ --%>
<table id="tablefull" width="100%" style="height:*;border-color: red;" border="1" cellpadding="0" cellspacing="0">
<tr valign="top">
<td>
<div style="height: 100%">
<iframe src="${urlToSelectPluginPage}" style="min-height: 200px" width="100%" height="500px" frameborder='3' id="myiframe" scrolling="auto">
<p>NO IFRAME</p>
</iframe>
</div>
</td>
</tr>
</table>
<script type="text/javascript" language="javascript"> 

     $(document).ready(function ()  { 
       console.log("XYZ Passa per ready start timeout")
       setTimeout(checkIframeSize, 1500);
     });

     var lastSize = 0;
    
    function checkIframeSize() {
        
        setTimeout(checkIframeSize, 2000);
                
        var iframe = document.getElementById('myiframe');
        
        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;

        // XYZ
        var d = new Date();
        console.log(" ================ " + d + " ===================");
        
        var h = Math.max($(iframeDocument.body).height(),iframeDocument.body.scrollHeight, $("#tablefull").height());
        if (h != lastSize && lastSize > h) {
            h = h + 100;
            lastSize = h;

            console.log(" XYZ checkIframeSize()::iframeDocument.body.scrollHeight = " + iframeDocument.body.scrollHeight);
            console.log(" XYZ checkIframeSize()::$(iframeDocument.body).height() = " + $(iframeDocument.body).height());
            console.log(" XYZ checkIframeSize()::$(TABLE).height() = " + $("#tablefull").height());
            console.log(" XYZ checkIframeSize():: SET " + h);
            document.getElementById('myiframe').style.height=h + "px";
            lastSize =  Math.max($(iframeDocument.body).height(),iframeDocument.body.scrollHeight, $("#tablefull").height());
            console.log(" XYZ checkIframeSize():: GET " + lastSize);
        }
    }

</script>
