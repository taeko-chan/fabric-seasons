package io.github.lucaargolo.seasons.utils;

import io.github.lucaargolo.seasons.FabricSeasons;

import java.util.Calendar;
import static io.github.lucaargolo.seasons.FabricSeasons.dayOfYear;

public class TCSeason {

    public static void updateWeather() {
        dayOfYear = TCSeason.getYearDay();
        FabricSeasons.currentTemperature = TCSeason.getSeasonalTemperature(dayOfYear, 1);
        FabricSeasons.currentDownfall = TCSeason.getSeasonalDownfall(dayOfYear, 1);
    }

    public static int getYearDay() {

        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        return dayOfYear;

    }

    public static double getSeasonalDownfall(int day, int a) {

        if (a < 1) {
            a = 1;
        }

        double dayc = (1f / 365f) * day;
        double sin = Math.sin(2 * a * Math.PI * dayc);
        double sindownfall = 0.75 * Math.pow(sin, 2) + 0.25;

        return sindownfall;

    }

    public static double getSeasonalTemperature(int day, int a) {

        if (a < 1) {
            a = 1;
        }

        double dayc = (1f / 365f) * day;
        double sin = Math.sin(a * Math.PI * dayc);
        double sintemp = Math.pow(sin, 2);

        double temp = sintemp - 0.05f;

        return temp;

    }

    public static double temperatureConverter(double temp, boolean toCelcius) {

        double unit = 9f / 0.2f;

        if (toCelcius) {
            temp -= 0.2;
            return temp * unit;
        } else {
            temp += 9;
            return temp / unit;
        }

    }

}

