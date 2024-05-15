import java.util.ArrayList;
import java.util.Random;

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
    int prevSeed = 0;
    Double prevRandomness = 0.0;

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
        prevSeed = (int)(Math.random() * 1000000);
        prevRandomness = randomness;
        Random rand = new Random(prevSeed);
        for (Node node : nodes) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight + (rand.nextDouble() - 0.5) * randomness * 2;
            }
        }
        for (Node node : outputs) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight + (rand.nextDouble() - 0.5) * randomness * 2;
            }
        }
    }

    public void unrandomize() {
        Random rand = new Random(prevSeed);
        for (Node node : nodes) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight - (rand.nextDouble() - 0.5) * prevRandomness * 2;
            }
        }
        for (Node node : outputs) {
            for (Connection connection : node.connections) {
                connection.weight = connection.weight - (rand.nextDouble() - 0.5) * prevRandomness * 2;
            }
        }
    }
}

public class NN {
    static ArrayList<Node> inputs = new ArrayList<Node>();
    public static int fitness(Network net) {
        for (int i = 0; i < 9; i++) {
            inputs.get(i).value = 0.0;
        }
        net.calc();
        Double max = inputs.get(0).value;
        int max_index = 0;
        for (int i = 1; i < 9; i++) {
            if (inputs.get(i).value > max) {
                max_index = i;
                max = inputs.get(i).value;
            }
        }
        return (int)(Math.random() * 1000000000);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            inputs.add(new Node());
        }
        Network net = new Network(inputs, 10, 10, 9);
        int prevFit = fitness(net);
        while (true) {
            net.randomize(2.0);
            int currFit = fitness(net);
            if (currFit > prevFit) {
                System.out.println("Better strategy found");
                System.out.println(net);
                prevFit = currFit;
            } else {
                System.out.println("Strategy is worse or equal");
            }
        }

        /* 
        final int N_NETWORKS = 100;


        ArrayList<Network> networks = new ArrayList<>();
        for (int i = 0; i < N_NETWORKS; i++) {
            networks.add(new Network(i, h, w, o));
        }

        ArrayList<Integer> scores = new ArrayList<>();
        for (Network n: networks) {
            int score = doSimulation(n);
            scores.add(score);
        }

        int bestThreshold = 5;
        int keepThreshold = 2;


        for (int i = 0; i < N_NETWORKS; i++) {
            if (scores.get(i) > bestThreshold) {
                // do nothing
            } else if (scores.get(i) > keepThreshold) {
                networks.get(i).randomize(null);
            } else {
                networks.set(i, new Neto)
            }
        }
        */

    }
}
