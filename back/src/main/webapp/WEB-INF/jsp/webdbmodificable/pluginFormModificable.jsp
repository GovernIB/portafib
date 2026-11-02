<c:if test="${not empty availablepluginproperties}">

    <script type="text/javascript">
                    function mostrarPropietatsDisponibles() {
                        $('#propietatsDisponiblesModal').modal('show');
                    }
                </script>


    <div class="modal fade" id="propietatsDisponiblesModal" tabindex="-1" role="dialog" aria-labelledby="propietatsDisponiblesLabel"
        aria-hidden="true" style="top: 25%;">
        <div class="modal-dialog" role="document" style="margin: auto; max-width: 750px;">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="propietatsDisponiblesLabel">Propietats disponibles</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="">
                    <div class="row" style="scrollbar-width: thin; max-height: 400px; overflow-y: auto;">
                        <c:forEach items="${availablepluginproperties}" var="prop">
                            <c:if test="${not empty prop.key}">
                                <div class="col-md-12 mb-3">
                                    <div class="card" style="border: 1px solid #dee2e6; border-radius: 4px;">
                                        <div class="card-body" style="padding: 0.4rem;">
                                            <h5 class="card-title">
                                                <b>${prop.key}</b>
                                            </h5>
                                            <p class="card-text">
                                                <strong>Descripci&oacute;:</strong> ${prop.description}
                                            </p>
                                            <p class="card-text">
                                                <strong>Tipus:</strong> ${empty prop.type ? 'N/A' : prop.type}
                                            </p>
                                            <p class="card-text">
                                                <strong>Obligatori:</strong> ${prop.required ? 'Si' : 'No'}
                                            </p>
                                            <c:if test="${not empty prop.defaultValue}">
                                                <p class="card-text">
                                                    <strong>Valor per defecte:</strong> ${prop.defaultValue}
                                                </p>
                                            </c:if>
                                            <c:if test="${not empty prop.listOfAvailableValues}">
                                                <p class="card-text">
                                                    <strong>Valors disponibles:</strong><br />
                                                <ul>
                                                    <c:forEach items="${prop.listOfAvailableValues}" var="entry">
                                                        <li>${entry.key}
                                                        <c:if test="${not empty entry.value}">
                                                        (<i>${entry.value}</i>), 
                                                        </c:if>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                                </p>
                                            </c:if>
                                            <c:if test="${not empty prop.examples}">
                                                <p class="card-text">
                                                    <strong>Exemples:</strong>
                                                <ul>
                                                    <c:forEach items="${prop.examples}" var="entry">
                                                        <li>${entry.key}
                                                        <c:if test="${not empty entry.value}">
                                                        -> <i>${entry.value}</i>
                                                        </c:if>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                                </p>
                                            </c:if>
                                            <c:if test="${not empty prop.valuePattern}">
                                                <p class="card-text">
                                                    <strong>Patr&oacute; de valor:</strong> ${prop.valuePattern}
                                                </p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>


</c:if>