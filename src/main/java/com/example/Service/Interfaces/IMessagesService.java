package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.MessagesEntity;

import java.util.List;

public interface IMessagesService {
    boolean addMessage(String request, String answer, int idTravel,int idTC);
    int addAnswer(String answerText, int idMessages);
    boolean deleteMessage(int idMessage);
    List<MessagesEntity> getMessages(int idTC);
}
