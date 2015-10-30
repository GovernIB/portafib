<%@page import="es.caib.portafib.utils.Configuracio"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><un:useConstants var="Constants" className="es.caib.portafib.utils.Constants"/>
<table id="tablefull" width="100%" style="height:600px;" border="1" cellpadding="0" cellspacing="0">
<tr valign="middle">
<td>
<!--  frameborcder  XYZ -->
<iframe src="${urlToSelectPluginPage}" width="100%" frameborder='3' id="myiframe" scrolling="auto">
<p>NO IFRAME</p>
</iframe>
</td>
</tr>
</table>

<script type="text/javascript" language="javascript"> 

    function alertsize(pixels){
        pixels+=100;
        document.getElementById('myiframe').style.height=pixels+"px";
    }
    
    

</script>