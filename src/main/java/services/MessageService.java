package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
	
	/**
	 * Récupération d'un message d'or à partir d'un identifiant
	 * 
	 * @param numMsg
	 * @return
	 * @throws Exception
	 */
	public MessageDor getMessageDorById(Integer numMsg) throws Exception {
		MessageDor messageDor = null;
		
		if (Objects.nonNull(numMsg)) {

			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * "
							+ "FROM Messages "
							+ "WHERE nummsg = ?");
			preparedStatement.setInt(1, numMsg);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				messageDor = recupererMessageDor(resultSet);
			}

			Database.close();
			
		}
		
		return messageDor;
	}
	
	/**
	 * Met à jour le message en base
	 * 
	 * @param inMessageDor
	 * @return
	 * @throws Exception 
	 */
	public MessageDor updateMessageDor(MessageDor inMessageDor) throws Exception {
		MessageDor messageDor = null;
		
		if (Objects.nonNull(inMessageDor)) {
			int numMsg = inMessageDor.getNumMsg();

			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE Messages "
							+ "SET pseudo = ?, "
							+ "	message = ?"
							+ "WHERE nummsg = ?");
			
			preparedStatement.setString(1, inMessageDor.getPseudo());
			preparedStatement.setString(2, inMessageDor.getMessage());
			preparedStatement.setInt(3, numMsg);
			
			int result = preparedStatement.executeUpdate();
			
			if (result > 0) {
				messageDor = getMessageDorById(numMsg);
			}

			Database.close();
			
		}
		
		return messageDor;
	}

	/**
	 * Ajoute message
	 * 
	 * @param messageDor
	 * @return
	 * @throws Exception
	 */
	public int ajouterMessage(MessageDor messageDor) throws Exception {
		if (Objects.isNull(messageDor)) {
			return 0;
		}
		
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Messages (pseudo, message) VALUES "
				+ "(?, ?)");
		preparedStatement.setString(1, messageDor.getPseudo());
		preparedStatement.setString(2, messageDor.getMessage());
		int result = preparedStatement.executeUpdate();

		Database.close();
		
		return result;
		
	}

	/**
	 * Supprime 
	 * 
	 * @param inNumMsgList
	 * @return
	 * @throws Exception
	 */
	public List<Integer> supprimerMessages(List<Integer> inNumMsgList) throws Exception {
		List<Integer> numMsgList = new ArrayList<Integer>();
		
		if (Objects.nonNull(inNumMsgList) && !inNumMsgList.isEmpty()) {

			Connection connection = Database.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Messages WHERE NumMsg = ?");
			
			for (Integer numMsg : inNumMsgList) {
				
				preparedStatement.setInt(1, numMsg);
				preparedStatement.addBatch();
			}

			int[] result = preparedStatement.executeBatch();
			
			numMsgList = Arrays.stream(result)
					.boxed()
					.collect(Collectors.toList());

			Database.close();
		}
		
		return numMsgList;
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
