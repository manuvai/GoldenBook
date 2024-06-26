<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setAttribute("pageTitle", "Sélection des messages à supprimer"); %>
<%@ include file="../../template/head.jsp" %>
<h1>Sélection du ou des message(s) à supprimer</h1>
<%@ include file="../../template/errors.jsp" %>

<% if (request.getAttribute("messages") == null || ((List<MessageDor>) request.getAttribute("messages")).size() == 0) {%>
	Aucun message trouvé, essayez d'en créer un.
<%

} else { 
	List<Integer> listeNumSession = (List<Integer>) request.getSession().getAttribute("numMsgList");
	listeNumSession = Objects.isNull(listeNumSession)
			? new ArrayList<>()
			: listeNumSession;
%>
<form action="" method="post">
	
	<table>
		<thead>
			<tr>
				<th>Choisir</th>
				<th>#NumMsg</th>
				<th>Pseudo</th>
				<th>Message</th>
			</tr>
		</thead>
		<tbody>
			<% 
			for (MessageDor messageDor : (List<MessageDor>) request.getAttribute("messages")) {
				boolean isChecked = listeNumSession.contains(messageDor.getNumMsg());
			%>
				<tr>
					<td>
						<input type="checkbox" 
							id="num_msg_<%= messageDor.getNumMsg() %>" 
							name="num_msg[]" 
							value="<%= messageDor.getNumMsg() %>" 
							<%= isChecked ? "checked" : "" %>/>
						</td>
					<td><label for="num_msg_<%= messageDor.getNumMsg() %>"><%= messageDor.getNumMsg() %></label></td>
					<td><label for="num_msg_<%= messageDor.getNumMsg() %>"><%= messageDor.getPseudo() %></label></td>
					<td><label for="num_msg_<%= messageDor.getNumMsg() %>"><%= messageDor.getMessage() %></label></td>
				</tr>
			<% } %>
		</tbody>
	</table>

	<button type="submit" class="button is-danger ">Supprimer</button>
</form>

<% } %>

<div>
	<a class="button is-link is-light" href="<%=request.getContextPath()%>">Retour menu principal</a>
</div>
<%@ include file="../../template/tail.jsp" %>