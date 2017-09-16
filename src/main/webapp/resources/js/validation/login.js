$(document).ready(function() {
    $.validator.setDefaults({
        errorClass: 'form-text',
        highlight: function(element) {
            $(element)
                .closest('.form-text')
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

   $("#login-form").validate({
       rules: {
           "email": {
               required: true,
               email: true
           },
           "password": {
               required: true
           }
       },
       messages: {
           "email": {
               required: "Please, enter an email address.",
               email: "Please, enter a valid email address."
           },
           "password": {
               required: "Please, enter your password."
           }
       }
   });
});