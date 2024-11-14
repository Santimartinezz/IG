/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.consola;

import java.util.List;
import java.util.Scanner;
import modelo.mascota.Gato;
import modelo.mascota.Perro;
import modelo.persona.duenio.Duenio;
import modelo.persona.veterinaria.Veterinaria;
import modelo.persona.veterinario.Veterinario;
import negocvio.abm.duenio.ABMDuenio;
import negocvio.abm.exception.duenio.DuenioException;
import negocvio.abm.perro.ABMPerro;
import negocvio.abm.veterinario.ABMVeterinario;
import negocvio.abm.veterinario.exception.VeterinarioException;
import vista.principal.jFromPrincipal;

/**
 *
 * @author Alumno
 */
public class Menu {
    
    private static ABMPerro ambPerro= new ABMPerro();
    private static ABMVeterinario aBMVeterinario=new ABMVeterinario();
    private static ABMDuenio aBMDuenio= new ABMDuenio();
    
    public static void main(String[] args){
        menuPrincipal();
    }
    
    private static void menuPrincipal(){
        
        System.out.println("========================== MENU ========================");
        
        System.out.println("1 ABM DUENIO");
        System.out.println("2 ABM VETERINARIO");
        System.out.println("3 ABM VETERINARIA");
        System.out.println("4 Vista de Turnos");
        System.out.println("5 Salir");
        
        Scanner scan= new Scanner(System.in);
        int opt=scan.nextInt();
        while(opt!=5){
            switch (opt) {
                case 1:

                    subMenuDuenio();
                    break;
                case 2:
                   subMenuVeterinario();
                    break;
                case 3:
                    modificarDatosPerro();
                    break;
                case 4: 
                    loadVistaConsulta();
                    break;
                default:
                    throw new AssertionError();
        }
            opt=scan.nextInt();
        }
        
    }
    
