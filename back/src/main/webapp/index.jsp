<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%
    String context = request.getContextPath();
    request.getSession().setAttribute("theContext", context);
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<c:redirect context="${theContext}" url="/common/principal.html"/>
</body>
</html>
