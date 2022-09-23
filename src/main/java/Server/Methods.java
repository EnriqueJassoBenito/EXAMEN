package Server;

import Client.BeanRFC;
import Client.DaoRFC;

import java.util.concurrent.ThreadLocalRandom;

public class Methods {

    public String datos(String name, String ap, String am, String curp, String date,int longitud) {

        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena="";
        for (int x= 0; x < longitud; x++){
            int indicealeatorio = numeroaleatorio(0,letras.length()-1);
            char caracteraleatorio = letras.charAt(indicealeatorio);
            cadena += caracteraleatorio;
        }
/*
        String rfc = ap.trim().substring(0,2).toUpperCase();
        rfc = rfc.concat(am.trim().substring(0,1)).toUpperCase();
        rfc = rfc.concat(name.trim().substring(0,1)).toUpperCase();
        rfc = rfc.concat(date.trim().substring(2,4)).toUpperCase();
        rfc = rfc.concat(cadena.trim().substring())
*/

        String pa = ap.trim().substring(0, 2).toUpperCase();
        String sm = am.trim().substring(0, 1).toUpperCase();
        String nom = name.trim().substring(0, 1).toUpperCase();
        String a = date.trim().substring(8).toUpperCase();
        String mes = date.trim().substring(3, 5).toUpperCase();
        String dia = date.trim().substring(0, 2).toUpperCase();

        String RFC = pa + sm + nom + a + mes + dia + cadena;


        return "RFC: " + RFC + "\n"
                + "Nombre: " + name + " " + ap + " " + am + " " + "\n"
                + "Curp: " + curp + "\n"
                + "Fecha de nacimiento: " + date;
    }

    public static int numeroaleatorio(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    public String rfc_registro(String name, String ap, String am, String curp, String date){
        DaoRFC daoRFC = new DaoRFC();
        BeanRFC rfc = new BeanRFC();
        rfc.setName(name);
        rfc.setAp(ap);
        rfc.setAm(am);
        rfc.setCurp(curp);
        rfc.setDate(date);
        DaoRFC.save(rfc);
        return "Resgistro exito"+daoRFC;
    }

    public String getCurp (String curp){
        DaoRFC daoRFC = new DaoRFC();
        BeanRFC beanRFC = daoRFC.getcurp(curp);
        String consulta = "Nombre Completo: " + beanRFC.getName() + " " + beanRFC.getAp() + " " + beanRFC.getAm() + " " +
                "\nCurp: " + beanRFC.getCurp() + "\nFecha de Nacimiento: " + beanRFC.getDate();
        return consulta;
    }

    public String delete(String curp){
        DaoRFC daoRFC = new DaoRFC();
        daoRFC.delete(curp);

        return "Persona eliminda" + curp;
    }

    public String update(String name, String ap, String am, String curp, String date){
        DaoRFC daoRFC = new DaoRFC();
        BeanRFC rfc = new BeanRFC();
        rfc.setName(name);
        rfc.setAp(ap);
        rfc.setAm(am);
        rfc.setCurp(curp);
        rfc.setDate(date);
        daoRFC.update(rfc);
        return "Persona actualizada" + rfc;
    }
}
