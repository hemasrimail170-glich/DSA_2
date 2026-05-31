import java.util.*;

class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path Compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}

public class SmartCityFiberNetwork {

    public static void main(String[] args) {

        String[] zones = {"A", "B", "C", "D", "E", "F"};

        List<Edge> edges = new ArrayList<>();

        // Candidate fiber links with installation costs
        edges.add(new Edge(0, 1, 3)); // A-B
        edges.add(new Edge(0, 2, 8)); // A-C
        edges.add(new Edge(1, 3, 4)); // B-D
        edges.add(new Edge(2, 3, 6)); // C-D
        edges.add(new Edge(2, 5, 8)); // C-F
        edges.add(new Edge(3, 4, 5)); // D-E
        edges.add(new Edge(4, 5, 7)); // E-F
        edges.add(new Edge(1, 4, 9)); // B-E

        Collections.sort(edges);

        UnionFind uf = new UnionFind(zones.length);

        int totalCost = 0;

        System.out.println("===== Smart City Fiber Network =====");
        System.out.println("\nSelected MST Edges:");

        for (Edge edge : edges) {

            if (uf.union(edge.src, edge.dest)) {

                System.out.println(
                        zones[edge.src] + " - " +
                        zones[edge.dest] + " : " +
                        edge.weight);

                totalCost += edge.weight;
            }
        }

        System.out.println("\nTotal MST Cost = " + totalCost);

        System.out.println("\nRedundancy Link Added:");
        System.out.println("C - F : 8");

        System.out.println("\nNetwork reliability improved with alternate path.");
    }
}