    private static void loadVistaConsulta(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFromPrincipal().setVisible(true);
            }
        });
    }
    private static void subMenuDuenio(){
        System.out.println("========================== SUB MENU ABM DUEÑO========================");
        
        System.out.println("1 ALTA DUEÑO");
        System.out.println("2 BAJA DUEÑO");
        System.out.println("3 MODIFICAR DUEÑO");
        System.out.println("4 LISTAR DUEÑO");
        System.out.println("5 AGREGAR MASCOTA A DUEÑO");
        System.out.println("6 LISTAR MASCOTA POR DUEÑO");
        System.out.println("7 Salir");
        
        Scanner scan= new Scanner(System.in);
        int opt=scan.nextInt();
        while(opt!=7){
            switch (opt) {
                case 1:
                    try{
                        altaDuenio();
                    }catch(DuenioException e){
                        System.out.println(e.getMessage());
                    }    
                    break;
                case 2:
                  
                    break;
                case 3:
                   
                    break;
                case 4: 
                    listarDuenio();
                    break;
                case 5: 
                    altaDeMascota();
                    break;
                case 6: 
                    listarMascotasPporDuenio();
                    break;    
                default:
                    throw new AssertionError();
        }
            subMenuDuenio();
            //1opt=scan.nextInt();
        }
        menuPrincipal();
    }
    
    private static void subMenuVeterinario(){
        System.out.println("========================== SUB MENU ABM DUEÑO========================");
        
        System.out.println("1 ALTA VETERINARIO");
        System.out.println("2 BAJA VETERINARIO");
        System.out.println("3 MODIFICAR VETERINARIO");
        System.out.println("4 LISTAR VETERINARIO");
        System.out.println("5 Salir");
        
        Scanner scan= new Scanner(System.in);
        int opt=scan.nextInt();
        while(opt!=5){
            switch (opt) {
                case 1:
                    try{
                      altaVeterinario();  
                    }catch(VeterinarioException e){
                        System.out.println(e.getMessage());
                    }
                    subMenuVeterinario();
                    break;
                case 2:
                  
                    break;
                case 3:
                   
                    break;
                case 4:
                   System.out.println("========= LISTAR VETERINARIOS=========");
                   aBMVeterinario.listarPersonas(0);
                   System.out.println("========= FIN LISTAR VETERINARIOS=========");
                   subMenuVeterinario(); 
                   break;
                default:
                    throw new AssertionError();
        }
            opt=scan.nextInt();
        }
    }
    private static void altaDuenio()throws DuenioException{
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese Nombre del Duenio");
        String nombre= scan.next();
        System.out.println("Ingrese apellido");
        String apellido=scan.next();
        System.out.println("Ingrese dni del dueño");
        int dni=scan.nextInt();
        //ABMDuenio aBMDuenio= new ABMDuenio();
        Duenio d= new Duenio(nombre, apellido, dni);
        aBMDuenio.altaPersona(d);
    }
    private static void listarDuenio(){
        aBMDuenio.listarPersonas(0);
    }
    
    private static void altaVeterinario()throws VeterinarioException{
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese Nombre del veterinario");
        String nombre= scan.next();
        System.out.println("Ingrese apellido");
        String apellido=scan.next();
        System.out.println("Ingrese dni del veterinario");    
        int dni=scan.nextInt();
        System.out.println("Ingrese la matricula del veterinario");
        String matricula=scan.next();
        Veterinario vete= new Veterinario(matricula, nombre, apellido, dni);
        aBMVeterinario.altaPersona(vete);
    }
    private static Perro cargarDatosPerro(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese Nombre del Perro");
        String nombre = scan.nextLine();
        System.out.println("Ingrese M si es macho o H si es hembra");
        char sexo=scan.next().charAt(0);
        System.out.println("Ingrese la edad del Perro");
        int edad=scan.nextInt();
        System.out.println("Ingrese el peso del Perro");
        float peso=scan.nextFloat();
        System.out.println("Ingrese la Raza del Perro");
        String raza=scan.nextLine();
        return  new Perro(raza, nombre, edad, peso,sexo);
    }
    
    private static void bajaPerro(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese el còdigo de la mascota a eliminar");
        String codigo=scan.nextLine();
        ambPerro.bajaMascotaArray(codigo);
    }
    
    private static void modificarDatosPerro(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese el còdigo de la mascota a eliminar");
        String codigo=scan.nextLine();
        Perro p= (Perro)ambPerro.buscarMascota(codigo);
        if(p!=null){
            System.out.println("Ingrese Nombre del Perro");
        String nombre = scan.nextLine();
        p.setNombre(nombre);
        System.out.println("Ingrese M si es macho o H si es hembra");
        String sexo=scan.nextLine();
        p.setSexo((sexo.equals("M"))?'M':'F');
        System.out.println("Ingrese la edad del Perro");
        int edad=scan.nextInt();
        p.setEdad(edad);
        System.out.println("Ingrese el peso del Perro");
        float peso=scan.nextFloat();
        p.setPeso(peso);
        System.out.println("Ingrese la Raza del Perro");
        String raza=scan.nextLine();
        p.setRaza(raza);
        ambPerro.modificacionMascotaArray(p);
        }else
            System.out.println("La mascota que se intenta modificar no existe");
    }
    
    private static void altaDeMascota(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese el dni del Dueño");
        int dni=scan.nextInt();
        System.out.println("Igrese 1 si la mascota es perro o 2 si es gato.");
        int tipo=scan.nextInt();
        System.out.println("Igrese el nombre de la mascota.");
        String nombre=scan.next();
        System.out.println("Igrese la raza de la mascota.");
        String raza=scan.next();
        System.out.println("Igrese la edad de la mascota.");
        int edad=scan.nextInt();
        System.out.println("Igrese el peso de la mascota.");
        float peso=scan.nextFloat();
        System.out.println("Igrese el sexo de la mascota.");
        char sexo=scan.next().charAt(0);
        if(tipo==1){
            Perro p=new Perro(raza, nombre, edad, peso, sexo);
            p.setCodigoPerro(ambPerro.nuevoCodigoMascota());
            ambPerro.altaMascota(p, dni);
        }else{
            Gato g= new Gato(raza, nombre, edad, peso, sexo);
            //creat el abm,Gato
        }   
    }
    
    private static void listarMascotasPporDuenio(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Ingrese el dni del Dueño");
        int dni=scan.nextInt();
        Duenio duenio=aBMDuenio.buscarDuenioPorDni(dni);
        if(duenio!=null){
            System.out.println("DUENIO: "+duenio.getNombre()+ " "+duenio.getApellido());
            List<Perro> perros=(List<Perro>)ambPerro.listarMascotaPorDuenio(dni);
            System.out.println("============= LISTADO DE MASCOTA==============");
            for (Perro perro : perros) {
                System.out.println(perro.toString());
            }
            System.out.println("============= FIN LISTADO==============");
        }
        
    }
}
