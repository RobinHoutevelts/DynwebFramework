var xHRObject = new XMLHttpRequest();
var imageDivId = 0;

function orderDetails(id) {
    document.getElementById('detailRow').setAttribute('class', 'row');
    xHRObject.open("GET", "c?a=3&id=" + id, true);
    xHRObject.onreadystatechange = loadOrderData;
    xHRObject.send();
}

