<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Ajout de message"); %>
<%@ include file="../template/head.jsp" %>
<h1>Merci de laisser votre message</h1>
<%@ include file="../template/errors.jsp" %>


<form action="" method="POST">
	<div class="field">
		<label for="pseudo" class="label">Entrez votre pseudo :</label>
		<div class="control">
			<input type="text" class="input" name="pseudo" id="pseudo" value="<%= request.getParameter("pseudo") == null ? "" : request.getParameter("pseudo") %>"/>
		</div>
	</div>
	<div class="field">
    	<label for="message" class="label">Entrez votre message :</label>
		<div class="control">
    		<input type="text" class="input" name="message" id="message" value="<%= request.getParameter("message") == null ? "" : request.getParameter("message") %>"/>
		</div>
	</div>

    <div class="field is-grouped">
        <div class="control">
            <button class="button is-link" type="submit">Ajouter</button>
        </div>
    </div>

</form>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../template/tail.jsp" %>