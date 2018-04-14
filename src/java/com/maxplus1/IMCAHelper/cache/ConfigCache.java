package com.maxplus1.IMCAHelper.cache;

import com.maxplus1.IMCAHelper.data.bean.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigCache {
    // 缓存
    public static  final List<Game> GAME_CACHE = Collections.synchronizedList(new ArrayList<>());

}
