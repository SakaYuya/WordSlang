package wordslang;

//references: https://openplanning.net/10167/java-jdbc
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import wordslang.connection;

public class queryData {
    
    public queryData() throws SQLException, ClassNotFoundException, FileNotFoundException {
        try {
            getData();
        } catch (IOException ex) {
            Logger.getLogger(queryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printAll() throws ClassNotFoundException,
          SQLException {
        connection SQLConnect = new connection();
        // Lấy ra đối tượng Connection kết nối vào DB.
        Connection connection = SQLConnect.getSQLServerConnection();

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "Select * from WORDSLANG";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            String empId = rs.getString(1);
            String empNo = rs.getString(2);
            Float empName = rs.getFloat(3);
            System.out.println("--------------------");
            System.out.println("EmpId:" + empId);
            System.out.println("EmpNo:" + empNo);
            System.out.println("EmpName:" + empName);
        }
        // Đóng kết nối
        connection.close();
    }
    
    public void getData() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        connection SQLConnect = new connection();
        // Lấy ra đối tượng Connection kết nối vào DB.
        Connection connection = SQLConnect.getSQLServerConnection();

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();
        
        //Đọc file
        FileReader fr = new FileReader("slang.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
            
        //Readfile
        while (line!=null)
        {
            String[] obj = line.split("`");
            String word = obj[0];
            String meaning = obj[1];
            
            String sql = "insert into WORDSLANG (Slag, Meaning) values ('" + word +"', '"+meaning + "')";
            
            int rowCount = statement.executeUpdate(sql);
            
            //Đọc dòng kế tiếp
            line = br.readLine();
        }
        fr.close(); 
    }

}