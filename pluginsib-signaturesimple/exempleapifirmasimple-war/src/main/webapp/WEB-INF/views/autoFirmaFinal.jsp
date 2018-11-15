<%@ include file="/WEB-INF/views/include.jsp"%>

<%@ include file="/WEB-INF/views/html_header.jsp"%>

<un:useConstants var="StatusFirma" className="org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStatus" />

<div style="margin:40px;">
<br>
<br>
<br>

    <h3 class="tabs_involved"><fmt:message key="autofirma.final.msg1" /></h3>
    <fmt:message key="autofirma.final.msg2" />
    <table class="table table-bordered" >
        <tr>
            <td>ID</td>
            <td>Dades</td>
            <td>Original</td>
            <td>Resultat</td>
        </tr>

        <c:forEach var="entry" items="${peticions}">
        
            <c:set var="result" value="${entry.value.resultat}" ></c:set>

            <tr>
                <td><c:out value="${entry.key}" /></td>
                <td>
                  SignID: ${result.signID} <br/>
                  Status Number: ${result.status.status} <br/>
                  Status String:

                  <c:choose>
                     <c:when test = "${result.status.status == StatusFirma.STATUS_INITIALIZING}">
                        INICIALITZANT
                     </c:when>
                     <c:when test = "${result.status.status == StatusFirma.STATUS_IN_PROGRESS}">
                        EN PROGRES
                     </c:when>
                     <c:when test = "${result.status.status == StatusFirma.STATUS_FINAL_OK}">
                        FINAL_OK
                     </c:when>
                     <c:when test = "${result.status.status == StatusFirma.STATUS_FINAL_ERROR}">
                        ERROR
                     </c:when>
                     <c:when test = "${result.status.status == StatusFirma.STATUS_CANCELLED}">
                        CANCEL·LAT
                     </c:when>
                     <c:otherwise>
                        Estat Desconegut
                     </c:otherwise>
                  </c:choose>
                  <br/>
                  Nom Fitxer: ${result.signedFile.nom} <br/>
                  Mime Fitxer: ${result.signedFile.mime} <br/>
                  Len Fitxer: ${fn:length(result.signedFile.data)} <br/>
                </td>
                
                <td><a
                    href="<c:url value="/common/autofirma/download/original/${entry.key}" />"
                    target="_blank" class="btn btn-primary" style="color: white;"> <fmt:message
                            key="descarregardocumentoriginal" />
                </a> &nbsp;</td>


                <td>                
                <c:if test="${result.status.status == StatusFirma.STATUS_FINAL_OK}">
                 <a
                    href="<c:url value="/common/autofirma/download/signed/${entry.key}" />"
                    target="_blank" class="btn btn-primary" style="color: white;"> <fmt:message
                            key="descarregardocumentfirmat" />
                </a>
                <br/>
                <br/>
                 <textarea rows="30" cols="70" style="width:auto;">${entry.value.signatureDetails}</textarea> 
                
                
                 </c:if>
                <c:if test="${result.status.status != StatusFirma.STATUS_FINAL_OK}">
                   <div class="alert alert-danger">
                     <button type="button" class="close" data-dismiss="alert">&times;</button>
                     <b>${result.status.errorMessage}</b><br/>
                     <i>${result.status.errorStackTrace}</i>
                   </div>
                </c:if>
                 &nbsp;
                </td>
            </tr>


            <br>
        </c:forEach>

    </table>
    
    <br/>
   
    <a href="<c:url value="/" />" class="btn"><fmt:message key="tornar"/></a>
   
</div>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>