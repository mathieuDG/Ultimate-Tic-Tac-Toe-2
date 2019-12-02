class Square {
    int x;
    int y;
    
    int pos;
    // 0 personne 1 moi -1 toi

    Square(int x,int y) {
        this.x = x;
        this.y = y;
        pos = 0;
    }
    
    int getx() {
        return x;
    }
    
    int gety() {
        return y;
    }
    
    int getPos() {
        return pos;
    }
    
    void setPos(int pos){
        this.pos = pos;
    }
    
    
    
    
}