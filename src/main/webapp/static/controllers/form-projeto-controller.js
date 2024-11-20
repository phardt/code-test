$('#btn-cancell').click(function() {
    window.location.href = "/projetos"
});

$('#btn-salvar').click(function() {
    $('#form').trigger('submit');
});