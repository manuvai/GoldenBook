<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if (request.getAttribute("errors") != null && ((List<String>) request.getAttribute("errors")).size() > 0) { %>
	<div class="errors">
		<ul>
			<% for (String error : (List<String>) request.getAttribute("errors")) { %>
			<li><%= error %></li>
			<% } %>
		</ul>
	</div>
<% } %>
