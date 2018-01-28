var map;
var ajaxRequest;
var plotlist;
var plotlayers=[];

function onMapClick(e) {
	popup
	.setLatLng(e.latlng)
	.setContent("You clicked the map at " + e.latlng.toString())
	.openOn(map);
}

function initmap() {
	// set up AJAX request
	ajaxRequest=getXmlHttpObject();
	if (ajaxRequest==null) {
		alert ("This browser does not support HTTP Request");
		return;
	}

	// set up the map
	map = new L.Map('map');

	// create the tile layer with correct attribution
	var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
	var osmAttrib='Map data � <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
	var osm = new L.TileLayer(osmUrl, {minZoom: 1, maxZoom: 30, attribution: osmAttrib});		

	// start the map in South-East England
	map.setView(new L.LatLng(40.4893538, -3.6827461),11);
	map.addLayer(osm);
	askForPlots();
	map.on('click', onMapClick);
}


var popup = L.popup();

function getXmlHttpObject() {
	if (window.XMLHttpRequest) { return new XMLHttpRequest(); }
	if (window.ActiveXObject)  { return new ActiveXObject("Microsoft.XMLHTTP"); }
	return null;
}

function askForPlots() {
	var marker = L.marker([40.488255, -3.684310]).addTo(map);
	var marker2 = L.marker([40.487595, -3.686479]).addTo(map);
	marker.bindPopup("<b>Hello world!</b>");
	var latlngs = Array();
	latlngs.push(marker.getLatLng());
	latlngs.push(marker2.getLatLng());
	var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
	map.fitBounds(polyline.getBounds());

	var json = ${json};
	obj = JSON.parse(json);

	alert(obj[0].lat);
}