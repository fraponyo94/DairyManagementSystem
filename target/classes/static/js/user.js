var password;
var pass2 = $("#pass2");
var pass1 = $("#pass1");
var username = $("#form100");
var signup =  $("#signup");
var error = $('#error');
pass2.focus(function () {
    password = pass1.val();
});
pass2.keyup(function () {
   var p = pass2.val();
   if ((p === password) && (username.val().length>3) && (p.length > 7)){
       signup.prop("disabled", false);
   } else {
       signup.prop("disabled", true);
   }
});
pass1.focus(function () {
    password = pass2.val();
});
pass1.keyup(function () {
    var p = pass1.val();
    if ((p === password) && (username.val().length>3) && (p.length > 7)){
        signup.prop("disabled", false);
    } else {
        signup.prop("disabled", true);
    }
});
username.keyup(function () {
    var p = pass1.val();
    error.remove();
    if ((p === password) && (username.val().length>3) && (p.length > 7)){
        signup.prop("disabled", false);
    } else {
        signup.prop("disabled", true);
    }
});
$(document).ready(function () {
    signup.prop("disabled", "disabled");
});
