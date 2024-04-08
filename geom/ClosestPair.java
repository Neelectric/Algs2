import java.awt.geom.Point2D;

public class ClosestPair {
    public static void main(String[] args) {
        ClosestPair cp = new ClosestPair();
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
        Double distance = cp.closestPair(points);
        
    }

    public void sortOnXCoord(PointSet p) {
        /** sorts the points in p on x-coordinate */
        for (int i=0; i < p.pArray.length-1; i++) {
            int min = i;
            for (int j=i+1; j < p.pArray.length; j++) {
                if (p.pArray[j].x < p.pArray[min].x) {
                    min = j;
                }
            }
            Point2D.Double temp = p.pArray[i];
            p.pArray[i] = p.pArray[min];
            p.pArray[min] = temp;
        }
    }

    public double closestPair(PointSet p) {
        /** Input: a PointSet p
        * Output: d, the distance between
        * the closest pair of points in p */
        sortOnXCoord(p); // sort points in p on x-coordinate
        return cPRec(p, 0, p.pArray.length-1);
    }
    
    private double cPRec (PointSet p, int i, int k) {
        /** assumes p[i..k] sorted on x-coordinate;
        * returns the distance between a
        * closest pair of points in p[i..k];
        * also returns, in p[i..k], the points
        * initially in p[i..k] sorted on y coordinate
        */
        double d;
        if (i == k) d = Double.MAX_VALUE;
        else { 
            int j = (i+k)/2; // mid-point of p[i..k]
            double mid =(p.pArray[j].x + p.pArray[j+1].x)/2.0;
            // x coord of mid-line
            double d1 = cPRec(p, i, j);
            // p[i..j] sorted on y coord
            double d2 = cPRec(p, j+1, k);
            // p[j+1..k] sorted on y coord
            merge(p, i, j, k); // p[i..k] sorted on y coord
            d = Math.min(d1, d2);
            PointSet s = filter(p, i, k, d, mid);
            // the points in the “strip”
            int m = s.pArray.length; // no. of points in s
            for (int a=0; a < m-1; a++) {
                for (int b=a+1; b <= Math.min(a+5,m-1); b++) {
                    if ( dist(s.pArray[a], s.pArray[b]) < d ) {
                        d = dist(s.pArray[a], s.pArray[b]);
                    }
                }
            }
        }
        return d;
    }

    private void merge(PointSet p, int i, int j, int k) {
        /** merges p[i..j] and p[j+1..k] into p[i..k] */
        Point2D.Double [] a = p.pArray;
        int index1, index2, index3;
        Point2D.Double [] temp = new Point2D.Double[a.length];
        index1 = i;
        index2 = j+1;
        index3 = i;
        while ( index1 <= j && index2 <= k )
        if (a[index1].y <= a[index2].y)
        temp[index3++] = a[index1++];
        else
        temp[index3++] = a[index2++];
        if ( index1 <= j )
        temp[index3..k] = a[index1..j];
        else if ( index2 <= k )
        temp[index3..k] = a[index2..k];
        a[i..k] = temp[i..k];
        }

    private PointSet filter(PointSet p, int i, int k, double d, double z){
    /** returns a PointSet containing points in p[i..k] with
    * x-coord within d of z; preserves relative order */
    return null;
    }

    /** returns the distance between the points a and b */
    private double dist(Point2D.Double a, Point2D.Double b){
        return 0.0;
    }




    private double angle(Point2D.Double a, Point2D.Double b, Point2D.Double c);
    }
}
