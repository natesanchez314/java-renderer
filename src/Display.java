import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable {

  private Thread thread;
  private JFrame frame;
  private static String title = "Java 3D";
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static boolean running = false;

  public Display() {
    this.frame = new JFrame();
    Dimension size = new Dimension(WIDTH, HEIGHT);
    frame.setTitle(title);
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
    this.thread = new Thread(this, "Display");
    this.thread.start();
  }

  public synchronized void stop() throws InterruptedException {
    running = false;
    try {
      this.thread.join();
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  @Override
  public void run() {

  }
}
