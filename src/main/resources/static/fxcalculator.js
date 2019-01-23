$(document).ready(function () {
    $("#convert").click(function () {
        $.ajax({
            url: "/convert",
            data: {
                amount: $("#amount").val(),
                fromCurrency: $("#fromCurrency").val(),
                toCurrency: $("#toCurrency").val()
            },
            success: function (result) {
                $("#results").html(result);
            }
        });
    });
});