




  <div id="nouUsuariAlGrup" class="modal hide fade">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h3><fmt:message key="afegir"></fmt:message></h3>
    </div>
    <div class="modal-body">
      &nbsp;<fmt:message key="form.titol.nif"></fmt:message>:&nbsp; <input id="userToAdd" name="userToAdd" type="text" value="">
    </div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary" onclick="addUserToGroup()" ><fmt:message key="afegir"></fmt:message></a>
    </div>
  </div>



  <script type="text/javascript">

  function openNouUsuariDialog() {
	  $('#nouUsuariAlGrup').modal('show');
  }
  

  function addUserToGroup() {

	    
	     var nif = document.getElementById("userToAdd");
	     var nifvalue = nif.value;
	     if (nifvalue == '') {
	         alert('<fmt:message key="genapp.validation.required"><fmt:param>NIF</fmt:param></fmt:message>');
	         return;
	     }

	     var form;
	     form = document.getElementById("grupEntitatUsuariEntitatFilterForm");
	     
	     form.action = "<c:url value="${grupEntitatUsuariEntitatFilterForm.contexte}/addUserToGroup"/>";
	     form.submit();
  }


  </script>
