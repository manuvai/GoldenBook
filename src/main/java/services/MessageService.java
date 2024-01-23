package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modeles.MessageDor;

public class MessageService {
	/**
	 * Retourne la liste des messages d'or
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MessageDor> getAllMessageDor() throws Exception {
		List<MessageDor> messageList = new ArrayList<>();
		
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Messages");
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			MessageDor messageDor = recupererMessageDor(resultSet);
			
			if (Objects.nonNull(messageDor)) {
				messageList.add(messageDor);
			}
		}

		Database.close();
		
		return messageList;
	}

	public int ajouterMessage(MessageDor messageDor) throws Exception {
		if (Objects.isNull(messageDor)) {
			return 0;
		}
		
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Messages (pseudo, message) VALUES (?, ?)");
		preparedStatement.setString(1, messageDor.getPseudo());
		preparedStatement.setString(2, messageDor.getMessage());
		int result = preparedStatement.executeUpdate();

		Database.close();
		
		return result;
		
	}

	/**
	 * Récupère un objet MessageDor à partir d'un resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private MessageDor recupererMessageDor(ResultSet resultSet) throws SQLException {
		MessageDor messageDor = null;
		
		if (Objects.nonNull(resultSet)) {
			messageDor = new MessageDor();
			
			messageDor.setNumMsg(resultSet.getInt("NUMMSG"));
			messageDor.setPseudo(resultSet.getString("PSEUDO"));
			messageDor.setMessage(resultSet.getString("MESSAGE"));
		}
		
		return messageDor;
	}
}
