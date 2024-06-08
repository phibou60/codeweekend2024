package philippe;

public class Coords {
    double x;
    double y;

    public Coords() {}

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Coords c2) {
        return Math.hypot(x - c2.x, y - c2.y);
    }
    
    /**
     * Retourne un point vers le point2 a une certaine distance.
     */
    
    public Coords getPointVers(Coords point2, double distance) {
        return this.doVtranslation(createVecteurVers(point2, distance));
    }
    
    /**
     * Retourne le vecteur qui fait la translation vers le point2 a une certaine distance.
     */
    
    private Coords createVecteurVers(Coords point2, double distance) {
        Coords vectorVersPoint2 = createVecteurVers(point2);
        return new Coords().createVFromFormeTrigono(vectorVersPoint2.getVAngle(), distance);
    }
    
    /**
     * Applique la translation du vecteur au point pour obtenir un nouveau point. 
     */
    private Coords doVtranslation(Coords point) {
        return new Coords(point.x + x, point.y + y);
    }
    
   /**
    * Retourne le vecteur qui fait la translation vers le point "to".
    */
            
    private Coords createVecteurVers(Coords point2) {
       return new Coords(point2.x - x, point2.y - y);
   }

    private double getVAngle() {
       return Math.atan2(y, x);
   }

    static Coords createVFromFormeTrigono(double angle, double norme) {
        return new Coords((int) Math.floor(Math.cos(angle) * norme), (int) Math.floor(Math.sin(angle) * norme));
    }

}
