public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(40));
        System.out.println("  SMALL GRAPH DEMO (10 vertices)");
        System.out.println("=".repeat(40));

        Graph smallGraph = new Graph();
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }
        smallGraph.addEdge(0, 1);
        smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3);
        smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5);
        smallGraph.addEdge(2, 6);
        smallGraph.addEdge(3, 7);
        smallGraph.addEdge(4, 8);
        smallGraph.addEdge(5, 9);
        smallGraph.addEdge(6, 7);
        smallGraph.addEdge(7, 8);
        smallGraph.addEdge(8, 9);

        smallGraph.printGraph();
        System.out.println();
        System.out.println("=".repeat(40));
        System.out.println("  PERFORMANCE EXPERIMENTS");
        System.out.println("=".repeat(40));

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
    }
}