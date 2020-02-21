<%@ include file="/WEB-INF/views/include.jsp"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<jsp:directive.attribute name="head" required="false" fragment="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <link rel="icon" type="image/vnd.microsoft.icon" href="imagen.ico">
    <title>apifirmasimple-web-example</title>
    
    <meta charset="UTF-8">
    <link rel="icon" type="image/vnd.microsoft.icon" href="<c:url value="/img/favicon.ico"/>">
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
        <%-- "https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous" --%>
    <script src="<c:url value="/js/jquery-3.2.1.slim.min.js"/>"></script>
    <%-- https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous" --%>
    <script src="<c:url value="/js/popper.min.js"/>"></script>
    <%-- https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous" --%>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    
    <jsp:invoke fragment="head" />
</head>
<body>
    <c:if test="${not empty missatges}">
        <c:forEach items="${missatges}" var="tipusList" varStatus="status">
            <c:forEach items="${tipusList.value}" var="msg">
                <div class="alert alert-${tipusList.key} alert-dismissible fade show" style="margin: 10px" role="alert">
                    ${msg}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:forEach>
        </c:forEach>
    </c:if>
    <c:remove var="missatges" scope="session" />
    <div class="spacer"></div>
    <jsp:doBody />
</body>
</html>