import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Arrays;

public class GrahamScan {
    public static void main(String[] args) {
        GrahamScan gs = new GrahamScan();
        Point2D.Double point1 = new Point2D.Double(7, 1);
        Point2D.Double point2 = new Point2D.Double(7, 2);
        Point2D.Double point3 = new Point2D.Double(6, 4);
        Point2D.Double point4 = new Point2D.Double(5, 3);
        Point2D.Double point5 = new Point2D.Double(3.5, 3);
        Point2D.Double point6 = new Point2D.Double(1, 4);
        Point2D.Double point7 = new Point2D.Double(4.5, 2.5);
        Point2D.Double point8 = new Point2D.Double(3, 2);
        Point2D.Double point9 = new Point2D.Double(4.5, 1.25);
        Point2D.Double point10 = new Point2D.Double(2, 1);
        PointSet points = new PointSet();
        points.pArray = new Point2D.Double[]{point1, point2, point3, point4, point5, point6, point7, point8, point9, point10};
        
        System.out.println("Calling graham scan: " );
        points.pArray = gs.grahamScan(points);
        
    }

    public double angle(LineSegment l1, LineSegment l2){
        double x1 = l1.p1.getX() - l1.p2.getX();
        double y1 = l1.p1.getY() - l1.p2.getY();
        double x2 = l2.p1.getX() - l2.p2.getX();
        double y2 = l2.p1.getY() - l2.p2.getY();
        double dot = x1*x2 + y1*y2;
        double det = x1*y2 - y1*x2;
        return Math.atan2(det, dot);
    }

    public Double[] grahamScan(PointSet points){
        points = new SimplePolygon().simplePolygon(points);
        PointSet output = new PointSet(points.pArray.length);
        for (int i = 0; i < points.pArray.length; i++) {
            output.pArray[i] = points.pArray[i];
        }
        int m = 2;
        for (int k = 3; k<points.pArray.length; k++) {
            double angle = angle(new LineSegment(output.pArray[m-1],output.pArray[m]), new LineSegment(output.pArray[m], points.pArray[k]));
            while (angle  >= Math.PI ){
                m--;
                angle = angle(new LineSegment(output.pArray[m-1],output.pArray[m]), new LineSegment(output.pArray[m], points.pArray[k]));
            }
            output.pArray[++m] = points.pArray[k];
        }
        return Arrays.copyOfRange(output.pArray, 0, m);
    }
}
