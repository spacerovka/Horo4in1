package com.spacerovka.horo4in1;

/**
 * Created by mbrunarskiy on 1/3/17.
 */

public class Astros {

    public static String getHyraxAstro(String astro){
        switch (astro){
            case "aries":
                return "Овен";
            case "taurus":
                return "Телец";
            case "gemini":
                return "Близнецы";
            case "cancer":
                return "Рак";
            case "leo":
                return "Лев";
            case "virgo":
                return "Дева";
            case "libra":
                return "Весы";
            case "scorpio":
                return "Скорпион";
            case "sagittarius":
                return "Стрелец";
            case"capricorn":
                return "Козерог";
            case "aquarius":
                return "Водолей";
            case "pisces":
                return "Рыбы";
            default:
                return null;
        }
    }
}
