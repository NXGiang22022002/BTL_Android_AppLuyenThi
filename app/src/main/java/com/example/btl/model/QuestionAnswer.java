package com.example.btl.model;

import java.io.Serializable;

public class QuestionAnswer implements Serializable {
    private int questionanswer_id;
    private int questionanswer_dapandachon;

    public QuestionAnswer(int questionanswer_id, int questionanswer_dapandachon) {
        this.questionanswer_id = questionanswer_id;
        this.questionanswer_dapandachon = questionanswer_dapandachon;
    }

    public int getQuestionanswer_id() {
        return questionanswer_id;
    }

    public int getQuestionanswer_dapandachon() {
        return questionanswer_dapandachon;
    }

    public void setQuestionanswer_id(int questionanswer_id) {
        this.questionanswer_id = questionanswer_id;
    }

    public void setQuestionanswer_dapandachon(int questionanswer_dapandachon) {
        this.questionanswer_dapandachon = questionanswer_dapandachon;
    }
}
