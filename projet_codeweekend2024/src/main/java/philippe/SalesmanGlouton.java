package philippe;

/**
 * Solution simpliste qui consiste à choisir pour chaque point suivant, le plus proche.
 */
public class SalesmanGlouton {

    TSProblem problem;
    double distSoluce;
    
    public SalesmanGlouton(TSProblem problem) {
        if (problem.from != 0 || problem.to != 0) throw new IllegalArgumentException(); 
        this.problem = problem;
    }

    public int[] getMeilleurSolution() {
       
        int[] soluce = new int[problem.number + 1];
        boolean[] visited = new boolean[problem.number];
        int previous = 0;
        distSoluce = 0;
        
        // Ajout des N-1 points (le point 0 est déjà en début) 
        
        for (int i = 1; i < problem.number; i++) {

            double min = Double.MAX_VALUE;
            int nextPoint = -1;

            // Boucle de recherche de la plus proche ville précédente.
            
            for (int j = 1; j < problem.number; j++) {
                if (!visited[j]) {
                    //System.err.println("> j: "+j+", dist: "+problem.distances[precedente][j]);
                    if (problem.distances[previous][j] < min) {
                        min = problem.distances[previous][j];
                        nextPoint = j;
                    }
                }
            }

            //System.err.println("select: "+jSelect+", dist: "+min);
            soluce[i] = nextPoint;
            visited[nextPoint] = true;
            distSoluce += min;
            previous = nextPoint;

        }

        distSoluce += problem.distances[soluce[problem.number - 1]][0];
        //System.err.println("Distance: " + Math.round(sum));
        
        return soluce;
    }
    
}