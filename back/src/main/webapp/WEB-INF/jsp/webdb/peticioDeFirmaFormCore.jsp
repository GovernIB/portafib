<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaFields" className="es.caib.portafib.model.fields.PeticioDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TITOL)}">
        <tr id="peticioDeFirma_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TITOL])?'peticioDeFirma.titol':__theForm.labels[PeticioDeFirmaFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TITOL]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.titol" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TITOL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TITOL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="peticioDeFirma.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DESCRIPCIO)}">
        <tr id="peticioDeFirma_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DESCRIPCIO])?'peticioDeFirma.descripcio':__theForm.labels[PeticioDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.descripcio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="peticioDeFirma.descripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.MOTIU)}">
        <tr id="peticioDeFirma_motiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.MOTIU])?'peticioDeFirma.motiu':__theForm.labels[PeticioDeFirmaFields.MOTIU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.MOTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.MOTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.motiu" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIU)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIU)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="peticioDeFirma.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FITXERAFIRMARID)}">
        <tr id="peticioDeFirma_fitxerAFirmarID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FITXERAFIRMARID])?'peticioDeFirma.fitxerAFirmarID':__theForm.labels[PeticioDeFirmaFields.FITXERAFIRMARID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FITXERAFIRMARID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.FITXERAFIRMARID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.fitxerAFirmarID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)? 'input uneditable-input' : 'input'}"  path="fitxerAFirmarID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.peticioDeFirma.fitxerAFirmar}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERAFIRMARID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerAFirmarIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.fitxerAFirmar)}"/>">${__theForm.peticioDeFirma.fitxerAFirmar.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.FITXERADAPTATID)}">
        <tr id="peticioDeFirma_fitxerAdaptatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.FITXERADAPTATID])?'peticioDeFirma.fitxerAdaptatID':__theForm.labels[PeticioDeFirmaFields.FITXERADAPTATID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.FITXERADAPTATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.FITXERADAPTATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.fitxerAdaptatID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)? 'input uneditable-input' : 'input'}"  path="fitxerAdaptatID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.peticioDeFirma.fitxerAdaptat}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FITXERADAPTATID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerAdaptatIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.fitxerAdaptat)}"/>">${__theForm.peticioDeFirma.fitxerAdaptat.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSDOCUMENTID)}">
        <tr id="peticioDeFirma_tipusDocumentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSDOCUMENTID])?'peticioDeFirma.tipusDocumentID':__theForm.labels[PeticioDeFirmaFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSDOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusDocumentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="peticioDeFirma.tipusDocumentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSDOCUMENTID)}" >
          <form:select id="peticioDeFirma_tipusDocumentID"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.tipusDocumentID">
            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.descripcioTipusDocument" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DESCRIPCIOTIPUSDOCUMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="peticioDeFirma.descripcioTipusDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}">
        <tr id="peticioDeFirma_posicioTaulaFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.POSICIOTAULAFIRMESID])?'peticioDeFirma.posicioTaulaFirmesID':__theForm.labels[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.posicioTaulaFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}" >
          <form:hidden path="peticioDeFirma.posicioTaulaFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.posicioTaulaFirmesID,__theForm.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.POSICIOTAULAFIRMESID)}" >
          <form:select id="peticioDeFirma_posicioTaulaFirmesID"  onchange="if(typeof onChangePosicioTaulaFirmesID == 'function') {  onChangePosicioTaulaFirmesID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.posicioTaulaFirmesID">
            <c:forEach items="${__theForm.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATASOLICITUD)}">
        <tr id="peticioDeFirma_dataSolicitud_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATASOLICITUD])?'peticioDeFirma.dataSolicitud':__theForm.labels[PeticioDeFirmaFields.DATASOLICITUD]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATASOLICITUD]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.DATASOLICITUD]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataSolicitud" cssClass="errorField alert alert-error" />
              <div id="dataSolicitud" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATASOLICITUD)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATASOLICITUD)? 'input-medium uneditable-input' : 'input-medium'}"  path="peticioDeFirma.dataSolicitud" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATASOLICITUD)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataSolicitud').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATAFINAL)}">
        <tr id="peticioDeFirma_dataFinal_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATAFINAL])?'peticioDeFirma.dataFinal':__theForm.labels[PeticioDeFirmaFields.DATAFINAL]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATAFINAL]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.DATAFINAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataFinal" cssClass="errorField alert alert-error" />
              <div id="dataFinal" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATAFINAL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATAFINAL)? 'input-medium uneditable-input' : 'input-medium'}"  path="peticioDeFirma.dataFinal" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATAFINAL)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFinal').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.DATACADUCITAT)}">
        <tr id="peticioDeFirma_dataCaducitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.DATACADUCITAT])?'peticioDeFirma.dataCaducitat':__theForm.labels[PeticioDeFirmaFields.DATACADUCITAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.DATACADUCITAT]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.DATACADUCITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.dataCaducitat" cssClass="errorField alert alert-error" />
              <div id="dataCaducitat" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATACADUCITAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATACADUCITAT)? 'input-medium uneditable-input' : 'input-medium'}"  path="peticioDeFirma.dataCaducitat" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.DATACADUCITAT)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCaducitat').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSFIRMAID)}">
        <tr id="peticioDeFirma_tipusFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSFIRMAID])?'peticioDeFirma.tipusFirmaID':__theForm.labels[PeticioDeFirmaFields.TIPUSFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.TIPUSFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSFIRMAID)}" >
          <form:hidden path="peticioDeFirma.tipusFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusFirmaID,__theForm.listOfTipusFirmaForTipusFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSFIRMAID)}" >
          <form:select id="peticioDeFirma_tipusFirmaID"  onchange="if(typeof onChangeTipusFirmaID == 'function') {  onChangeTipusFirmaID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.tipusFirmaID">
            <c:forEach items="${__theForm.listOfTipusFirmaForTipusFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.algorismeDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="peticioDeFirma.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.algorismeDeFirmaID,__theForm.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.ALGORISMEDEFIRMAID)}" >
          <form:select id="peticioDeFirma_algorismeDeFirmaID"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.algorismeDeFirmaID">
            <c:forEach items="${__theForm.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.MODEDEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MODEDEFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };"  path="peticioDeFirma.modeDeFirma">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <tr id="peticioDeFirma_tipusEstatPeticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID])?'peticioDeFirma.tipusEstatPeticioDeFirmaID':__theForm.labels[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.tipusEstatPeticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}" >
          <form:hidden path="peticioDeFirma.tipusEstatPeticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.tipusEstatPeticioDeFirmaID,__theForm.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}" >
          <form:select id="peticioDeFirma_tipusEstatPeticioDeFirmaID"  onchange="if(typeof onChangeTipusEstatPeticioDeFirmaID == 'function') {  onChangeTipusEstatPeticioDeFirmaID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.tipusEstatPeticioDeFirmaID">
            <c:forEach items="${__theForm.listOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.MOTIUDEREBUIG]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.motiuDeRebuig" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.MOTIUDEREBUIG)? 'true' : 'false'}" path="peticioDeFirma.motiuDeRebuig"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.motiuDeRebuig'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.IDIOMAID)}">
        <tr id="peticioDeFirma_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.IDIOMAID])?'peticioDeFirma.idiomaID':__theForm.labels[PeticioDeFirmaFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.IDIOMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.idiomaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.IDIOMAID)}" >
          <form:hidden path="peticioDeFirma.idiomaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.IDIOMAID)}" >
          <form:select id="peticioDeFirma_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.PRIORITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.prioritatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PRIORITATID)}" >
          <form:hidden path="peticioDeFirma.prioritatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.prioritatID,__theForm.listOfPrioritatForPrioritatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.PRIORITATID)}" >
          <form:select id="peticioDeFirma_prioritatID"  onchange="if(typeof onChangePrioritatID == 'function') {  onChangePrioritatID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.prioritatID">
            <c:forEach items="${__theForm.listOfPrioritatForPrioritatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.fluxDeFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FLUXDEFIRMESID)}" >
          <form:hidden path="peticioDeFirma.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.FLUXDEFIRMESID)}" >
          <form:select id="peticioDeFirma_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.fluxDeFirmesID">
            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.USUARIAPLICACIOID)}">
        <tr id="peticioDeFirma_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.USUARIAPLICACIOID])?'peticioDeFirma.usuariAplicacioID':__theForm.labels[PeticioDeFirmaFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.USUARIAPLICACIOID)}" >
          <form:hidden path="peticioDeFirma.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.USUARIAPLICACIOID)}" >
          <form:select id="peticioDeFirma_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.REMITENTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.remitentNom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTNOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTNOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="peticioDeFirma.remitentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.REMITENTDESCRIPCIO)}">
        <tr id="peticioDeFirma_remitentDescripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.REMITENTDESCRIPCIO])?'peticioDeFirma.remitentDescripcio':__theForm.labels[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.REMITENTDESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="peticioDeFirma.remitentDescripcio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTDESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.REMITENTDESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="500" path="peticioDeFirma.remitentDescripcio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.INFORMACIOADICIONAL)}">
        <tr id="peticioDeFirma_informacioAdicional_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.INFORMACIOADICIONAL])?'peticioDeFirma.informacioAdicional':__theForm.labels[PeticioDeFirmaFields.INFORMACIOADICIONAL]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.INFORMACIOADICIONAL]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.INFORMACIOADICIONAL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.informacioAdicional" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.INFORMACIOADICIONAL)? 'true' : 'false'}" path="peticioDeFirma.informacioAdicional"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAdicional'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAdicional'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('peticioDeFirma.informacioAdicional'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.LOGOSEGELLID)}">
        <tr id="peticioDeFirma_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.LOGOSEGELLID])?'peticioDeFirma.logoSegellID':__theForm.labels[PeticioDeFirmaFields.LOGOSEGELLID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.LOGOSEGELLID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.LOGOSEGELLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="peticioDeFirma.logoSegellID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)? 'input uneditable-input' : 'input'}"  path="logoSegellID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.peticioDeFirma.logoSegell}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.LOGOSEGELLID)}" >
                    <span class="add-on">
                        <form:checkbox path="logoSegellIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.peticioDeFirma.logoSegell)}"/>">${__theForm.peticioDeFirma.logoSegell.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.CUSTODIAINFOID)}">
        <tr id="peticioDeFirma_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.CUSTODIAINFOID])?'peticioDeFirma.custodiaInfoID':__theForm.labels[PeticioDeFirmaFields.CUSTODIAINFOID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.CUSTODIAINFOID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.CUSTODIAINFOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.custodiaInfoID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CUSTODIAINFOID)}" >
          <form:hidden path="peticioDeFirma.custodiaInfoID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.CUSTODIAINFOID)}" >
          <form:select id="peticioDeFirma_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.custodiaInfoID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioDeFirmaFields.USUARIENTITATID)}">
        <tr id="peticioDeFirma_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioDeFirmaFields.USUARIENTITATID])?'peticioDeFirma.usuariEntitatID':__theForm.labels[PeticioDeFirmaFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[PeticioDeFirmaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="peticioDeFirma.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="peticioDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.peticioDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.USUARIENTITATID)}" >
          <form:select id="peticioDeFirma_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="peticioDeFirma.usuariEntitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.AVISWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.AVISWEB)}" >
              <form:errors path="peticioDeFirma.avisWeb" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.AVISWEB)? 'false' : 'true'}" path="peticioDeFirma.avisWeb" />
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
              <i class="icon-info-sign" title="${__theForm.help[PeticioDeFirmaFields.SEGELLATDETEMPS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)}" >
              <form:errors path="peticioDeFirma.segellatDeTemps" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)? 'false' : 'true'}" path="peticioDeFirma.segellatDeTemps" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioDeFirmaFields.SEGELLATDETEMPS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.peticioDeFirma.segellatDeTemps}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
