import java.util.*;

class Graph {

    private int vertices;
    private LinkedList<Integer>[] adjacencyList;

    @SuppressWarnings("unchecked")

    Graph(int v) {

        vertices = v;

        adjacencyList = new LinkedList[v];

        for (int i = 0; i < v; i++) {

            adjacencyList[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(int source, int destination) {

        adjacencyList[source].add(destination);

        adjacencyList[destination].add(source);
    }

    void BFS(int startVertex) {

        boolean[] visited = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<Integer>();

        visited[startVertex] = true;

        queue.add(startVertex);

        System.out.println("BFS Traversal:");

        while (!queue.isEmpty()) {

            int vertex = queue.poll();

            System.out.print(vertex + " ");

            for (Integer adjacent : adjacencyList[vertex]) {

                if (!visited[adjacent]) {

                    visited[adjacent] = true;

                    queue.add(adjacent);
                }
            }
        }

        System.out.println();
    }

    void DFSUtil(int vertex, boolean[] visited) {

        visited[vertex] = true;

        System.out.print(vertex + " ");

        for (Integer adjacent : adjacencyList[vertex]) {

            if (!visited[adjacent]) {

                DFSUtil(adjacent, visited);
            }
        }
    }

    void DFS(int startVertex) {

        boolean[] visited = new boolean[vertices];

        System.out.println("\nDFS Traversal:");

        DFSUtil(startVertex, visited);

        System.out.println();
    }
}

// ------------------------------------------------

class Edge implements Comparable<Edge> {

    int source;
    int destination;
    int weight;

    Edge(int s, int d, int w) {

        source = s;
        destination = d;
        weight = w;
    }

    public int compareTo(Edge other) {

        return this.weight - other.weight;
    }
}

// ------------------------------------------------

class KruskalMST {

    int vertices;

    ArrayList<Edge> edges;

    KruskalMST(int v) {

        vertices = v;

        edges = new ArrayList<Edge>();
    }

    void addEdge(int source, int destination, int weight) {

        edges.add(
                new Edge(source, destination, weight)
        );
    }

    int find(int[] parent, int vertex) {

        if (parent[vertex] == vertex) {

            return vertex;
        }

        return find(parent, parent[vertex]);
    }

    void union(int[] parent, int x, int y) {

        int xParent = find(parent, x);

        int yParent = find(parent, y);

        parent[xParent] = yParent;
    }

    void kruskalAlgorithm() {

        Collections.sort(edges);

        int[] parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {

            parent[i] = i;
        }

        System.out.println(
                "\nKruskal Minimum Spanning Tree:"
        );

        int edgeCount = 0;

        for (Edge edge : edges) {

            int x = find(parent, edge.source);

            int y = find(parent, edge.destination);

            if (x != y) {

                System.out.println(
                        edge.source + " - "
                                + edge.destination
                                + " : "
                                + edge.weight
                );

                union(parent, x, y);

                edgeCount++;
            }

            if (edgeCount == vertices - 1) {

                break;
            }
        }
    }
}

// ------------------------------------------------

public class CivicEyeCO3 {

    public static void main(String[] args) {

        Graph cityGraph = new Graph(6);

        cityGraph.addEdge(0, 1);
        cityGraph.addEdge(0, 2);
        cityGraph.addEdge(1, 3);
        cityGraph.addEdge(2, 4);
        cityGraph.addEdge(3, 5);

        System.out.println(
                "=== CIVICEYE SMART CITY ANALYSIS ===\n"
        );

        cityGraph.BFS(0);

        cityGraph.DFS(0);

        KruskalMST mst = new KruskalMST(4);

        mst.addEdge(0, 1, 10);
        mst.addEdge(0, 2, 6);
        mst.addEdge(0, 3, 5);
        mst.addEdge(1, 3, 15);
        mst.addEdge(2, 3, 4);

        mst.kruskalAlgorithm();

        System.out.println(
                "\n=== CIVICEYE CONCLUSION ==="
        );

        System.out.println(
                "BFS and DFS optimized emergency route traversal."
        );

        System.out.println(
                "Kruskal algorithm minimized infrastructure cost."
        );

        System.out.println(
                "The system improved smart city connectivity."
        );
    }
}