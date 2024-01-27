<%@page import="modeles.MessageDor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% MessageDor messageDor = (MessageDor) request.getAttribute("messageDor"); %>

<form action="" method="POST">
	<div class="field">
		<label for="pseudo" class="label">Entrez votre pseudo :</label>
		<div class="control">
			<input
				type="text"
				class="input"
				name="pseudo"
				id="pseudo"
				value="<%= messageDor.getPseudo() == null ? "" : messageDor.getPseudo() %>"/>
		</div>
	</div>
	<div class="field">
    	<label for="message" class="label">Entrez votre message :</label>
		<div class="control">
    		<input
				type="text"
				class="input"
				name="message"
				id="message"
				value="<%= messageDor.getMessage() == null ? "" : messageDor.getMessage() %>"/>
		</div>
	</div>

    <div class="field is-grouped">
        <div class="control">
            <button class="button is-link" type="submit">Valider</button>
        </div>
    </div>

</form>