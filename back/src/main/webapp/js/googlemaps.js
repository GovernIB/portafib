
/**
 *  FUNCIONS COMUNS PER MAPES GOOGLE MAPS
 *  @author anadal@ibit.org
 */

var map;
var marker = null;
  
function initGoogleMap(mapname, zoom, lat, lon) {
	var mapOptions = {
	  zoom: zoom,
	  center: new google.maps.LatLng(lat,lon),
	  mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById(mapname), mapOptions);
}
  
function afegirMarker(latlon, name) {
	if (marker != null) {
	  marker.setMap(null);
	  marker = null;
	}
	
	marker = new google.maps.Marker({
		position: latlon,
		map: map,
		title: name
	});
}