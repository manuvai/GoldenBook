<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Modification de message"); %>
<%@ include file="../../template/head.jsp" %>
<h1>Édition du message</h1>
<%@ include file="../../template/errors.jsp" %>

<%@ include file="../_form.jsp" %>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>