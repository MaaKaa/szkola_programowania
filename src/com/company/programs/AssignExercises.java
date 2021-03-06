package com.company.programs;

import com.company.model.Exercise;
import com.company.model.Solution;
import com.company.model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static com.company.db.DatabaseConnection.getEfficientConnection;
import static com.company.programs.AdminPanel.printAdminMainMenu;

public class AssignExercises {

    public static void assignExercises() {
        Solution solution = new Solution();

        System.out.println("All solutions:");
        try {
            System.out.println(Arrays.toString(solution.loadAll(getEfficientConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printAssignExercisesMenu();
    }

    public static void printAssignExercisesMenu() {
        System.out.println("Press:");
        System.out.println("1 - Add: assign an exercise to a user ");
        System.out.println("2 - View: display solutions of a particular user");
        System.out.println("3 - Quit the program and display the Main Menu.");

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int choice = 0;

        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    assignExercise();
                    break;
                case 2:
                    viewUsersSolutions();
                    break;
                case 3:
                    quit = true;
                    printAdminMainMenu();
                    break;
            }
        }
    }

    public static void assignExercise(){
        try {
            Scanner scanner = new Scanner(System.in);
            Solution solution = new Solution();
            Exercise exercise = new Exercise();
            User user = new User();

            System.out.println("ASSIGN AN EXERCISE:");
            System.out.println(Arrays.toString(user.loadAll(getEfficientConnection())));
            System.out.println("Choose a user: enter user ID: ");
            int userId = scanner.nextInt();
            System.out.println(Arrays.toString(exercise.loadAll(getEfficientConnection())));
            System.out.println("Choose an exercise: enter ID: ");
            int exerciseId = scanner.nextInt();
            solution.setUser(userId);
            solution.setExercise(exerciseId);
            solution.saveToDB(getEfficientConnection());
            System.out.println("The exercise has been assigned.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            printAssignExercisesMenu();
        }
    }

    public static void viewUsersSolutions(){
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.println("VIEW USER'S SOLUTIONS:");
        System.out.println("Press user ID: ");
        try {
            int userId = scanner.nextInt();
            System.out.println(Arrays.toString(solution.loadAllByUserId(getEfficientConnection(), userId)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            printAssignExercisesMenu();
        }
    }
}
