$(document).ready(function() {
    $.validator.setDefaults({
        highlight: function (element) {
            $(element)
                .closest('.input-group')
                .addClass('has-danger')
                .removeClass('has-success')
        },
        unhighlight: function (element) {
            $(element)
                .closest('.input-group')
                .removeClass('has-danger')
                .addClass('has-success')
        },
        errorElement: 'div',
        errorClass: 'form-text',
        errorPlacement: function(error, element) {
            error.insertAfter(element.parent('div'));
        }
    });

    $("#settings-form").validate({
        rules: {
            "email": {
                required: true,
                email: true
            },
            "password": {
                required: true,
                minlength: 6
            },
            "confirm-password": {
                required: true,
                minlength: 6,
                equalTo: "#password"
            }
        },
        messages: {
            "email": {
                required: "Please, enter email address.",
                email: "Please, enter a valid email address."
            },
            "password": {
                required: "Please, enter your password.",
                minlength: "Password must contain at least 6 characters."
            },
            "confirm-password": {
                required: "Please, confirm your password.",
                equalTo: "Passwords don't match."
            }
        }
    });
});