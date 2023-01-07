package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.MessagesDAO;
import com.example.HibernateOracle.Model.MessagesEntity;
import com.example.Service.Interfaces.IMessagesService;

import java.util.List;

public class MessagesService implements IMessagesService {

    private final MessagesDAO messagesDAO = new MessagesDAO();


    @Override
    public boolean addMessage(String request, String answer, int idTravel,int idTC) {
        return messagesDAO.addData(new MessagesEntity(request, answer, idTravel, idTC));
    }

    @Override
    public int addAnswer(String answerText, int idMessages) {
        return messagesDAO.addAnswer(answerText,idMessages);
    }

    @Override
    public boolean deleteMessage(int idMessage) {
        return messagesDAO.deleteData(idMessage);
    }

    @Override
    public List<MessagesEntity> getMessages(int idTC) {
        return messagesDAO.getMessages(idTC);
    }
}
