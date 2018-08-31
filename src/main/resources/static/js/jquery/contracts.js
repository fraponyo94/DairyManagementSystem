var msg;
var info;
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
        "timeOut": 10000,
        "extendedTimeOut": 1000,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    if(msg !== ""){
        //get the information in the message
        info = msg.substr(msg.indexOf(':') + 1, msg.length).trim();
        if (msgType === "SUCCESS") {
            toastr["success"](info);
        } else if (msgType === "ERROR") {
            toastr["error"](info);
        }
    }
});
$(document).ready(function () {
    msg = $("#toast_msg").text();
    console.log(msg);
    //get the type of the message from the first word of the message
    msgType = msg.substr(0, msg.indexOf(':'));
    $('#myTable').DataTable();
    $('#toast_link').click();
});