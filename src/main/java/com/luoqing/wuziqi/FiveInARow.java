package com.luoqing.wuziqi;

import com.luoqing.paren.Game;

import java.util.Scanner;

/**
 * Created by lq on 2017/8/22.
 */
public class FiveInARow extends Game{
    private int playerFirst;
    private int fourOrEight;
    private int[][] chessboard;
    private static final int max=100;

    //class MyClient extends CliRunner{

    //}

    public void begin(String []args){
        /*
        MyClient client = new MyClient();
        client.options();
        if(client.validateOptions(args)){
            client.start();
        }*/
    }

    @Override
    protected void startGame(){
        int currentPlayer = 1;
        int []position;
        boolean result;

        chessboard=new int[max][max];
        //if(playerFirst==2){
         //   currentPlayer=2;
        //}

        while(true){
            position=play(currentPlayer);
            result=check(position,currentPlayer);
            if(result){
                System.out.println("游戏结束！恭喜玩家"+currentPlayer+"获得胜利！");
                return;
            }
            if(currentPlayer==1)
                currentPlayer=2;
            else
                currentPlayer=1;

        }
    }

    protected boolean check(int[] position,int currentPlayer){

        if(partLeftCheck(position,currentPlayer))
            return true;
        if(partRightCheck(position,currentPlayer))
            return true;
        if(partUpCheck(position,currentPlayer))
            return true;
        if(partBelowCheck(position,currentPlayer))
            return true;
        return false;

    }

    protected boolean partLeftCheck(int[] position,int currentPlayer){
        int count;
        int indexX,indexY;

        indexX=position[0];
        indexY=position[1];
        count=0;
        if(indexX-4>=0){
            for(;indexX>=position[0]-4;indexX--){
                if(chessboard[indexX][indexY]==currentPlayer){
                    count++;
                }
            }
            if(count==5)
                return true;
        }
        return false;
    }

    protected boolean partRightCheck(int[] position,int currentPlayer){
        int count;
        int indexX,indexY;

        indexX=position[0];
        indexY=position[1];
        count=0;
        if(indexX+4<=max){
            for(;indexX<=position[0]+4;indexX++){
                if(chessboard[indexX][indexY]==currentPlayer){
                    count++;
                }
            }
            if(count==5)
                return true;
        }
        return false;
    }

    protected boolean partUpCheck(int[] position,int currentPlayer){
        int count;
        int indexX,indexY;

        indexX=position[0];
        indexY=position[1];
        count=0;
        if(indexY-4>=0){
            for(;indexY>=position[1]-4;indexY--){
                if(chessboard[indexX][indexY]==currentPlayer){
                    count++;
                }
            }
            if(count==5)
                return true;
        }
        return false;
    }

    protected boolean partBelowCheck(int[] position,int currentPlayer){
        int count;
        int indexX,indexY;

        indexX=position[0];
        indexY=position[1];
        count=0;
        if(indexY+4<=max){
            for(;indexY<=position[1]+4;indexY++){
                if(chessboard[indexX][indexY]==currentPlayer){
                    count++;
                }
            }
            if(count==5)
                return true;
        }
        return false;
    }

    protected int[] play(int currentPlayer){
        int []position=new int[2];
        Scanner sc= new Scanner(System.in);
        System.out.println(""+currentPlayer+"号玩家输入x坐标 y坐标:");

        position[0]=sc.nextInt();
        position[1]=sc.nextInt();

        chessboard[position[0]][position[1]]=currentPlayer;
        return position;
    }
}
