import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Integer>> adjacencyList;
    private Map<Integer, Map<Integer, Integer>> weightMap;
    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
        weightMap = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
            weightMap.put(v.getId(), new HashMap<>());
        }
    }

    public void addEdge(int from, int to) { addEdge(from, to, 1); }

    public void addEdge(int from, int to, int weight) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
            weightMap.get(from).put(to, weight);
        }
    }

    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int from = entry.getKey();
            List<Integer> neighbors = entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(from).append(": [");
            for (int i = 0; i < neighbors.size(); i++) {
                int to = neighbors.get(i);
                int w = weightMap.get(from).get(to);
                sb.append(to).append("(w=").append(w).append(")");
                if (i < neighbors.size() - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    public void bfs(int start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS from " + start + ": ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        System.out.print("DFS from " + start + ": ");

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.print(current + " ");

                List<Integer> neighbors = adjacencyList.get(current);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    if (!visited.contains(neighbors.get(i))) {
                        stack.push(neighbors.get(i));
                    }
                }
            }
        }
        System.out.println();
    }

    public void dijkstra(int start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        int size = vertices.size();
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int u = -1;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }

            if (dist[u] == Integer.MAX_VALUE) break;

            visited[u] = true;

            for (int neighbor : adjacencyList.get(u)) {
                int edgeWeight = weightMap.get(u).get(neighbor);
                if (!visited[neighbor] && dist[u] + edgeWeight < dist[neighbor]) {
                    dist[neighbor] = dist[u] + edgeWeight;
                    prev[neighbor] = u;
                }
            }
        }

        System.out.println("Dijkstra from vertex " + start + ":");
        System.out.printf("  %-10s %-15s %s%n", "Vertex", "Distance", "Path");
        System.out.println("  " + "-".repeat(40));
        for (int i = 0; i < size; i++) {
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "unreachable" : String.valueOf(dist[i]);
            String path = buildPath(prev, i, start);
            System.out.printf("  %-10d %-15s %s%n", i, distStr, path);
        }
    }

    private String buildPath(int[] prev, int target, int start) {
        if (target == start) return String.valueOf(start);

        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = prev[v]) {
            path.add(v);
        }

        if (path.get(path.size() - 1) != start) return "unreachable";

        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) sb.append(" -> ");
        }
        return sb.toString();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}