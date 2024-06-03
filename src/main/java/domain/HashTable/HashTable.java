package domain.HashTable;


import domain.list.SinglyLinkedList;

public class HashTable {
    private SinglyLinkedList[] table;

    private static final int tableSize = 10;

    public HashTable() {
    }

    public void add(Object objeto, String valor) {
        this.table = new SinglyLinkedList[tableSize];
        int hashCode = getHashCode(valor);
        SinglyLinkedList listAux =  table[hashCode];
        listAux.add(objeto);
        //aqui se añaden los datos en la lista


    }
    private int getHashCode (String valor){
        int result = 0;
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            result = (int) c * (i + 1);


        }
        return result % 10;//para hacer hash con las ultimas dos cifras del valor
    }
}





