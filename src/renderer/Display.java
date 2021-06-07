package renderer;

import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable {

  private Thread thread;
  private JFrame frame;
  private static String title = "Java 3D";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  private static boolean running = false;

  public Display() {
    this.frame = new JFrame();
    Dimension size = new Dimension(WIDTH, HEIGHT);
    this.frame.setTitle(title);
    this.setPreferredSize(size);
    this.frame.add(this);
    this.frame.pack();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setLocationRelativeTo(null);
    this.frame.setResizable(false);
    this.frame.setVisible(true);
  }

  public synchronized void start() {
    running = true;
    this.thread = new Thread(this, "renderer.Display");
    this.thread.start();
  }

  public synchronized void stop() throws InterruptedException {
    running = false;
    try {
      this.thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1000000000.0 / 60;
    double delta = 0;
    int frames = 0;

    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        update();
        delta--;
      }
      render();
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        this.frame.setTitle(title + " | " + frames + " fps");
        frames = 0;
      }
    }
    try {
      stop();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    MyPolygon myPoly = new MyPolygon(
            new MyPoint(0, 100, 0),
            new MyPoint(100, 50, 0),
            new MyPoint(0, 0, 100)
    );

    myPoly.render(g);

    g.dispose();
    bs.show();
  }

  private void update() {

  }
}
