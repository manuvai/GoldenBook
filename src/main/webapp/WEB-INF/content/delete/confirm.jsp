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
	<a href="<%=request.getContextPath()%>/delete">Non</a>
	<a href="<%=request.getContextPath()%>/delete/result">Oui</a>

<% } %>

<div>
	<a href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>