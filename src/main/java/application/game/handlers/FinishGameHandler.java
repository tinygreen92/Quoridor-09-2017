package application.game.handlers;

import application.game.GameService;
import application.game.GameSessionService;
import application.game.messages.FinishGame;
import application.websocket.MessageHandler;
import application.websocket.MessageHandlerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Component
public class FinishGameHandler extends MessageHandler<FinishGame> {
    @NotNull
    private GameSessionService gameSessionService;

    @NotNull
    private GameService gameService;

    @NotNull
    private MessageHandlerContainer messageHandlerContainer;

    public FinishGameHandler(@NotNull GameSessionService gameSessionService,
                           @NotNull GameService gameService,
                           @NotNull MessageHandlerContainer messageHandlerContainer) {
        super(FinishGame.class);
        this.gameSessionService = gameSessionService;
        this.gameService = gameService;
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @PostConstruct
    private void init() {
        messageHandlerContainer.registerHandler(FinishGame.class, this);
    }

    @Override
    public void handle(@NotNull FinishGame message, @NotNull Long userId) {
        gameSessionService.handleUnexpectedEnding(userId, message, gameService.getAnticipatedSteps().get(userId));
    }
}
