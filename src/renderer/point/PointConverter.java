package renderer.point;

import renderer.Display;

import java.awt.*;

public class PointConverter {

  public static Point convertPoint(MyPoint point3D) {
    int x2d = (int) (Display.WIDTH / 2 + point3D.x);
    int y2d = (int) (Display.HEIGHT / 2 + point3D.y);
    return new Point(x2d, y2d);
  }
}
