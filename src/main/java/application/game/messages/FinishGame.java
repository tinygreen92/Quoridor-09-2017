package application.game.messages;

import application.websocket.Message;

@SuppressWarnings("unused")
public class FinishGame extends Message {
    private Boolean isWon;

    public FinishGame(Boolean isWon) {
        this.isWon = isWon;
    }

    public Boolean getWon() {
        return isWon;
    }

    public void setWon(Boolean won) {
        isWon = won;
    }
}
