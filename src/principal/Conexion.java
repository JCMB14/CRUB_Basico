
package principal;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author JEAN CARLOS
 */
public class Conexion {
 
    public  Connection getConexion(){
        
        Connection conexion = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "dragonballsuper142022");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexion: "+e.toString());
        }
        return conexion;
    }
    
    
    private String id;
    private String nombre;
    private String ape;
    private String nac;
    private String clave;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getNac() {
        return nac;
    }

    public void setNac(String nac) {
        this.nac = nac;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
       public void seleccionar(JTable paraTable, JTextField paraID, JTextField paraNombre, JTextField paraApellido, JTextField Fechanac, JTextField paraClave){
                try {
            int fila = paraTable.getSelectedRow();

            if (fila >= 0) {
                paraID.setText((String) paraTable.getValueAt(fila, 0).toString());
                paraNombre.setText((String) paraTable.getValueAt(fila, 1).toString());
                paraApellido.setText((String) paraTable.getValueAt(fila, 2).toString());
                Fechanac.setText((String) paraTable.getValueAt(fila, 3).toString());
                paraClave.setText((String) paraTable.getValueAt(fila, 4).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
       
     public void ModificarCliente(JTextField ID, JTextField nombre, JTextField ape,
            JTextField nac, JTextField clave) {

        setId(ID.getText());
        setNombre(nombre.getText());
        setApe(ape.getText());
        setNac(nac.getText());
         setClave(clave.getText());

        Conexion c = new Conexion();

        String consulta = "update persona p set p.DNI=?, p.Nombre=?, p.Apellido=?, p.FechaDeNac=?, p.contraseña=? where DNI=?; ";

        try {
            CallableStatement cs = c.getConexion().prepareCall(consulta);
            cs.setString(1, getId());
            cs.setString(2, getNombre());
            cs.setString(3, getApe());
            cs.setString(4, getNac());
            cs.setString(5, getClave());
            cs.setString(6, getId());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modficacion exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error: " + e.toString());
        }
    }
     
    public void eliminar(JTextField paramID){
        setId(paramID.getText());

        Conexion c = new Conexion();

        String consulta = "delete from persona where DNI=?";

        try {
            CallableStatement cs = c.getConexion().prepareCall(consulta);
            cs.setString(1, getId());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el cliente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    } 
    
}
