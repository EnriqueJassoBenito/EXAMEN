package Client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RFCClient {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
            throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        String option = "";
        String name,ap,am,curp,date;


        do {
            System.out.println("1. Registrar persona");
            System.out.println("2. Consultar datos por Curp");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Salir");
            option = sc.next();
            if (isNumber(option)){
                switch (Integer.parseInt(option)){
                    case 1:
                        System.out.println("Registrar Persona\n");
                        System.out.println("Dime tu nombre");
                        name = sc.next();
                        System.out.println("Primer Apellido");
                        ap = sc.next();
                        System.out.println("Segundo Apellido");
                        am = sc.next();
                        System.out.println("Inserta tu curp");
                        curp = sc.next().toUpperCase();
                        System.out.println("Fecha de Nacimiento");
                        date = sc.next();

                        Object [] data = {name,ap,am,curp,date,3};
                        String response = (String) client.execute("Methods.datos",data);
                        System.out.println(response);
                        //String RFC = response;
                        BeanRFC rfc = new BeanRFC(name,ap,am,curp,date);
                        DaoRFC daoRFC = new DaoRFC();
                        daoRFC.save(rfc);
                        break;
                    case 2:
                        System.out.println("Consultas \n");
                        System.out.println("Dime la curp");
                        curp = sc.next();

                        String [] data1 = {curp};
                        String response1 = (String) client.execute("Methods.getCurp",data1);
                        System.out.println(response1);
                        break;
                    case 3:
                        System.out.println("Actualizar datos \n");
                        System.out.println("Dime tu nombre");
                        name = sc.next();
                        System.out.println("Primer Apellido");
                        ap = sc.next();
                        System.out.println("Segundo Apellido");
                        am = sc.next();
                        System.out.println("Inserta tu curp");
                        curp = sc.next();
                        System.out.println("Fecha de Nacimiento");
                        date = sc.next();

                        Object [] data3 = {name,ap,am,curp,date};
                        String response3 = (String) client.execute("Methods.update",data3);
                        System.out.println(response3);
                        break;
                    case 4:
                        System.out.println("Eliminar Persona \n");
                        System.out.println("Dime la curp");
                        curp = sc.next();

                        String [] data2 = {curp};
                        String response2 = (String) client.execute("Methods.delete",data2);
                        System.out.println(response2);
                        break;
                    default:
                        System.out.println("Ingresa una opción válida");
                }
            }else{
                System.out.println("Ingresa un valor válido");
            }
        }while (!option.equals("5"));
    }

    public static boolean isNumber(String number){
        try{
            int num = Integer.parseInt(number);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
