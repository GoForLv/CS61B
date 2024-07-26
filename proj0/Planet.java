/*
 * Simulator a body in the universe.
 */
public class Planet {
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;
    // N * m^2 / kg^2
    static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * (mass * p.mass) / (distance * distance);
    }

    // r = (p.pos - pos) is a vector.
    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet planets[]) {
        double netForceX = 0; 
        for (Planet p : planets) {
            if (this.equals(p)) continue;
            netForceX += calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet planets[]) {
        double netForceY = 0; 
        for (Planet p : planets) {
            if (this.equals(p)) continue;
            netForceY += calcForceExertedByY(p);
        }
        return netForceY;
    }

    // error about the calculation of position update.
    public void update(double dt, double netForceX, double netForceY) {
        double xAcc = netForceX / mass;
        double yAcc = netForceY / mass;
        xxVel += xAcc * dt;
        yyVel += yAcc * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
