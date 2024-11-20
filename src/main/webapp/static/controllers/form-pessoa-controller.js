$('#btn-cancell').click(function() {
    window.location.href = "/pessoas"
});

$('#btn-salvar').click(function() {
    $('#form').trigger('submit');
});