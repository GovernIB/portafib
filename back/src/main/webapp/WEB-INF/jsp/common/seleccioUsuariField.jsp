<%@page import="es.caib.portafib.back.security.LoginInfo"%>
<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%
  String entitatID = null;
  if (LoginInfo.getInstance() != null) {
    entitatID = LoginInfo.getInstance().getEntitatID();
  }

int minchars=PropietatGlobalUtil.getMinCharsToStartAutocomplete(entitatID);
int maxitems=PropietatGlobalUtil.getMaxItemsToShowInAutocomplete(entitatID);

%>
<c:if test="${ usuarimodalconfig == null }">
  <c:set var="usuarimodalconfig" value="" />
</c:if>
<script src="<c:url value="/js/bootstrap-typeahead.js"/>"></script>

<form:hidden path="id" id="id" />
<form:hidden path="param1" id="param1"  />
<form:hidden path="param2" id="param2"  />

<c:set var="placeholder" value="${(empty seleccioUsuariForm.usuarisFavorits)? 'formselectionby.placeholderemptyfavorits' :'formselectionby.placeholder' }" />

<form:errors id="error_id" path="id" cssClass="errorField alert alert-danger" />
<c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
<div class="input-group">
</c:if>
  <input id="search${usuarimodalconfig}" class="form-control w-75 typeahead" autocomplete="off"  type="text" placeholder="<fmt:message key="${placeholder}"/>">
<c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
  <div class="dropdown">
    <button id="search${usuarimodalconfig}favorit" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="far fa-star"></i>
        <span class="caret"></span>
    </button>

    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" style="right: 0; left: auto; max-height: 540px; overflow-y: auto; ">
      <c:set var="lastItem" value="" />
      <c:forEach var="favorit" items="${seleccioUsuariForm.usuarisFavorits}" >
         <c:set var="tmpNom" value="${favorit.value}" />
         <c:if  test="${(fn:startsWith(lastItem, '(*)') == true) && fn:startsWith(tmpNom, '(*)') == false}">
            <div class="dropdown-divider"></div>
         </c:if>
         <c:set var="lastItem" value="${tmpNom}" />
         <a class="dropdown-item" href="javascript:selectItem${usuarimodalconfig}('${favorit.key}','${pfi:escapeJavaScript(tmpNom)}')">${tmpNom}</a>
      </c:forEach>
    </div>
  </div>
</div>
</c:if>
<small>
<fmt:message key="formselectionby.info" >
  <fmt:param><%=minchars%></fmt:param>
  <fmt:param><%=maxitems%></fmt:param>
</fmt:message> 
</small>
        
<script type="text/javascript">

 function selectItem${usuarimodalconfig}(id,nom) {
     $('#id').val(id);
     $('#search${usuarimodalconfig}').val(nom);
 }

<%-- http://tatiyants.com/how-to-use-json-objects-with-twitter-bootstrap-typeahead/ --%> 
$('#search${usuarimodalconfig}').typeahead({
    items:<%=maxitems %>,    
    minLength:<%= minchars %>,
    source: function (query, process) {

        $('#id').val("");

        map = {};
        
        var parameters = { query: query };
        var p1 = document.getElementById('param1');
        if (p1 != null) {
            parameters['param1'] = p1.value;
        }
        var p2 = document.getElementById('param2');
        if (p2 != null) {
            parameters['param2'] = p2.value;
        }

        return $.ajax({
            url: '<c:url value="${seleccioUsuariForm.urlData}"/>',
            type: 'post',
            data: parameters, //{ query: query },
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