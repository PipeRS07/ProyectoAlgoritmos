package Data;

import domain.clasesBase.User;
import domain.clasesBase.Usuario;
import util.Encriptacion;
import util.Fabrica;
import util.Ruta;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserData {
    private RandomAccessFile raf;
    private int cantidadDeRegistros;
    private int tamanioRegistro;
    //id:4   name:100   email:100    contrasenia:100
    //4+100+100+100 =304


    public UserData() throws IOException {
        init();
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
    }

    public boolean registrarUser(int posicion, User usuario) throws IOException {
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
            }
        }
        return respuesta;
    }


    public ArrayList<User> getUsuarios() throws IOException {
        //el metodo retorna la lista de los usuarios que están guardados en el archivo
        ArrayList<User> usuarios= new ArrayList<>();
        int id=0;
        String name="";
        String email="";
        String role="";
        String contrasenia="";
        for (int i = 0; i < cantidadDeRegistros; i++) {
            this.raf.seek(i*tamanioRegistro);
            id=raf.readInt();
            name= raf.readUTF();
            email= raf.readUTF();
            role= raf.readUTF();
            contrasenia= raf.readUTF();
            if(Fabrica.fabricaUsuarios(id, name, email, role, contrasenia)!=null)
                usuarios.add(Fabrica.fabricaUsuarios(id, name, email, role, contrasenia));
        }
        return usuarios;
    }

    public User getUsuarioPorContrasenia(String contrasenia) throws NoSuchAlgorithmException, IOException {
        // El método retorna un usuario en caso de que lo encuentre
        // Busca un usuario por la contrasenia
        String contraEncriptada = Encriptacion.obtenerContraseniaCifrada(contrasenia);
        ArrayList<User> usuarios = this.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getContrasenia().equals(contraEncriptada)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public User getUsuarioPorNombre(String nombre) throws NoSuchAlgorithmException, IOException {
        // El método retorna un usuario en caso de que lo encuentre
        // Busca un usuario por el nombre
        ArrayList<User> usuarios = this.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getName().equals(nombre)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public boolean agregarAlFinal(User usuario) throws IOException {
        if (registrarUser(this.cantidadDeRegistros, usuario)) {
            ++cantidadDeRegistros;
            return true;
        }
        return false;
    }
}
