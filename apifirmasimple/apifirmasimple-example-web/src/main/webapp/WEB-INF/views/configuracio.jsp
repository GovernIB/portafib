<%@ page language="java" contentType="text/html;charset=UTF-8"%><%@ include file="/WEB-INF/views/include.jsp"%>
<tags:template>
    <jsp:body>
<center>
<br/>
<br/>
<h3 class="tabs_involved">
    &nbsp;&nbsp;&nbsp;
    <fmt:message key="${configuracioCodeLabel}" />
</h3>

<form:form modelAttribute="configuracioForm" method="post"
    enctype="multipart/form-data">

    <div style="margin: 20px 20px 20px 20px;" style="width:auto;">

        <div class="module_content" style="width: auto;">
            <div class="tab_container" style="width: auto;">

                <table
                    class="table table-bordered " style="width:auto">
                    <tbody>
                        <tr>
                            <td><form:label  path="endPoint" ><fmt:message key="configuracioForm.endPoint" /> &nbsp;(*)</form:label></td>
                            <td>  
                            <div class="input-group">                              
                                <form:input  path="endPoint" aria-describedby="endpointHelpBlock" cssErrorClass="form-control is-invalid" cssClass="form-control"   />
                                <form:errors path="endPoint"  cssClass="invalid-feedback" element="div" />
                                </div>
                                <small id="endpointHelpBlock" class="form-text text-muted">
                                 <c:if test="${isConfigWeb}">
                                  Ex.: http://localhost:8080/portafib/common/rest/apifirmawebsimple/v1/
                                  </c:if>
                                  <c:if test="{not isConfigWeb}">
                                  Ex.: http://localhost:8080/portafib/common/rest/apifirmaenservidorsimple/v1/
                                  </c:if>
                                </small>
                                
                            </td>
                        </tr>

                        <tr>
                            <td>
                               <form:label path="username">
                                <fmt:message key="configuracioForm.username" /> &nbsp;(*)
                                </form:label>
                            </td>
                            <td>
                            <div class="input-group">                               
                               <form:input path="username" cssErrorClass="form-control is-invalid" cssClass="form-control"  />
                               <form:errors path="username" cssClass="invalid-feedback" element="div" />
                               </div>
                            </td>
                        </tr>

                        <tr>
                            <td>
                               <form:label path="password">
                                <fmt:message key="configuracioForm.password" /> &nbsp;(*)
                                </form:label>
                            </td>
                            <td>
                            <div class="input-group">
                               <form:input path="password" cssErrorClass="form-control is-invalid" cssClass="form-control"  />
                               <form:errors path="password" cssClass="invalid-feedback" element="div" />
                               </div>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div style="text-align: center; width: 100%">

                    <input id="submitbutton2" name="guardar" type="submit"
                        class="btn btn-lg active btn-primary" value="<fmt:message key="guardar"/>"/>
                    &nbsp;&nbsp;&nbsp; 
                    <a href="<c:url value="/"/>" class="btn btn-lg active btn-secondary"
                        role="button" aria-pressed="true"> <fmt:message
                            key="cancelar" /></a>

                </div>


            </div>
            <%-- Final DIV OPCIONS --%>

        </div>

    </div>

</form:form>

</center>


 </jsp:body>
</tags:template>

