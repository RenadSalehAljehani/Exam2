package com.example.exam2.Service;

import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    static ArrayList<User> users = new ArrayList<>();

    //CRUD
    // Get all Users
    public ArrayList<User> getUsers(){
        return users;
    }

    // Add a new user
    public void addUser(User user){
        users.add(user);
    }

    // Update user
    public boolean updateUser(String ID, User user){
        for (int i = 0; i < users.size() ; i++) {
            if(users.get(i).getID().equalsIgnoreCase(ID)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    // Delete user
    public boolean deleteUser(String ID){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getID().equalsIgnoreCase(ID)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    /* Create an endpoint that takes a
    balance then returns all users have this balance or above . */
    public ArrayList<User> getUsersByBalance(double balance){
        ArrayList<User> usersByBalanceList = new ArrayList<>();
        for (User user: users) {
            if(user.getBalance() >= balance){
                usersByBalanceList.add(user);
            }
        }
        if(usersByBalanceList.isEmpty()){
            return null;
        }
        return usersByBalanceList;
    }

    /* Create an endpoint that takes an age then return
    all User who have this age or above .*/
    public ArrayList<User> getUsersByAge(int age){
        ArrayList<User> usersByAgeList = new ArrayList<>();
        for (User user: users){
            if(user.getAge() >= age){
                usersByAgeList.add(user);
            }
        }
        if(usersByAgeList.isEmpty()){
            return null;
        }
        return usersByAgeList;
    }
}