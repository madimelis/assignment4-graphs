## Assignment 4: Graph Traversal and Representation System

**A. Project Overview**


This project implements a graph data structure in Java and applies two fundamental graph traversal algorithms — Breadth-First Search (BFS) and Depth-First Search (DFS) — to graphs of varying sizes.

A graph is a collection of vertices (nodes) connected by edges (links). Graphs are used to model real-world relationships like social networks, road maps, web page links, and task dependencies.

Vertex — a single entity in the graph.

Edge — a connection between two vertices.

Directed graph — edges have a specific direction, so A → B does not mean B → A.



**B. Class Descriptions**

Vertex: 
Represents a single node in the graph. Stores a unique integer id. Provides a constructor, getter, and toString() for readable output.

Edge:
Represents a directed connection between two vertices. Stores a source and destination vertex. Provides constructors and getters.

Graph:
The core data structure, implemented using an adjacency list.

Experiment:
Handles automated performance testing across graph sizes (10, 30, 100 vertices). Builds graphs, times both algorithms using System.nanoTime(), and prints a formatted results table.

Main:
The entry point of the program. Just creates an Experiment object and calls runMultipleTests() and printResults().



**C. Algorithm Descriptions**


Breadth-First Search

Step-by-step:
1. Mark the start vertex as visited; add it to the queue.
2. While the queue is not empty:
3. 
   Dequeue the front vertex.
   
   Print / process it.
   
   For each unvisited neighbor: mark it visited and enqueue it.


Use cases:
Finding the shortest path in an unweighted graph;

Level-order tree traversal;

Social network "degrees of separation".


Time Complexity: O(V + E) — every vertex is visited once, and every edge is checked once.


Depth-First Search (DFS)

Step-by-step:
1. Push the start vertex onto the stack.
2. While the stack is not empty:
   Pop the top vertex.
   If unvisited: mark it visited, print it, push its unvisited neighbors.

Use cases:
Detecting cycles in a graph;

Topological sorting;

Solving mazes or puzzles.


Time Complexity: O(V + E) — same as BFS; visits every vertex and edge once.



**D. Experimental Results**


Graph Configuration
Each graph uses a circular directed structure where vertex i connects to (i+1) % size and (i+2) % size. This ensures every vertex always has outgoing edges regardless of graph size.

Execution Time Comparison

| Graph Size | BFS Time (ns) | DFS Time (ns) |
|------------|---------------|---------------|
| 10         | 3014900       | 1190500       |
| 30         | 1237400       | 1283800       |
| 100        | 3018100       | 3201300       |


Observations
Results generally align with O(V + E), showing a linear increase in time as the number of vertices and edges grows.
One algorithm may appear faster depending on the specific structure (directed/undirected) or the density of the edges.



**F. Reflection**

In this assignment, I learned how graph structure shapes algorithm behavior. BFS's level-by-level exploration makes it ideal when you need the shortest path or want to find something close to the source. DFS's willingness to go deep first makes it better suited for exhaustive searches, cycle detection, and topological ordering. Both algorithms share the same O(V + E) time complexity, which shows that even very different traversal strategies can be equally efficient when expressed in Big-O terms.


