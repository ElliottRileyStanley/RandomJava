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

    public static Double fitness(Network net, int games) {
        Double total = 0.0;
        for (int t = 0; t < games; t++) {
            for (int i = 0; i < 9; i++) {
                inputs.get(i).value = 0.0;
            }
            int player = (int) (Math.random() * 2 + 1);
            while (true) {
                if (player == 1) {
                    net.calc();
                    Double max = Double.MIN_VALUE;
                    int max_index = -1;
                    for (int i = 0; i < 9; i++) {
                        if (inputs.get(i).value != 0.0) {
                            continue;
                        }
                        if (net.outputs.get(i).value > max) {
                            max_index = i;
                            max = net.outputs.get(i).value;
                        }
                    }
                    if (max_index == -1) {
                        player = 0;
                        break;
                    }
                    inputs.get(max_index).value = 1.0;
                    player = 2;
                } else {
                    int choice = (int) (Math.random() * 9);
                    while (inputs.get(choice).value != 0.0) {
                        choice = (int) (Math.random() * 9);
                    }
                    inputs.get(choice).value = 2.0;
                    player = 1;
                }
                if ((inputs.get(0).value != 0.0 && inputs.get(0).value == inputs.get(1).value && inputs.get(0).value == inputs.get(2).value) ||
                    (inputs.get(3).value != 0.0 && inputs.get(3).value == inputs.get(4).value && inputs.get(3).value == inputs.get(5).value) ||
                    (inputs.get(6).value != 0.0 && inputs.get(6).value == inputs.get(7).value && inputs.get(6).value == inputs.get(8).value) ||
                    (inputs.get(0).value != 0.0 && inputs.get(0).value == inputs.get(3).value && inputs.get(0).value == inputs.get(6).value) ||
                    (inputs.get(1).value != 0.0 && inputs.get(1).value == inputs.get(4).value && inputs.get(1).value == inputs.get(7).value) ||
                    (inputs.get(2).value != 0.0 && inputs.get(2).value == inputs.get(5).value && inputs.get(2).value == inputs.get(8).value) ||
                    (inputs.get(0).value != 0.0 && inputs.get(0).value == inputs.get(4).value && inputs.get(0).value == inputs.get(8).value) || 
                    (inputs.get(2).value != 0.0 && inputs.get(2).value == inputs.get(4).value && inputs.get(2).value == inputs.get(6).value)) {
                    break;
                }
                int filled_cells = 0;
                for (Node node : inputs) {
                    if (node.value == 0) {filled_cells++;}
                }
                if (filled_cells == 9) {
                    player = 0;
                    break;
                }
                
            }
            if (player == 1) {
                total++;
            } else if (player == 2) {
                total--;
            }
        }
        return total/games;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            inputs.add(new Node());
        }
        Network net = new Network(inputs, 1, 1, 9);
        Double prevFit = fitness(net, 5);
        while (true) {
            net.randomize(2.0);
            Double currFit = fitness(net, 5);
            if (currFit > prevFit) {
                System.out.println("Better strategy found");
                System.out.println(net);
                prevFit = currFit;
            } else {
                System.out.println("Strategy is worse or equal");
            }
        }

        

    }
}



