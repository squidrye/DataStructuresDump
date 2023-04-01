package Algorithms.GraphTheoryAlgorithms.mst;

import java.util.*;

class PNode implements Comparator<PNode>
{
    private int v;
    private int weight;

    PNode(int _v, int _w) { v = _v; weight = _w; }

    PNode() {}

    int getV() { return v; }
    int getWeight() { return weight; }

    @Override
    public int compare(PNode PNode1, PNode PNode2)
    {
        if (PNode1.weight < PNode2.weight)
            return -1;
        if (PNode1.weight > PNode2.weight)
            return 1;
        return 0;
    }
}

public class Prims
{
    void primsAlgo(ArrayList<ArrayList<PNode>> adj, int N)
    {
        int key[] = new int[N];
        int parent[] = new int[N];
        boolean mstSet[] = new boolean[N];
        for(int i = 0;i<N;i++) {
            key[i] = 100000000;
            mstSet[i] = false;
        }

        PriorityQueue<PNode> pq = new PriorityQueue<PNode>(N, new PNode());

        key[0] = 0;
        parent[0] = -1;
        pq.add(new PNode(key[0], 0));
        while(!pq.isEmpty()) {
            int u = pq.poll().getV();
            mstSet[u] = true;

            for(PNode it: adj.get(u)) {
                if(mstSet[it.getV()] == false && it.getWeight() < key[it.getV()]) {
                    parent[it.getV()] = u;
                    key[it.getV()] = it.getWeight();
                    pq.add(new PNode(it.getV(), key[it.getV()]));
                }
            }
        }

        for(int i = 1;i<N;i++) {
            System.out.println(parent[i] + " - " + i);
        }
    }
    public static void main(String args[])
    {
        int n = 5;
        ArrayList<ArrayList<PNode> > adj = new ArrayList<ArrayList<PNode> >();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<PNode>());

//        0--1--2

        adj.get(0).add(new PNode(1, 2));
        adj.get(1).add(new PNode(0, 2));

        adj.get(1).add(new PNode(2, 3));
        adj.get(2).add(new PNode(1, 3));

        adj.get(0).add(new PNode(3, 6));
        adj.get(3).add(new PNode(0, 6));

        adj.get(1).add(new PNode(3, 8));
        adj.get(3).add(new PNode(1, 8));

        adj.get(1).add(new PNode(4, 5));
        adj.get(4).add(new PNode(1, 5));

        adj.get(2).add(new PNode(4, 7));
        adj.get(4).add(new PNode(2, 7));

        Prims obj = new Prims();
        obj.primsAlgo(adj, n);

    }
}
