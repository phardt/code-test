function handleAddMember(id, nome, elementId, projetoId) {
    updateProjectMember(id, projetoId);
    $("#member-project").append(`
        <li id="member-id-${id}" class="list-group-item">
            <div class="container">
                <div class="row">
                    <div class="col-11">
                        ${nome}
                    </div>
                    <div class="col-1"> 
                        <button type="button" onClick="handleRemoveMember(${id}, '${nome}', 'member-id-${id}', ${projetoId})" class="btn btn-outline-danger"><i class="bi bi-person-dash"></i></button>                                
                    </div>
                </div>
            </div>
        </li>
    `);

    $(`#${elementId}`).remove();
}

function handleRemoveMember(id, nome, elementId, projetoId) {
    removeProjectMember(id, projetoId);
    $("#members").append(`
        <li id="list-${id}" class="list-group-item">
            <div class="container">
                <div class="row">
                    <div class="col-11">
                        ${nome}
                    </div>
                    <div class="col-1"> 
                        <button type="button" onClick="handleAddMember(${id}, '${nome}', 'list-${id}', ${projetoId})" class="btn btn-outline-success"><i class="bi bi-person-plus-fill"></i></button>                                
                    </div>
                </div>
            </div>
        </li>    
    `);

    $(`#${elementId}`).remove();
}

function getData(id, projetoId) {
    return {
        id,
        projetoId
    }
}

function updateProjectMember(id, projetoId) {
    $.ajax({
        type: "PUT",
        url: "/projetos/membros",
        data: JSON.stringify(getData(id, projetoId)),
        processData: false,
        contentType: "application/json; charset=UTF-8",
        success: function (response) {
            console.log(`adicionado ${id} com sucesso.`);
        },
        error: function(request, msg, error) {
            alert(`erro ao adicionar o registro ${id}.`);
        }
    });
}

function removeProjectMember(id, projetoId) {
    $.ajax({
        type: "DELETE",
        url: "/projetos/membros",
        data: JSON.stringify(getData(id, projetoId)),
        processData: false,
        contentType: "application/json; charset=UTF-8",
        success: function (response) {
            console.log(`removido ${id} com sucesso.`);
        },
        error: function(request, msg, error) {
            alert(`erro ao remover o registro ${id}.`);
        }
    });
}