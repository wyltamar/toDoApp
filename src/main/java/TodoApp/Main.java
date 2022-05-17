
package TodoApp;

import controller.TaskController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Task;

/**
 *
 * @author Wyltamar Douglas
 */
public class Main {

    public static void main(String[] args) throws SQLException {
    
//        ProjectController projectController = new ProjectController();
        
//        Project project = new Project();
//        project.setName("Comanda");
//        project.setDescription("Informatizar comandas");
//        
//        
//        projectController.save(project);

//          Project project = new Project();
//          project.setName("Comanda Eletrônica");
//          project.setDescription("Informatizar comandas");
//          project.setId(3);
          
//          projectController.update(project);
          
          
//          List<Project> projects = new ArrayList<Project>();
//          
//          projects = projectController.getAll();
//          
//          System.out.println("Total de projetos: "+projects.size());
//        

//            projectController.removeById(1);


            TaskController taskController = new TaskController();
            
//            Task task = new Task();
//            task.setIdProject(3);
//            task.setName("Cadastro Atualizado");
//            task.setDescription("Implementar o método para cadastrar o cliente");
//            task.setNotes("O cliente deve está cadastrado para ter acesso as funciionalidades do sistema");
//            task.setDeadline(new Date());
//            task.setIsCompleted(false);
//        
//            //taskController.save(task);
//              
//              task.setId(1);
//              taskController.update(task);

            List<Task> tasks = new ArrayList<Task>();
            
            tasks = taskController.getAll(3);
            
            System.out.println("Qantidades de tasks cadastradas: "+tasks.size());
            
            taskController.removeById(3, 2);
            
    }
    
}
