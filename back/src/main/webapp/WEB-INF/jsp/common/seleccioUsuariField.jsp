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
<script src="<c:url value="/js/bootstrap-typeahead.js"/>"></script>

<form:hidden path="id" id="id" />
<form:hidden path="param1" id="param1"  />
<form:hidden path="param2" id="param2"  />

<c:set var="placeholder" value="${(empty seleccioUsuariForm.usuarisFavorits)? 'formselectionby.placeholderemptyfavorits' :'formselectionby.placeholder' }" />

<form:errors id="error_id" path="id" cssClass="errorField alert alert-error" />
<c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
<div class="input-append">
</c:if>
  <input id="search" class="input-xxlarge" autocomplete="off"  type="text" placeholder="<fmt:message key="${placeholder}"/>">
<c:if test="${not empty seleccioUsuariForm.usuarisFavorits }">
  <div class="btn-group">
    <button id="searchfavorit" class="btn dropdown-toggle" data-toggle="dropdown">
        <i class="icon-star"></i>
        <span class="caret"></span>
    </button>

    <ul class="dropdown-menu" style="right: 0; left: auto; max-height: 540px; overflow-y: auto; ">
      <c:set var="lastItem" value="" />
      <c:forEach var="favorit" items="${seleccioUsuariForm.usuarisFavorits}" >
         <c:set var="tmpNom" value="${favorit.value}" />
         <c:if  test="${(fn:startsWith(lastItem, '(*)') == true) && fn:startsWith(tmpNom, '(*)') == false}">
            <li class="divider"></li>
         </c:if>
         <c:set var="lastItem" value="${tmpNom}" />
         <li><a href="javascript:selectItem('${favorit.key}','${pfi:escapeJavaScript(tmpNom)}')">${tmpNom}</a></li>
      </c:forEach>
    </ul>
  </div>
</div>
</c:if>
<br/>
<small>
<fmt:message key="formselectionby.info" >
  <fmt:param><%=minchars%></fmt:param>
  <fmt:param><%=maxitems%></fmt:param>
</fmt:message> 
</small>
        
<script type="text/javascript">

 function selectItem(id,nom) {
     $('#id').val(id);
     $('#search').val(nom);
 }

<%-- http://tatiyants.com/how-to-use-json-objects-with-twitter-bootstrap-typeahead/ --%> 
$('#search').typeahead({
    items:<%=maxitems %>,    
    minLength:<%= minchars %>,
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