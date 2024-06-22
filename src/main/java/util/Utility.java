package util;

import Data.CursoData;
import Data.UserData;
import domain.bTree.BTree;
import domain.bTree.BTreeNode;
import domain.bTree.TreeException;
import domain.clasesBase.*;

import domain.graph.GraphException;
import domain.list.*;
import domain.queue.LinkedQueue;
import domain.stack.LinkedStack;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

import static java.lang.StringTemplate.STR;

public  class Utility {

    public static CircularDoublyLinkedList usuariosRegistrados ;
    public static CircularLinkedList usuariosEnELSistema ;
    public static AVL cursosRegistrados ;
    public static BTree inscripcionesSolicitadas;
    public static BST leccionesRegistradas;
    private static UserData userData;
    private static CursoData cursoData;
    public static User UserActivo;


    static {
        try {
            usuariosRegistrados= new CircularDoublyLinkedList();
            cursosRegistrados = new AVL();
            usuariosEnELSistema = new CircularLinkedList();
            inscripcionesSolicitadas = new BTree();
            leccionesRegistradas = new BST();

            userData = new UserData();
            cursoData = new CursoData();

            // Inicialización de listas
            userData.cargarObjetos();
            cursoData.cargarCursos();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void guardarCursos() throws IOException, domain.clasesBase.TreeException, TreeException {
            if(cursosRegistrados.isEmpty()){
                System.out.println("no hay cursos ");
            }else {
                cursoData.guardarCursos();
            }
    }

    public static void guardarUsuarios() throws ListException, IOException {
        if(usuariosRegistrados.isEmpty()){
            System.err.println("La lista está vacía");
        }else{
            userData.guardarUsuarios();
        }

    }

    public static CircularDoublyLinkedList getUsuariosRegistrados(){
        return usuariosRegistrados;
    }

    private static String convertirAHexadecimal(byte[] digest){
        String hash="";
        // foreach
        for(byte aux:digest){
            int b=aux&0xff;
            if(Integer.toHexString(b).length()==1){
                hash+="0";
            }
            hash+=Integer.toHexString(b);
        } // foreach

        return hash;
    } // convertirAHexadecimal



    public static String format(double value){
        return new DecimalFormat("###,###,###.##").format(value);
    }
    public static String $format(double value){
        return new DecimalFormat("$###,###,###.##").format(value);
    }
    public static String show(int[] a, int size) {
        String result="";
        for (int i = 0; i < size; i++) {
            result+= STR."\{a[i]} ";
        }
        return result;
    }

    public static void fill(int[] a, int bound) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(bound);
        }
    }

    public static int getRandom(int bound) {
        return new Random().nextInt(bound)+1;
    }
    public static int getRandom(int inicio, int fin) {

        return new Random().nextInt(fin-inicio+1)+inicio;
    }

    public static int compare(Object a, Object b) {
        switch (instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2 ? -1 : int1 > int2 ? 1 : 0; //0 == equal
            case "String":
                String st1 = (String)a; String st2 = (String)b;
                return st1.compareTo(st2)<0 ? -1 : st1.compareTo(st2) > 0 ? 1 : 0;
            case "Character":
                Character c1 = (Character)a; Character c2 = (Character)b;
                return c1.compareTo(c2)<0 ? -1 : c1.compareTo(c2)>0 ? 1 : 0;
            case "SinglyLinkendList":
                SinglyLinkedList s1 = (SinglyLinkedList)a; SinglyLinkedList s2 = (SinglyLinkedList)b;
                return s1==s2?0:-1;
            case "LinkedQueue":
                LinkedQueue lq1 = (LinkedQueue)a; LinkedQueue lq2 = (LinkedQueue)b;
                return lq1==lq2?0:-1;
            case "CircularDoublyLinkedList":
                CircularDoublyLinkedList clq1 = (CircularDoublyLinkedList)a; CircularDoublyLinkedList clq2 = (CircularDoublyLinkedList)b;
                return clq1==clq2?0:-1;
            case "LinkedStack":
                LinkedStack ls1 = (LinkedStack)a; LinkedStack ls2 = (LinkedStack)b;
                return ls1==ls2?0:-1;
            case "BTree":
                BTree bt1 = (BTree)a; BTree Bt2 = (BTree)b;
                return bt1==Bt2?0:-1;
            case "BtreNodeString":
                BTreeNode avl1 = (BTreeNode) a; String str = (String) b;
                Curso c = (Curso) avl1.data;
                return c.getNombre().equals(str)?0:-1;
            case "User":
                User u = (User)a; User u1 = (User)b;
                return u==u1?0:-1;
            case "CursoString":
                Curso cu1 = (Curso)a; String cu2 = (String) b;
                return compare(cu1.getNombre(), cu2);
            case "Curso":
                Curso cur1 = (Curso)a; Curso cur2 = (Curso) b;
                //return cur1.getNombre().equalsIgnoreCase(cur2.getNombre()) ?0: cur1.getId()==cur2.getId()?0:-1;
                System.out.println(compare(cur1.getId(), cur2.getId()));
                return compare(cur1.getId(), cur2.getId());
            case "UserId":
                User us = (User) a; Integer st = (Integer) b;

                return compare(us.getId(), st);
            case "CursoId":
                Curso cus = (Curso) a; Integer cuid = (Integer) b;
                return compare(cus.getId(), cuid);
            case "InscripcionString":
                Inscripcion ins = (Inscripcion) a; String insnm = (String) b;
                System.out.println("Utility.compare"+ins.getCurso().getNombre()+"***********"+insnm);
                return Objects.equals(ins.getCurso().getNombre(), insnm) ?0:-1;

        }
        return 2; //Unknown
    }
    public static void guardar() throws IOException, TreeException, domain.bTree.TreeException, domain.clasesBase.TreeException {
        cursoData.guardarCursos();
        userData.getUsuarios();
    }

    public static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer) return "Integer";
        if(a instanceof String && b instanceof String) return "String";
        if(a instanceof Character && b instanceof Character) return "Character";
        if(a instanceof SinglyLinkedList && b instanceof SinglyLinkedList) return "SinglyLinkendList";
        if(a instanceof BTree && b instanceof BTree) return "BTree";
        if(a instanceof LinkedQueue && b instanceof LinkedQueue) return "LinkedQueue";
        if(a instanceof CircularDoublyLinkedList && b instanceof CircularDoublyLinkedList) return "CircularDoublyLinkedList";
        if(a instanceof LinkedStack && b instanceof LinkedStack) return "LinkedStack";
        if(a instanceof CircularLinkedList && b instanceof CircularLinkedList) return "CircularLinkedList";
        if(a instanceof BTreeNode && b instanceof String) return "BtreNodeString";
        if(a instanceof User && b instanceof User) return "User";
        if(a instanceof Curso && b instanceof String) return "CursoString";
        if(a instanceof Curso && b instanceof Curso) return "Curso";
        if(a instanceof User && b instanceof Integer) return "UserId";
        if(a instanceof Curso && b instanceof Integer) return "CursoId";
        if(a instanceof Inscripcion && b instanceof String) return "InscripcionString";

        return "Unknown";
    }
}