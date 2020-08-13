(function() {
    var placesAutocomplete = places({
        appId: 'plDRZNU40FKE',
        apiKey: '9a77b5926c99724d70b31f07b17b0eb7',
        container: document.querySelector('#city1'),
        })
        .configure({
        type: 'city',
        aroundLatLngViaIP: false
        });
        placesAutocomplete.on('change', function resultSelected(e) {
            document.querySelector('#city1-coordinates').value = e.suggestion.latlng.lat + "/" + e.suggestion.latlng.lng+"/"+e.suggestion.name;
        });
})();
(function() {
    var placesAutocomplete = places({
        appId: 'plDRZNU40FKE',
        apiKey: '9a77b5926c99724d70b31f07b17b0eb7',
        container: document.querySelector('#city2'),
    })
        .configure({
            type: 'city',
            aroundLatLngViaIP: false
        });
    placesAutocomplete.on('change', function resultSelected(e) {
        document.querySelector('#city2-coordinates').value = e.suggestion.latlng.lat + "/" + e.suggestion.latlng.lng+"/"+e.suggestion.name;
    });
})();
(function() {
    var placesAutocomplete = places({
        appId: 'plDRZNU40FKE',
        apiKey: '9a77b5926c99724d70b31f07b17b0eb7',
        container: document.querySelector('#city3'),
    })
        .configure({
            type: 'city',
            aroundLatLngViaIP: false
        });
    placesAutocomplete.on('change', function resultSelected(e) {
        document.querySelector('#city3-coordinates').value = e.suggestion.latlng.lat + "/" + e.suggestion.latlng.lng+"/"+e.suggestion.name;
    });
})();
(function() {
    var placesAutocomplete = places({
        appId: 'plDRZNU40FKE',
        apiKey: '9a77b5926c99724d70b31f07b17b0eb7',
        container: document.querySelector('#city4'),
    })
        .configure({
            type: 'city',
            aroundLatLngViaIP: false
        });
    placesAutocomplete.on('change', function resultSelected(e) {
        document.querySelector('#city4-coordinates').value = e.suggestion.latlng.lat + "/" + e.suggestion.latlng.lng+"/"+e.suggestion.name;
    });
})();
(function() {
    var placesAutocomplete = places({
        appId: 'plDRZNU40FKE',
        apiKey: '9a77b5926c99724d70b31f07b17b0eb7',
        container: document.querySelector('#city5'),
    })
        .configure({
            type: 'city',
            aroundLatLngViaIP: false
        });
    placesAutocomplete.on('change', function resultSelected(e) {
        document.querySelector('#city5-coordinates').value = e.suggestion.latlng.lat + "/" + e.suggestion.latlng.lng+"/"+e.suggestion.name;
    });
})();