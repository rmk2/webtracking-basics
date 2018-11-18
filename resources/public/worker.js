// Tracking event send function

function sendEvent(message) {
    var data = message.data;
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:3000/tracking" + "?" + data;

    xhr.open("GET", url, false);
    xhr.onload = function(){
	if (xhr.readyState === 4 && xhr.status === 200) {
	    postMessage(xhr.responseText)
	};
    }
    xhr.send();
};

onmessage = message => sendEvent(message);
