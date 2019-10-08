<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FirmaFields" className="es.caib.portafib.model.fields.FirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.DESTINATARIID)}">
        <tr id="firma_destinatariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.DESTINATARIID])?'firma.destinatariID':__theForm.labels[FirmaFields.DESTINATARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FirmaFields.DESTINATARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.DESTINATARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="firma.destinatariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.DESTINATARIID)}" >
          <form:hidden path="firma.destinatariID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.firma.destinatariID,__theForm.listOfUsuariEntitatForDestinatariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.DESTINATARIID)}" >
          <form:select id="firma_destinatariID"  onchange="if(typeof onChangeDestinatariID == 'function') {  onChangeDestinatariID(this); };"  cssClass="input-xxlarge" path="firma.destinatariID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForDestinatariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.BLOCDEFIRMAID)}">
        <tr id="firma_blocDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.BLOCDEFIRMAID])?'firma.blocDeFirmaID':__theForm.labels[FirmaFields.BLOCDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FirmaFields.BLOCDEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.BLOCDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="firma.blocDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.BLOCDEFIRMAID)}" >
          <form:hidden path="firma.blocDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.firma.blocDeFirmaID,__theForm.listOfBlocDeFirmesForBlocDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.BLOCDEFIRMAID)}" >
          <form:select id="firma_blocDeFirmaID"  onchange="if(typeof onChangeBlocDeFirmaID == 'function') {  onChangeBlocDeFirmaID(this); };"  cssClass="input-xxlarge" path="firma.blocDeFirmaID">
            <c:forEach items="${__theForm.listOfBlocDeFirmesForBlocDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.OBLIGATORI)}">
        <tr id="firma_obligatori_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.OBLIGATORI])?'firma.obligatori':__theForm.labels[FirmaFields.OBLIGATORI]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.OBLIGATORI]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.OBLIGATORI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)}" >
              <form:errors path="firma.obligatori" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)? 'false' : 'true'}" path="firma.obligatori" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.OBLIGATORI)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.obligatori}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.FITXERFIRMATID)}">
        <tr id="firma_fitxerFirmatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.FITXERFIRMATID])?'firma.fitxerFirmatID':__theForm.labels[FirmaFields.FITXERFIRMATID]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.FITXERFIRMATID]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.FITXERFIRMATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="firma.fitxerFirmatID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)? 'input uneditable-input' : 'input'}"  path="fitxerFirmatID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.firma.fitxerFirmat}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.FITXERFIRMATID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerFirmatIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.firma.fitxerFirmat)}"/>">${__theForm.firma.fitxerFirmat.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NUMFIRMADOCUMENT)}">
        <tr id="firma_numFirmaDocument_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NUMFIRMADOCUMENT])?'firma.numFirmaDocument':__theForm.labels[FirmaFields.NUMFIRMADOCUMENT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.NUMFIRMADOCUMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.NUMFIRMADOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.numFirmaDocument" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMFIRMADOCUMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMFIRMADOCUMENT)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.numFirmaDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAPAGINA)}">
        <tr id="firma_caixaPagina_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAPAGINA])?'firma.caixaPagina':__theForm.labels[FirmaFields.CAIXAPAGINA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAPAGINA]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CAIXAPAGINA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.caixaPagina" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAPAGINA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAPAGINA)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.caixaPagina"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAX)}">
        <tr id="firma_caixaX_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAX])?'firma.caixaX':__theForm.labels[FirmaFields.CAIXAX]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAX]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CAIXAX]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.caixaX" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAX)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAX)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.caixaX"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAY)}">
        <tr id="firma_caixaY_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAY])?'firma.caixaY':__theForm.labels[FirmaFields.CAIXAY]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAY]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CAIXAY]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.caixaY" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAY)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAY)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.caixaY"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAAMPLE)}">
        <tr id="firma_caixaAmple_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAAMPLE])?'firma.caixaAmple':__theForm.labels[FirmaFields.CAIXAAMPLE]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAAMPLE]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CAIXAAMPLE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.caixaAmple" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAAMPLE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAAMPLE)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.caixaAmple"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CAIXAALT)}">
        <tr id="firma_caixaAlt_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CAIXAALT])?'firma.caixaAlt':__theForm.labels[FirmaFields.CAIXAALT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CAIXAALT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CAIXAALT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.caixaAlt" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAALT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CAIXAALT)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.caixaAlt"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NUMEROSERIECERTIFICAT)}">
        <tr id="firma_numeroSerieCertificat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NUMEROSERIECERTIFICAT])?'firma.numeroSerieCertificat':__theForm.labels[FirmaFields.NUMEROSERIECERTIFICAT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.NUMEROSERIECERTIFICAT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.NUMEROSERIECERTIFICAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.numeroSerieCertificat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMEROSERIECERTIFICAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.NUMEROSERIECERTIFICAT)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.numeroSerieCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.EMISSORCERTIFICAT)}">
        <tr id="firma_emissorCertificat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.EMISSORCERTIFICAT])?'firma.emissorCertificat':__theForm.labels[FirmaFields.EMISSORCERTIFICAT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.EMISSORCERTIFICAT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.EMISSORCERTIFICAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.emissorCertificat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.EMISSORCERTIFICAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.EMISSORCERTIFICAT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="1000" path="firma.emissorCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.NOMCERTIFICAT)}">
        <tr id="firma_nomCertificat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.NOMCERTIFICAT])?'firma.nomCertificat':__theForm.labels[FirmaFields.NOMCERTIFICAT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.NOMCERTIFICAT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.NOMCERTIFICAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.nomCertificat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.NOMCERTIFICAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.NOMCERTIFICAT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="1000" path="firma.nomCertificat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <tr id="firma_tipusEstatDeFirmaFinalID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.TIPUSESTATDEFIRMAFINALID])?'firma.tipusEstatDeFirmaFinalID':__theForm.labels[FirmaFields.TIPUSESTATDEFIRMAFINALID]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="firma.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:hidden path="firma.tipusEstatDeFirmaFinalID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.firma.tipusEstatDeFirmaFinalID,__theForm.listOfValuesForTipusEstatDeFirmaFinalID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:select id="firma_tipusEstatDeFirmaFinalID"  onchange="if(typeof onChangeTipusEstatDeFirmaFinalID == 'function') {  onChangeTipusEstatDeFirmaFinalID(this); };"  cssClass="input-xxlarge" path="firma.tipusEstatDeFirmaFinalID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaFinalID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MOSTRARRUBRICA)}">
        <tr id="firma_mostrarRubrica_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MOSTRARRUBRICA])?'firma.mostrarRubrica':__theForm.labels[FirmaFields.MOSTRARRUBRICA]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.MOSTRARRUBRICA]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.MOSTRARRUBRICA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)}" >
              <form:errors path="firma.mostrarRubrica" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)? 'false' : 'true'}" path="firma.mostrarRubrica" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.MOSTRARRUBRICA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.mostrarRubrica}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MOTIU)}">
        <tr id="firma_motiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MOTIU])?'firma.motiu':__theForm.labels[FirmaFields.MOTIU]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.MOTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.MOTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.motiu" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MOTIU)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.MOTIU)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="firma.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.MINIMDEREVISORS)}">
        <tr id="firma_minimDeRevisors_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.MINIMDEREVISORS])?'firma.minimDeRevisors':__theForm.labels[FirmaFields.MINIMDEREVISORS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FirmaFields.MINIMDEREVISORS]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.MINIMDEREVISORS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.minimDeRevisors" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.MINIMDEREVISORS)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.MINIMDEREVISORS)? 'input-mini uneditable-input' : 'input-mini'}"   path="firma.minimDeRevisors"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <tr id="firma_checkAdministrationIdOfSigner_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER])?'firma.checkAdministrationIdOfSigner':__theForm.labels[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CHECKADMINISTRATIONIDOFSIGNER]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCheckAdministrationIdOfSigner == 'function') {  onChangeCheckAdministrationIdOfSigner(this); };"  path="firma.checkAdministrationIdOfSigner">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkAdministrationIdOfSigner}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <tr id="firma_checkDocumentModifications_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKDOCUMENTMODIFICATIONS])?'firma.checkDocumentModifications':__theForm.labels[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CHECKDOCUMENTMODIFICATIONS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCheckDocumentModifications == 'function') {  onChangeCheckDocumentModifications(this); };"  path="firma.checkDocumentModifications">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKDOCUMENTMODIFICATIONS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkDocumentModifications}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.CHECKVALIDATIONSIGNATURE)}">
        <tr id="firma_checkValidationSignature_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.CHECKVALIDATIONSIGNATURE])?'firma.checkValidationSignature':__theForm.labels[FirmaFields.CHECKVALIDATIONSIGNATURE]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.CHECKVALIDATIONSIGNATURE]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.CHECKVALIDATIONSIGNATURE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKVALIDATIONSIGNATURE)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCheckValidationSignature == 'function') {  onChangeCheckValidationSignature(this); };"  path="firma.checkValidationSignature">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.CHECKVALIDATIONSIGNATURE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.firma.checkValidationSignature}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.PERFILDEFIRMA)}">
        <tr id="firma_perfilDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.PERFILDEFIRMA])?'firma.perfilDeFirma':__theForm.labels[FirmaFields.PERFILDEFIRMA]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.PERFILDEFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.PERFILDEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.perfilDeFirma" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.PERFILDEFIRMA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.PERFILDEFIRMA)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="firma.perfilDeFirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNNOM)}">
        <tr id="firma_usuariExternNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNNOM])?'firma.usuariExternNom':__theForm.labels[FirmaFields.USUARIEXTERNNOM]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNNOM]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.usuariExternNom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="firma.usuariExternNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNLLINATGES)}">
        <tr id="firma_usuariExternLlinatges_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNLLINATGES])?'firma.usuariExternLlinatges':__theForm.labels[FirmaFields.USUARIEXTERNLLINATGES]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNLLINATGES]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNLLINATGES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.usuariExternLlinatges" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNLLINATGES)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNLLINATGES)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="firma.usuariExternLlinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNEMAIL)}">
        <tr id="firma_usuariExternEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNEMAIL])?'firma.usuariExternEmail':__theForm.labels[FirmaFields.USUARIEXTERNEMAIL]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNEMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.usuariExternEmail" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNEMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNEMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="firma.usuariExternEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNIDIOMA)}">
        <tr id="firma_usuariExternIdioma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNIDIOMA])?'firma.usuariExternIdioma':__theForm.labels[FirmaFields.USUARIEXTERNIDIOMA]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNIDIOMA]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNIDIOMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.usuariExternIdioma" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNIDIOMA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNIDIOMA)? 'input-mini uneditable-input' : 'input-mini'}"  maxlength="2" path="firma.usuariExternIdioma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNTOKEN)}">
        <tr id="firma_usuariExternToken_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNTOKEN])?'firma.usuariExternToken':__theForm.labels[FirmaFields.USUARIEXTERNTOKEN]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNTOKEN]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNTOKEN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="firma.usuariExternToken" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNTOKEN)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNTOKEN)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="firma.usuariExternToken"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}">
        <tr id="firma_usuariExternNivellSeguretat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FirmaFields.USUARIEXTERNNIVELLSEGURETAT])?'firma.usuariExternNivellSeguretat':__theForm.labels[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}" />
              <c:if test="${not empty __theForm.help[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}">
              <i class="icon-info-sign" title="${__theForm.help[FirmaFields.USUARIEXTERNNIVELLSEGURETAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="firma.usuariExternNivellSeguretat" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}" >
          <form:hidden path="firma.usuariExternNivellSeguretat"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.firma.usuariExternNivellSeguretat,__theForm.listOfValuesForUsuariExternNivellSeguretat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FirmaFields.USUARIEXTERNNIVELLSEGURETAT)}" >
          <form:select id="firma_usuariExternNivellSeguretat"  onchange="if(typeof onChangeUsuariExternNivellSeguretat == 'function') {  onChangeUsuariExternNivellSeguretat(this); };"  cssClass="input-xxlarge" path="firma.usuariExternNivellSeguretat">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForUsuariExternNivellSeguretat}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
