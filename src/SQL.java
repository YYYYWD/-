import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL
{

    Statement stmt;
    Connection dbConn;
    ResultSet rs;
    static List<SQL> list=new ArrayList<SQL>();
    SQL(String URL)
    {


        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=master";

        String userName = "test";   //默认用户名

        String userPwd = "123456";   //密码

        URL=new String();
        URL=dbURL;
        System.out.println(URL);

        try {

            Class.forName(driverName);

            this.dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            this.stmt = this.dbConn.createStatement();
            //System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!


            //stmt.executeUpdate();查询用的
            /**
             * ResultSet executeQuery(String sql) throws SQLException 执行给定的 SQL
             * 语句，该语句返回单个 ResultSet 对象
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        //list.add(this);
    }

    SQL()
    {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Airport";

        String userName = "test";   //默认用户名

        String userPwd = "123456";   //密码

        try {

            Class.forName(driverName);

            this.dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            this.stmt = this.dbConn.createStatement();
            //System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!


            //stmt.executeUpdate();查询用的
            /**
             * ResultSet executeQuery(String sql) throws SQLException 执行给定的 SQL
             * 语句，该语句返回单个 ResultSet 对象
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(this);
    }
     public void ALL_close()
    {
        for (int i=0;i<list.size();i++)
        {
            try {
                list.get(i).dbConn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
