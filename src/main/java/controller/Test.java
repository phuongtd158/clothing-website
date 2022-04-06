package controller;

import utils.EncryptUtil;
import utils.ValidateUtil;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        String regex = "(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})";
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.println(EncryptUtil.encrypt(email));
    }
}
