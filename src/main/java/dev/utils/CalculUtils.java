package dev.utils;

import org.springframework.stereotype.Component;

@Component
public class CalculUtils {

    public double calculerDistanceEntreDeuxPoints(
            double x1,
            double y1,
            double x2,
            double y2) {

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }
}
