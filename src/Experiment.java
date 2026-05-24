public class Experiment {
    private long[] bfsTimes = new long[3];
    private long[] dfsTimes = new long[3];
    private long[] dijkstraTimes = new long[3];
    private int[] sizes = {10, 30, 100};

 public void runMultipleTests() {
        System.out.println("=== PERFORMANCE EXPERIMENTS ===\n");
         for (int i = 0; i < sizes.length; i++) {
             Graph g = buildGraph(sizes[i]);
             System.out.println("--- Size: " + sizes[i] + " ---");
             g.printGraph();

             long t = System.nanoTime(); g.bfs(0);      bfsTimes[i]      = System.nanoTime() - t;
             t = System.nanoTime();      g.dfs(0);      dfsTimes[i]      = System.nanoTime() - t;
             t = System.nanoTime();      g.dijkstra(0); dijkstraTimes[i] = System.nanoTime() - t;
             System.out.println();
         }
     }

        public void printResults() {
            System.out.println("=== RESULTS ===");
            System.out.printf("%-10s %-15s %-15s %-15s%n", "Size", "BFS (ns)", "DFS (ns)", "Dijkstra (ns)");
            System.out.println("-".repeat(55));
            for (int i = 0; i < sizes.length; i++)
                System.out.printf("%-10d %-15d %-15d %-15d%n", sizes[i], bfsTimes[i], dfsTimes[i], dijkstraTimes[i]);
        }


    private Graph buildGraph(int size) {
        Graph g = new Graph();
        for (int i = 0; i < size; i++) g.addVertex(i);
        for (int i = 0; i < size; i++) {
            g.addEdge(i, (i + 1) % size, (i % 5) + 1);
            g.addEdge(i, (i + 2) % size, (i % 7) + 1);
        }

        return g;
    }
}