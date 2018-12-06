package phonebook;


import java.sql.*;

public class SqliteDataBase extends DAO {
    String dbname;
    String connUrl;

    public SqliteDataBase(String dbname) {
        this.dbname = dbname;
        this.connUrl = "jdbc:sqlite:" + dbname;
        try {
            Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement();
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS phonebook (\n"
                        + "	name text PRIMARY KEY,\n"
                        + "	number text NOT NULL\n"
                        + ");";
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }
    @Override
    public void addAnEntry(String name, String number) {
        try {
            Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement();
            if (conn != null) {
                String sql = String.format("INSERT INTO phonebook VALUES('%s', '%s');", name, number);
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }

    @Override
    public String searchPhoneNumber(String name) {
        String number = "";
        try  {
            Connection conn = DriverManager.getConnection(connUrl);
            Statement stat = conn.createStatement();
            if (conn != null) {
                String sql = String.format("SELECT number FROM phonebook WHERE name= '%s';", name);
                ResultSet result =  stat.executeQuery(sql);

                while(result.next()) {
                    number=result.getString(1);
                }
                result.close();
            }
        } catch (SQLException e) { }
        return number;
    }

    @Override
    public String editData(String name, String number) {
        String result=null;
        try {
            Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement();
            if (conn != null) {
                String sql = String.format("UPDATE phonebook SET number = '%s' WHERE name = '%s';", number, name);
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            result="";
        }
        if(result==null)
            result = EDIT_RESULT;
        return result;
    }

    @Override
    public String deleteData(String name) {
        String result=null;
        try {
            Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement();
            if (conn != null) {
                String sql = String.format("DELETE FROM phonebook WHERE name = '%s';", name);
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            result="";
        }
        if(result==null)
            result = DELETE_RESULT;
        return result;
    }
}
