import java.util.*;

public class Graph {

    private Map<Integer, Vertex> vertices;

    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
        }
    }

    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        // Sort by vertex ID for consistent, readable output
        List<Integer> sortedKeys = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(sortedKeys);
        for (int id : sortedKeys) {
            System.out.print("  " + id + " -> ");
            List<Integer> neighbors = adjacencyList.get(id);
            if (neighbors.isEmpty()) {
                System.out.print("(none)");
            } else {
                System.out.print(neighbors);
            }
            System.out.println();
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