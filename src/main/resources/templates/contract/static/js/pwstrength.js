$(document).ready(function() {
    function checkPassword(str) {
        var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
        return re.test(str);
    }
    // Disable Submit Button By Default
    $("button[type=submit]").attr('disabled','disabled');

    //Validate password to ensure matching and enforce constraint
    $('#password,#confirm-password').on('keyup', function () {
        var password = $('#password').val();
        var ConfirmPassword = $('#confirm-password').val();
        if(password != ''){
            if(!checkPassword(password)){
                $('.password').addClass('alert alert-danger').html('Weak')
                $("#confirm-password").attr('readonly', true);
            }else {
                $("#confirm-password").removeAttr('readonly');
                $('.password').removeClass('alert alert-danger').html('');
                if (ConfirmPassword != '') {
                    if (password != ConfirmPassword) {
                        $('.confirmPassword').addClass('alert alert-warning').html('Password not  matching');
                    }
                    else {
                        // To Enable Submit Button after password fields are filled correctly
                        $('button[type=submit]').removeAttr('disabled');
                        $('.confirmPassword').removeClass('alert alert-warning').html('');
                    }
                }

            }
        }});

    //Disable submit button after first submission to prevent resubmission
    $('form').submit(function(e) {
        e.preventDefault
        $(this).find("button[type='submit']").prop('disabled',true);
    });


});
