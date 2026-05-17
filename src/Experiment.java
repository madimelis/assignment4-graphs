public class Experiment {
    private long[] bfsTimes;
    private long[] dfsTimes;
    private int[] graphSizes;

    public Experiment() {
        graphSizes = new int[]{10, 30, 100};
        bfsTimes = new long[graphSizes.length];
        dfsTimes = new long[graphSizes.length];
    }

    public void runTraversals(Graph g) {
        int startVertex = 0;

        long bfsStart = System.nanoTime();
        g.bfs(startVertex);
        long bfsEnd = System.nanoTime();
        long bfsDuration = bfsEnd - bfsStart;

        long dfsStart = System.nanoTime();
        g.dfs(startVertex);
        long dfsEnd = System.nanoTime();
        long dfsDuration = dfsEnd - dfsStart;

        System.out.println("  BFS time: " + bfsDuration + " ns");
        System.out.println("  DFS time: " + dfsDuration + " ns");
    }

 public void runMultipleTests() {
        System.out.println("=== PERFORMANCE EXPERIMENTS ===\n");

        for (int i = 0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            Graph g = buildGraph(size);

            System.out.println("--- Graph Size: " + size + " vertices, "
                    + g.getEdgeCount() + " edges ---");
            if (size == 10) {
                runTraversals(g);
            } else {
                long bfsStart = System.nanoTime();
                g.bfs(0);
                long bfsEnd = System.nanoTime();
                bfsTimes[i] = bfsEnd - bfsStart;

                long dfsStart = System.nanoTime();
                g.dfs(0);
                long dfsEnd = System.nanoTime();
                dfsTimes[i] = dfsEnd - dfsStart;

                System.out.println("  BFS time: " + bfsTimes[i] + " ns");
                System.out.println("  DFS time: " + dfsTimes[i] + " ns");
            }
            System.out.println();
        }
    }

    public void printResults() {
        System.out.println("=== RESULTS SUMMARY TABLE ===");
        System.out.printf("%-15s %-20s %-20s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("-".repeat(55));

        for (int i = 0; i < graphSizes.length; i++) {
            System.out.printf("%-15d %-20d %-20d%n",
                    graphSizes[i], bfsTimes[i], dfsTimes[i]);
        }
        System.out.println();
    }

private Graph buildGraph(int size) {
        Graph g = new Graph();

        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }

        for (int i = 0; i < size; i++) {
            for (int offset = 1; offset <= 3; offset++) {
                if (i + offset < size) {
                    g.addEdge(i, i + offset);
                }
            }
        }
        return g;
    }
}