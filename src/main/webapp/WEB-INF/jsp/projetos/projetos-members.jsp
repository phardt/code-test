<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
    <head>
        <link href='<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>'
              rel="stylesheet">
        <link href='<c:url value="/static/node_modules/bootstrap-icons/font/bootstrap-icons.css"/>'
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="/static/node_modules/jquery/dist/jquery.min.js"></script>
    </head>
    <jsp:include page="../layout/navbar.jsp" />
    <body>
        <div class="row border rounded p-4 m-2">
            <h5>Membros do projeto: <a href="/projetos/projeto/${projeto.id}"><b><i> ${projeto.nome} </i></b></a></h5> 
            <ul id="member-project" class="list-group">
                <c:forEach var="pMember" items="${projeto.membros}" varStatus="loop">
                    <li id="member-id-${pMember.id}" class="list-group-item">
                        <div class="container">
                            <div class="row">
                                <div class="col-11">
                                    ${pMember.nome}
                                </div>
                                <div class="col-1"> 
                                    <button type="button" onClick="handleRemoveMember(${pMember.id}, '${pMember.nome}', 'member-id-${pMember.id}', ${projeto.id})" class="btn btn-outline-danger"><i class="bi bi-person-x"></i></button>                                
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="row border rounded p-4 m-2">
            <h5>Membros Cadastrados</h5>
            <div class="accordion" id="accordionPanelsStayOpenExample">
                <ul id="members" class="list-group">
                    <c:forEach var="pessoa" items="${pessoas}" varStatus="loop">
                        <li id="list-${pessoa.id}" class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-11">
                                        ${pessoa.nome}
                                    </div>
                                    <div class="col-1"> 
                                        <button type="button" onClick="handleAddMember(${pessoa.id}, '${pessoa.nome}', 'list-${pessoa.id}', ${projeto.id})" class="btn btn-outline-success"><i class="bi bi-person-plus-fill"></i></button>                                
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                            
            </div>
        </div>
        <script src="/static/controllers/projetos-members-controller.js"></script>
   </body>
</html>