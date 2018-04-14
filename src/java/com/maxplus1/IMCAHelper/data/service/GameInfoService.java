package com.maxplus1.IMCAHelper.data.service;

import com.maxplus1.IMCAHelper.data.bean.Game;
import com.maxplus1.IMCAHelper.data.bean.GameInfo;
import com.maxplus1.IMCAHelper.cache.ConfigCache;
import com.maxplus1.IMCAHelper.data.remote.IGameInfoService;
import com.maxplus1.IMCAHelper.utils.YmlUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GameInfoService implements IGameInfoService {

    @Value("${game.file}")
    private String gameFilePath;

    @Override
    public Game getGameInfo() {
        if(ConfigCache.GAME_CACHE.isEmpty()){
            GameInfo gameInfo = YmlUtils.read(gameFilePath, GameInfo.class);
            ConfigCache.GAME_CACHE.add(gameInfo.getGame());
        }
        return ConfigCache.GAME_CACHE.get(0);
    }

    @Override
    public void saveGameInfo(Game game) {
        //写入Yml文本
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGame(game);
        YmlUtils.write(gameFilePath,gameInfo);
        //清空缓存
        ConfigCache.GAME_CACHE.clear();
    }
}
