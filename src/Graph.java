import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    public void addVertex(int id) {
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        if (adjList.containsKey(from) && adjList.containsKey(to))
            adjList.get(from).add(new Edge(new Vertex(from), new Vertex(to), weight));
    }

    public void printGraph() {
        adjList.forEach((id, edges) -> System.out.println(id + ": " + edges));
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            for (Edge e : adjList.getOrDefault(v, List.of()))
                if (visited.add(e.getDestination().getId()))
                    queue.add(e.getDestination().getId());
        }
        System.out.println();
    }

    public void dfs(int start) {
        System.out.print("DFS: ");
        dfsHelper(start, new HashSet<>());
        System.out.println();
    }

    private void dfsHelper(int v, Set<Integer> visited) {
        visited.add(v);
        System.out.print(v + " ");
        for (Edge e : adjList.getOrDefault(v, List.of()))
            if (!visited.contains(e.getDestination().getId()))
                dfsHelper(e.getDestination().getId(), visited);
    }

    public void dijkstra(int start) {
        int n = adjList.size();
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++)
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) u = j;

            if (dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;

            for (Edge e : adjList.getOrDefault(u, List.of())) {
                int nb = e.getDestination().getId();
                if (!visited[nb] && dist[u] + e.getWeight() < dist[nb]) {
                    dist[nb] = dist[u] + e.getWeight();
                    prev[nb] = u;
                }
            }
        }

        System.out.println("Dijkstra from " + start + ":");
        System.out.println("  " + "-".repeat(35));
        for (int i = 0; i < n; i++) {
            String d = dist[i] == Integer.MAX_VALUE ? "unreachable" : String.valueOf(dist[i]);
            System.out.println("  To node " + i + " -> Distance: " + d);
        }
    }
}