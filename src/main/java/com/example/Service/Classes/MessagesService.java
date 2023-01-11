package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.MessagesDAO;
import com.example.HibernateOracle.Model.MessagesEntity;

import java.util.List;

public class MessagesService{

    private final MessagesDAO messagesDAO = new MessagesDAO();



    public boolean addMessage(String request, String answer, int idTravel,int idTC) {
        return messagesDAO.addData(new MessagesEntity(request, answer, idTravel, idTC));
    }


    public int addAnswer(String answerText, int idMessages) {
        return messagesDAO.addAnswer(answerText,idMessages);
    }


    public boolean deleteMessage(int idMessage) {
        return messagesDAO.deleteData(idMessage);
    }


    public List<MessagesEntity> getMessages(int idTC) {
        return messagesDAO.getMessages(idTC);
    }
}
