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

    $("#registration-form").validate({
        rules: {
            "email": {
                required: true,
                pattern: "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$"
            },
            "password": {
                required: true,
                minlength: 6
            },
            "confirm-password": {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
            "full-name": {
                required: true,
                pattern: "^[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+$"
            },
            "passport-serial-numbers": {
                required: true,
                pattern: "^([А-Я]{2} \\d{6})|(\\d{10})$"
            },
            "phone-number": {
                required: true,
                pattern: "^(\\+?\\d{12})|(\\d{10})$"
            },
            "age": {
                required: true,
                pattern: "^(1[89]|[2-9][0-9])$"
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
            },
            "full-name": {
                required: "Please, enter full name.",
                pattern: "Please, enter valid full name(ex. John Smith)."
            },
            "passport-serial-numbers": {
                pattern: "Please, enter valid passport serial numbers(ex. ВТ 123456)."
            },
            "phone-number": {
                required: "Please, enter phone number!",
                pattern: "Please, enter a valid phone number(ex. +380664039952)."
            },
            "age": {
                pattern: "Please, enter a valid age. You need to be over 18 years old.",
                required: "Please, enter your age."
            }
        }
    });
});