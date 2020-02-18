<%@ page language="java" contentType="text/html;charset=UTF-8"
%><%@ include file="/WEB-INF/views/include.jsp"%>
<tags:template>
    <jsp:body>


<center>
    <h3 class="tabs_involved">
        &nbsp;&nbsp;&nbsp;
        <fmt:message key="${firmaCodeLabel}" />
    </h3>

    <form:form modelAttribute="firmaForm" method="post" enctype="multipart/form-data">



        <div style="margin: 20px 20px 20px 20px;" style="width:auto;">

            <div class="module_content" style="width: auto;">
                <div class="tab_container" style="width: auto;">

                    <table class="table table-bordered" style="width: auto;">
                        <tbody>
                            <tr>

                                <td><form:label path="motiu">
                                        <fmt:message key="firmaForm.motiu" /> &nbsp;(*)</form:label></td>
                                <td>
                                    <div class="input-group">
                                        <form:input cssErrorClass="form-control is-invalid" cssClass="form-control"
                                            path="motiu" />
                                        <form:errors path="motiu" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td><form:label path="username">
                                        <fmt:message
                                            key="${ isFirmaWeb?'firmaForm.username_web':'firmaForm.username_server'}" />
                                    </form:label></td>
                                <td>
                                    <div class="input-group">

                                        <form:input path="username" cssErrorClass="form-control is-invalid" cssClass="form-control" />
                                        <form:errors path="username" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td><form:label path="nif">

                                        <fmt:message
                                            key="${ isFirmaWeb?'firmaForm.nif_usuari':'firmaForm.nif_certificat'}" />


                                    </form:label></td>
                                <td>
                                    <div class="input-group">


                                        <form:input path="nif" cssErrorClass="form-control is-invalid"
                                            cssClass="form-control" />
                                        <form:errors path="nif" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>


                            <c:if test="${isFirmaWeb}">




                                <tr>
                                    <td><form:label path="email">Email</form:label></td>
                                    <td>
                                        <div class="input-group">

                                            <form:input path="email" cssErrorClass="form-control is-invalid"
                                                cssClass="form-control" />
                                            <form:errors path="email" cssClass="invalid-feedback" element="div" />
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td><form:label path="visualitzacio">
                                            <fmt:message key="visualitzacio" /> &nbsp;(*)</form:label></td>
                                    <td>
                                        <div class="input-group">

                                            <form:radiobutton path="visualitzacio" value="fullview"
                                                cssErrorClass="form-control is-invalid" cssClass="form-control" />
                                            FullView &nbsp;&nbsp;&nbsp;
                                            <form:radiobutton path="visualitzacio" value="iframe"
                                                cssErrorClass="form-control is-invalid" cssClass="form-control" />
                                            IFrame
                                            <form:errors path="visualitzacio" cssClass="invalid-feedback" element="div" />
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td><form:label path="location">
                                            <fmt:message key="firmaForm.location" />
                                        </form:label></td>
                                    <td>
                                        <div class="input-group">

                                            <form:input cssClass="input-xxlarge" path="location" />
                                            <form:errors path="location" cssClass="invalid-feedback" element="div" />
                                        </div>
                                    </td>
                                </tr>

                            </c:if>

                            <tr>
                                <td><form:label path="profile">
                                        <fmt:message key="firmaForm.profile" />
                                    </form:label></td>
                                <td>
                                    <div class="input-group">

                                        <form:select path="profile" cssErrorClass="form-control is-invalid"
                                            cssClass="form-control">
                                            <form:option value="">-</form:option>
                                            <c:forEach items="${firmaForm.profiles}" var="profile">
                                                <form:option value="${profile.code}">${profile.name} - ${profile.description}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="profile" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td><form:label path="langUI">Idioma UI &nbsp;(*)</form:label></td>
                                <td>
                                    <div class="input-group">

                                        <form:select path="langUI" cssErrorClass="form-control is-invalid"
                                            cssClass="form-control">
                                            <form:option value="ca" selected="true">Catal&agrave;</form:option>
                                            <form:option value="es">Castellano</form:option>
                                        </form:select>
                                        <form:errors path="langUI" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td><form:label path="langDoc">Idioma Doc. &nbsp;(*)</form:label></td>
                                <td>
                                    <div class="input-group">

                                        <form:select path="langDoc" cssErrorClass="form-control is-invalid"
                                            cssClass="form-control">
                                            <form:option value="ca" selected="true">Catal&agrave;</form:option>
                                            <form:option value="es">Castellano</form:option>
                                        </form:select>
                                        <form:errors path="langDoc" cssClass="invalid-feedback" element="div" />
                                    </div>
                                </td>
                            </tr>


                            <tr>
                                <td><form:label path="fitxerAFirmarID">
                                        <fmt:message key="firmaForm.fitxerAFirmarID" />
                                    &nbsp;(*)</form:label></td>
                                <td>

                                    <div class="input-group">
                                        <form:input path="fitxerAFirmarID" type="file"
                                            cssErrorClass="form-control is-invalid" cssClass="form-control" />
                                        <form:errors path="fitxerAFirmarID" cssClass="invalid-feedback" element="div" />
                                    </div>

                                </td>
                            </tr>

                            <tr>
                                <td><form:label path="fitxerAFirmarID2">
                                        <fmt:message key="firmaForm.fitxerAFirmarID2" />
                                    </form:label></td>
                                <td>
                                    <div class="input-group">
                                        <form:input path="fitxerAFirmarID2" type="file"
                                            cssErrorClass="form-control is-invalid" cssClass="form-control" />
                                        <form:errors path="fitxerAFirmarID2" cssClass="invalid-feedback" element="div" />
                                    </div>


                                </td>
                            </tr>
                            <%--
                        <tr>
                            <td><label><fmt:message
                                        key="firmaForm.fitxerAFirmarID3" /></label></td>
                            <td> <div class="input-group"><form:errors path="fitxerAFirmarID3"
                                    cssClass="invalid-feedback" element="div" /> <form:input
                                    path="fitxerAFirmarID3" type="file" /></td>
                        </tr>
                        
                        <tr>
                            <td><label><fmt:message
                                        key="firmaForm.fitxerAFirmarID4" /></label></td>
                            <td> <div class="input-group"><form:errors path="fitxerAFirmarID4"
                                    cssClass="invalid-feedback" element="div" /> <form:input
                                    path="fitxerAFirmarID4" type="file" /></td>
                        </tr>
                        --%>

                        </tbody>
                    </table>

                    <div style="text-align: center; width: 100%">

                        <c:if test="${isFirmaWeb}">
                            <input id="submitbutton2" name="firmarviaweb" type="submit" class="btn btn-primary"
                                value="<fmt:message key="firmaweb"/>">
                        </c:if>
                        <c:if test="${not isFirmaWeb}">
                            <input id="submitbutton" name="firmarenservidor" type="submit" class="btn btn-primary"
                                value="<fmt:message key="firmaservidor"/>">
                        </c:if>
                        &nbsp;&nbsp; <a href="<c:url value="/"/>" class="btn active btn-secondary" role="button"
                            aria-pressed="true"> <fmt:message key="cancelar" /></a>
                    </div>


                </div>
                <%-- Final DIV OPCIONS --%>

            </div>

        </div>

        <form:hidden path="hostUrl" />

    </form:form>
</center>
<script>
    var hu = document.getElementById("hostUrl");
    hu.value = window.location.href;
</script>
 </jsp:body>
</tags:template>

