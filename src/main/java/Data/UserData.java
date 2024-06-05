package Data;

import domain.clasesBase.User;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
        File file = new File("users.dat");
        if(file.exists() && !file.isFile()){  //si el archivo existe y está dañado
            throw new IOException(file.getName()+"no es un archivo valido( 001)");
        }else{

            this.raf= new RandomAccessFile("users.dat", "rw");
            //toma el tamaño del archivo == 344*cantidad de registros y lo divide entre 344
            this.cantidadDeRegistros= (int)Math.ceil(this.raf.length()/(double)this.tamanioRegistro); //Math.cel hace un redondeo para calcular la cantidad de registros
        }
    }

    public boolean registrarUser(int posicion, User usuario) throws IOException {
        boolean respuesta =false;
        if(posicion>= 0 && posicion <= cantidadDeRegistros){
            //si el tamaño del registro es adecuado
            if(!(usuario.size() > this.tamanioRegistro)){
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




}
