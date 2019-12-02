class MinTable {
     int[][][] allignes = {{{0,0},{0,1},{0,2}},{{1,0},{1,1},{1,2}},{{2,0},{2,1},{2,2}},{{0,0},{1,0},{2,0}},{{0,1},{1,1},{2,1}},{{0,2},{1,2},{2,2}},{{0,0},{1,1},{2,2}},{{0,2},{1,1},{2,0}}};
    
    int x ;
    int y ;
    ArrayList<Square> poss;
    int pos;
    
    MinTable(int x, int y) {
        this.x = x;
        this.y = y;
        
        poss = new ArrayList<Square>();
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Square pos = new Square(3*x+i,3*y+j);
                poss.add(pos);
            }
        }
    }
    
    ArrayList<Square> getPoss() {
        return poss;
    }
    
    Square getSquare(int x,int y){
        for(Square pos: poss){
            if(x==pos.getx()&&y==pos.gety()){
                return pos;
            }
        }
        return new Square(-1,-1);
    }
    
    int getx() {
        return x;
    }
    
    int gety() {
        return y;
    }

    void updateSquare(Square square, int pos) {
        int x = square.getx();
        int y = square.gety();
        for(int i = 0; i<poss.size(); i++) {
            if(poss.get(i).getx()==x && poss.get(i).gety()==y) {
                poss.get(i).setPos(pos);
            }
        }
    }
    
    int getPos(){
        return pos;
    }
    
    void isPossessed() {
        int compt=0;
        int x_part = this.getx();
        int y_part = this.gety();
        Boolean posi = false;
        for(int[][] alligne : allignes){ 
            for(int[] pos: alligne){
                compt+=this.getSquare(x_part*3+pos[0],y_part*3+pos[1]).getPos();
            }
            if(compt ==3) {
                this.setPos(1);
                posi=true;
            }
            else if(compt == -3) {
                this.setPos(-1);
                posi=true;
            }
            compt = 0;
        }
        if(!posi){
            this.setPos(0);
        }
        
        // System.err.println("REGARDE ICI x " +x_part +" y " + y_part+" "+this.getPos());
    
    }
    
    void setPos(int pos) {
        this.pos = pos;
    }
    
}
    