package philippe;

import java.util.Arrays;

public class TSProblem {
    int number;
    int from;
    int to;
    double[][] distances;
    
    public TSProblem(int number, int from, int to, double[][] distances) {
        this.number = number;
        this.from = from;
        this.to = to;
        this.distances = distances;
    }
    
    public TSProblem(double[][] distances) {
        this.number = distances.length;
        this.from = 0;
        this.to = 0;
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "TSProblem [number=" + number + ", from=" + from + ", to=" + to + ", distances=" + Arrays.toString(distances) + "]";
    }
    
}
