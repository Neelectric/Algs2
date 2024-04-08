import java.awt.geom.Point2D;

public class LineSegmentIntersection {
    public static void main(String[] args) {
        // Create an instance of LineSegmentIntersection
        LineSegmentIntersection lineSegmentIntersection = new LineSegmentIntersection();

        // Call the intersect method
        Line l1 = new Line();
        Line l2 = new Line();
        lineSegmentIntersection.intersect(l1, l2);
    }

    private boolean onOppositeSides(Point2D.Double a, Point2D.Double b, Line l) {
        double g, h;
        g = (l.p2.x - l.p1.x) * (a.y - l.p1.y) - (l.p2.y - l.p1.y) * (a.x - l.p1.x);
        h = (l.p2.x - l.p1.x) * (b.y - l.p1.y) - (l.p2.y - l.p1.y) * (b.x - l.p1.x);

        return g * h <= 0.0;
    }

    /** returns true if the rectangles containing line segments
     * l1 and l2 intersect, and returns false otherwise*/
    private boolean boundingBox(LineSegment l1,LineSegment l2) {
        double x1,x2,x3,x4,y1,y2,y3,y4;
        x1 = Math.min(l1.p1.x, l1.p2.x);
        x2 = Math.max(l1.p1.x, l1.p2.x);
        x3 = Math.min(l2.p1.x, l2.p2.x);
        x4 = Math.max(l2.p1.x, l2.p2.x);
        y1 = Math.min(l1.p1.y, l1.p2.y);
        y2 = Math.max(l1.p1.y, l1.p2.y);
        y3 = Math.min(l2.p1.y, l2.p2.y);
        y4 = Math.max(l2.p1.y, l2.p2.y);
    
        /* Rectangle containing l1 has bottom LH corner (x1,y1)
        * and top RH corner (x2,y2)
        * Rectangle containing l2 has bottom LH corner (x3,y3)
        * and top RH corner (x4,y4) */
        return x4 >= x1 && y4 >= y1 && x2 >= x3 && y2 >= y3;
    }

    public boolean intersect(Line l1, Line l2) {
        System.out.println("Running intersect method");
        Point2D.Double p = l1.p1;
        Point2D.Double q = l1.p2;
        Point2D.Double r = l2.p1;
        Point2D.Double s = l2.p2;

        return (onOppositeSides(p, q, l2) && onOppositeSides(r, s, l1) && boundingBox(p—q, r—s));
        return true;
    }
}