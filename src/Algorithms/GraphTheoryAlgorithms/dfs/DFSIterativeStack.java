package Algorithms.GraphTheoryAlgorithms.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//using custom int stack which is faster than fastest stack in java library however we need an upper bound to initialize the stack
class IntStack {
    int[] stack;
    int top = -1, sz;

    public IntStack(int size) {
        sz = size;
        stack = new int[sz];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int peek() {
        return stack[top];
    }

    public void push(int value) {
        if (top == sz - 1) {
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return stack[top--];
    }
}

public class DFSIterativeStack {
   static class Edge {
        int src, dest, wt;

        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void addUndirectedEdge(Map<Integer, List<Edge>> graph, int src, int dest, int wt) {
        List<Edge> l = graph.get(src);
        List<Edge> d2s = graph.get(dest);
        if (l == null) {
            l = new ArrayList<Edge>();
            graph.put(src, l);
        }
        if(d2s == null){
            d2s = new ArrayList<>();
            graph.put(dest, d2s);
        }
        l.add(new Edge(src, dest, wt));
        d2s.add(new Edge(dest, src, wt));
    }

    public static int dfs(Map<Integer, List<Edge>> graph, int nodeCount, int startNode) {
        int count = 0;
        boolean[] visited = new boolean[nodeCount];
        Arrays.fill(visited, false);
        IntStack st = new IntStack(nodeCount);
        st.push(startNode);
        while (!st.isEmpty()) {
            int curNode = st.pop();

            if (!visited[curNode]) {

                count++;
                visited[curNode] = true;
                List<Edge> edges = graph.get(curNode);

                if (edges != null)
                    for (Edge edge : edges) {
                        st.push(edge.dest);
                    }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        addUndirectedEdge(graph, 0, 9, 0);
        addUndirectedEdge(graph, 0, 1, 0);
        addUndirectedEdge(graph, 1, 8, 0);
        addUndirectedEdge(graph, 9, 8, 0);
        addUndirectedEdge(graph, 8, 7, 0);
        addUndirectedEdge(graph, 7, 10, 0);
        addUndirectedEdge(graph, 10, 11, 0);
        addUndirectedEdge(graph, 11, 7, 0);
        addUndirectedEdge(graph, 7, 3, 0);
        addUndirectedEdge(graph, 7, 6, 0);
        addUndirectedEdge(graph, 6, 5, 0);
        addUndirectedEdge(graph, 3, 2, 0);
        addUndirectedEdge(graph, 3, 4, 0);
        addUndirectedEdge(graph, 3, 5, 0);
        addUndirectedEdge(graph, 12, 12, 0);
        // for(Integer i: graph.keySet()){
        //         System.out.print(i);
        //         for(Edge e: graph.get(i)){
        //             System.out.print(" -->["+e.dest+"]");
        //         }
        //         System.out.println();
        //     }
        int sz = dfs(graph, graph.size(), 0);
        System.out.println(sz);
    }
}
