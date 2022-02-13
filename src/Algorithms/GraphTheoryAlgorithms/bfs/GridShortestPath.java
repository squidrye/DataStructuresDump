package Algorithms.GraphTheoryAlgorithms.bfs;

import java.util.*;
/*Many problems in graph theory can be represented
using a grid. Grids are a form of implicit graph
because we can determine a node’s neighbours
based on our location within the grid.*/

// 2D version of dungeon master problem: : open.kattis.com/problems/dungeon

public class GridShortestPath {
    private int r,c;
    private char[][]dungeon;
    private int sr,sc;
    private Queue<Integer> qr, qc;
    boolean reachedEnd=false;
    boolean [][] visited;
    Pair prev[][];

    private int nextLayerCount=0;
    private int nodesLeftinLayer=1;
    private int stepCount = 0;

    private class Pair{
        int i;
        int j;
        String dir;
        Pair(int i , int j, String dir){
            this.i = i;
            this.j=j;
            this.dir=dir;
        }

    }
    public GridShortestPath(char[][]matrix, int sr, int sc){
        dungeon = matrix;
        r=matrix.length;
        c=matrix[0].length;
        this.sr = sr;
        this.sc = sc;
        qr =new ArrayDeque<>();
        qc = new ArrayDeque<>();
        prev = new Pair[r][c];
    }
    public void solve(){
        int destRow=-1;
        int destCol=-1;
        visited = new boolean[r][c];
        for(int i=0;i<visited.length;i++)
        Arrays.fill(visited[i], false);
        qr.offer(sr);
        qc.offer(sc);
        visited[sr][sc]=true;
        while(!qr.isEmpty()){
            int r=qr.poll();
            int c=qc.poll();
            nodesLeftinLayer--;
            if(dungeon[r][c]=='E'){
                reachedEnd = true;
                destRow=r;
                destCol=c;
                break;
            }
            exploreNeighbours(r,c);
            if(nodesLeftinLayer == 0){
                nodesLeftinLayer = nextLayerCount;
                nextLayerCount = 0;
                stepCount++;
            }
        }
        if(reachedEnd){
            System.out.println("Took "+stepCount+" minutes to escaped dungeon");
            System.out.println(reconstructPath(destRow, destCol));
        }else{
            System.out.println("Trapped");
        }
    }
    public void exploreNeighbours(int r, int c){
        int [] rowVectors = {-1,0,1,0};
        int [] colVectors = {0,1,0,-1};
        String [] dir = {"top","right","down","left"};
        for(int i=0;i<rowVectors.length;i++){
            int rr=r,cc=c;
            rr+=rowVectors[i];
            cc+=colVectors[i];
            if(rr<0 || cc<0 || rr>=dungeon.length || cc>=dungeon[0].length){
                continue;
            }
            if(dungeon[rr][cc]=='#' || visited[rr][cc]){
                continue;
            }
            qr.offer(rr);
            qc.offer(cc);
            prev[rr][cc]=new Pair(r,c,dir[i]);
            visited[rr][cc]=true;
            nextLayerCount++;

        }
    }
    public String reconstructPath(int destRow, int destCol){
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> dir = new ArrayList<String>();
        if(destRow==-1 || destCol==-1){
            return "[]";
        }
        int rowTrack = destRow, colTrack =destCol;
        while(rowTrack!=sr || colTrack!=sc){
            Pair temp = prev[rowTrack][colTrack];
            al.add(rowTrack+","+colTrack);
            dir.add(temp.dir);
            rowTrack = temp.i;
            colTrack = temp.j;
            // System.out.println(rowTrack+"=="+sr + " "+colTrack+"=="+sc);
        }
        Collections.reverse(dir);
        System.out.println(dir.toString());
        return al.toString();
    }
    public static void main(String[] args) {
        char[][]dungeonTest ={
                                {'.','.','.','#','.','.','.',},
                                {'.','#','.','.','.','#','.',},
                                {'.','#','.','.','.','.','E',},
                                {'.','.','#','#','.','.','.',},
                                {'#','.','#','#','.','#','.',},
                                };
        GridShortestPath path =new GridShortestPath(dungeonTest, 0, 0);
        path.solve();
        
    }
}
