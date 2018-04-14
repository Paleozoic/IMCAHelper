package com.maxplus1.IMCAHelper.ctrl;

import com.maxplus1.IMCAHelper.data.bean.Game;
import com.maxplus1.IMCAHelper.data.service.GameInfoService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class HelloController implements Initializable {

    @FXML
    private TextField goldField;
    @FXML
    private TextField baField;
    @FXML
    private TextField biField;
    @FXML
    private TextField bhField;
    @FXML
    private TextField cmdField;

    // Be aware: This is a Spring bean. So we can do the following:
    @Autowired
    private GameInfoService gameInfoService;

    @FXML
    private void saveGameInfo(final Event event) {
        Game game = new Game();
        game.setGold(goldField.getText());
        game.setBi(biField.getText());
        game.setBh(bhField.getText());
        game.setBa(baField.getText());
        game.setCmd(cmdField.getText());
        //写入Yml文本
        gameInfoService.saveGameInfo(game);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Game game = gameInfoService.getGameInfo();
        goldField.setText(game.getGold());
        biField.setText(game.getBi());
        bhField.setText(game.getBh());
        baField.setText(game.getBa());
        cmdField.setText(game.getCmd());
    }


}