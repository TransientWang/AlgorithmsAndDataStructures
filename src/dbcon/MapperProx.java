package dbcon;

import com.mysql.jdbc.PreparedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Optional;
import java.util.TreeMap;

public class MapperProx implements InvocationHandler {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        select selects = method.getAnnotation(select.class);
        if (selects != null) {
            String sentences = selects.sentence();
            Optional<String> optional = Optional.of(sentences);
            optional.ifPresent(o -> fetch(o));


//
//            if (sentences )
        }


        return null;
    }

    private void fetch(String sql) {



        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        try {
            int column;

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ymq?useUnicode=true&amp" +
                    ";characterEncoding=UTF-8", "root", "root");
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
//            resultSet.last();
            ResultSetMetaData metaData = resultSet.getMetaData();
            column = metaData.getColumnCount();

            System.out.println("表名：" + metaData.getTableName(1));
            for (int i = 1; i <= column; i++) {
                System.out.print(metaData.getColumnName(i) + "    | ");
            }
            System.out.println();

            while (resultSet.next()) {

                for (int i = 1; i <= column; i++) {


                    if (i == column)
                        System.out.println(resultSet.getString(i) + " | ");
                    else System.out.print(resultSet.getString(i) + " | ");

                }
                System.out.println("-----------------------------------------------------------------------------");
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (Exception r) {
        }


    }

}
