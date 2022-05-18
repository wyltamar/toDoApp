
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author wyltamar Douglas
 */
public class ProjectController {
    
    public void save(Project project){
        
        //String que representa o query de inserção de projects no banco de dados
        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES(?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            //Estabelecendo uma conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query Sql
            statement = connection.prepareStatement(sql);
            
            //Setando o statement com dados do objeto passado como parâmetro 
            //para salvar os dados no banco
            statement.setString(1,project.getName());
            statement.setString(2,project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir Projeto " );
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
           
    }
    
    public void update(Project project){
        
        String sql = "UPDATE projects SET "
                + "name = ?,"
                + "description = ?,"
                + "createdAt = ?,"
                + "updatedAt = ?  WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2,project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.executeUpdate();
            
        } catch (Exception e) {
           throw new RuntimeException("Erro ao atualizar Projeto "+e.getMessage()); 
        }finally{
           ConnectionFactory.closeConnection(connection, statement); 
        }
        
    }
    
    public void removeById(int projectId){
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.executeUpdate();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar Projeto "+e.getMessage()); 
        }finally{
           ConnectionFactory.closeConnection(connection, statement); 
        }
        
    }
    
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM projects";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        
        List<Project> projects = new ArrayList<Project>();
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
       
            
        while(resultset.next()){
            
            Project project = new Project();
            
            project.setId(resultset.getInt("id"));
            project.setName(resultset.getString("name"));
            project.setDescription(resultset.getString("description"));
            project.setCreatedAt(resultset.getDate("createdAt"));
            project.setUpdatedAt(resultset.getDate("updatedAt"));
            
            projects.add(project);
        }
        
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Projeto "+e.getMessage());
        }finally{
            ConnectionFactory.closeConnection(connection, statement, resultset);
        }
        
        return projects;
    }
    
}
