import java.util.ArrayList;

class Connection {
    Double weight;
    Node node;

    public Connection (Node n, Double w) {
        node = n;
        weight = w;
    }

    public Double calc() {
        return node.getValue() * weight;
    }
}

class Node {
    static int curId = 1;
    int id;
    int bias;
    Double value = 0.0;
    ArrayList<Connection> connections;

    public Node (int b, ArrayList<Connection> c) {
        this.id = curId;
        curId++;
        bias = b;
        connections = c;
    }

    public Node (Double v) {
        this.id = curId;
        curId++;
        value = v;
    }

    public Node () {
        this.id = curId;
        curId++;
        value = 0.0;
    }

    public void calc() {
        value = 0.0;
        for (Connection connection: connections) {
            value += connection.calc();
        }
        value += bias;
    }

    public Double getValue() {
        return value;
    }

    public String toString() {
        String returned = "Node " + id + "\n" + "Value - " + value + "\n" + "Bias - " + bias + "\n" + "---Connections--- (Value * Weight)" + "\n";
        if (connections != null) {
            for (Connection connection : connections) {
                returned += "Node " + connection.node.id + " - " + connection.node.value + "*" + connection.weight + "\n";
            }
        } else {
            returned += "None (:" + "\n";
        }
        return returned + "\n";
    }
}

class Network {
    ArrayList<Node> inputs;
    ArrayList<Node> nodes = new ArrayList<Node>();
    ArrayList<Node> outputs = new ArrayList<Node>();

    public Network(ArrayList<Node> i, int h, int w, int o) {
        inputs = i;
        ArrayList<Node> prev = i;
        ArrayList<Node> curr = new ArrayList<Node>();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                ArrayList<Connection> connections = new ArrayList<Connection>();
                for(Node node: prev) {
                    connections.add(new Connection(node, (Math.random() * 21 - 10)));
                }
                Node node = new Node((int)(Math.random() * 21 - 10), connections);
                nodes.add(node);
                curr.add(node);
            }
            prev = curr;
            curr = new ArrayList<Node>();
        }
        for (int row = 0; row < o; row++) {
            ArrayList<Connection> connections = new ArrayList<Connection>();
            for(Node node: prev) {
                connections.add(new Connection(node, (Math.random() * 21 - 10)));
            }
            Node node = new Node((int)(Math.random() * 21 - 10), connections);
            outputs.add(node);
        }
    }

    public String toString() {
        String returned = "";
        for (Node node : inputs) {
            returned += node;
        }
        for (Node node : nodes) {
            returned += node;
        }
        for (Node node : outputs) {
            returned += node;
        }
        return returned;
    }

    public void calc() {
        for (Node node : nodes) {
            node.calc();
        }
        for (Node node : outputs) {
            node.calc();
        }
    }

    public void randomize(Double randomness) {
        for (Node node : nodes) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight + (Math.random() - 0.5) * randomness * 2;
            }
        }
        for (Node node : outputs) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight + (Math.random() - 0.5) * randomness * 2;
            }
        }
    }
}

public class NN {
    public static void main(String[] args) {
        ArrayList<Node> input = new ArrayList<Node>();
        for (int i = 0; i < 9; i++) {
            input.add(new Node());
        }
        Network main = new Network(input, 2, 1, 9);
        while (true) {
            Network alt = new Network(input, 2, 1, 9);

        }
    }
}
