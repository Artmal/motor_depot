$(document).ready(function() {
    $.validator.setDefaults({
        errorClass: 'form-text',
        highlight: function(element) {
            $(element)
                .closest('.form-text')
                .addClass('.aria-describedby')
                .addClass('has-danger');
        },
        highlight: function(element) {
            $(element)
                .closest('.form-group')
                .addClass('has-danger');
        },
        unhighlight: function(element) {
            $(element)
                .closest('.form-group')
                .removeClass('has-danger');
        }
    });

    $("#add-trip-form").validate({
        rules: {
            "town-from": {
                required: true,
                pattern: "^[A-ZА-Я][a-zа-я-\\s]+$"
            },
            "town-to": {
                required: true,
                pattern: "^[A-ZА-Я][a-zа-я-\\s]+$"
            },
            "time-out": {
                required: true,
                pattern: "[2][\\d]{3}-[\\d][1-9]-[\\d][1-9] [\\d]{2}:[\\d]{2}:[\\d]{2}$"
            },
            "time-in": {
                required: true,
                pattern: "[2][\\d]{3}-[\\d][1-9]-[\\d][1-9] [\\d]{2}:[\\d]{2}:[\\d]{2}$"
            },
            "payment-in-dollars": {
                required: true,
                pattern: "^[1-9][\\d]+$"
            }
        },
        messages: {
            "town-from": {
                required: "Please, enter a town name.",
                pattern: "Please, enter a valid town name(ex. Kiev)."
            },
            "town-to": {
                required: "Please, enter a town name.",
                pattern: "Please, enter a valid town name(ex. Kiev)."
            },
            "time-out": {
                required: "Please, enter a time out.",
                pattern: "Please, enter a valid time(follow pattern)."
            },
            "time-in": {
                required: "Please, enter a time out.",
                pattern: "Please, enter a valid time(follow pattern)."
            },
            "payment-in-dollars": {
                required: "Please, enter a payment.",
                pattern: "Please, enter a valid payment(ex. 123)."
            }
        }
    });
});