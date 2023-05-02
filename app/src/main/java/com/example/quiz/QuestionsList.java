package com.example.quiz;

public class QuestionsList {

  private String option1, option2, option3, option4, question, answer, userAnswer;

  public QuestionsList(String question, String option1, String option2, String option3, String option4, String answer, String userAnswer) {
    this.option1 = option1;
    this.option2 = option2;
    this.option3 = option3;
    this.option4 = option4;
    this.question = question;
    this.answer = answer;
    this.userAnswer = userAnswer;
  }

  public String getOption1() {
    return option1;
  }

  public String getOption2() {
    return option2;
  }

  public String getOption3() {
    return option3;
  }

  public String getOption4() {
    return option4;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  public String getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(String userAnswer) {
    this.userAnswer = userAnswer;
  }
}
