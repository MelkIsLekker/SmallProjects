package Grafieke;

import java.util.*;

class Edge {
    int target, weight;

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}


public class GrafiekeJava {
    static Map<Integer, List<Edge>> adjList = new HashMap<>();
    static final int NODE_COUNT = 6;

    public static void main(String[] args) {
        buildGraph();
        printGraph();
        System.out.println();
        int[][] adjMatrix = convertToAdjMatrix();
        printMatrix(adjMatrix);
        System.out.println();

        //bfs
        ArrayList<Integer> bfsResult = bfs();
        for (Integer integer : bfsResult) {
            System.out.print(integer + " ");
        }
        System.out.println();

        //dfs
        ArrayList<Integer> dfsResult = dfs();
        for (Integer integer : dfsResult) {
            System.out.print(integer + " ");
        }
        System.out.println();

        //isCycle?
        if (isCycle()) 
            System.out.println("It is a cycle");
        else 
            System.out.println("It is not a cycle");
    }
    
    private static void buildGraph() {
        //initiate
        for (int i = 0; i < NODE_COUNT; i++) {
            adjList.put(i, new ArrayList<>());
        }

        //E = {(v3, v5, 3), (v5, v2, 1)}
        addEdge(0, 1, 5);
        addEdge(1, 2, 4);
        addEdge(2, 3, 9);
        addEdge(3, 4, 7);
        addEdge(4, 0, 1);
        addEdge(0, 5, 2);
        addEdge(5, 4, 8);
        addEdge(3, 5, 3);
        addEdge(5, 2, 1);

    }

    private static void printGraph() {
        System.out.println("Vertex | Connected to");
        for (int vertex : adjList.keySet()) {
            System.out.print("V: " + vertex + "   | ");
            for (int i = 0; i < adjList.get(vertex).size(); i++) {
                System.out.print(adjList.get(vertex).get(i).target + "   ");
            }
            System.out.println();
        }
    }

    private static void addEdge(int vertex, int target, int weight) {
        adjList.get(vertex).add(new Edge(target, weight));
    }

    private static int[][] convertToAdjMatrix() {
        int[][] adjMatrix = new int[NODE_COUNT][NODE_COUNT];

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }


        for (int i = 0; i < NODE_COUNT; i++) {
            int vertexListSize = adjList.get(i).size();
            for (int j = 0; j < vertexListSize; j++) {
                adjMatrix[i][adjList.get(i).get(j).target] = adjList.get(i).get(j).weight;
                adjMatrix[adjList.get(i).get(j).target][i] = adjList.get(i).get(j).weight;
            }
        }
        return adjMatrix;
    }

    private static void printMatrix(int[][] matrix) {
        //header
        System.out.print("   ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("V" + i + " ");
        }

        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("V" + i + " ");
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static ArrayList<Integer> bfs() {
        ArrayList<Integer> bfsResult = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int currentvertex = 0;
        bfsResult.add(currentvertex);
        bfsHelper(bfsResult, queue, currentvertex);
        return bfsResult;
    }

    private static void bfsHelper(ArrayList<Integer> bfsResult, Queue<Integer> queue, int currentvertex) {
        for (int i = 0; i < adjList.get(currentvertex).size(); i++) {
            if (!queue.contains(adjList.get(currentvertex).get(i).target) && !bfsResult.contains(adjList.get(currentvertex).get(i).target)) {
                bfsResult.add(adjList.get(currentvertex).get(i).target);
                queue.add(adjList.get(currentvertex).get(i).target);
            }
        }
        if (queue.isEmpty()) {
            return;
        }
        bfsHelper(bfsResult, queue, queue.remove());
    }

    private static ArrayList<Integer> dfs() {
        ArrayList<Integer> dfsResult = new ArrayList<>();
        int currentVertex = 0;
        dfsResult.add(currentVertex);
        dfsHelper(dfsResult, currentVertex);
        return dfsResult;
    }

    private static void dfsHelper(ArrayList<Integer> dfsResult, int currentVertex) {
        for (int i = 0; i < adjList.get(currentVertex).size(); i++) {
            if (!dfsResult.contains(adjList.get(currentVertex).get(i).target)) {
                dfsResult.add(adjList.get(currentVertex).get(i).target);
                dfsHelper(dfsResult, adjList.get(currentVertex).get(i).target);                
            }
        }
    }

    private static boolean isCycle() {
        boolean isCycle = false;
        for (int vertex : adjList.keySet()) {
            isCycle = isCycleHelper(vertex, vertex, new LinkedList<Integer>(), new ArrayList<Integer>());
            if (isCycle)
                return isCycle;
        }
        return isCycle;
    }

    private static boolean isCycleHelper(int lookFor, int current, Queue<Integer> queue, ArrayList<Integer> traversed) {
        if (current == lookFor && traversed.size() > 1) {
            return true;
        }
        for (int i = 0; i < adjList.get(current).size(); i++) {
            if (!queue.contains(adjList.get(current).get(i).target) && !traversed.contains(adjList.get(current).get(i).target)) {
                queue.add(adjList.get(current).get(i).target);
                traversed.add(adjList.get(current).get(i).target);    
            }
        }
        System.out.println(queue);
        if (!queue.isEmpty()) {
            current = queue.remove();
            return isCycleHelper(lookFor, current, queue, traversed);
        }
        return false; 
    }
}
