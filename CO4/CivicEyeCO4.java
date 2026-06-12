import java.util.*;

public class CivicEyeCO4 {

    static final int INF = 99999;

    // Dijkstra Algorithm
    static void dijkstra(int graph[][], int source) {

        int n = graph.length;
        int dist[] = new int[n];
        boolean visited[] = new boolean[n];

        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int count = 0; count < n - 1; count++) {

            int u = -1;
            int min = INF;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Dijkstra Shortest Paths:");

        for (int i = 0; i < n; i++) {
            System.out.println("0 -> " + i + " = " + dist[i]);
        }
    }

    // Bellman Ford Algorithm
    static void bellmanFord(int edges[][], int V, int source) {

        int dist[] = new int[V];

        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int i = 1; i < V; i++) {

            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != INF
                        && dist[u] + w < dist[v]) {

                    dist[v] = dist[u] + w;
                }
            }
        }

        System.out.println("\nBellman-Ford Shortest Paths:");

        for (int i = 0; i < V; i++) {
            System.out.println("0 -> " + i + " = " + dist[i]);
        }
    }

    // Floyd Warshall Algorithm
    static void floydWarshall(int graph[][]) {

        int V = graph.length;

        int dist[][] = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < V; k++) {

            for (int i = 0; i < V; i++) {

                for (int j = 0; j < V; j++) {

                    if (dist[i][k] != INF
                            && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        System.out.println("\nFloyd-Warshall Matrix:");

        for (int i = 0; i < V; i++) {

            for (int j = 0; j < V; j++) {

                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("=== CIVICEYE ROUTE OPTIMIZATION ===\n");

        // Dijkstra Graph
        int graph[][] = {
                {0, 4, 2, 0},
                {4, 0, 1, 5},
                {2, 1, 0, 8},
                {0, 5, 8, 0}
        };

        dijkstra(graph, 0);

        // Bellman Ford Edges
        int edges[][] = {
                {0, 1, 4},
                {0, 2, 2},
                {2, 1, 1},
                {1, 3, 5},
                {2, 3, 8}
        };

        bellmanFord(edges, 4, 0);

        // Floyd Warshall Graph
        int fwGraph[][] = {
                {0, 4, 2, INF},
                {4, 0, 1, 5},
                {2, 1, 0, 8},
                {INF, 5, 8, 0}
        };

        floydWarshall(fwGraph);

        System.out.println("\n=== CIVICEYE CONCLUSION ===");
        System.out.println("Dijkstra optimized positive-weight routing.");
        System.out.println("Bellman-Ford handled weighted networks.");
        System.out.println("Floyd-Warshall computed all-pairs shortest paths.");
    }
}
