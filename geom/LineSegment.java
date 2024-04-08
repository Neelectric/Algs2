import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class LineSegment {
    public LineSegment(Double double1, Double double2) {
        this.p1 = double1;
        this.p2 = double2;
    }

    public LineSegment() {
    }

    public Point2D.Double p1,p2;
   }