package domain.list;

public class CircularLinkedList implements List {
    private Node first; //apuntador al inicio de la lista
    private Node last;

    public CircularLinkedList() {
        this.first = this.last = null; //la lista no existe
    }

    @Override
    public int size() throws ListException {
        if(isEmpty()){
            throw new ListException("Doubly Linked List is empty");
        }
        Node aux = first;
        int count=0;
        while(aux!=last){
            count++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return count +1 ;
    }

    @Override
    public void clear() {
        this.last = this.first = null ; //anulamos la lista
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
        do { //itera hasta el final comparando hasta el ultimo elemento
            if (util.Utility.compare(aux.data, element) == 0) {
                return true;//retorna true si encuentra el elemento e incluye al ultimo elemento
            }
            aux = aux.next; // lo movemos al siguiente nodo
        } while (aux != first);//se valida que no se llegue al inicio osea se detiene cuando llega al inicio
        return false;
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first= last = newNode;
        }else{

            last.next = newNode;
            //ponemos last a apuntar al ultimo nodo
            last = newNode;
        }
        //hacemos el enlace circular
        last.next=first;

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first =last= newNode;
        }else{
            newNode.next = first;
            first= newNode;
        }
        last.next=first;

    }

    @Override
    public void addLast(Object element) {

        add(element);
    }

    @Override
    public void addInSortedList(Object element) {

        Node newNode = new Node(element);

        if (isEmpty() || util.Utility.compare(element, first.data) <= 0) {
            addFirst(element);
        } else if (util.Utility.compare(element, last.data) >= 0) {
            add(element);
        } else {
            Node aux = first;
            while (util.Utility.compare(element, aux.next.data) > 0) {
                aux = aux.next;
            }
            newNode.next = aux.next;
            aux.next = newNode;
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
            while(aux!=last && !(util.Utility.compare(aux.data, element)==0)){
                prev = aux;
                aux = aux.next;
            }
            //se sale cuando aux encutra a last o cuando encuentra el elemento
            if( util.Utility.compare(aux.data, element)==0){
                //ya lo encontro, procedo a desenlazar el nodo
                prev.next = aux.next;
            }
            //que oasa si el elemento a suprimir esta en el ultimo nodo
            //es decir donde esta last
            if( aux== last && util.Utility.compare(aux.data, element)==0){
                last = prev;//desenlaza el ultimo nodo
            }



            // hay que probarlo

        }
        //mantego el enlace circular
        last.next=first;
        // sisolo queda un nodo y es el que quiero eliminar
        if( first== last && util.Utility.compare(first.data, element)==0)
            clear();
    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }
        Object removedData = first.data;
        first = first.next;

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
    public void sort() throws ListException { //Este sigue igual y funcionan en circularLinkedList
        if(isEmpty()){
            throw new ListException("Circular Linked List is Empty");
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
            throw new ListException("Circular Linked List is Empty");
        }
        Node aux = first;
        int index=1; //la lista inicia en 1
        while(aux!=last){
            if(util.Utility.compare(aux.data, element)==0){
                return index;
            }
            index++; //incremento el indice
            aux=aux.next; //muevo aux al sgte nodo
        }
        if(util.Utility.compare(aux.data, element)==0)
            return index;
        return -1; //indica q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Linked List is Empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Linked List is Empty");
        }
        return last;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular" +
                    " Linked List is Empty");
        }
        if(util.Utility.compare(first.data, element)==0){
            return "It's the first, it has no previous";
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=last.next){
            if(util.Utility.compare(aux.next.data, element)==0){
                return aux.data; //retornamos la data del nodo actual
            }
            aux=aux.next;
        }
        //se sale cuando aux.next es last
        if(util.Utility.compare(aux.next.data, element)==0)
            return aux.data;

        return "Does not exist in Doubly Linked List";

    }

    @Override
    //revisar
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }
        Node aux = first;
        while (aux != last) {
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
        while(aux!=last){
            if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
                return aux;
            }
            i++; //incremento la var local
            aux = aux.next; //muevo aux al sgte nodo
        }
        // se sale cuando aux es igual a last
        if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
            return aux;
        }
        return null; //si llega aqui es xq no encontro el index

    }

    @Override
    public String toString() {
        String result = "Circular List Content\n\n";
        Node aux = first;
        while(aux!=last){
            result+= STR."\{aux.data}\n";
            aux = aux.next;
        }
        return result+"\n"+aux.data;
    }


}