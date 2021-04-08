package com.iitu.kz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Logic {
    private static final Scanner in = new Scanner(System.in);

    private static Map<String, String> db = new HashMap<>();
    private static Map<String, String> dw = new HashMap<>();
    private static Map<String, String> dd = new HashMap<>();

    private static int temp = 0;

    public void calculate(String varName, String pseudo_ops, String variable){

        switch (varName){
            case "ADD", "SUB", "MUL", "DIV" -> {
                getCalculation(pseudo_ops, variable.toUpperCase(), varName);
            }
            case "INC"->{
                inc(pseudo_ops);
            }
            case "DEC"->{
                dec(pseudo_ops);
            }
            case "MOV"->{
                mov(pseudo_ops, variable.toUpperCase());
            }
            case "XCHG"->{
                xchg(pseudo_ops, variable.toUpperCase());
            }
            default -> {
                while (variable.charAt(0) == ' ' || variable.charAt(0) == 39 || variable.charAt(0) == 34)
                    variable = deleteCharAt(variable, 0);
                while (variable.charAt(variable.length() - 1) == ' '
                        || variable.charAt(variable.length() - 1) == 39 || variable.charAt(variable.length() - 1) == 34)
                    variable = deleteCharAt(variable, variable.length() - 1);
                System.out.println(variable);
                if (pseudo_ops.toLowerCase().equals("db")) {
                    if (isNum(variable))
                        db.put(varName, variable + 'd');
                    else
                        db.put(varName, variable);
                } else if (pseudo_ops.toLowerCase().equals("dw")) {
//                    pseudo_ops = in.next();
                    dw.put(varName, pseudo_ops);
                }
            }

        }

    }

    private void xchg(String var_a, String var_b) {
        String tempVar = db.get(var_a);
        if (db.containsKey(var_a)) {
            if (db.containsKey(var_b)) {
                db.replace(var_a, db.get(var_b));
                db.replace(var_b, tempVar);
            } else System.out.println("Can not calculate");
            System.out.println(var_a+ ": " + db.get(var_a));
            System.out.println(var_b+ ": " + db.get(var_b));
        } else if (dw.containsKey(var_a)) {
            if (db.containsKey(var_b)) {
                dw.replace(var_a, db.get(var_b));
                dw.replace(var_b, tempVar);
            } else if (dw.containsKey(var_b)) {
                dw.replace(var_a, dw.get(var_b));
                dw.replace(var_b, tempVar);
            } else System.out.println("Can not calculate");
            System.out.println(var_a+ ": " + db.get(var_a));
            System.out.println(var_b+ ": " + db.get(var_b));
        } else if (dd.containsKey(var_a)) {
            if (db.containsKey(var_b)) {
                dd.replace(var_a, db.get(var_b));
                dd.replace(var_b, tempVar);
            } else if (dw.containsKey(var_b)) {
                dd.replace(var_a, dw.get(var_b));
                dd.replace(var_b, tempVar);
            } else if (dd.containsKey(var_b)) {
                dd.replace(var_a, dd.get(var_b));
                dd.replace(var_b, tempVar);
            } else System.out.println("Can not calculate");
            System.out.println(var_a+ ": " + db.get(var_a));
            System.out.println(var_b+ ": " + db.get(var_b));
        } else {
            System.out.println("Can not calculate");
        }
    }

    private void mov(String var_a, String var_b) {
        if (db.containsKey(var_a)) {
            if (db.containsKey(var_b)) db.replace(var_a, db.get(var_b));
            else if (dw.containsKey(var_b)) System.out.println("Can not calculate");
            else if (dd.containsKey(var_b)) System.out.println("Can not calculate");
            else {
                if (isNum(var_b)) db.replace(var_a, var_b + 'd');
                else db.replace(var_a, var_b);
            }
            System.out.println(var_a+ ": " + db.get(var_a));
            System.out.println(var_b+ ": " + db.get(var_b));
        } else if (dw.containsKey(var_a)) {
            if (db.containsKey(var_b)) dw.replace(var_a, db.get(var_b));
            else if (dw.containsKey(var_b)) dw.replace(var_a, dw.get(var_b));
            else if (dd.containsKey(var_b)) System.out.println("Can not calculate");
            else {
                if (isNum(var_b)) dw.replace(var_a, var_b + 'd');
                else dw.replace(var_a, var_b);
            }
            System.out.println(var_a+ ": " + dw.get(var_a));
            System.out.println(var_b+ ": " + dw.get(var_b));
        } else if (dd.containsKey(var_a)) {
            if (db.containsKey(var_b)) dd.replace(var_a, db.get(var_b));
            else if (dw.containsKey(var_b)) dd.replace(var_a, dw.get(var_b));
            else if (dd.containsKey(var_b)) dd.replace(var_a, dd.get(var_b));
            else {
                if (isNum(var_b)) dw.replace(var_a, var_b + 'd');
                else dw.replace(var_a, var_b);
            }
            System.out.println(var_a+ ": " + dw.get(var_a));
            System.out.println(var_b+ ": " + dw.get(var_b));
        }
    }

    private void dec(String var) {
        if (db.containsKey(var)) {
            temp = convertToDec(db.get(var)) - 1;
            db.replace(var, castType(temp, db.get(var)));
            System.out.println(db.get(var));
        } else if (dw.containsKey(var)) {
            temp = convertToDec(dw.get(var)) - 1;
            dw.replace(var, castType(temp, dw.get(var)));
            System.out.println(dw.get(var));
        } else if (dd.containsKey(var)) {
            temp = convertToDec(dd.get(var)) - 1;
            dd.replace(var, castType(temp, dd.get(var)));
            System.out.println(dd.get(var));
        }
    }

    private void inc(String var) {
        if (db.containsKey(var)) {
            temp = convertToDec(db.get(var)) + 1;
            db.replace(var, castType(temp, db.get(var)));
            System.out.println(db.get(var));
        } else if (dw.containsKey(var)) {
            temp = convertToDec(dw.get(var)) + 1;
            dw.replace(var, castType(temp, dw.get(var)));
            System.out.println(dw.get(var));
        } else if (dd.containsKey(var)) {
            temp = convertToDec(dd.get(var)) + 1;
            dd.replace(var, castType(temp, dd.get(var)));
            System.out.println(dd.get(var));
        }
    }

    private void getCalculation(String var_a, String var_b, String operation) {
        if (db.containsKey(var_a)) {
            if (dw.containsKey(var_b)) {
                System.out.println("Can not calculate");
                return;
            } else if (dd.containsKey(var_b)) {
                System.out.println("Can not calculate");
                return;
            }
//            temp = convertToDec(db.get(var_a)) / convertToDec(db.get(var_b));
            temp = operationType(convertToDec(db.get(var_a)), convertToDec(db.get(var_b)), operation);
            db.replace(var_a, castType(temp, db.get(var_a)));
            System.out.println(db.get(var_a));
        } else if (dw.containsKey(var_a)) {
            if (dw.containsKey(var_b)) {
//                temp = convertToDec(dw.get(var_a)) / convertToDec(dw.get(var_b));
                temp = operationType(convertToDec(dw.get(var_a)), convertToDec(dw.get(var_b)), operation);
                dw.replace(var_a, castType(temp, dw.get(var_a)));
            } else if (db.containsKey(var_b)) {
//                temp = convertToDec(dw.get(var_a)) / convertToDec(db.get(var_b));
                temp = operationType(convertToDec(dw.get(var_a)), convertToDec(db.get(var_b)), operation);
                dw.replace(var_a, castType(temp, dw.get(var_a)));
            } else if (dd.containsKey(var_b)) {
                System.out.println("Can not calculate");
                return;
            }
            System.out.println(dw.get(var_a));
        } else if (dd.containsKey(var_a)) {
            if (dw.containsKey(var_b)) {
//                temp = convertToDec(dd.get(var_a)) / convertToDec(dw.get(var_b));
                temp = operationType(convertToDec(dd.get(var_a)), convertToDec(dw.get(var_b)), operation);
                dd.replace(var_a, castType(temp, dw.get(var_a)));
            } else if (db.containsKey(var_b)) {
//                temp = convertToDec(dd.get(var_a)) / convertToDec(db.get(var_b));
                temp = operationType(convertToDec(dd.get(var_a)), convertToDec(db.get(var_b)), operation);
                dd.replace(var_a, castType(temp, dw.get(var_a)));
            } else if (dd.containsKey(var_b)) {
//                temp = convertToDec(dd.get(var_a)) / convertToDec(dd.get(var_b));
                temp = operationType(convertToDec(dd.get(var_a)), convertToDec(dd.get(var_b)), operation);
                dd.replace(var_a, castType(temp, dd.get(var_a)));
            }
            System.out.println(dd.get(var_a));
        }
    }

    private int operationType(int var_a, int var_b, String operation){
        return switch (operation) {
            case "MUL" -> var_a * var_b;
            case "DIV" -> var_a / var_b;
            case "ADD" -> var_a + var_b;
            case "SUB" -> var_a - var_b;
            default -> 0;
        };
    }

    public static boolean isNum(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


    public static String castType(int temp, String var_a) {
        if (typeOf(var_a).equals("h"))
            return (Integer.toHexString(temp) + "h");
        else if (typeOf(var_a).equals("d"))
            return (temp + "d");
        else if (typeOf(var_a).equals("q"))
            return (Integer.toOctalString(temp) + "q");
        else
            return (Integer.toBinaryString(temp) + "b");
    }

    public static int convertToDec(String str) {
        if (typeOf(str).equals("h"))
            return Integer.parseInt(str.substring(0, str.length() - 1), 16);
        else if (typeOf(str).equals("q"))
            return Integer.parseInt(str.substring(0, str.length() - 1), 8);
        else if (typeOf(str).equals("b"))
            return Integer.parseInt(str.substring(0, str.length() - 1), 2);
        else if (typeOf(str).equals("d"))
            return Integer.parseInt(str.substring(0, str.length() - 1));
        else
            return Integer.parseInt(str);
    }

    public static String typeOf(String str) {
        return str.substring(str.length() - 1, str.length()).toLowerCase();
    }

    public static String deleteCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

}
