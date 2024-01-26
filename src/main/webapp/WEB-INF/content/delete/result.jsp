<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Résultat de suppression"); %>
<%@ include file="../../template/head.jsp" %>
<h1>Confirmez la suppression du ou des messages suivants</h1>
<%@ include file="../../template/errors.jsp" %>

<% if (request.getAttribute("numMsgList") != null 
		&& ((List<Integer>) request.getAttribute("numMsgList")).size() > 0
) {
	List<Integer> msgList = (List<Integer>) request.getAttribute("numMsgList");
	List<Integer> statusList = (List<Integer>) request.getAttribute("statusList");
%>
	
	<ul>
		<% for (int i = 0; i < msgList.size(); i++) {%>
			<li><%= msgList.get(i) %> : <% if (statusList.get(i) >= 0) out.print("OK"); else out.print("Erreur"); %></li>
		<% } %>
	</ul>

<% } %>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>