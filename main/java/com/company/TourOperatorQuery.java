package com.company;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class TourOperatorQuery {
    private List<User> users;
    int authorizedUserIndex;
    private List<Tourism> tourisms;

    private String userFile;

    public TourOperatorQuery(String userFile, String tourismFile) throws Exception {
        this.authorizedUserIndex = -1;
        this.userFile = userFile;
        users = new ArrayList<>();
        tourisms = new ArrayList<>();

        Scanner scUser = new Scanner(new File(userFile));
        Scanner scTour = new Scanner(new File(tourismFile));
        while (scUser.hasNextLine()) {
            String[] line = scUser.nextLine().split("[ ,]+");
            if (line.length != 5) {
                throw new Exception("wrong format of data");
            }
            User tmp = new User();
            tmp.setName(line[0]);
            tmp.setLogin(line[1]);
            tmp.setEmail(line[2]);
            tmp.setPassword(line[3]);
            switch (line[4].toUpperCase()) {
                case User.RoleKeyWord.ADMIN:
                    tmp.setRole(User.Role.ADMIN);
                    break;
                case User.RoleKeyWord.USER:
                    tmp.setRole(User.Role.USER);
                    break;
                default:
                    throw new Exception("wrong format of field Role");
            }
            users.add(tmp);
        }

        while (scTour.hasNextLine()) {
            String[] line = scTour.nextLine().split("[ ,]+");
            if (line.length != 9) {
                throw new Exception("wrong format of data");
            }
            Tourism tmp = new Tourism();
            tmp.setId(Integer.parseInt(line[0]));
            tmp.setName(line[1]);
            tmp.setTourId(Integer.parseInt(line[2]));
            tmp.setTourName(line[3]);
            tmp.setTourStartingDate(new SimpleDateFormat("dd/MM/yyyy").parse(line[4]));
            tmp.setTourEndingDate(new SimpleDateFormat("dd/MM/yyyy").parse(line[5]));
            tmp.setCategory(line[6]);
            tmp.setPrice(Integer.parseInt(line[7]));
            tmp.setRating(Double.parseDouble(line[8]));
            tourisms.add(tmp);
        }
    }

    public boolean isLogged() {
        return authorizedUserIndex != -1;
    }

    public String register(User user) throws Exception {
        if (user.getRole() == User.Role.ADMIN) {
            throw new Exception("register() doesn't accept ADMIN");
        }
        for (User value : users) {
            if (user.getLogin().equals(value.getLogin())) {
                return "you are already registered";
            }
        }
        add(user);
        return "registration successful";
    }

    public String logIn(String login, String password) {
        if (authorizedUserIndex != -1) {
            return "first logout to logIn";
        } else {
            for (int i = 0; i < users.size(); ++i) {
                if (login.equals(users.get(i).getLogin())) {
                    if (password.equals(users.get(i).getPassword())) {
                        authorizedUserIndex = i;
                        return "you are logged in";
                    } else {
                        return "wrong password";
                    }
                }
            }
        }
        return "wrong login";
    }

    public void logOut() {
        authorizedUserIndex = -1;
    }

    public List<Tourism> viewAllTours() throws Exception {
        if (authorizedUserIndex == -1) {
            throw new Exception("login to make queries");
        }
        return new ArrayList<>(tourisms);
    }

    public List<Tourism> searchByID(int id) throws Exception {
        if (authorizedUserIndex == -1) {
            throw new Exception("login to make queries");
        } else if (users.get(authorizedUserIndex).getRole() != User.Role.ADMIN) {
            throw new Exception("only available for admin");
        }
        List<Tourism> result = new ArrayList<>();
        for (Tourism val : tourisms) {
            if (val.getTourId() == id) {
                result.add(val);
            }
        }
        return result;
    }

    public List<Object> topN(int n) throws Exception {
        if (n > tourisms.size()) {
            throw new Exception("n is greater than size of tours");
        }
        Object[] sorted =  tourisms.stream().sorted((o1, o2) -> Double.compare(o2.getRating(), o1.getRating())).toArray();
        return Arrays.asList(Arrays.copyOfRange(sorted, 0, n));
    }

    public void averageRating() {
        // <company, <category, {count, rating}>>
        HashMap<String, HashMap<String, Double[]>> dict = new HashMap<>();
        for (Tourism val : tourisms) {
            if (dict.containsKey(val.getName())) {
                if (dict.get(val.getName()).containsKey(val.getCategory())) {
                    double count = dict.get(val.getName()).get(val.getCategory())[0];
                    double avRating = dict.get(val.getName()).get(val.getCategory())[1];
                    if (count == 0) {
                        avRating = val.getRating();
                    } else {
                        avRating *= count / (count + 1);
                    }
                    ++count;
                    dict.get(val.getName()).put(val.getCategory(), new Double[]{count, avRating});
                }
            }
        }

    }


    /**
     * adds user both in users and file
     * @param user
     */
    private void add(User user) {
        users.add(user);
        try (FileWriter writer = new FileWriter(userFile, true)) {
            writer.write(user.toString() + '\n');
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
