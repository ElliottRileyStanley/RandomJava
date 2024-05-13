import java.util.ArrayList;

class connection {
    int weight;
    node node;

    public connection (node n, int w) {
        node = n;
        weight = w;
    }

    public int calc() {
        return node.getValue() * weight;
    }
}

class node {
    int bias;
    int value = 0;
    ArrayList<connection> connections;

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

    public String toString() {
        String returned = super.toString() + "\n" + "Value - " + value + "\n" + "Bias - " + bias + "\n" + "Connections - ";
        for (connection connection : connections) {
            returned += connection.node;
            returned += connection.weight;
        }
        return returned;
    }

}

class Network {
    ArrayList<node> inputs;
    ArrayList<node> nodes = new ArrayList<node>();
    ArrayList<node> outputs;

    public Network(ArrayList<node> i, int h, int w, int o) {
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
        node two = new node(-5);
        ArrayList<node> input = new ArrayList<node>();
        input.add(one);
        input.add(two);
        Network net = new Network(input, 2, 1, 1);
        System.out.println(net.nodes.get(0));
    }
}
