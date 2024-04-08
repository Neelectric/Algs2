import java.awt.geom.Point2D;

public class SimplePolygon {

    public PointSet selectLargestX(PointSet points){
        Point2D.Double largestX = points.pArray[0];
        for (Point2D.Double point: points.pArray){
            if (point.getX() > largestX.getX()) {
                largestX = point;
            }
        }
        PointSet newSet = new PointSet();
        newSet.pArray = new Point2D.Double[points.pArray.length];
        newSet.pArray[0] = largestX;
        int index = 1;
        for (Point2D.Double point : points.pArray) {
            if (point != largestX) {
                newSet.pArray[index] = point;
                index++;
            }
        }
        return newSet;
    }

    public PointSet simplePolygon(PointSet points) {
        // Implement the simple polygon algorithm
        double num, denom;
        double [] angle = new double[points.pArray.length];
        points = selectLargestX(points);

        for (int i=1; i<points.pArray.length; i++) {
            num = points.pArray[0].x - points.pArray[i].x;
            denom = points.pArray[i].y - points.pArray[0].y;
            if (denom > 0.0) {
                angle[i] = Math.atan(num/denom);
            }
            else if (denom == 0.0) {
                angle[i] = Math.PI/2.0;
            }
            else {
                angle[i] = Math.atan(num/denom) + Math.PI;
            }
        }
        points = sort(points, 1, points.pArray.length, angle);
        return points;
    }

    /* sorts points[1..n-1] using the values of angle[1..n-1] */
    private PointSet sort(PointSet points, int i, int length, double[] angle) {
        for (int j = i + 1; j < length; j++) {
            for (int k = j + 1; k < length; k++) {
                if (angle[k] < angle[j]) {
                    double tempAngle = angle[j];
                    angle[j] = angle[k];
                    angle[k] = tempAngle;

                    Point2D.Double tempPoint = points.pArray[j];
                    points.pArray[j] = points.pArray[k];
                    points.pArray[k] = tempPoint;
                }
            }
        }
        return points;
    }

    public static void main(String[] args) {
        // Create an instance of LineSegmentIntersection
        SimplePolygon simplePolygon = new SimplePolygon();

        // Call the intersect method
        //PointSet points = new PointSet();
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
        
        System.out.println("Calling simple polygon: " );
        points = simplePolygon.simplePolygon(points);
    }    

}
