const api ='http://127.0.0.1:3003'; // Change This

function redirect(url) {
    window.location.href = url;
}

function redirectMeja() {
    var meja = document.getElementById('mejaInput').value;
    if (meja) {
        var url = `${api}/order?meja=${meja}`
        redirect(url);
    } else {
        alert('Masukkan nomor meja terlebih dahulu!');
    }
}