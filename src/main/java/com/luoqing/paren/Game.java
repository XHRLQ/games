package com.luoqing.paren;

import java.util.Scanner;

/**
 * Created by lq on 2017/8/22.
 */
public abstract class Game {

    public void begin() {
        boolean tag = true;
        int command;
        command = menu();
        while (tag) {
            boolean result;
            result = dispose(command);
            if (!result) {
                break;
            }
            command = endMenu();
        }
        gameExit();
    }

    protected int menu() {
        Scanner sc = new Scanner(System.in);
        int command;
        System.out.println("**------------------------------------菜单------------------------------------**\n1-开始游戏\n2-退出");
        command = sc.nextInt();
        return command;
    }

    protected int endMenu() {
        Scanner sc = new Scanner(System.in);
        int command;
        System.out.println("\n**------------------------------------菜单------------------------------------**\n1-重新开始\n2-退出游戏");
        command = sc.nextInt();
        return command;
    }

    protected void gameExit() {
        System.out.println("成功退出游戏");
    }


    protected boolean dispose(int command){
        switch (command) {
            case 1:
                startGame();
                return true;
            case 2:
                return false;
            default:
                System.out.println("输入有误，直接退出！");
                return false;
        }
    }

    protected abstract void startGame();

}
