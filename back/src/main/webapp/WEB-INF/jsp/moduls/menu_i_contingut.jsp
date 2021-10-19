<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="menu" />
<tiles:importAttribute name="contingut" />
<div class="row">

    <!--  INICI MENU -->
    <div id="principal" class="mainMenu col-3">
        <div id="mostrarMenu" class="upper-left-corner no-disponible">
            <a id="mostrar" href="#" data-toggle="tooltip"
                title="Mostrar Menu"> <i class="icon-fullscreen"></i>
            </a>
        </div>
        <div id="ocultarMenu" class="upper-right-corner disponible">
            <a id="ocultar" href="#" data-toggle="tooltip"
                title="Ocultar Menu"> <i class="icon-remove"></i>
            </a>
        </div>
        <div id="thumbnailmenu" class="thumbnail disponible">
            <tiles:insertAttribute name="menu">
            </tiles:insertAttribute>
        </div>
    </div>

    <!--  CONTINGUT  -->
    <div class="col-9">

        <!--  Missatges  -->
        <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

        <!-- Contingut de la pagina -->
        <tiles:insertAttribute name="contingut">
        </tiles:insertAttribute>

        <!-- FINAL DIV CONTINGUT -->
    </div>

    <div class="clearfix"></div>

</div>
<script>
	$('#ocultar').click(function() {
		$('#principal').removeClass('span3');
		show('#mostrarMenu');
		hide('#ocultarMenu');
		hide('#thumbnailmenu');
		return false;
	});

	$('#mostrar').click(function() {
		$('#principal').addClass('span3');
		hide('#mostrarMenu');
		show('#ocultarMenu');
		show('#thumbnailmenu');
		return false;
	});

	function hide(item) {
		$(item).removeClass('disponible');
		$(item).addClass('no-disponible');
	}

	function show(item) {
		$(item).removeClass('no-disponible');
		$(item).addClass('disponible');
	}
</script>