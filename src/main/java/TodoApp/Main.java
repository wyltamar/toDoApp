
package TodoApp;

import java.sql.Connection;
import util.ConnectionFactory;

/**
 *
 * @author Wyltamar Douglas
 */
public class Main {

    
    public static void main(String[] args) {
    
        Connection connection = ConnectionFactory.getConnection();
        
        if(connection != null){
            System.out.println("Tudo certo com a conexão");
        }
            
    }
    
}
