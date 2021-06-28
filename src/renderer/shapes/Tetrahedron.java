package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
  private final MyPolygon[] polygons;
  private Color color;

  public Tetrahedron(Color color, MyPolygon..._polygons) {
    this.color = color;
    //this.polygons = new MyPolygon[_polygons.length];
    this.polygons = _polygons;
    //System.arraycopy(_polygons, 0, this.polygons, 0, polygons.length);
    this.setPolygonsColor();
  }

  public Tetrahedron(MyPolygon..._polygons) {
    this.color = Color.WHITE;
    //this.polygons = new MyPolygon[_polygons.length];
    this.polygons = _polygons;
    //System.arraycopy(_polygons, 0, this.polygons, 0, polygons.length);
    //this.setPolygonsColor();
  }

  public void render(Graphics g) {
    //System.out.println(polygons.length);
    for (MyPolygon poly : this.polygons) {
      poly.render(g);
    }
  }

  public void rotate(boolean CW, float xDeg, float yDeg, float zDeg) {
    for (MyPolygon p : polygons) {
      p.rotate(CW, xDeg, yDeg, zDeg);
      this.sortPolygons();
    }
  }

  private void sortPolygons() {
    // TODO
  }

  private void setPolygonsColor() {
    for (MyPolygon poly : this.polygons) {
      poly.setColor(this.color);
    }
  }
}
