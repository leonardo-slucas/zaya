package tests;

import zaya.tasks.Kmeans;
import tests.models.ActionContextTest;
import java.util.logging.Logger;

public class KmeansTest {
    public static void main(String[] args) {
        Kmeans application = new Kmeans();
        
        try {
            application.doExecute(new ActionContextTest().getActionContext());
        } catch (Exception e) {
            Logger.getLogger("A2").info("Erro: " + e.getMessage());
        }
    }
}
