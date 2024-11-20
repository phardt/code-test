<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link href='<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>'
                rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="/static/node_modules/jquery/dist/jquery.min.js"></script>
    </head>
    <jsp:include page="../layout/navbar.jsp" />

    <body>
        <div class="row border rounded p-4 m-2">
            <form:form id="form" method="POST" action="/projetos/projeto/${projeto.id}" modelAttribute="projeto" class="has-validated">
                <div class="row">
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="nome">Nome do projeto</form:label>
                            <input class='form-control <c:if test="${errors.getFieldError('nome') != null}"> is-invalid </c:if>' value="${projeto.nome}" name="nome" />
                            <div class="invalid-feedback">
                                <form:errors path="nome" />
                            </div>
                        </div>
                    </div>
                </div>
                    
                <div class="row">
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="dataInicio">Data inicio</form:label>
                            <input type="date"  class='form-control <c:if test="${errors.getFieldError('dataInicio') != null}"> is-invalid </c:if>' value='<fmt:formatDate value="${projeto.dataInicio}" pattern="yyyy-MM-dd" />' name="dataInicio" />
                        </div>
                    </div>
                
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="dataPrevisao">Data previsão de entrega</form:label>                                
                            <input type="date" class='form-control <c:if test="${errors.getFieldError('dataPrevisao') != null}"> is-invalid </c:if>' value='<fmt:formatDate value="${projeto.dataPrevisao}" pattern="yyyy-MM-dd" />' name="dataPrevisao" />
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="dataFim">Data fim do projeto</form:label>
                            <input type="date"  class='form-control <c:if test="${errors.getFieldError('dataFim') != null}"> is-invalid </c:if>' value='<fmt:formatDate value="${projeto.dataFim}" pattern="yyyy-MM-dd" />' name="dataFim" />
                        </div>
                    </div>
                </div>                           
                

                <div class="row">
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="status">Status do projeto</form:label>
                            <form:select class="form-select" path="status" items="${status}" />
                        </div>
                    </div>

                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="risco">Risco do projeto</form:label>
                            <form:select class="form-select" path="risco" items="${risco}" />
                        </div>
                    </div>

                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="gerente">Gerente do projeto</form:label>
                            <form:select class="form-select" path="gerente" items="${gerentes}" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="orcamento">Orçamento</form:label>
                            <input type="number"  class='form-control <c:if test="${errors.getFieldError('orcamento') != null}"> is-invalid </c:if>' value="${projeto.orcamento}" name="orcamento" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="mb-3">
                            <form:label class="form-label" path="descricao">Descrição do projeto</form:label>
                            <textarea class='form-control <c:if test="${errors.getFieldError('descricao') != null}" > is-invalid </c:if>' name="descricao" >${projeto.descricao}</textarea>
                            <div class="invalid-feedback">
                                <form:errors path="descricao" />
                            </div>
                        </div>
                    </div>
                </div>              
            </form:form>
             <div class="row">
                <div class="col-12">
                    <button id="btn-salvar" class="btn btn-primary">Salvar</button>
                    <button id="btn-cancell" class="btn btn-secondary">Cancelar</button>                    
                </div>                    
            </div>
        </div>
        <script src="/static/controllers/projetos-form-controller.js"></script>
    </body>
</html>