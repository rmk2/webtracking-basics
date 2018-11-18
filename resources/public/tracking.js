// Initialise Data Layer

window.dataLayer = []

// Web worker setup

if (window.Worker) {
    var worker = new Worker('worker.js');
};

function workerSendEvent(data){
    window.dataLayer.push(data);

    worker.postMessage(encodeData(data));
    worker.onmessage = message => console.log(message.data);
};

// Helper function to transform dictionaries into url-encoded strings

function encodeData(data){
    return Object.entries(data).map(([k, v]) => `${encodeURIComponent(k)}=${encodeURIComponent(v)}`).join('&');
};

// Page Visit event callback function

function pageVisit(e){
    var location = document.location;
    var screen = window.screen;

    data = {
	'event': 'pageview',
	'url': location.href,
	'protocol': location.protocol,
	'hostname': location.hostname,
	'port': location.port,
	'pathname': location.pathname,
	'width': screen.width,
	'height': screen.height,
	'depth': screen.depth,
	'referrer': document.referrer
    };

    return data;
};

// Form Submit event callback function

function formSubmit(e){
    var element = e.target;

    data = {
	'event': 'form',
	'element': element,
	'id': element.id,
	'name': element.name,
	'method': element.method,
	'text': element.innerText
    };

    return data;
};

// Click event callback function

function clickEvent(e){
    var element = e.target;

    data = {
	'event': 'click',
	'element': element,
	'id': element.id,
	'href': element.href,
	'pathname': element.pathname,
	'target': element.target,
	'text': element.innerText
    };

    return data;
};

// Page load event
window.addEventListener('load', e => workerSendEvent(pageVisit(e)));

// Form submit event
Array.from(document.forms)
    .map(elem => elem.addEventListener('submit', e => workerSendEvent(formSubmit(e))));

// Link click listener
Array.from(document.querySelectorAll('a'))
    .filter(elem => elem.parentNode.tagName != 'BUTTON')
    .map(elem => elem.addEventListener('click', e => workerSendEvent(clickEvent(e))));

// Button click listener
Array.from(document.querySelectorAll('button'))
    .map(elem => elem.addEventListener('click', e => workerSendEvent(clickEvent(e))));

// Submit click listener
// Array.from(document.querySelectorAll('input[type="submit"]'))
//     .map(elem => elem.addEventListener('click', e => workerSendEvent(clickEvent(e))));
