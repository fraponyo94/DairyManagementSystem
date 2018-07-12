var msg;
var msgType;
$(document).on('click', '#toast_link', function () {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": 300,
        "hideDuration": 1000,
        "timeOut": 5000,
        "extendedTimeOut": 1000,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    if (msgType === "SUCCESS") {
        toastr["success"](msg);
    }else if(msgType === "ERROR"){
        toastr["error"](msg);
    }
});
$(document).ready(function () {
    msg = $("#toast_msg").text();
    msgType = msg.substr(0, msg.indexOf(':'));
    $('#myTable').DataTable();
    // msg_display.show();
    $('#toast_link').click();
});