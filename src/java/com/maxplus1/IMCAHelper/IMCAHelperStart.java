package com.maxplus1.IMCAHelper;

import com.maxplus1.IMCAHelper.utils.Const;
import com.maxplus1.IMCAHelper.view.HelloView;
import com.melloware.jintellitype.JIntellitype;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.GUIState;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.event.KeyEvent;
import java.util.Optional;

@SpringBootApplication
@Slf4j
public class IMCAHelperStart extends AbstractJavaFxApplicationSupport {


    public static void main(String[] args) {
        try {
            stageCloseMonitor();
            launch(IMCAHelperStart.class, HelloView.class, args);//程序运行时，此代码下的代码不会被执行
        }catch (Exception e){
            log.error("[ERROR===>>>]程序异常退出！",e);
            // 销毁热键
            JIntellitype.getInstance().unregisterHotKey(Const.PAY_GOLD);
            JIntellitype.getInstance().unregisterHotKey(Const.PRINT_CMD);
            System.exit(0);
        }
    }


    //stage
    private static void stageCloseMonitor(){
        //线程异步从后台获取stage，然后注册按钮X的事件，销毁程序（主要是全局热键的注册）
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Stage stage = null;
                while (stage==null){
                    log.debug("===================monitor stage loop================");
                    stage = GUIState.getStage();
                }
                log.info("===================monitor stage close start================");
                stage.setOnCloseRequest(event -> {
                    // 销毁热键
                    JIntellitype.getInstance().unregisterHotKey(Const.PAY_GOLD);
                    JIntellitype.getInstance().unregisterHotKey(Const.PRINT_CMD);
                    System.exit(0);
                });
                Thread.yield();
                log.info("===================monitor stage close end================");
            }
        });
        thread.start();
    }

}
