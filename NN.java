import java.util.ArrayList;

class connection {
    private int weight;
    private node node;

    public connection (node n, int w) {
        node = n;
        weight = w;
    }

    public int calc() {
        return node.getValue() * weight;
    }
}

class node {
    private int bias;
    private int value = 0;
    private ArrayList<connection> connections;

    public node (int b, ArrayList<connection> c) {
        bias = b;
        connections = c;
    }

    public node (int v) {
        value = v;
    }

    public void calc() {
        for (connection connection: connections) {
            value += connection.calc();
        }
        value += bias;
    }

    public int getValue() {
        return value;
    }

}

class Network {
    private ArrayList<node> inputs;
    private ArrayList<node> nodes;
    private ArrayList<node> outputs;

    public Network(ArrayList<node> i, int h, int w) {
        inputs = i;
        ArrayList<node> prev = i;
        ArrayList<node> curr = new ArrayList<node>();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                ArrayList<connection> connections = new ArrayList<connection>();
                for(node node: prev) {
                    connections.add(new connection(node, (int) (Math.random() * 21 - 10)));
                }
                node node = new node((int)(Math.random() * 21 - 10), connections);
                nodes.add(node);
                curr.add(node);
            }
            prev = curr;
            curr = new ArrayList<node>();
        }
    }
}

public class NN {
    public static void main(String[] args) {
        node one = new node(5);
        node two = new node(-2);
        ArrayList<connection> connections = new ArrayList<connection>();
        connections.add(new connection(one, 2));
        connections.add(new connection(two, -2));
        node three = new node(6, connections);
        three.calc();
        System.out.println(three.getValue());
    }
}
