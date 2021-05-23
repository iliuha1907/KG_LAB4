package com.cg;

import com.cg.algorithms.Brezenkhem;
import com.cg.algorithms.BrezenkhemCircle;
import com.cg.algorithms.CDA;
import com.cg.algorithms.StepByStep;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private final int WIDTH = 1100;
    private final int HEIGHT = 800;
    private String[] methods = {"Step by step", "Brezenkhem", "Brezenkhem circle", "CDA"};

    private final DrawPanel drawingPanel = new DrawPanel();

    private final JTextField x1Input = new JTextField(21);
    private final JTextField y1Input = new JTextField(21);
    private final JTextField x2Input = new JTextField(21);
    private final JTextField y2Input = new JTextField(21);
    private final JTextField radiusInput = new JTextField(21);

    private final JButton stepByStepButton;
    private JButton goButton = new JButton("Go");
    private final JComboBox menu = new JComboBox(methods);


    private final double PANEL_SCALE_W = 1.0;
    private final double PANEL_SCALE_H = 0.6;

    private final Brezenkhem brezenkhem = new Brezenkhem();
    private final BrezenkhemCircle brezenkhemCircle = new BrezenkhemCircle();
    private final CDA cda = new CDA();
    private final StepByStep stepByStep = new StepByStep();

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        Font labelFont = new Font("Sans-Serif", Font.PLAIN, 20);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(labelFont);
        timeLabel.setBounds(10, 700, 300, 40);
        add(timeLabel);

        stepByStepButton = new JButton("step by step");
        stepByStepButton.setBounds(10, 500, 160, 40);


        menu.setBounds(10, 500, 160, 40);
        goButton.setBounds(10, 550, 160, 40);

        add(menu);
        add(goButton);
        goButton.addActionListener(e -> {
            int x1 = 0, y1 = 0, x2 = 0, y2 = 0, r = 0;
            try {
                x1 = Integer.parseInt(x1Input.getText());
                y1 = Integer.parseInt(y1Input.getText());
                if (menu.getSelectedIndex() == 2) {
                    r = Integer.parseInt(radiusInput.getText());
                } else {
                    x2 = Integer.parseInt(x2Input.getText());
                    y2 = Integer.parseInt(y2Input.getText());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error at input");
                return;
            }
            long start = System.nanoTime();
            switch (menu.getSelectedIndex()) {
                case 0 -> drawingPanel.drawPoints(stepByStep.stepByStep(x1, y1, x2, y2));
                case 1 -> drawingPanel.drawPoints(brezenkhem.bresenkhem(x1, y1, x2, y2));
                case 2 -> drawingPanel.drawPoints(brezenkhemCircle.brezenkhemCircle(x1, y1, r));
                case 3 -> drawingPanel.drawPoints(cda.cda(x1, y1, x2, y2));
            }
            long end = System.nanoTime();
            timeLabel.setText("Time: " + (end - start) / 1000 + " ms");
        });

        drawingPanel.setBounds((int) ((1 - PANEL_SCALE_W) * WIDTH), 0, (int) (PANEL_SCALE_W * WIDTH), (int) (PANEL_SCALE_H * HEIGHT));
        add(drawingPanel);


        x1Input.setBounds(670, 500, 100, 40);
        x1Input.setFont(labelFont);
        add(x1Input);

        JLabel x1Label = new JLabel("X1: ");
        x1Label.setFont(labelFont);
        x1Label.setBounds(x1Input.getX() - 35, x1Input.getY(), 50, 40);
        add(x1Label);


        y1Input.setBounds(x1Input.getX() + x1Input.getWidth() + 50, x1Input.getY(), 100, 40);
        y1Input.setFont(labelFont);
        add(y1Input);

        JLabel y1Label = new JLabel("Y1: ");
        y1Label.setFont(labelFont);
        y1Label.setBounds(y1Input.getX() - 35, y1Input.getY(), 50, 40);
        add(y1Label);

        x2Input.setBounds(x1Input.getX(), x1Input.getY() + x1Input.getHeight() + 50, 100, 40);
        x2Input.setFont(labelFont);
        add(x2Input);


        JLabel x2Label = new JLabel("X2: ");
        x2Label.setFont(labelFont);
        x2Label.setBounds(x2Input.getX() - 30, x2Input.getY(), 50, 40);
        add(x2Label);

        y2Input.setBounds(x2Input.getX() + x2Input.getWidth() + 50, x2Input.getY(), 100, 40);
        y2Input.setFont(labelFont);
        add(y2Input);


        JLabel y2Label = new JLabel("Y2: ");
        y2Label.setFont(labelFont);
        y2Label.setBounds(y2Input.getX() - 35, y2Input.getY(), 50, 40);
        add(y2Label);


        radiusInput.setBounds(x2Input.getX(), x2Input.getY() + x2Input.getHeight() + 50, 100, 40);
        radiusInput.setFont(labelFont);
        add(radiusInput);

        JLabel radiusLabel = new JLabel(" R: ");
        radiusLabel.setFont(labelFont);
        radiusLabel.setBounds(radiusInput.getX() - 30, radiusInput.getY(), 50, 40);
        add(radiusLabel);


        setResizable(false);
    }

    public static void main(String[] args) {
        Main mainWindow = new Main();
        mainWindow.repaint();
    }
}
