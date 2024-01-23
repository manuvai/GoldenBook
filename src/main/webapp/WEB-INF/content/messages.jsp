<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../template/head.jsp" %>
<h1>Les messages du livre d'or</h1>

<% if (request.getAttribute("messages") == null || ((List<MessageDor>) request.getAttribute("messages")).size() == 0) {%>
	Yen a pas
<% } else { %>
	<% for (MessageDor messageDor : (List<MessageDor>) request.getAttribute("messages")) {%>
		<%= messageDor.getNumMsg() %>
	<% } %>
<% } %>

<div>
	<a href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../template/tail.jsp" %>