package domain.list;

public class DoublyLinkedList implements List {
    private Node first; //apuntador al inicio de la lista

    public DoublyLinkedList() {
        this.first = null; //la lista no existe
    }

    @Override
    public int size() throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is empty");
        }
        Node aux = first;
        int count=0;
        while(aux!=null){
            count++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return count;
    }

    @Override
    public void clear() {
        this.first = null; //anulamos la lista
    }

    @Override
    public boolean isEmpty() {
        return this.first == null; //si es nulo está vacía
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is empty");
        }
        Node aux = first;
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0){
                return true;
            }
            aux = aux.next; //lo movemos al sgte nodo
        }
        return false; //indica q el elemento no existe
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = newNode;
        }else{
            Node aux = first;
            //mientras no llegue al ult nodo
            while(aux.next!=null){
                aux=aux.next;
            }
            //una vez que se sale del while, quiere decir q
            //aux esta en el ult nodo, por lo q lo podemos enlazar
            //con el nuevo nodo
            aux.next = newNode;
            //hago el doble enlace
            newNode.prev = aux;
        }

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = newNode;
        }else{
            newNode.next = first;
            //hago el doble enlace
            first.prev = newNode;
            first = newNode;
        }

    }

    @Override
    public void addLast(Object element) {

        //add(element); que hace???????
        Node newNode = new Node(element);
        if (isEmpty()) {
            first = newNode; // Si la lista está vacía, el nuevo nodo se convierte en el primer nodo
        } else {
            Node aux = first;
            // Sigue hasta el último nodo
            while (aux.next != null) {
                aux = aux.next;
            }
            // El nuevo nodo se enlaza con el último nodo y se establece el enlace previo
            aux.next = newNode;
            newNode.prev = aux;
        }
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);

        // Si la lista está vacía o el nuevo elemento es menor que el primer elemento
        if (isEmpty() || util.Utility.compare(element, first.data) < 0) {
            addFirst(element);
        } else {
            Node aux = first;
            // Se busca la posición donde se debe insertar
            while (aux.next != null && util.Utility.compare(element, aux.next.data) > 0) {
                aux = aux.next;
            }
            // Se inserta el elemento después del nodo actual
            newNode.next = aux.next;
            if (aux.next != null) {
                aux.next.prev = newNode; // Se establece el enlace previo del siguiente nodo al nuevo nodo
            }
            aux.next = newNode;
            newNode.prev = aux;
        }
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        //Caso 1. El elemento a suprimir esta al inicio
        if(util.Utility.compare(first.data, element)==0){
            first = first.next; //saltamos el primer nodo
        }else{  //Caso 2. El elemento a suprimir puede estar al medio o final
            Node prev = first; //dejo un apuntador al nodo anterior
            Node aux = first.next;
            while(aux!=null && !(util.Utility.compare(aux.data, element)==0)){
                prev = aux;
                aux = aux.next;
            }
            //se sale cuando alcanza nulo o cuando encuentra el elemento
            if(aux!=null && util.Utility.compare(aux.data, element)==0){
                //ya lo encontro, procedo a desenlazar el nodo
                prev.next = aux.next;
                //mantengo el doble enlace
                if(aux.next!=null){
                    aux.next.prev = aux.prev;
                }
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }
        Object removedData = first.data;
        first = first.next;
        if (first != null) {
            first.prev = null; // El enlace previo del nuevo primer nodo es nulo
        }
        return removedData;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        // Sigue hasta el penúltimo nodo
        while (aux.next != null && aux.next.next != null) {
            aux = aux.next;
        }
        Object removedData = aux.next.data;
        aux.next = null; // Elimina el enlace al último nodo
        return removedData;
    }

    @Override
    public void sort() throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        for (int i = 1; i <= size() ; i++) {
            for (int j = i+1; j <= size() ; j++) {
                if(util.Utility.compare(getNode(j).data, getNode(i).data)<0){
                    Object aux = getNode(i).data;
                    getNode(i).data = getNode(j).data;
                    getNode(j).data = aux;
                }
            }
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        int index=1; //la lista inicia en 1
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0){
                return index;
            }
            index++; //incremento el indice
            aux=aux.next; //muevo aux al sgte nodo
        }
        return -1; //indica q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=null){
            aux=aux.next;
        }
        //se sale del while cuando aux esta en el ult nodo
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        if(util.Utility.compare(first.data, element)==0){
            return "It's the first, it has no previous";
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=null){
            if(util.Utility.compare(aux.next.data, element)==0){
                return aux.data; //retornamos la data del nodo actual
            }
            aux=aux.next;
        }
        return "Does not exist in Doubly Linked List";
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) {
                // Si el elemento está presente, devuelve el siguiente elemento si existe
                if (aux.next != null) {
                    return aux.next.data;
                } else {
                    return "There is no next element";
                }
            }
            aux = aux.next;
        }
        // Si el elemento no está en la lista
        return "The element is not in the list";
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        int i = 1; // pos del primer nodo
        while(aux!=null){
            if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
                return aux;
            }
            i++; //incremento la var local
            aux = aux.next; //muevo aux al sgte nodo
        }
        return null; //si llega aqui es xq no encontro el index
    }

    @Override
    public String toString() {
        String result = "Doubly Linked List Content\n\n";
        Node aux = first;
        while(aux!=null){
            result+= STR."\{aux.data}\n";
            aux = aux.next;
        }
        return result;
    }


}