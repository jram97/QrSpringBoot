function invokeQR(){
/*    var pais_;
    
    var OSName="Desconocido";

    if (navigator.appVersion.indexOf("Win")!=-1) OSName="Windows";
    if (navigator.appVersion.indexOf("Mac")!=-1) OSName="MacOS";
    if (navigator.appVersion.indexOf("X11")!=-1) OSName="UNIX";
    if (navigator.appVersion.indexOf("Linux")!=-1) OSName="Linux";
    if (navigator.appVersion.indexOf("Android")!=-1) OSName="Android";
    
    pais_ = "El Salvador";*/
    
    var data = {
        title:      $("#title").val(),
        desc:       $("#desc").val(),
        website:    $("#website").val(),
        id:         parseInt($("#idSizes").val())+1
    };

    if( $("#placeHolder").children().length > 0 ){
        draw_qrcode(data)
    }else{
        update_qrcode(data)
    }
    
}



var draw_qrcode = function(data) {
    var typeNumber = 4;
    var errorCorrectionLevel = 'L';

    document.getElementById('placeHolder').innerHTML = create_qrcode(data, typeNumber, errorCorrectionLevel, 'Byte');
};

var create_qrcode = function(data, typeNumber,
                             errorCorrectionLevel, mode) {

    var qr = qrcode(typeNumber || 4, errorCorrectionLevel || 'M');

    //Usar url de soluciones roots


    qr.addData(`https://www.mediexpress.solucionesroots.com:8080/QRCode/campaign/ver/${data.id}`, mode);
    qr.make();
    return qr.createImgTag();
};

var update_qrcode = function(data) {
    $("#placeHolder").empty();
    document.getElementById('placeHolder').innerHTML = create_qrcode(data, 4, 'L', 'Byte');
};

