package philippe;

public class SalesmanTools {

    static double getDistance(TSProblem problem, int[] soluce) {
        double sum = 0;
        for (int i = 0; i < soluce.length - 1; i++) {
            sum += problem.distances[soluce[i]][soluce[i+1]];
        }
        return sum;
    }
    
}
