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

    $("#add-dispatcher-form").validate({
        rules: {
            "email": {
                required: true,
                email: true
            },
            "password": {
                required: true,
                minlength: 6
            },
            "full-name": {
                required: true,
                pattern: "^[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+$"
            },
            "passport-serial-numbers": {
                required: true,
                pattern: "^[А-Я]{2} [\\d]{6}$"
            },
            "phone-number": {
                required: true,
                pattern: "^(1[ \\-\\+]{0,3}|\\+1[ -\\+]{0,3}|\\+1|\\+)?((\\(\\+?1-[2-9][0-9]" +
                "{1,2}\\))|(\\(\\+?[2-8][0-9][0-9]\\))|(\\(\\+?[1-9][0-9]\\))|(\\(\\+?[17]\\))|(\\([2-9][2-9]\\))|" +
                "([ \\-\\.]{0,3}[0-9]{2,4}))?([ \\-\\.][0-9])?([ \\-\\.]{0,3}[0-9]{2,4}){2,3}$"
            },
            "salary-in-dollars": {
                pattern: "^[1-9][\\d]+$"
            }
        },
        messages: {
            "email": {
                required: "Please, enter email address.",
                email: "Please, enter valid email address."
            },
            "password": {
                required: "Please, enter password.",
                minlength: "Password must contain at least 6 characters."
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
                pattern: "Please, enter valid phone number(ex. +380664039952)."
            },
            "salary-in-dollars": {
                pattern: "Enter valid salary(ex. 1234)."
            }
        }
    });
});