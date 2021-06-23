package renderer.shapes;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

import java.awt.*;

public class MyPolygon {

  private Color color;
  private final MyPoint[] points;

  public MyPolygon(Color color, MyPoint..._points) {
    this.color = color;
    this.points = new MyPoint[_points.length];
    for (int i = 0; i < this.points.length; i++) {
      MyPoint p = _points[i];
      this.points[i] = new MyPoint(p.x, p.y, p.z);
    }
  }

  public MyPolygon(MyPoint..._points) {
    this.color = Color.WHITE;
    this.points = new MyPoint[_points.length];
    for (int i = 0; i < this.points.length; i++) {
      MyPoint p = _points[i];
      this.points[i] = new MyPoint(p.x, p.y, p.z);
    }
  }

  public void render(Graphics g) {
    Polygon poly = new Polygon();
    for (MyPoint point : points) {
      Point p = PointConverter.convertPoint(point);
      poly.addPoint(p.x, p.y);
    }
    g.setColor(this.color);
    g.fillPolygon(poly);
  }

  public void setColor(Color c) {
    this.color = c;
  }
}
