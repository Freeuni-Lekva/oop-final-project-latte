package model;

public enum QuestionType {
    question_response, fill_in_the_blank, multiple_choice, picture_response_questions;
    public static QuestionType fromString(String type){
        return QuestionType.valueOf(type.toUpperCase());
    }
    public String getType(){
        return this.name().toLowerCase();
    }

}
