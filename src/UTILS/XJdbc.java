package UTILS;

import java.sql.*;

public class XJdbc {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;databaseName=EduSys";
    static String user = "sa";
    static String password = "123";
    static{
        try {
            Class.forName(driver);
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException{
        Connection con = DriverManager.getConnection(dburl, user, password);
        PreparedStatement pstm;
        if (sql.trim().startsWith("{")){
            pstm = con.prepareCall(sql);
        }
        else{
            pstm = con.prepareStatement(sql);
        }
        for (int i=0;i<args.length;i++){
            pstm.setObject(i+1, args[i]);
        }
        return pstm;
    }
    public static int update(String sql, Object... args) throws SQLException{
        PreparedStatement pstm = XJdbc.getStmt(sql, args);
        try {
            return pstm.executeUpdate();
        } 
        finally {
            pstm.getConnection().close();
        }
    }
    public static ResultSet query(String sql, Object... args) throws SQLException{
        PreparedStatement pstm = XJdbc.getStmt(sql, args);
        return pstm.executeQuery();
    }
    public static Object value(String sql, Object... args){
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if (rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } 
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
