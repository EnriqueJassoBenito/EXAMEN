package Client;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoRFC {

    public List<BeanRFC> lisRFC() {
        List<BeanRFC> listRFC = new ArrayList<>();
        try {
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from datos");
            while (rs.next()) {
                BeanRFC rfc = new BeanRFC();
                rfc.setName(rs.getString("name"));
                rfc.setAp(rs.getString("ap"));
                rfc.setAm(rs.getString("am"));
                rfc.setCurp(rs.getString("curp"));
                rfc.setDate(rs.getString("date"));
                //rfc.setRFC(rs.getString("RFC"));

                listRFC.add(rfc);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRFC;
    }

    public static boolean save(BeanRFC rfc) {

        boolean result = false;
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO datos (name,ap,am,curp,date) " +
                        "VALUES (?,?,?,?,?);");
        )
        {
            pstm.setString(1,rfc.getName());
            pstm.setString(2,rfc.getAp());
            pstm.setString(3,rfc.getAm());
            pstm.setString(4,rfc.getCurp());
            pstm.setString(5,rfc.getDate());
            //pstm.setString(6,rfc.getRFC());
            result = pstm.executeUpdate()==1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public BeanRFC getcurp(String curp) {
        BeanRFC rfc = new BeanRFC();
        try(
                Connection con = MySQLConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement("select * from datos where curp=?;");
        ){
            pstm.setString(1, curp);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                rfc.setName(rs.getString("name"));
                rfc.setAp(rs.getString("ap"));
                rfc.setAm(rs.getString("am"));
                rfc.setCurp(rs.getString("curp"));
                rfc.setDate(rs.getString("date"));
                //rfc.setRFC(rs.getString("RFC"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rfc;
    }

    public boolean delete(String curp) {
        boolean result = false;
        try (
                Connection conn = MySQLConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement("delete from datos where curp = ?; ");
        ) {
            pstm.setString(1, curp);
            result = pstm.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(BeanRFC rfc_update) {
        boolean result = false;
        try (
                Connection con = MySQLConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement("update datos set name = ?, paterno = ?, materno = ?, curp = ?, fecha_nac = ? where RFC=?;");
        ){
            pstm.setString(1, rfc_update.getName());
            pstm.setString(2, rfc_update.getAp());
            pstm.setString(3, rfc_update.getAm());
            pstm.setString(4, rfc_update.getCurp());
            pstm.setString(5, rfc_update.getDate());
            //pstm.setString(6, rfc_update.getRFC());
            result = pstm.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
