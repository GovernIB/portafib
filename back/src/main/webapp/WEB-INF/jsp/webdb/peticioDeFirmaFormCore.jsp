<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TITOL)}">
        <tr id="peticioDeFirma_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TITOL])?'peticioDeFirma.titol':__theForm.labels[PeticioDeFirmaFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TITOL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.titol" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TITOL)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TITOL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIO)}">
        <tr id="peticioDeFirma_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DESCRIPCIO])?'peticioDeFirma.descripcio':__theForm.labels[PeticioDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.descripcio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.descripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.MOTIU)}">
        <tr id="peticioDeFirma_motiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.MOTIU])?'peticioDeFirma.motiu':__theForm.labels[PeticioDeFirmaFields.MOTIU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.MOTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.MOTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.motiu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIU)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FITXERAFIRMARID)}">
        <tr id="peticioDeFirma_fitxerAFirmarID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FITXERAFIRMARID])?'peticioDeFirma.fitxerAFirmarID':__theForm.labels[PeticioDeFirmaFields.FITXERAFIRMARID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FITXERAFIRMARID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.FITXERAFIRMARID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.fitxerAFirmarID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(fitxerAFirmarID.fitxerAFirmarID)}"/>">${fitxerAFirmarID.fitxerAFirmarID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)? ' uneditable-input' : ''}"   path="fitxerAFirmarID" type="file" />
                  <label class="custom-file-label" for="fitxerAFirmarID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticioDeFirma.fitxerAFirmar}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.fitxerAFirmar)}"/>">${__theForm.peticioDeFirma.fitxerAFirmar.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerAFirmarIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerAFirmarID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerAFirmarID').on('change', function(){
						var ruta = $('#fitxerAFirmarID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerAFirmarID-custom-file-label').css('display','block');
						$('#fitxerAFirmarID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}">
        <tr id="peticioDeFirma_firmaOriginalDetachedID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID])?'peticioDeFirma.firmaOriginalDetachedID':__theForm.labels[PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.firmaOriginalDetachedID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(firmaOriginalDetachedID.firmaOriginalDetachedID)}"/>">${firmaOriginalDetachedID.firmaOriginalDetachedID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID)? ' uneditable-input' : ''}"   path="firmaOriginalDetachedID" type="file" />
                  <label class="custom-file-label" for="firmaOriginalDetachedID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticioDeFirma.firmaOriginalDetached}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.firmaOriginalDetached)}"/>">${__theForm.peticioDeFirma.firmaOriginalDetached.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="firmaOriginalDetachedIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="firmaOriginalDetachedID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#firmaOriginalDetachedID').on('change', function(){
						var ruta = $('#firmaOriginalDetachedID').val(); 
						var rutaArray = ruta.split('\\');
						$('#firmaOriginalDetachedID-custom-file-label').css('display','block');
						$('#firmaOriginalDetachedID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
        <tr id="peticioDeFirma_fitxerAdaptatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FITXERADAPTATID])?'peticioDeFirma.fitxerAdaptatID':__theForm.labels[PeticioDeFirmaFields.FITXERADAPTATID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FITXERADAPTATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.FITXERADAPTATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.fitxerAdaptatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(fitxerAdaptatID.fitxerAdaptatID)}"/>">${fitxerAdaptatID.fitxerAdaptatID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)? ' uneditable-input' : ''}"   path="fitxerAdaptatID" type="file" />
                  <label class="custom-file-label" for="fitxerAdaptatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticioDeFirma.fitxerAdaptat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.fitxerAdaptat)}"/>">${__theForm.peticioDeFirma.fitxerAdaptat.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerAdaptatIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerAdaptatID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerAdaptatID').on('change', function(){
						var ruta = $('#fitxerAdaptatID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerAdaptatID-custom-file-label').css('display','block');
						$('#fitxerAdaptatID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
        <tr id="peticioDeFirma_tipusDocumentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSDOCUMENTID])?'peticioDeFirma.tipusDocumentID':__theForm.labels[PeticioDeFirmaFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusDocumentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="peticioDeFirma.tipusDocumentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSDOCUMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_tipusDocumentID"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.tipusDocumentID">
            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)}">
        <tr id="peticioDeFirma_descripcioTipusDocument_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT])?'peticioDeFirma.descripcioTipusDocument':__theForm.labels[PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.descripcioTipusDocument" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.descripcioTipusDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATASOLICITUD)}">
        <tr id="peticioDeFirma_dataSolicitud_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATASOLICITUD])?'peticioDeFirma.dataSolicitud':__theForm.labels[PeticioDeFirmaFields.DATASOLICITUD]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATASOLICITUD]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.DATASOLICITUD]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataSolicitud" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="peticioDeFirma_dataSolicitud" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATASOLICITUD)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticioDeFirma_dataSolicitud" path="peticioDeFirma.dataSolicitud" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATASOLICITUD)}" >
                    <div class="input-group-append"  data-target="#peticioDeFirma_dataSolicitud"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#peticioDeFirma_dataSolicitud').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATAFINAL)}">
        <tr id="peticioDeFirma_dataFinal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATAFINAL])?'peticioDeFirma.dataFinal':__theForm.labels[PeticioDeFirmaFields.DATAFINAL]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATAFINAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.DATAFINAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataFinal" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="peticioDeFirma_dataFinal" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATAFINAL)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticioDeFirma_dataFinal" path="peticioDeFirma.dataFinal" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATAFINAL)}" >
                    <div class="input-group-append"  data-target="#peticioDeFirma_dataFinal"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#peticioDeFirma_dataFinal').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATACADUCITAT)}">
        <tr id="peticioDeFirma_dataCaducitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATACADUCITAT])?'peticioDeFirma.dataCaducitat':__theForm.labels[PeticioDeFirmaFields.DATACADUCITAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATACADUCITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.DATACADUCITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataCaducitat" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="peticioDeFirma_dataCaducitat" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATACADUCITAT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticioDeFirma_dataCaducitat" path="peticioDeFirma.dataCaducitat" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATACADUCITAT)}" >
                    <div class="input-group-append"  data-target="#peticioDeFirma_dataCaducitat"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#peticioDeFirma_dataCaducitat').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}">
        <tr id="peticioDeFirma_tipusOperacioFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSOPERACIOFIRMA])?'peticioDeFirma.tipusOperacioFirma':__theForm.labels[PeticioDeFirmaFields.TIPUSOPERACIOFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSOPERACIOFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.TIPUSOPERACIOFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusOperacioFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}" >
          <form:hidden path="peticioDeFirma.tipusOperacioFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusOperacioFirma,__theForm.listOfValuesForTipusOperacioFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSOPERACIOFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_tipusOperacioFirma"  onchange="if(typeof onChangeTipusOperacioFirma == 'function') {  onChangeTipusOperacioFirma(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.tipusOperacioFirma">
            <c:forEach items="${__theForm.listOfValuesForTipusOperacioFirma}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
        <tr id="peticioDeFirma_tipusFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSFIRMAID])?'peticioDeFirma.tipusFirmaID':__theForm.labels[PeticioDeFirmaFields.TIPUSFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.TIPUSFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSFIRMAID)}" >
          <form:hidden path="peticioDeFirma.tipusFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusFirmaID,__theForm.listOfValuesForTipusFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_tipusFirmaID"  onchange="if(typeof onChangeTipusFirmaID == 'function') {  onChangeTipusFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.tipusFirmaID">
            <c:forEach items="${__theForm.listOfValuesForTipusFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}">
        <tr id="peticioDeFirma_algorismeDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.ALGORISMEDEFIRMAID])?'peticioDeFirma.algorismeDeFirmaID':__theForm.labels[PeticioDeFirmaFields.ALGORISMEDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.ALGORISMEDEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.algorismeDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="peticioDeFirma.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.algorismeDeFirmaID,__theForm.listOfValuesForAlgorismeDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_algorismeDeFirmaID"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.algorismeDeFirmaID">
            <c:forEach items="${__theForm.listOfValuesForAlgorismeDeFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.MODEDEFIRMA)}">
        <tr id="peticioDeFirma_modeDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.MODEDEFIRMA])?'peticioDeFirma.modeDeFirma':__theForm.labels[PeticioDeFirmaFields.MODEDEFIRMA]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.MODEDEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.MODEDEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MODEDEFIRMA)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };"  path="peticioDeFirma.modeDeFirma">
                <form:option value="true" ><fmt:message key="modedefirma.true" /></form:option>
                <form:option value="false" ><fmt:message key="modedefirma.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MODEDEFIRMA)}" >
                <fmt:message key="modedefirma.${__theForm.peticioDeFirma.modeDeFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
        <tr id="peticioDeFirma_posicioTaulaFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.POSICIOTAULAFIRMESID])?'peticioDeFirma.posicioTaulaFirmesID':__theForm.labels[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.posicioTaulaFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}" >
          <form:hidden path="peticioDeFirma.posicioTaulaFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.posicioTaulaFirmesID,__theForm.listOfValuesForPosicioTaulaFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_posicioTaulaFirmesID"  onchange="if(typeof onChangePosicioTaulaFirmesID == 'function') {  onChangePosicioTaulaFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.posicioTaulaFirmesID">
            <c:forEach items="${__theForm.listOfValuesForPosicioTaulaFirmesID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <tr id="peticioDeFirma_tipusEstatPeticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID])?'peticioDeFirma.tipusEstatPeticioDeFirmaID':__theForm.labels[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusEstatPeticioDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}" >
          <form:hidden path="peticioDeFirma.tipusEstatPeticioDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusEstatPeticioDeFirmaID,__theForm.listOfValuesForTipusEstatPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_tipusEstatPeticioDeFirmaID"  onchange="if(typeof onChangeTipusEstatPeticioDeFirmaID == 'function') {  onChangeTipusEstatPeticioDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.tipusEstatPeticioDeFirmaID">
            <c:forEach items="${__theForm.listOfValuesForTipusEstatPeticioDeFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.MOTIUDEREBUIG)}">
        <tr id="peticioDeFirma_motiuDeRebuig_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.MOTIUDEREBUIG])?'peticioDeFirma.motiuDeRebuig':__theForm.labels[PeticioDeFirmaFields.MOTIUDEREBUIG]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.MOTIUDEREBUIG]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.MOTIUDEREBUIG]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.motiuDeRebuig" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIUDEREBUIG)? 'true' : 'false'}" path="peticioDeFirma.motiuDeRebuig"  />
      <div id="dropdownMenuButton_motiuDeRebuig" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_motiuDeRebuig" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_motiuDeRebuig').on('click', function(){
					var valor = ($('#dropdownMenuContainer_motiuDeRebuig').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_motiuDeRebuig').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.IDIOMAID)}">
        <tr id="peticioDeFirma_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.IDIOMAID])?'peticioDeFirma.idiomaID':__theForm.labels[PeticioDeFirmaFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.IDIOMAID)}" >
          <form:hidden path="peticioDeFirma.idiomaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.PRIORITATID)}">
        <tr id="peticioDeFirma_prioritatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.PRIORITATID])?'peticioDeFirma.prioritatID':__theForm.labels[PeticioDeFirmaFields.PRIORITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.PRIORITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.PRIORITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.prioritatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PRIORITATID)}" >
          <form:hidden path="peticioDeFirma.prioritatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.prioritatID,__theForm.listOfValuesForPrioritatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PRIORITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_prioritatID"  onchange="if(typeof onChangePrioritatID == 'function') {  onChangePrioritatID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.prioritatID">
            <c:forEach items="${__theForm.listOfValuesForPrioritatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FLUXDEFIRMESID)}">
        <tr id="peticioDeFirma_fluxDeFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FLUXDEFIRMESID])?'peticioDeFirma.fluxDeFirmesID':__theForm.labels[PeticioDeFirmaFields.FLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.fluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FLUXDEFIRMESID)}" >
          <form:hidden path="peticioDeFirma.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.fluxDeFirmesID">
            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}">
        <tr id="peticioDeFirma_solicitantUsuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID])?'peticioDeFirma.solicitantUsuariAplicacioID':__theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.solicitantUsuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}" >
          <form:hidden path="peticioDeFirma.solicitantUsuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.solicitantUsuariAplicacioID,__theForm.listOfUsuariAplicacioForSolicitantUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_solicitantUsuariAplicacioID"  onchange="if(typeof onChangeSolicitantUsuariAplicacioID == 'function') {  onChangeSolicitantUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.solicitantUsuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForSolicitantUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.REMITENTNOM)}">
        <tr id="peticioDeFirma_remitentNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.REMITENTNOM])?'peticioDeFirma.remitentNom':__theForm.labels[PeticioDeFirmaFields.REMITENTNOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.REMITENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.REMITENTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.remitentNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTNOM)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="peticioDeFirma.remitentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
        <tr id="peticioDeFirma_remitentDescripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.REMITENTDESCRIPCIO])?'peticioDeFirma.remitentDescripcio':__theForm.labels[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.remitentDescripcio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTDESCRIPCIO)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTDESCRIPCIO)? ' uneditable-input' : ''}"  style="" maxlength="500" path="peticioDeFirma.remitentDescripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTCODI)}">
        <tr id="peticioDeFirma_expedientCodi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.EXPEDIENTCODI])?'peticioDeFirma.expedientCodi':__theForm.labels[PeticioDeFirmaFields.EXPEDIENTCODI]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.EXPEDIENTCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.EXPEDIENTCODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.expedientCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTCODI)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTCODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.expedientCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTNOM)}">
        <tr id="peticioDeFirma_expedientNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.EXPEDIENTNOM])?'peticioDeFirma.expedientNom':__theForm.labels[PeticioDeFirmaFields.EXPEDIENTNOM]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.EXPEDIENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.EXPEDIENTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.expedientNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTNOM)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.expedientNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.EXPEDIENTURL)}">
        <tr id="peticioDeFirma_expedientUrl_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.EXPEDIENTURL])?'peticioDeFirma.expedientUrl':__theForm.labels[PeticioDeFirmaFields.EXPEDIENTURL]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.EXPEDIENTURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.EXPEDIENTURL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.expedientUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTURL)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.EXPEDIENTURL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.expedientUrl"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTCODI)}">
        <tr id="peticioDeFirma_procedimentCodi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.PROCEDIMENTCODI])?'peticioDeFirma.procedimentCodi':__theForm.labels[PeticioDeFirmaFields.PROCEDIMENTCODI]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.PROCEDIMENTCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.PROCEDIMENTCODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.procedimentCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PROCEDIMENTCODI)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PROCEDIMENTCODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.procedimentCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.PROCEDIMENTNOM)}">
        <tr id="peticioDeFirma_procedimentNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.PROCEDIMENTNOM])?'peticioDeFirma.procedimentNom':__theForm.labels[PeticioDeFirmaFields.PROCEDIMENTNOM]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.PROCEDIMENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.PROCEDIMENTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.procedimentNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PROCEDIMENTNOM)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PROCEDIMENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticioDeFirma.procedimentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONAL)}">
        <tr id="peticioDeFirma_informacioAddicional_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.INFORMACIOADDICIONAL])?'peticioDeFirma.informacioAddicional':__theForm.labels[PeticioDeFirmaFields.INFORMACIOADDICIONAL]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.INFORMACIOADDICIONAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.INFORMACIOADDICIONAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.informacioAddicional" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.INFORMACIOADDICIONAL)? 'true' : 'false'}" path="peticioDeFirma.informacioAddicional"  />
      <div id="dropdownMenuButton_informacioAddicional" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_informacioAddicional" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAddicional'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAddicional'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAddicional'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_informacioAddicional').on('click', function(){
					var valor = ($('#dropdownMenuContainer_informacioAddicional').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_informacioAddicional').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)}">
        <tr id="peticioDeFirma_informacioAddicionalAvaluable_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE])?'peticioDeFirma.informacioAddicionalAvaluable':__theForm.labels[PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.informacioAddicionalAvaluable" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE)? ' uneditable-input' : ''}"  style=""  path="peticioDeFirma.informacioAddicionalAvaluable"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
        <tr id="peticioDeFirma_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.LOGOSEGELLID])?'peticioDeFirma.logoSegellID':__theForm.labels[PeticioDeFirmaFields.LOGOSEGELLID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.LOGOSEGELLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.logoSegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoSegellID.logoSegellID)}"/>">${logoSegellID.logoSegellID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)? ' uneditable-input' : ''}"   path="logoSegellID" type="file" />
                  <label class="custom-file-label" for="logoSegellID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticioDeFirma.logoSegell}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.logoSegell)}"/>">${__theForm.peticioDeFirma.logoSegell.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="logoSegellIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoSegellID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoSegellID').on('change', function(){
						var ruta = $('#logoSegellID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoSegellID-custom-file-label').css('display','block');
						$('#logoSegellID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
        <tr id="peticioDeFirma_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.CUSTODIAINFOID])?'peticioDeFirma.custodiaInfoID':__theForm.labels[PeticioDeFirmaFields.CUSTODIAINFOID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.CUSTODIAINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.CUSTODIAINFOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.custodiaInfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CUSTODIAINFOID)}" >
          <form:hidden path="peticioDeFirma.custodiaInfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CUSTODIAINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.custodiaInfoID">
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticioDeFirma.custodiaInfoID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticioDeFirma.custodiaInfoID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}">
        <tr id="peticioDeFirma_solicitantUsuariEntitat1ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID])?'peticioDeFirma.solicitantUsuariEntitat1ID':__theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.solicitantUsuariEntitat1ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}" >
          <form:hidden path="peticioDeFirma.solicitantUsuariEntitat1ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.solicitantUsuariEntitat1ID,__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat1ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_solicitantUsuariEntitat1ID"  onchange="if(typeof onChangeSolicitantUsuariEntitat1ID == 'function') {  onChangeSolicitantUsuariEntitat1ID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.solicitantUsuariEntitat1ID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat1ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticioDeFirma.solicitantUsuariEntitat1ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticioDeFirma.solicitantUsuariEntitat1ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}">
        <tr id="peticioDeFirma_solicitantUsuariEntitat2ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID])?'peticioDeFirma.solicitantUsuariEntitat2ID':__theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.solicitantUsuariEntitat2ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}" >
          <form:hidden path="peticioDeFirma.solicitantUsuariEntitat2ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.solicitantUsuariEntitat2ID,__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat2ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT2ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_solicitantUsuariEntitat2ID"  onchange="if(typeof onChangeSolicitantUsuariEntitat2ID == 'function') {  onChangeSolicitantUsuariEntitat2ID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.solicitantUsuariEntitat2ID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat2ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticioDeFirma.solicitantUsuariEntitat2ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticioDeFirma.solicitantUsuariEntitat2ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}">
        <tr id="peticioDeFirma_solicitantUsuariEntitat3ID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID])?'peticioDeFirma.solicitantUsuariEntitat3ID':__theForm.labels[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.solicitantUsuariEntitat3ID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}" >
          <form:hidden path="peticioDeFirma.solicitantUsuariEntitat3ID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.solicitantUsuariEntitat3ID,__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat3ID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT3ID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_solicitantUsuariEntitat3ID"  onchange="if(typeof onChangeSolicitantUsuariEntitat3ID == 'function') {  onChangeSolicitantUsuariEntitat3ID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.solicitantUsuariEntitat3ID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForSolicitantUsuariEntitat3ID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticioDeFirma.solicitantUsuariEntitat3ID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticioDeFirma.solicitantUsuariEntitat3ID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.AVISWEB)}">
        <tr id="peticioDeFirma_avisWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.AVISWEB])?'peticioDeFirma.avisWeb':__theForm.labels[PeticioDeFirmaFields.AVISWEB]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.AVISWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.AVISWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.AVISWEB)}" >
              <form:errors path="peticioDeFirma.avisWeb" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.AVISWEB)? 'false' : 'true'}" path="peticioDeFirma.avisWeb" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.AVISWEB)}" >
                <fmt:message key="genapp.checkbox.${__theForm.peticioDeFirma.avisWeb}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.SEGELLATDETEMPS)}">
        <tr id="peticioDeFirma_segellatDeTemps_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.SEGELLATDETEMPS])?'peticioDeFirma.segellatDeTemps':__theForm.labels[PeticioDeFirmaFields.SEGELLATDETEMPS]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.SEGELLATDETEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.SEGELLATDETEMPS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)}" >
              <form:errors path="peticioDeFirma.segellatDeTemps" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)? 'false' : 'true'}" path="peticioDeFirma.segellatDeTemps" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.peticioDeFirma.segellatDeTemps}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}">
        <tr id="peticioDeFirma_origenPeticioDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.ORIGENPETICIODEFIRMA])?'peticioDeFirma.origenPeticioDeFirma':__theForm.labels[PeticioDeFirmaFields.ORIGENPETICIODEFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.ORIGENPETICIODEFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.ORIGENPETICIODEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.origenPeticioDeFirma" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}" >
          <form:hidden path="peticioDeFirma.origenPeticioDeFirma"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.origenPeticioDeFirma,__theForm.listOfValuesForOrigenPeticioDeFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ORIGENPETICIODEFIRMA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_origenPeticioDeFirma"  onchange="if(typeof onChangeOrigenPeticioDeFirma == 'function') {  onChangeOrigenPeticioDeFirma(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.origenPeticioDeFirma">
            <c:forEach items="${__theForm.listOfValuesForOrigenPeticioDeFirma}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}">
        <tr id="peticioDeFirma_configuracioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.CONFIGURACIODEFIRMAID])?'peticioDeFirma.configuracioDeFirmaID':__theForm.labels[PeticioDeFirmaFields.CONFIGURACIODEFIRMAID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.CONFIGURACIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioDeFirmaFields.CONFIGURACIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.configuracioDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}" >
          <form:hidden path="peticioDeFirma.configuracioDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.configuracioDeFirmaID,__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CONFIGURACIODEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticioDeFirma_configuracioDeFirmaID"  onchange="if(typeof onChangeConfiguracioDeFirmaID == 'function') {  onChangeConfiguracioDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="peticioDeFirma.configuracioDeFirmaID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticioDeFirma.configuracioDeFirmaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticioDeFirma.configuracioDeFirmaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
