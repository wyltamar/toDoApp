
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author wyltamar
 */
public class TaskController {

    public void save(Task task)throws SQLException{
        
        String sql = "INSERT INTO tasks(idProject,name,description,completed,notes,deadline,createdAt,updatedAt) VALUES (?,?,?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.executeUpdate();
            
            
        } catch(SQLException ex){
           throw new SQLException("Erro de SQL "+ex.getMessage());
        }catch(Exception ex){
            throw new RuntimeException("Erro ao cadastrar Task: "+ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
    }
    
    public void update(Task task){
        
        String sql = "UPDATE tasks SET"
                + " idProject = ?,"
                + "name = ?,"
                + "description = ?,"
                + "completed = ?"
                + "notes = ?"
                + "deadline = ?"
                + "createdAt = ?"
                + "updatedAt = ?"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Houve um erro de SQL:"+ e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao atualizar a task:"+ e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    
    }

    public void removeById(int taskId) throws SQLException{
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL "+e.getMessage());
        }catch(Exception ex){
            throw new RuntimeException("Erro ao deletar Task: "+ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(conn,statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                
                Task task = new Task();
                task.setId(resultset.getInt("id"));
                task.setIdProject(resultset.getInt("idProject"));
                task.setName(resultset.getString("name"));
                task.setDescription(resultset.getString("description"));
                task.setIsCompleted(resultset.getBoolean("completed"));
                task.setNotes(resultset.getString("notes"));
                task.setDeadline(resultset.getDate("deadline"));
                task.setCreatedAt(resultset.getDate("createdAt"));
                task.setUpdatedAt(resultset.getDate("updatedAt"));
                
                tasks.add(task);
                
            }
              
             
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro de SQL: "+e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Erro na busca por task: "+e.getMessage());
        }finally{
            ConnectionFactory.closeConnection(connection, statement,resultset);
        }
         
         return tasks;
    }
    
}
