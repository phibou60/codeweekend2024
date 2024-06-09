package philippe;

import java.util.*;

/*
 * Optimisation 2-OPT.
 * 
 * Ce 2-OPT a été modifiée : à partir d'une solution donnée, il teste toutes les inversions
 * possibles puis fait la meilleure.
 * Cela le rend plus efficace qu'un 3-opt.
 * 
 * la methode "getMeilleurSolution2" fait de multiples 2-opt sur les solutions altérés
 * 
 * 
 * Explications :
  
           i     j
    *--->--*     *---------*
            \  /           |
             \/            |
             /\            |
            /  \           |
    *---<--*    *-----*----* 
          j+1   i+1
    
    Si la longueur(i, i+1) + longueur(j, j+1) > longueur(i, j) + longueur(i+1, j+1)
    alors on peut couper de i à j et de i+1 à j+1.
    Attention car le sens de circulation entre i+1 et j doit être inversé.
*/

public class Salesman2Opt {

    TSProblem problem;
    int[] soluce;
    int nbUpdates;
    double distSoluce;
     
    public Salesman2Opt(TSProblem problem, int[] soluce) {
        if (problem.from != 0 || problem.to != 0) throw new IllegalArgumentException(); 
        this.soluce = Arrays.copyOf(soluce, soluce.length);
        this.problem = problem;
        distSoluce = SalesmanTools.getDistance(problem, soluce);
    }

    public int[] getMeilleurSolution(long nsLimit) {
               
        boolean improve = true;
        
        while (improve && System.nanoTime() < nsLimit) {
            improve = false;
            
            int[] bestOptimisation = null;
            double bestGain = 0;

            for (int i = 0; i < soluce.length - 2; i++) {
                for (int j = i + 2; j < soluce.length - 1; j++) {
                    
                    //System.err.println("test :" + soluce[i] + ", " + soluce[i+1] + ", " + soluce[j] + ", " + soluce[j+1]);

                    double d1 = problem.distances[soluce[i]][soluce[j]];
                    double d2 = problem.distances[soluce[i+1]][soluce[j+1]];
                    double d3 = problem.distances[soluce[i]][soluce[i+1]];
                    double d4 = problem.distances[soluce[j]][soluce[j+1]];
                    
                    double gain = d3 + d4 - (d1 + d2); // Should be positive if better

                    if (gain > bestGain) {
                        bestGain = gain;
                        bestOptimisation = new int[] {i, j};
                    }
                }
                
            }
            

            if (bestOptimisation != null) {

                int i = bestOptimisation[0];
                int j = bestOptimisation[1];
                
                //System.err.println("inverser :" + (i+1) + " et "+j + ", distance: "+getDistance());
                inverser(soluce, i+1, j);

                distSoluce -= bestGain;
                
                //System.err.println("soluce :" + Arrays.toString(soluce) + ", distance: "+getDistance());
                improve = true;
                nbUpdates++;
            }

        }
       
        return soluce;
    }

    /**
     * Fonction qui fait un 2-opt à partir de multiples altérations aléatoire. 
     */
    
    public int[] getMeilleurSolution2(long nsLimit) {
        
        int[] soluce1 = getMeilleurSolution(Long.MAX_VALUE);
        System.err.println("nbUpdates." + nbUpdates);
        System.err.println("distSoluce." + distSoluce);

        Random rand = new Random(0);
        while (System.nanoTime() < nsLimit) {
            int[] soluce2 = Arrays.copyOf(soluce, soluce.length);
            for (int i = 0; i < problem.number / 20; i++) {
                int j = rand.nextInt(problem.number - 1) + 1;
                int k = rand.nextInt(problem.number - 1) + 1;

                int temp = soluce2[j];
                soluce2[j] = soluce2[k];
                soluce2[k] = temp;
            }
            Salesman2Opt s2optb = new Salesman2Opt(problem, soluce2);
            
            soluce2 = s2optb.getMeilleurSolution(nsLimit);
            
            if (s2optb.distSoluce < distSoluce) {
                distSoluce = s2optb.distSoluce;
                soluce = soluce2;
                System.err.println("" + s2optb.nbUpdates + "/" + Math.round(s2optb.distSoluce));
            }
        }
        return soluce;
    }
    
    /**
     * Inverser les postes entre 2 indices inclus.
     */
    static void inverser(int[] soluce, int start, int end) {
        
        int temp;
        int inverse = end;
        for (int i = start; i < inverse; i++) {
            temp = soluce[i];
            soluce[i] = soluce[inverse];
            soluce[inverse] = temp;
            inverse--;
        }
        
    }
    
}