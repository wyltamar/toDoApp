
package TodoApp;

import controller.ProjectController;
import model.Project;

/**
 *
 * @author Wyltamar Douglas
 */
public class Main {

    public static void main(String[] args) {
    
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Comanda");
        project.setDescription("Informatizar comandas");
        
        
        projectController.save(project);
        
    }
    
}
