/*global app*/
app.constant('GOOGLE_LOGIN_DATA', {
    scope: 'profile email',
    client_id: '398946939215-q8ldvn1djcgjlhmhg5n355aak78r0hpi.apps.googleusercontent.com',
    api_key: ' AIzaSyBioIlR3hK_zxaaeHbLsujBp7tVanQq-hE'
});

app.constant('MAP_OPTIONS', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
    '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © ' +
    '<a href="http://mapbox.com">Mapbox</a>',
    center: [45.255, 19.844722],
    zoom: 16,
    maxZoom: 18,
    minZoom: 14,
    bounds: [
        [45.19776423003451, 19.745006561279297],
        [45.30930375832607, 19.893150329589844]
    ]
});

app.constant('EMAIL_REGEX', /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/);