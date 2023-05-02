package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuestionStorage {

  private static List<QuestionsList> flagsQuestions() {

    final List<QuestionsList> questionsList = new ArrayList<>();

    final QuestionsList question1 = new QuestionsList(
      "Вопрос 1",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    final QuestionsList question2 = new QuestionsList(
      "Вопрос 2",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 2",
      ""
    );

    final QuestionsList question3 = new QuestionsList(
      "Вопрос 3",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 3",
      ""
    );

    final QuestionsList question4 = new QuestionsList(
      "Вопрос 4",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 4",
      ""
    );

    final QuestionsList question5 = new QuestionsList(
      "Вопрос 5",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    questionsList.add(question1);
    questionsList.add(question2);
    questionsList.add(question3);
    questionsList.add(question4);
    questionsList.add(question5);

    return questionsList;
  }

  private static List<QuestionsList> capitalsQuestions() {

    final List<QuestionsList> questionsList = new ArrayList<>();

    final QuestionsList question1 = new QuestionsList(
      "Вопрос 1",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    final QuestionsList question2 = new QuestionsList(
      "Вопрос 2",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 2",
      ""
    );

    final QuestionsList question3 = new QuestionsList(
      "Вопрос 3",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 3",
      ""
    );

    final QuestionsList question4 = new QuestionsList(
      "Вопрос 4",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 4",
      ""
    );

    final QuestionsList question5 = new QuestionsList(
      "Вопрос 5",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    questionsList.add(question1);
    questionsList.add(question2);
    questionsList.add(question3);
    questionsList.add(question4);
    questionsList.add(question5);

    return questionsList;
  }

  private static List<QuestionsList> historyQuestions() {

    final List<QuestionsList> questionsList = new ArrayList<>();

    final QuestionsList question1 = new QuestionsList(
      "Вопрос 1",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    final QuestionsList question2 = new QuestionsList(
      "Вопрос 2",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 2",
      ""
    );

    final QuestionsList question3 = new QuestionsList(
      "Вопрос 3",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 3",
      ""
    );

    final QuestionsList question4 = new QuestionsList(
      "Вопрос 4",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 4",
      ""
    );

    final QuestionsList question5 = new QuestionsList(
      "Вопрос 5",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    questionsList.add(question1);
    questionsList.add(question2);
    questionsList.add(question3);
    questionsList.add(question4);
    questionsList.add(question5);

    return questionsList;
  }

  private static List<QuestionsList> personsQuestions() {

    final List<QuestionsList> questionsList = new ArrayList<>();

    final QuestionsList question1 = new QuestionsList(
      "Вопрос 1",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    final QuestionsList question2 = new QuestionsList(
      "Вопрос 2",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 2",
      ""
    );

    final QuestionsList question3 = new QuestionsList(
      "Вопрос 3",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 3",
      ""
    );

    final QuestionsList question4 = new QuestionsList(
      "Вопрос 4",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 4",
      ""
    );

    final QuestionsList question5 = new QuestionsList(
      "Вопрос 5",
      "Вариант 1",
      "Вариант 2",
      "Вариант 3",
      "Вариант 4",
      "Вариант 1",
      ""
    );

    questionsList.add(question1);
    questionsList.add(question2);
    questionsList.add(question3);
    questionsList.add(question4);
    questionsList.add(question5);

    return questionsList;
  }

  public static List<QuestionsList> getQuestions (String selectedQuizName) {
    switch (selectedQuizName) {
      case "flags": return flagsQuestions();
      case "capitals": return capitalsQuestions();
      case "history": return historyQuestions();
      case "persons": return personsQuestions();
    }

    return null;
  }
}
