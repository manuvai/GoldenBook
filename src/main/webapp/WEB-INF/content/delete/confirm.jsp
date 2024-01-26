<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Confirmation de suppression"); %>
<%@ include file="../../template/head.jsp" %>
<h1>Confirmez la suppression du ou des messages suivants</h1>
<%@ include file="../../template/errors.jsp" %>

<% if (request.getAttribute("numMsgList") != null 
		&& ((List<Integer>) request.getAttribute("numMsgList")).size() > 0
) {%>
	
	<ul>
		<% for (Integer numMessageDor : (List<Integer>) request.getAttribute("numMsgList")) {%>
			<li><%= numMessageDor %></li>
		<% } %>
	</ul>
	<a class="button is-info" href="<%=request.getContextPath()%>/delete?a=no">Non</a>
	<a class="button is-danger" href="<%=request.getContextPath()%>/delete/result">Oui</a>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>/delete">Annuler</a>

<% } %>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>