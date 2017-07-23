
public class Percolation {
    
  private  int sideLength = 0;
  private  int[] id, state;
  private  int[] sz;

    
    public Percolation(int n) {
        
        id = new int[n * n + 2];
        sz = new int[n * n + 2];
        state = new int[n * n + 2];
        
        sideLength = n;
 
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            sz[i] = 1;
            state[i] = 0;
        }
        
        id[n * n + 1] = n * n+1;
        id[n * n] = n * n;
        
        sz[n * n + 1] = n * n+1;
        sz[n * n] = n * n+1;
        
        state[n * n + 1] = 1;
        state[n * n] = 1;
    }
    
   public void open(int row, int col) {
       
 
       int rawNumber =  convertToID(row, col); 
       state[rawNumber] = 1;
      
       if (row == 1){
          union(rawNumber, sideLength*sideLength+1); 
       }
       
       if (row == sideLength){
           union(rawNumber, sideLength*sideLength); 
       }
      
       // north (first make there r proper adjacent blocks
       // then check state
       // then union)
 if (row != 1){
       if (state[convertToID(row-1, col)] == 1) {
      union(rawNumber, convertToID(row-1, col));
       }
 }
      // south
  if (row != sideLength){
      if (state[convertToID(row+1, col)] == 1) {
      union(rawNumber, convertToID(row+1, col));
      }
  }
      // east
  if (col != sideLength){
      if (state[convertToID(row, col+1)] == 1) {
      union(rawNumber, convertToID(row, col+1));
      }
  }
      // west
  if (col != 1){
      if (state[convertToID(row, col-1)] == 1) {
      union(rawNumber, convertToID(row, col-1));
      }
  }
        
 }   
   
   private void union(int p, int q) {
       
        int i = root(p);
        int j = root(q);
        
         if (i == j) return;
        
        if (sz[i] < sz[j]) {
    
            id[i] = j;

            sz[j] += sz[i];
    
        }else{
    
            id[j] = i;
            sz[i] += sz[j];
        }
   
        id[i] = j;
}
   
    private int root(int i) {
   
        while (i != id[i]) { 
                id[i] = id[id[i]];

            i = id[i]; 
        }
       return i;
  }
   
   private int convertToID(int row, int col) {
       return (col - 1) + sideLength * (row-1);
   }
     
   public boolean isOpen(int row, int col) {
       return state[convertToID(row, col)] == 1;
    } 
   
   public boolean isFull(int row, int col){
        return state[convertToID(row, col)] == 0;
    }   
   
   public int numberOfOpenSites() {
       
       int n = 0;
       
       for (int i = 0; i < id.length; i++) {
           // disregard virtual components
          if (state[i] == 1 && i != id.length - 1 && i != id.length - 2) n++;
       }
       
        return n;
    }   
   
   public boolean percolates() {
      return root(sideLength * sideLength+1) == root(sideLength * sideLength);
    }     
     
}