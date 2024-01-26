<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Compte rendu de l'opération"); %>
<%@ include file="../template/head.jsp" %>
<h1>Compte rendu de l'opération</h1>
<%@ include file="../template/errors.jsp" %>

<%= request.getAttribute("total_rows") %> message(s) enregistré(s).

<div>
	<a href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../template/tail.jsp" %>