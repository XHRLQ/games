package com.luoqing.puke;

import com.luoqing.paren.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lq on 2017/8/21.
 */
public class Puke extends Game {

    /**
     * 方法不要一直使用 Public static ,如果不给外部调用就用 protected/private
     */
    private List<String> puke;

    @Override
    protected void startGame() {
        createPuke();
        handOut();
    }

    protected void createPuke() {
        puke = new ArrayList<String>();
        createPartPuke("黑桃");
        createPartPuke("红桃");
        createPartPuke("方块");
        createPartPuke("梅花");
        puke.add("大王");
        puke.add("小王");
    }

    protected void createPartPuke(String part) {
        for (int i = 2; i <= 10; i++) {
            puke.add(part + i);
        }
        puke.add(part + "A");
        puke.add(part + "J");
        puke.add(part + "Q");
        puke.add(part + "K");
    }

    @Deprecated
    protected void handOut() {
        Collections.shuffle(puke);
        // todo  优化 StringBuilder/StringBuffer
        System.out.println("******发牌结果******：");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= puke.size(); i++) {
            System.out.print(puke.get(i - 1) + " ");
            if (i % 18 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

}
