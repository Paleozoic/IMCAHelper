package com.maxplus1.IMCAHelper.utils;

import lombok.extern.slf4j.Slf4j;
import org.ho.yaml.Yaml;

import java.io.*;

@Slf4j
public class YmlUtils {

    /**
     * @param filePath filePath "/game.yml"
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T read(String filePath, Class<T> clz) {
//        InputStream inputStream = YmlUtils.class.getResourceAsStream(filePath);
        T bean = null;
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            bean = Yaml.loadType(inputStream, clz);
        } catch (FileNotFoundException e) {
            log.error("[ERROR===>>>]文件不存在！", e);
        }
        return bean;
    }

    public static <T> void write(String filePath, T bean) {
//        String file = YmlUtils.class.getResource(filePath).getFile();
        try {
//            Yaml.dump(bean, new FileOutputStream(file));
            Yaml.dump(bean, new File(filePath));
        } catch (FileNotFoundException e) {
            log.error("[ERROR===>>>]文件不存在！", e);
        }
    }
}
