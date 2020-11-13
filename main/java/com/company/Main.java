package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try (Scanner sc = new Scanner(System.in)) {
            System.out.println("enter input files (userFile and tourismFile)");
            String userIn = sc.next();
            String tourismIn = sc.next();
            TourOperatorQuery query = new TourOperatorQuery(userIn, tourismIn);
            System.out.println("queries : \n" +
                                "register - 0\n" +
                                "login - 1\n" +
                                "logout - 2\n" +
                                "view all tourisms - 3\n" +
                                "search by tour id - 4\n" +
                                "top N tourisms - 5\n" +
                                "exit - 6");
            int i;
            String token;
            boolean finish = false;
            while (!finish) {
                token = sc.next();
                try {
                    i = Integer.parseInt(token);
                    switch (i) {
                        case 0:
                            System.out.println("enter user:");
                            User tmp = new User();
                            tmp.setName(sc.next());
                            tmp.setLogin(sc.next());
                            tmp.setEmail(sc.next());
                            tmp.setPassword(sc.next());
                            String role = sc.next().toUpperCase();
                            boolean roleError = true;
                            for (User.Role val : User.Role.values()) {
                                if (val.getValue().equals(role)) {
                                    tmp.setRole(val);
                                    roleError = false;
                                    break;
                                }
                            }
                            if (roleError) {
                                System.out.println("wrong format of field Role");
                            }
                            try {
                                System.out.println(query.register(tmp));
                            } catch (Exception ex) {
                                System.out.println("can't register a admin");
                            }
                            break;
                        case 1:
                            System.out.println("enter login, password");
                            String login = sc.next();
                            String pswrd = sc.next();
                            System.out.println(query.logIn(login, pswrd));
                            break;
                        case 2:
                            if (query.isLogged()) {
                                query.logOut();
                                System.out.println("log out successfully");
                            } else {
                                System.out.println("you are not logged in");
                            }
                            break;
                        case 3:
                            if (query.isLogged()) {
                                query.viewAllTours().forEach(System.out::println);
                            } else {
                                System.out.println("you are not logged in");
                            }
                            break;
                        case 4:
                            if (query.isLogged()) {
                                System.out.println("enter tour id:");
                                int id = sc.nextInt();
                                query.searchByID(id).forEach(System.out::println);
                            } else {
                                System.out.println("you are not logged in");
                            }
                            break;
                        case 5:
                            if (query.isLogged()) {
                                System.out.println("enter n:");
                                int n = sc.nextInt();
                                query.topN(n).forEach(System.out::println);
                            } else {
                                System.out.println("you are not logged in");
                            }
                            break;
                        case 6:
                            finish = true;
                            break;
                        default:
                            System.out.println("wrong query");
                    }
                } catch(InputMismatchException | NumberFormatException ex) {
                    System.out.println("wrong input");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
