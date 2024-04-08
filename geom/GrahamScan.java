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

    public double angle(Point2D.Double a, Point2D.Double b, Point2D.Double c){
        double angle = Math.atan2(c.y - b.y, c.x - b.x) - Math.atan2(a.y - b.y, a.x - b.x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public Double[] grahamScan(PointSet points){
        points = new SimplePolygon().simplePolygon(points);
        PointSet output = new PointSet(points.pArray.length);
        for (int i = 0; i < points.pArray.length; i++) {
            output.pArray[i] = points.pArray[i];
        }
        int m = 2;
        for (int k = 3; k<points.pArray.length; k++) {
            double pi = Math.PI;
            double angle = angle(output.pArray[m-1],output.pArray[m], points.pArray[k]);
            while (angle  <= Math.PI ){
                m--;
                angle = angle(output.pArray[m-1],output.pArray[m], points.pArray[k]);
            }
            output.pArray[++m] = points.pArray[k];
        }
        return Arrays.copyOfRange(output.pArray, 0, m);
    }
}
