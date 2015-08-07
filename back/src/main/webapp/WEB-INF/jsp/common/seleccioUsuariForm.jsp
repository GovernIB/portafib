<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<script src="<c:url value="/js/bootstrap-typeahead.js"/>"></script>

<form:form modelAttribute="seleccioUsuariForm" method="${method}" name="seleccioUsuariForm">

<form:hidden path="id" id="id" />
<form:hidden path="param1"  />
<form:hidden path="param2"  />

<h3 class="tabs_involved">
  <fmt:message key="${seleccioUsuariForm.titol}" />
</h3>

<fmt:message key="${seleccioUsuariForm.subtitol}" />

<table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="margin-bottom: 0px;">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="formselectionby.label" /> &nbsp;(*)</label>
      </td>
      <td>
         
         <c:set var="placeholder" value="${(empty seleccioUsuariForm.usuarisFavorits)? 'formselectionby.placeholderemptyfavorits' :'formselectionby.placeholder' }" />
         
         <form:errors id="error_id" path="id" cssClass="errorField alert alert-error" />
         <c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
         <div class="input-append">
         </c:if>
              <input id="search" class="input-xxlarge" autocomplete="off"  type="text" placeholder="<fmt:message key="${placeholder}"/>">
         <c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
              <div class="btn-group">
                <button class="btn dropdown-toggle" data-toggle="dropdown">    <i class="icon-star"></i> <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <c:forEach var="favorit" items="${seleccioUsuariForm.usuarisFavorits}" >
                    <c:set var="tmpNom" value="${favorit.value}" />
                    <li><a href="javascript:selectItem('${favorit.key}','${pfi:escapeJavaScript(tmpNom)}')">${tmpNom}</a></li>
                  </c:forEach>
                </ul>
              </div>
            </div>
         </c:if>
         
         
      </td>
  </tr>
</tbody>
</table>
<small>
   <fmt:message key="formselectionby.info" >
      <fmt:param><%=Configuracio.getMinCharsToStartAutocomplete() %></fmt:param>
      <fmt:param><%=Configuracio.getMaxItemsToShowInAutocomplete() %></fmt:param>
   </fmt:message> 
</small>

<div class="navbar-form pagination-centered" style="margin-top: 15px;">
  <button class="btn btn-primary" type="button" onclick="preSubmit()" class="btn"><fmt:message key="genapp.continue"/></button>
  <button class="btn" type="button" onclick="goTo('<c:url value="${seleccioUsuariForm.cancelUrl}"/>')">
    <fmt:message key="genapp.cancel"/>
  </button>
</div>

</form:form>

<script type="text/javascript">

 function selectItem(id,nom) {
     $('#id').val(id);
     $('#search').val(nom);
 }


 function preSubmit() {
     var id = $('#id').val();
     // alert(" PRESUBMIT Valor de ID ]" + id + "[")
     
     if (id) {
       document.getElementById("seleccioUsuariForm").submit();
     } else {
       alert("<fmt:message key="formselectionby.error.emptyid"/>");
     }
     
 }

<%-- http://tatiyants.com/how-to-use-json-objects-with-twitter-bootstrap-typeahead/ --%> 
$('#search').typeahead({
    items:<%=Configuracio.getMaxItemsToShowInAutocomplete() %>,    
    minLength:<%=Configuracio.getMinCharsToStartAutocomplete() %>,
    source: function (query, process) {
        $('#id').val("");
        
        map = {};
                
        return $.ajax({
            url: '<c:url value="${seleccioUsuariForm.urlData}"/>',
            type: 'post',
            data: { query: query },
            dataType: 'json',
            success: function (result) {
                var resultList = result.map(function (item) {
                    map[item.Name] = item.Id;
                    return item.Name;
                });

                return process(resultList);

            }
        });
        

    },    
    updater: function (item) {
        var id = map[item];
        $('#id').val(id);
        return item;
    }    ,
    matcher: function (item) {
      // L'ajustament es fa en el servidor, per la qual cosa tots els items s'ajusten
      return true;  
    },
    sorter: function (items) {
      // L'ordenacio es realitza en el servidor
      return items;
    },
    highlighter: function (item) {
        var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
        
        var mySplitResult = query.split(" ");
        
        var newQuery ="";
        var i;
        for (i = 0; i < mySplitResult.length; i++) {
          if (i != 0) {
              newQuery = newQuery + "|";
          }
          newQuery = newQuery + mySplitResult[i];
        }
        
        return item.replace(new RegExp('(' + newQuery + ')', 'ig'), function ($1, match) {
            return '<strong>' + match + '</strong>'
          });
        
        return item;
    }
});


</script>
