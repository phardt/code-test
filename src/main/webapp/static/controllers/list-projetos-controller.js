function handleDelete(id) {
    $.ajax({
        type: "DELETE",
        url: "/projetos/projeto/"+id,
        success: function (response) {
            window.location.href = "/projetos"
        },
        error: function(request, msg, error) {
            console.log(request,msg, error);
        }
    });
}