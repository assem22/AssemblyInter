package com.iitu.kz;

import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static Logic logic;

    public static void main(String[] args) {
        logic = new Logic();
        System.out.println("Syntax: VAR_NAME PSEUDO-OPS VARIABLE");
        while (true){
            menu();
        }
    }

    private static void menu(){
        System.out.println("Write request: ");
        String varName = in.next().toUpperCase();
        String pseudo_ops = in.next().toUpperCase();
        String variable = "";
        if (pseudo_ops.equals("DB") || pseudo_ops.equals("DW") || pseudo_ops.equals("DD"))
            variable = in.nextLine();
        else if (!varName.equals("INC") && !varName.equals("DEC"))
            variable = in.next();
            logic.calculate(varName, pseudo_ops, variable);
    }
}
