<%@ page language="java" contentType="text/html;charset=UTF-8"
%><%@ include file="/WEB-INF/views/include.jsp"%>
<tags:template>
    <jsp:body>

<div style="margin: 40px;">
    <br>

    <h3 class="tabs_involved">
        <fmt:message key="firma.final.msg1" />
    </h3>
    <fmt:message key="firma.final.msg2" />
    <table class="table table-bordered">
        <tr>
            <td>ID</td>
            <td>Dades</td>
            <td>Original</td>
            <td>Resultat</td>
        </tr>

        <c:forEach var="entry" items="${peticions}">

            <c:set var="result" value="${entry.value.resultat}"></c:set>

            <tr>
                <td><c:out value="${entry.key}" /></td>
                <td>
                    <ul>

                        <li>SignID: ${result.signID}</li>
                        <li>Status Number: ${result.status.status}</li>
                        <li>Status String: <c:choose>
                                <c:when test="${result.status.status == 0}">
                        INICIALITZANT
                     </c:when>
                                <c:when test="${result.status.status == 1}">
                        EN PROGRES
                     </c:when>
                                <c:when test="${result.status.status == 2}">
                        FINAL_OK
                     </c:when>
                                <c:when test="${result.status.status == -1}">
                        ERROR
                     </c:when>
                                <c:when test="${result.status.status == -2}">
                        CANCEL·LAT
                     </c:when>
                                <c:otherwise>
                        Estat Desconegut
                     </c:otherwise>
                            </c:choose>
                        </li>
                        <li>Nom Fitxer: ${result.signedFile.nom}</li>
                        <li>Mime Fitxer: ${result.signedFile.mime}</li>
                        <li>Tamany Fitxer: ${fn:length(result.signedFile.data)}
                        </li>

                    </ul>
                </td>
 
                <td><a
                    href="<c:url value="${contextWeb}/download/original/${entry.key}" />"
                    target="_blank" class="btn btn-primary" style="color: white;">
                        <fmt:message key="descarregardocumentoriginal" />
                </a> &nbsp;</td>


                <td><c:if test="${result.status.status == 2}">
                        <a
                            href="<c:url value="${contextWeb}/download/signed/${entry.key}" />"
                            target="_blank" class="btn btn-primary"
                            style="color: white;"> <fmt:message
                                key="descarregardocumentfirmat" />
                        </a>
                        <br />
                        <br />
                        <textarea rows="30" cols="70" readonly="readonly"
                            style="width: auto;">${entry.value.signatureDetails}</textarea>


                    </c:if> <c:if test="${result.status.status != 2}">
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <b>${result.status.errorMessage}</b><br /> <i>${result.status.errorStackTrace}</i>
                        </div>
                    </c:if> &nbsp;</td>
            </tr>


            <br/>
        </c:forEach>

    </table>

    <br /> <a href="<c:url value="/" />" class="btn btn-primary active"
        role="button" aria-pressed="true"><fmt:message key="tornar" /></a>

</div>

 </jsp:body>
</tags:template>
