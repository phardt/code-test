<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <link href='<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>'
              rel="stylesheet">
        <link href='<c:url value="/static/node_modules/bootstrap-icons/font/bootstrap-icons.css"/>'
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <jsp:include page="../layout/navbar.jsp" />
    <body>
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <c:forEach var="map" items="${projetos}" varStatus="loop">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#acc${loop.index}" aria-expanded="true" aria-controls="acc${loop.index}">
                            ${map.nome}
                        </button>
                    </h2>
                    <div id="acc${loop.index}" class="accordion-collapse collapse show">
                        <div class="accordion-body">
                            <c:forEach var="projeto" items="${map.projetos}">
                                <div class="row border rounded p-4 m-2">
                                    <div class="col-1 text-center">
                                        <i class="bi bi-globe2 fs-1"></i>
                                    </div>
                                    <div class="col-9">
                                        <h5 class="card-title"> ${projeto.nome} </h5>  
                                        <p class="card-text">${projeto.descricao}</p>
                                    </div>
                                    <div class="col-2 text-end">
                                        <a href="<c:url value="/projetos/projeto/${projeto.id}" />" class="btn btn-outline-secondary"><span class="bi-pencil-square"></span></a>
                                        
                                        <c:if test="${projeto.status!='PLANEJADO' && projeto.status!='INICIADO' && projeto.status!='ANDAMENTO'}">
                                            <a href="<c:url value="/projetos/projeto/${projeto.id}" />" class="btn btn-outline-danger"><span class="bi-trash"></span></a>                                            
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>                 
            </c:forEach>
        </div>
   </body>
</html>