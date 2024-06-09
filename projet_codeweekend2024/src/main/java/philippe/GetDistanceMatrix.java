package philippe;

import java.util.ArrayList;
import java.util.List;

import commons.model.GameInput;
import commons.model.Monster;

public class GetDistanceMatrix {

    public static double[][] calculate(GameInput gameInput) {
        
        List<Coords> entities = new ArrayList<>();
        entities.add(new Coords(gameInput.getStartX(), gameInput.getStartY()));
        
        for (int i = 0; i < gameInput.getMonsters().size() - 1; i++) {
            Monster m = gameInput.getMonsters().get(i);
            entities.add(new Coords(m.getX(), m.getY()));
        }
        
        double[][] distances = new double[entities.size()][entities.size()];
        
        for (int i = 0; i < entities.size() - 1; i++) {
            Coords e1 = entities.get(i);
            
            for (int j = i + 1; j < entities.size(); j++) {
                Coords e2 = entities.get(j);
                double dist = Math.hypot(e1.x - e2.x, e1.y - e2.y);
                distances[i][j] = dist;
                distances[j][i] = dist;
            }
        }
        return distances;
    }
    
}
