package com.maxplus1.IMCAHelper.view;

import com.maxplus1.IMCAHelper.data.bean.Game;
import com.maxplus1.IMCAHelper.data.remote.IGameInfoService;
import com.maxplus1.IMCAHelper.utils.Const;
import com.melloware.jintellitype.JIntellitype;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import de.felixroske.jfxsupport.GUIState;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * TODO AbstractFxmlView的resource = getURLResource(annotation);最终会调用：
 * //    public java.net.URL getResource(String name) {
 * //        name = resolveName(name);
 * //        ClassLoader cl = getClassLoader0();
 * //        if (cl==null) {
 * //            // A system class.
 * //            return ClassLoader.getSystemResource(name);
 * //        }
 * //        return cl.getResource(name);
 * //    }
 * resolveName(name)会在那么附加类路径：com/maxplus1/IMCAHelper/com.maxplus1.IMCAHelper.fxml/hello.com.maxplus1.IMCAHelper.fxml，然后导致getResource无法找到。
 * 正确的应为：getResource("com.maxplus1.IMCAHelper.fxml/hello.com.maxplus1.IMCAHelper.fxml")  所以fxml文件不能放在resources，应该放在IMCAHelper下，或者resources创建相同的路径
 */
@FXMLView(value = "fxml/hello.fxml")
@Slf4j
public class HelloView extends AbstractFxmlView {

    @Autowired
    private IGameInfoService gameInfoService;

    public HelloView() throws AWTException {
        super();
    }


    private enum RobotInstance {
        INSTANCE;
        private Robot robot;

        //JVM会保证此方法绝对只调用一次
        RobotInstance() {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                log.error("[ERROR===>>>]Robot初始化失败！",e);
            }
        }
    }

    @PostConstruct
    private void hotKey() {
        //第一步：注册热键，第一个参数表示该热键的标识，第二个参数表示组合键，如果没有则为0，第三个参数为定义的主要热键
        JIntellitype.getInstance().registerHotKey(Const.PAY_GOLD, JIntellitype.MOD_ALT, KeyEvent.VK_F8);
        JIntellitype.getInstance().registerHotKey(Const.PRINT_CMD, JIntellitype.MOD_ALT, KeyEvent.VK_F9);


        //第二步：添加热键监听器
        JIntellitype.getInstance().addHotKeyListener(markCode -> {
            Game game = gameInfoService.getGameInfo();
            Robot robot = RobotInstance.INSTANCE.robot;
            switch (markCode) {
                case Const.PAY_GOLD:
                    log.info("===================gold start================");
                    int gold = Integer.parseInt(game.getGold());
                    for (int i = 0; i < gold/3; i++) {
                        robot.mousePress(KeyEvent.BUTTON1_MASK);
                        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    }
                    log.info("===================gold end================");
                    break;
                case Const.PRINT_CMD:
                    log.info("===================cmd start================");
                    // TODO 开启大写锁定 方法Toolkit.getDefaultToolkit().getLockingKeyState不能正确获得按键状态，所以无效
//                    capsLock(robot);
                    // 快捷键暂停游戏，并且唤出聊天框
                    click(robot,KeyEvent.VK_F10);
                    click(robot,KeyEvent.VK_M);
                    click(robot,KeyEvent.VK_R);

                    // 复制粘贴命令
                    copy(robot,game.getBa());
                    robot.delay(1500);
                    copy(robot,game.getBh());
                    robot.delay(1500);
                    copy(robot,game.getBi());
                    robot.delay(1500);
                    copy(robot,game.getCmd());

                    // 快捷键开始游戏，并且唤出聊天框
                    click(robot,KeyEvent.VK_F10);
                    click(robot,KeyEvent.VK_M);
                    click(robot,KeyEvent.VK_R);
                    log.info("===================cmd end================");
                    break;
            }
        });
    }


    private void click(Robot robot,int key){
        robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
        robot.delay(100);
    }

    private void copy(Robot robot,String str){
        if(StringUtils.isEmpty(str)){
            return;
        }
        StringSelection stringSelection = new StringSelection(str);
        //使用Toolkit对象的setContents将字符串放到粘贴板中 ；
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        click(robot,KeyEvent.VK_ENTER);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(100);
        click(robot,KeyEvent.VK_ENTER);
    }


    private void capsLock(Robot robot){
        if(!isCapsLock()){
//            click(robot,KeyEvent.VK_CAPS_LOCK);
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK,true);
        }
    }

    private static boolean isCapsLock() {
        return Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);

    }

}
