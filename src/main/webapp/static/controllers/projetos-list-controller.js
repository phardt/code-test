function handleDelete(id) {
    $.ajax({
        type: "DELETE",
        url: "/projetos/projeto/"+id,
        success: function (response) {
            window.location.href = "/projetos"
        },
        error: function(request, msg, error) {
            alert(`registro ${id} não pode ser excluido.`)
        }
    });
}