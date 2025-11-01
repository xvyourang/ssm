package cn.xyr.mhxy.lianyao;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import cn.xyr.mhxy.lianyao.pet.*;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Config {
    /**
     * 胚子优先级
     */
    public static final ArrayList<Class<? extends Pet>> typeList = new ArrayList<Class<? extends Pet>>() {{
        add(RiYouShen.class);
        add(LeiYao.class);
        add(LeiNiaoRen.class);
        add(DaBianFu.class);
    }};
    /**
     * 最小垫书技能书(胚子/副宠)
     */
    public static int minPaveSkill = 8;
    /**
     * 主宠最小垫书技能书
     */
    public static int minMainPaveSkill = 8;

    /**
     * 胚子最少技能数
     */
    public static int minBaseSkill = 4;

    public static boolean paveCommonSkill = true;
    /**
     * 副宠比主宠多几个技能？
     */
    public static int addSkill = 3;


//    /**
//     * 配置指定类型的胚子的洗66/c66最低技能数
//     *
//     * @param type  胚子类型
//     * @param skill 最低技能数
//     */
//    public static void setMinRestSkill(Map<Class<? extends Pet>, Integer> map, int type, int skill) {
//        map.forEach((clazz, integer) -> {
//            try {
//                Constructor<? extends Pet> constructor = clazz.getConstructor(Integer.class);
//                Pet pet = constructor.newInstance(0);
//                if (pet.getType() == type) {
//                    map.put(clazz, skill);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }


    /**
     * 兽决价格
     */
    public static int bookPrice = 80;
    /**
     * 练级价格
     */
    public static int upgrade = 20;
    /**
     * 获取超过最低技能数的召唤兽时通过炼妖还是通过洗
     */
    public static boolean rest = true;

    public static void setLevel(Level Level) {
        LoggerContext iLoggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = iLoggerFactory.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level);
            ConsoleAppender<ILoggingEvent> appender = (ConsoleAppender<ILoggingEvent>) logger.getAppender("console");
            if (appender == null) {
                return;
            }
            PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
            patternLayoutEncoder.setPattern("%msg%n");
            patternLayoutEncoder.setContext(logger.getLoggerContext());
            patternLayoutEncoder.start();
            appender.setEncoder(patternLayoutEncoder);
        });
    }
}
