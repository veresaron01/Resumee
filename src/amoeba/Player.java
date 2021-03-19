package amoeba;

import java.util.ArrayList;
import java.util.List;

public class Player {

    static List<Player> playerList = new ArrayList();

    private String name;
    private boolean isWinner = false;

    public Player (String name) {
        this.name = name;
        playerList.add(this);
    }
}
