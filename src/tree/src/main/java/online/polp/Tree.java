package online.polp;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Creare una classe Nodo per rappresentare un albero in cui ogni nodo può avere più figli (tramite il puntatore al primo figlio) e più fratelli (tramite il puntatore al primo fratello). Scrivere i metodi per calcolare:
//la massima profondità del nodo (altezza dell'albero),
//il numero totale di nodi nell'albero,
//il numero di foglie (nodi che non hanno figli).
//Le visite sono tecniche che determinano l'ordine in cui i nodi di un albero vengono analizzati. In particolare, in questo caso sono da implementare due tipologie di visita: la visita in preordine (Depth-First Search, DFS) e la visita in larghezza (Breadth-First Search, BFS).
//
//Depth-First Search, DFS
//La visita in preordine è una visita in profondità che segue questa sequenza:
//visita il nodo corrente (prima di visitare i figli),
//visita ricorsivamente il primo figlio,
//visita ricorsivamente i fratelli (in sequenza).
//In altre parole, la visita esplora un ramo dell'albero fino a raggiungere una foglia e solo dopo si sposta al ramo successivo.
//
//Breadth-First Search
//La visita di in larghezza esplora l'albero orizzontalmente, livello per livello. In altre parole, prima visita tutti i nodi al livello più alto (ossia, tutti i figli diretti di un nodo), quindi passa ai nodi di livello successivo, e così via. Questo viene fatto utilizzando una coda per memorizzare i nodi da visitare.
//La visita in larghezza segue questi passaggi:
//visita il nodo corrente,
//aggiungi tutti i figli del nodo corrente alla coda,
//estrai e visita il prossimo nodo dalla coda, ripetendo il processo.

public class Tree<T> {
    @Data
    public class Node {
        private T value;
        private Node child;
        private Node sibling;
    }

    private final Node root;

    public Tree() {
        root = new Node();
    }

    public Tree(T value) {
        root = new Node();
        root.setValue(value);
    }

    /**
     * The maximum depth of the node (tree height)
     *
     * @return the maximum depth of the node (tree height)
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        int childMaxHeight = height(node.getChild());
        int siblingMaxHeight = height(node.getSibling());

        return 1 + Math.max(childMaxHeight, siblingMaxHeight);
    }

    /**
     * The total number of nodes in the tree
     *
     * @return the total number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        int childSize = size(node.getChild());
        int siblingSize = size(node.getSibling());

        return 1 + childSize + siblingSize;
    }

    /**
     * The number of leaves (nodes that do not have children)
     *
     * @return the number of leaves (nodes that do not have children)
     */
    public int leaves() {
        return leaves(root);
    }

    private int leaves(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.getChild() == null) {
            return 1 + leaves(node.getSibling());
        }

        int childLeaves = leaves(node.getChild());
        int siblingLeaves = leaves(node.getSibling());

        return childLeaves + siblingLeaves;
    }

    /**
     * Depth-First Search, DFS
     *
     * @return the representation of the tree in pre-order
     */
    public List<T> dfs() {
        List<T> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(Node node, List<T> result) {
        if (node == null) {
            return;
        }

        result.add(node.getValue());
        dfs(node.getChild(), result);
        dfs(node.getSibling(), result);
    }

    /**
     * Breadth-First Search, BFS
     *
     * @return the representation of the tree in breadth-first order
     */
    // Uses java.util.Queue
    public List<T> bfs() {
        List<T> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // Poll retrieves and removes the head of the queue
            result.add(node.getValue());

            Node child = node.getChild();
            while (child != null) {
                queue.add(child);
                child = child.getSibling();
            }
        }

        return result;
    }
}
