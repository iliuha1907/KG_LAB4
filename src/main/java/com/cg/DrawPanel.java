package com.cg;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private int numCellsX = 90;
    private int numCellsY;
    private int cellSize = 30;
    private ArrayList<java.awt.Point> points = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoordinates(g);
        drawCoordinateRectangles(numCellsX, g);

        for (java.awt.Point point : points) {
            fillCell(point.x, point.y, cellSize, g);
        }
    }

    private void drawCoordinates(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = getBounds();

        Line2D yLine = new Line2D.Double((int)(bounds.width / 2), 0, (int)(bounds.width / 2), bounds.height);
        Line2D xLine = new Line2D.Double(0, (int)(bounds.height / 2), bounds.width, (int)(bounds.height / 2));


        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));

        g2.draw(xLine);
        g2.draw(yLine);
    }

    private void drawCoordinateRectangles(int n, Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = getBounds();

        cellSize = (bounds.width < bounds.height ? bounds.width : bounds.height) / n;

        int nX = bounds.width / cellSize;
        int nY = bounds.height / cellSize;

        numCellsY = nY * 2;

        g2.setStroke(new BasicStroke(1));
        for (int i = -nX / 2; i < nX / 2; i++){
            Line2D line = new Line2D.Double((int)(bounds.width / 2) + cellSize * i,0,(int)(bounds.width / 2) + cellSize * i,bounds.height);

            g2.draw(line);
        }

        for (int i = -nY / 2; i < nY / 2; i++){
            Line2D line = new Line2D.Double(0,(int)(bounds.height / 2) + cellSize * i,bounds.width,(int)(bounds.height / 2) +cellSize * i);
            g2.draw(line);
        }

    }

    private void fillCell(int x, int y, int cellSize, Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = getBounds();

        g2.fillRect((bounds.width / 2) + cellSize * (x - 1), (bounds.height / 2) - cellSize * (y ), cellSize, cellSize);
    }


    public void drawPoints(ArrayList<java.awt.Point> points){
        this.points = points;
        Point<Integer> deltas = getMaxDxDy(points);

        if (numCellsX / 2 < deltas.getX() || numCellsX / 2 + 6 > deltas.getX())
            numCellsX = deltas.getX() * 2 + 6;

        if (numCellsY / 2 < deltas.getY())
            numCellsX = deltas.getY() * 2 + 2;

        repaint();
    }

    private Point<Integer> getMaxDxDy(ArrayList<java.awt.Point> points){
        int dx = 0;
        int dy = 0;
        for (java.awt.Point point : points){
            if (Math.abs(point.x) > dx)
                dx = Math.abs(point.x);

            if (Math.abs(point.y) > dy)
                dy = Math.abs(point.y);
        }
        return new Point<>(dx, dy);
    }


    public static class Point<T> {
        T x;
        T y;

        public Point(T x, T y) {
            this.x = x;
            this.y = y;
        }

        public T getX() {
            return x;
        }

        public void setX(T x) {
            this.x = x;
        }

        public T getY() {
            return y;
        }

        public void setY(T y) {
            this.y = y;
        }
    }


}
