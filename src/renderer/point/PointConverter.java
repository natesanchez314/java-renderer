package renderer.point;

import renderer.Display;

import java.awt.*;

public class PointConverter {

  private static float scale = 1.0f;

  public static Point convertPoint(MyPoint point3D) {
    /*float x3d = point3D.x * scale;
    float y3d = point3D.y * scale;
    float depth = point3D.z;*/

    float[] newVals = scale(point3D.x * scale, point3D.y * scale, point3D.z);

    int x2d = (int) (Display.WIDTH / 2 + newVals[0]);
    int y2d = (int) (Display.HEIGHT / 2 + newVals[1]);

    return new Point(x2d, y2d);
  }

  private static float[] scale(float x3d, float y3d, float depth) {
    float dist = (float) Math.sqrt((x3d * x3d) + (y3d * y3d));
    float newDepth = 15 - depth;
    float theta = (float) Math.atan2(y3d, x3d);
    float localScale = Math.abs(1400.0f / (newDepth + 1400.0f));

    dist *= localScale;
    float[] newVal = new float[2];
    newVal[0] = (float) (dist * Math.cos(theta));
    newVal[1] = (float) (dist * Math.sin(theta));

    return newVal;
  }
}
