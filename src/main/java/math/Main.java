package math;


import math.business.Answer;
import math.business.Choice;
import math.business.Question;
import math.business.User;
import math.dao.AnswerDao;
import math.dao.ChoiceDao;
import math.dao.QuestionDao;
import math.dao.UserDao;
import math.utils.DataConnect;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static UserDao um;
    private static AnswerDao am;
    private static ChoiceDao cm;
    private static QuestionDao qm;
    private static User userConnected;

    public static void main(String[] args) {
        try {
            um = new UserDao(DataConnect.getConnection());
            am = new AnswerDao(DataConnect.getConnection());
            cm = new ChoiceDao(DataConnect.getConnection());
            qm = new QuestionDao(DataConnect.getConnection());
            System.out.println(um.getUser("amed"));
            showFirstMenu();
            showHome();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showFirstMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose you action ");
        System.out.println("1. Subscribe");
        System.out.println("2. Connect");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                subscribe();
                break;
            case "2":
                connect();
                break;
            default:
                System.out.println("Please, choose a choice from the menu");
                showFirstMenu();
        }

    }

    final static String format = "dd/MM/yyyy";

    private static void subscribe() {
        System.out.println("You want to subscribe, please give us your infos");
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your name ?");
        String name = sc.nextLine();
        System.out.println("What is your lastname ? ");
        String lastname = sc.nextLine();
        System.out.println("What is your login");
        String login = sc.nextLine();
        System.out.println("What is your password");
        String password = sc.nextLine();
        System.out.println("What is your sex");
        String sex = sc.nextLine();
        System.out.println("What is your birthday ? format(" + format + ")");
        String birthday = sc.nextLine();
        System.out.println("What is your description ?");
        String description = sc.nextLine();
        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setLogin(login);
        user.setPassword(password);
        user.setSexe(sex);
        user.setBirthday(StringToDate(birthday));
        user.setDescription(description);
        user = um.createUser(user);
        if (user != null) {
            System.out.println("CREATION OK ! ");
        } else {
            System.out.println("THERE IS A MISTAKE, TOO BAD :(");
        }
        showFirstMenu();
    }

    private static Date StringToDate(String date) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void connect() {
        System.out.println("To connect, you have to insert you login, then your password. ");
        System.out.println("Login");
        Scanner sc = new Scanner(System.in);
        String login = sc.nextLine();
        System.out.println("Password");
        String password = sc.nextLine();
        User user = getIfUserExist(um.getAllUsers(), login, password);
        if (user != null) {
            System.out.println("You Are connected !");
            userConnected = user;
        } else {
            System.out.println("You Are Not connected, please try again");
            connect();
        }
    }

    private static User getIfUserExist(List<User> users, String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void showHome() {
        boolean isNotEnded = true;
        while (isNotEnded) {
            System.out.println("What do you want to do ?");
            System.out.println("1. Show personnal profile");
            System.out.println("2. Show Matches");
            System.out.println("3. Answers questions");
            System.out.println("4. Disconnect");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    showProfile();
                    break;
                case "2":
                    showMatches();
                    break;
                case "3":
                    answerQuestions();
                    break;
                case "4":
                    disconnect();
                    isNotEnded = false;
                    showFirstMenu();
                    break;
                default:
                    System.out.println("Please, choose a choice from the menu");
                    showHome();
            }
        }

    }

    private static void showProfile() {
        System.out.println(userConnected.toString());
    }

    private static void showMatches() {
        List<User> users = um.getAllUsers();
        for (User user : users) {
            System.out.println(user.toPresent());
            System.out.println("Compatibility = " + getComptability(userConnected, user));
            showAnsweredQuestions(user);
            System.out.println("\n");
        }
    }

    private static void showAnsweredQuestions(User user){
        List<Answer> answers = am.getAllAnswerFromUser(user.getId());
        for(Answer answer : answers){
            System.out.println(qm.getQuestionFromChoice(answer.getChoice().getId()).getText());
            System.out.println(answer.getChoice().getText());
        }

    }

    private static int getComptability(User user1, User user2) {
        List<Answer> answersUser1 = am.getAllAnswerFromUser(user1.getId());
        List<Answer> answersUser2 = am.getAllAnswerFromUser(user2.getId());
        if (answersUser1.size() > answersUser2.size()) {
            return compareTwoAnswers(answersUser2, answersUser1);
        } else {
            return compareTwoAnswers(answersUser1, answersUser2);
        }
    }

    private static int compareTwoAnswers(List<Answer> answers, List<Answer> answersToCompare) {
        int numberOfSameAnswer = 0;
        for (Answer answer : answers) {
            int x = 0;
            for (Answer answerToCompare : answersToCompare) {
                if (answer.getChoice().getId() == answerToCompare.getChoice().getId()) {
                    ++x;
                }
            }
            if (x > 0) {
                ++numberOfSameAnswer;
            }
        }
        return numberOfSameAnswer;
    }

    private static void answerQuestions() {
        List<Answer> answeredAnswers = am.getAllAnswerFromUser(userConnected.getId());
        List<Question> questions = qm.getAllQuestion();
        List<Question> questionsToAsk = new ArrayList<>();
        for (Question question : questions) {
            //List<Answer> answers = answeredAnswer.stream().filter(answer -> question.getChoices().contains(answer.getChoice())).collect(Collectors.toList());
            List<Answer> answers = new ArrayList<>();
            for (Answer answer: answeredAnswers){
                for (Choice choice : question.getChoices()){
                    if (choice.getId() == answer.getChoice().getId()){
                        answers.add(answer);
                    }
                }
            }
            if (answers.size() == 0) {
                questionsToAsk.add(question);
            }
        }
        if (questionsToAsk.size() <= 0) {
            System.out.println("You have already answered all question available, sorry...");
            showHome();
        } else {
            Random rand = new Random();
            int n = rand.nextInt(questionsToAsk.size());
            askQuestion(questionsToAsk.get(n));
        }
    }

    private static void askQuestion(Question questionToAsk) {
        List<Choice> choices = questionToAsk.getChoices();
        System.out.println(questionToAsk.getText());
        for (Choice choice : choices) {
            System.out.println(String.valueOf(choices.indexOf(choice) + 1) + " " + choice.getText());
        }

        Scanner sc = new Scanner(System.in);
        int choice = Integer.valueOf(sc.nextLine());
        if (choice <= choices.size() && choice > 0) {
            am.addAnswer(new Answer(userConnected, choices.get(choice - 1)));
            System.out.println("Answer registered, what do you want to do now ?");
            System.out.println("1. Answer a new question");
            System.out.println("2. Go back to the menu");
            String menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "1":
                    answerQuestions();
                    break;
                case "2":
                    showHome();
                    break;
                default:
                    System.out.println("Please, choose a valid task to accomplish");
            }
        } else {
            System.out.println("Please, choose a valid answer");
            askQuestion(questionToAsk);
        }
    }

    private static void disconnect() {
        userConnected = null;
    }


}
