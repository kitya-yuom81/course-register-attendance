package main.cram;

import main.cram.view.MenuView;

public class Main {
    public static void main(String[] args) {
        System.out.println("CRAM- Course Registration & Attendance Manager");
        new MenuView().showWelcome();
    }
}