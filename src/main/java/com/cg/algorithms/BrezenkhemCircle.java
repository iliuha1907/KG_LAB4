package com.cg.algorithms;

import java.awt.*;
import java.util.ArrayList;

public class BrezenkhemCircle {

    public ArrayList<Point> brezenkhemCircle(int x1, int y1, int r) {
        ArrayList<Point> points = new ArrayList<>();

        int x = 0;
        int y = r;
        int e = 3 - 2 * r;
        points.add(new Point(x + x1, y + y1));
        points.add(new Point(x + x1, -y + y1));
        points.add(new Point(-x + x1, y + y1));
        points.add(new Point(-x + x1, -y + y1));

        points.add(new Point(y + x1, x + y1));
        points.add(new Point(-y + x1, x + y1));
        points.add(new Point(y + x1, -x + y1));
        points.add(new Point(-y + x1, -x + y1));
        while (x < y) {
            if (e >= 0) {
                e = e + 4 * (x - y) + 10;
                x = x + 1;
                y = y - 1;
            } else {
                e = e + 4 * x + 6;
                x = x + 1;
            }

            points.add(new Point(x + x1, y + y1));
            points.add(new Point(x + x1, -y + y1));
            points.add(new Point(-x + x1, y + y1));
            points.add(new Point(-x + x1, -y + y1));

            points.add(new Point(y + x1, x + y1));
            points.add(new Point(-y + x1, x + y1));
            points.add(new Point(y + x1, -x + y1));
            points.add(new Point(-y + x1, -x + y1));


        }


        return points;
    }
}
