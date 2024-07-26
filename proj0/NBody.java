/*
 * Simulator a universe with n-bodys.
 */
public class NBody {
    public static double readRadius(String universeFile) {
        In in = new In(universeFile);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String universeFile) {
        In in = new In(universeFile);
        int numBody = in.readInt();
        in.readDouble();
        Planet planets[] = new Planet[numBody];
        for (int i = 0; i < numBody; ++i) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        String background = "images/starfield.jpg";
        Planet planets[] = readPlanets(filename);

        StdDraw.setScale(- 2 * radius, 2 * radius);
        StdDraw.clear();
        
        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t < T) {
            double xForces[] = new double[planets.length];
            double yForces[] = new double[planets.length];
            for (int i = 0; i < planets.length; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, background);

            for (int i = 0; i < planets.length; ++i) {
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
