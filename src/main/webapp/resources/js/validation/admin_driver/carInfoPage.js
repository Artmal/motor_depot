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

    $("#edit-car-form").validate({
        rules: {
            "registration-number": {
                required: true,
                pattern: "^[А-Я]{2} [\\d]{4} [А-Я]{2}$"
            },
            "type": {
                required: true
            },

            "condition": {
                required: true
            },
            "model": {
                required: true
            },
            "number-of-seats": {
                required: true,
                pattern: "^[1-9][\\d]?$"
            },
            "color": {
                required: true,
                pattern: "^[A-ZА-Я][a-zа-я]+$"
            }
        },
        messages: {
            "registration-number": {
                required: "Please, enter registration number of the car you want to add.",
                pattern: "Please, enter valid registration number of your car(ex. ВТ 1234 АВ)."
            },
            "type": {
                required: "Please, enter car type."
            },
            "condition": {
                required: "Please, enter condition of the car you want to add."
            },
            "model": {
                required: "Please, enter model of the car you want to add."
            },
            "number-of-seats": {
                required: "Please, enter number of seats in the car you want to add.",
                pattern: "Please, enter valid number of seats in the car you want to add(ex. 2)."
            },
            "color": {
                required: "Please, enter color in the car you want to add.",
                pattern: "Please, enter a valid color in the car you want to add(ex. Yellow)."
            }
        }
    });
});