package philippe;

import java.util.Arrays;

/**
 * Solution simple mais qui donne de meilleurs résultats que le Glouton.
 * 
 * On essaye toutes les combinaisons sur le deuxième point suivi d'un Glouton.
 */
public class SalesmanNearestNeighbours {

    TSProblem problem;
    double distSoluce = Double.MAX_VALUE;
     
    public SalesmanNearestNeighbours(TSProblem problem) {
        if (problem.from != 0 || problem.to != 0) throw new IllegalArgumentException(); 
        this.problem = problem;
    }

    public int[] getMeilleurSolution() {
        System.err.println("problem.number: " + problem.number);
        
        int length = problem.number + 1;
        int[] soluce = null;
        
        for (int k = 1; k < problem.number; k++) {
            
            int[] newSoluce = new int[length];
            newSoluce[1] = k;
            double sum = problem.distances[0][k];
            
            boolean[] visited = new boolean[problem.number];
            visited[k] = true;
            
            int previous = k;
    
            // Ajout des N-2 villes restantes en mode glouton 
            
            for (int i = 2; i < problem.number; i++) {
    
                double min = Double.MAX_VALUE;
                int nextPoint = -1;
    
                // Boucle de recherche de la plus proche ville précédente.
                
                for (int j = 1; j < problem.number; j++) {
                    
                    if (!visited[j]) {
                        if (problem.distances[previous][j] < min) {
                            min = problem.distances[previous][j];
                            nextPoint = j;
                        }
                    }
                    
                }
    
                newSoluce[i] = nextPoint;
                visited[nextPoint] = true;
                sum += min;
                previous = nextPoint;
    
            }
    
            //System.err.println("newSoluce: " + Arrays.toString(newSoluce));
            sum += problem.distances[newSoluce[problem.number - 1]][0];
            //System.err.println("Distance: " + Math.round(sum));
            
            if (sum < distSoluce) {
                distSoluce = sum;
                soluce = newSoluce;
                //System.err.println(" -- Meilleure solution pour l'instant " + Math.round(sum));
            }
            
        }
        
        return soluce;
    }
    
}