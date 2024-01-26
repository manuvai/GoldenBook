<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Ajout de message"); %>
<%@ include file="../template/head.jsp" %>
<h1>Merci de laisser votre message</h1>
<%@ include file="../template/errors.jsp" %>


<form action="" method="POST">
    <label for="pseudo">Entrez votre pseudo :</label>
    <input type="text" name="pseudo" id="pseudo" value="<%= request.getParameter("pseudo") == null ? "" : request.getParameter("pseudo") %>"/>
    
    <label for="message">Entrez votre message :</label>
    <input type="text" name="message" id="message" value="<%= request.getParameter("message") == null ? "" : request.getParameter("message") %>"/>

    <button type="submit">Ajouter</button>

</form>

<div>
	<a href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../template/tail.jsp" %>