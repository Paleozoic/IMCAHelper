package com.maxplus1.IMCAHelper.data.remote;

import com.maxplus1.IMCAHelper.data.bean.Game;

public interface IGameInfoService {
    Game getGameInfo();
    void saveGameInfo(Game game);
}
