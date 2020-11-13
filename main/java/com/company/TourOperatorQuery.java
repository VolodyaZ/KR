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

        try (Scanner scUser = new Scanner(new File(userFile));
            Scanner scTour = new Scanner(new File(tourismFile))) {
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
                String role = line[4].toUpperCase();
                boolean roleError = true;
                for (User.Role val : User.Role.values()) {
                    if (val.getValue().equals(role)) {
                        tmp.setRole(val);
                        roleError = false;
                        break;
                    }
                }
                if (roleError) {
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
