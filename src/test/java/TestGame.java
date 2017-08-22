import com.luoqing.paren.Game;
import com.luoqing.puke.Puke;
import com.luoqing.wuziqi.FiveInARow;

/**
 * Created by lq on 2017/8/22.
 */
public class TestGame {
    public static void main(String[] args) {
        //Game game=new Puke();
       // game.begin();
        Game game = new FiveInARow();
        game.begin();
    }
}
