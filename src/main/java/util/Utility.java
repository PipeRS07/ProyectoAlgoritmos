package util;

import Data.CursoData;
import Data.UserData;
import domain.HashTable.HashTable;
import domain.bTree.BTree;
import domain.clasesBase.AVL;
import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.CircularLinkedList;
import domain.list.SinglyLinkedList;
import domain.queue.LinkedQueue;
import domain.stack.LinkedStack;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.StringTemplate.STR;

public class Utility {


    public static CircularDoublyLinkedList usuariosRegistrados;
    public static CircularLinkedList usuariosEnELSistema;
    public static AVL cursosRegistrados;


    public static HashTable circularLinkedList;
    private static UserData userData;
    private static CursoData cursoData;
    public static User usuarioactivo=null;


    static {
        try {
            userData = new UserData();
            cursoData = new CursoData();

            usuariosRegistrados = new CircularDoublyLinkedList();
            userData.cargarObjetos(usuariosRegistrados);

            usuariosEnELSistema= new CircularLinkedList();

            cursosRegistrados= new AVL();
            userData.cargarObjetos(cursosRegistrados);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static String obtenerContraseniaCifrada(String mensaje, String algoritmo) throws NoSuchAlgorithmException, NoSuchAlgorithmException {
        byte[] digest=null;

        byte[] buffer=mensaje.getBytes();
        MessageDigest messageDigest=MessageDigest.getInstance(algoritmo);
        messageDigest.reset();
        messageDigest.update(buffer);
        digest=messageDigest.digest();
        //System.out.println(digest);
        return convertirAHexadecimal(digest);

    } // obtenerContraseniaSifrada

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
        }
        return 2; //Unknown
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
        return "Unknown";
    }
}
