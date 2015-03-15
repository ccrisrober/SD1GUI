package com.distribuidos.sd12015;

import com.distribuidos.sd12015.data.ClaseConError;
import com.distribuidos.sd12015.data.ClaseConOk;
import com.distribuidos.sd12015.models.Domicilio;
import com.distribuidos.sd12015.models.Huesped;
import com.distribuidos.sd12015.models.Reserva;
import com.thoughtworks.xstream.XStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    protected static void showMenu() {
        System.out.println("Gestión hotel");
        System.out.println("----------------");
        System.out.println("Selecciona opción: ");
        System.out.println("1. Ver listado de huespeds");
        System.out.println("2. Ver listado de reservas");
        System.out.println("3. Añadir nuevo huesped");
        System.out.println("4. Añadir nueva reserva");
        System.out.println("5. Ver huesped");
        System.out.println("6. Ver reserva");
        System.out.println("7. Salir");
    }

    protected static String DOMAIN = "http://localhost:8080/SD12015/WS/";

    public static void main(String[] argv) throws MalformedURLException, IOException, ParseException {
        int opc = 0;

        XStream miStream = new XStream();
        URL url;
        HttpURLConnection conn;
        InputStream is;
        int codigo_http;
        while (opc != 7) {
            showMenu();

            Scanner sc = new Scanner(System.in);
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    url = new URL(DOMAIN + "Huespeds");

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

                    is = conn.getInputStream();
                    codigo_http = conn.getResponseCode();
                    if (codigo_http / 100 != 2) {
                        System.out.println("Error HTTP " + codigo_http);
                    } else {
                        List<Huesped> huespeds = (List<Huesped>) miStream.fromXML(is);
                        for (Huesped h : huespeds) {
                            System.out.println(h);
                        }
                    }
                    break;
                case 2:
                    url = new URL(DOMAIN + "Reservas");

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

                    is = conn.getInputStream();
                    codigo_http = conn.getResponseCode();
                    if (codigo_http / 100 != 2) {
                        System.out.println("Error HTTP " + codigo_http);
                    } else {
                        List<Reserva> reservas = (List<Reserva>) miStream.fromXML(is);
                        for (Reserva r : reservas) {
                            System.out.println(r);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Dime el nombre: ");
                    String name = sc.next();
                    System.out.print("Dime los apellidos: ");
                    String surname = sc.next();
                    System.out.print("Dime NIF: ");
                    String nif = sc.next();
                    System.out.print("Dime fecha de nacimiento: ");
                    String date_ = sc.next();
                    
                    //Date date = ServicioREST.strToDate(date_);
                    
                    System.out.println("¿Quieres añadir dirección? [s]");
                    String addDir = sc.next();
                    Domicilio dom = new Domicilio();
                    if(addDir.compareToIgnoreCase("s") == 0) {
                        System.out.println("Dime dirección: ");
                        dom.setDireccion(sc.next());
                        System.out.println("Dime localidad: ");
                        dom.setLocalidad(sc.next());
                        System.out.println("Dime código postal: ");
                        dom.setCodigoPostal(sc.nextInt());
                        System.out.println("Dime provicincia: ");
                        dom.setProvincia(sc.next());
                    }
                    
                    //Huesped h = new Huesped(nif, name, surname, date, dom);
                    //String toSend = miStream.toXML(h);
                    
                    url = new URL(DOMAIN + "AniadirHuesped");

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml
                    conn.setRequestMethod("POST");
                    
                    String params = 
                            "huesped.nombre=" + name + 
                            "&huesped.apellidos=" + surname + 
                            "&huesped.NIF=" + nif + 
                            "&huesped.nacimiento=" + date_;/* + 
                            "&huesped.apellidos=" + surname + 
                            "&huesped.apellidos=" + surname + 
                            "&huesped.apellidos=" + surname + 
                            "&huesped.apellidos=" + surname;*/
 
                    // Send post request
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(params);
                    wr.flush();
                    wr.close();

                    is = conn.getInputStream();
                    codigo_http = conn.getResponseCode();
                    if (codigo_http / 100 != 2) {
                        System.out.println("Error HTTP " + codigo_http);
                    } else {
                        ClaseConOk ok = (ClaseConOk) miStream.fromXML(is);
                        if(ok.isOk()) {
                            System.out.println("Agregado con éxito.");
                        } else {
                            System.out.println("No se ha podido crear.");
                        }
                    }
                    break;
                case 4:
                    break;
                case 5:
                    System.out.print("Dime NIF: ");
                    String cadena = sc.next();
                    url = new URL(DOMAIN + "VerHuesped?NIF=" + cadena);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

                    is = conn.getInputStream();
                    codigo_http = conn.getResponseCode();
                    if (codigo_http / 100 != 2) {
                        System.out.println("Error HTTP " + codigo_http);
                    } else {
                        Object o = miStream.fromXML(is);
                        try {
                            Huesped hh = (Huesped) o;

                            if (hh == null) {
                                System.err.println("ERROR");
                            } else {
                                System.out.println(hh);
                            }
                            System.out.println("¿Quieres editar (E) o borrar (B)?");
                            String delUpd = sc.next();
                            if(delUpd.equalsIgnoreCase("E")) {
                                // TODO: Editamos
                            } else if (delUpd.equalsIgnoreCase("B")) {
                                // TODO: Borramos
                            }
                        } catch(ClassCastException ex) {
                            ClaseConError err = (ClaseConError) o;
                            System.out.println(err.getCodigoError() + " - " + err.getMensajeError());
                        }
                    }
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Saliendo ...");
                    break;
                default:

            }
        }
    }
}