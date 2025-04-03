import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseIT {
    @Test
    public void connectToDatabase() throws SQLException {
        //TODO
        // Discuss how we will manage database connection.
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase",
        //        "USER-NAME", "PASSWORD");
    }
}
