package xyz.anonym.randommobblock;


import java.sql.*;

public class SQLHelper {
    private Connection conn;
    public SQLHelper(String path){
        try {
            connect(path);
            init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void connect(String path) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:"+path);
    }

    public void addBlockMob(String block, String mob) throws SQLException {
        PreparedStatement statement=conn.prepareStatement("INSERT INTO `blockmob` (`mob`, `block`) VALUES (?,?);");
        statement.setString(1,mob);
        statement.setString(2,block);
        statement.execute();
    }

    public String getMob(String block) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT `mob` from `blockmob` where `block`=?;");
        statement.setString(1,block);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return result.getString(1);
        } else {
            return null;
        }
    }

    private void init() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `blockmob` (`mob` VARCHAR(128) NOT NULL , `block` VARCHAR(128) NOT NULL , PRIMARY KEY (`mob`));");
    }
}
