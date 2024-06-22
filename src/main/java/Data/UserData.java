package Data;

import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import util.Fabrica;
import util.Ruta;
import util.Utility;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class UserData {
    private RandomAccessFile raf;
    private int cantidadDeRegistros;
    private int tamanioRegistro;
    //id:4   name:100   email:100    contrasenia:100
    //4+100+100+100 =304
    private CircularDoublyLinkedList users;


    public UserData() throws IOException {
        init();
    }

    public void guardarUsuarios() throws ListException, IOException {
        int size= util.Utility.usuariosRegistrados.size();
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            System.out.println("UserData.guardarUsuarios"+i+util.Utility.getUsuariosRegistrados().getNode(i+1).data);
            if(!(Utility.usuariosRegistrados.getNode(i+1).data==null))
                System.out.println("UserData.guardarUsuarios2"+i);
                registrarUser(i, (User) Utility.usuariosRegistrados.getNode(i+1).data );
        }

    }

    private void init() throws IOException {
        this.tamanioRegistro=304;
        File file = new File(Ruta.RUTAUSUARIO);
        if(file.exists() && !file.isFile()){  //si el archivo existe y está dañado
            throw new IOException(file.getName()+"no es un archivo valido( 001)");
        }else{

            this.raf= new RandomAccessFile(Ruta.RUTAUSUARIO, "rw");
            //toma el tamaño del archivo == 344*cantidad de registros y lo divide entre 344
            this.cantidadDeRegistros= (int)Math.ceil(this.raf.length()/(double)this.tamanioRegistro); //Math.cel hace un redondeo para calcular la cantidad de registros
        }
        this.users = util.Utility.usuariosRegistrados;
    }

    private boolean registrarUser(int posicion, User usuario) throws IOException {
        boolean respuesta =false;
        if(posicion>= 0 && posicion <= cantidadDeRegistros){
            //si el tamaño del registro es adecuado
            if(!(usuario.size() > this.tamanioRegistro)){
                this.raf.seek(posicion * this.tamanioRegistro);
                raf.writeInt(usuario.getId());
                raf.writeUTF(usuario.getName());
                raf.writeUTF(usuario.getEmail());
                raf.writeUTF(usuario.getRole());
                raf.writeUTF(usuario.getContrasenia());
                respuesta=true;
                cantidadDeRegistros++;
            }
        }
        return respuesta;
    }

    public boolean agregarAlFinal(User usuario) throws IOException {
        if (registrarUser(this.cantidadDeRegistros++, usuario)) {
            //++cantidadDeRegistros;
            return true;
        }
        return false;
    }

    public ArrayList<User> getUsuarios() throws IOException {
        //el metodo retorna la lista de los usuarios que están guardados en el archivo
        ArrayList<User> usuarios = new ArrayList<>();
        int id;
        String name;
        String email;
        String role;
        String contrasenia;

        for (int i = 0; i < cantidadDeRegistros; i++) {
            this.raf.seek(i * tamanioRegistro);
            id = raf.readInt();
            name = raf.readUTF();
            email = raf.readUTF();
            role = raf.readUTF();
            contrasenia = raf.readUTF();

            // Crear un objeto User temporal con los datos leídos

            User tempUser = Fabrica.fabricaUsuarios(id, name, email, role, contrasenia);

            // Utilizar el método fabricaUsuarios que toma un User como parámetro
            User usuario = Fabrica.fabricaUsuarios(tempUser);

            if (usuario != null) {
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public void cargarObjetos() throws IOException {
        ArrayList<User> usuarios= getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
           Utility.usuariosRegistrados.add(usuarios.get(i));
            //System.out.println(Utility.usuariosRegistrados);
        }
    }

}
