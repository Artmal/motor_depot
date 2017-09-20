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

    $("#add-car-form").validate({
        rules: {
            "type": {
                required: true
            },
            "registration-number": {
                required: true,
                pattern: "^[А-Я]{2} [\\d]{4} [А-Я]{2}$"
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
            },
            "owner-id": {
                required: true,
                pattern: "^[1-9][\\d]*$"
            }
        },
        messages: {
            "type": {
                required: "Please, enter a car type."
            },
            "registration-number": {
                required: "Please, enter registration number of the car you want to add.",
                pattern: "Please, enter valid registration number of your car(ex. ВТ 1234 АВ)."
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
            },
            "owner-id": {
                required: "Please, enter a driver ID to link the car with.",
                pattern: "Please, enter a valid ID(ex. 12)."
            }
        }
    });
});