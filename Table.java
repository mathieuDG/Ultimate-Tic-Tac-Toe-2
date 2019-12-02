class Table{
    
    ArrayList<MinTable> minTables;
    
    Table(ArrayList<MinTable> minTables){
        this.minTables = minTables;
    }
    
    ArrayList<MinTable> getMinTables(){
        return minTables;
    }
    
    MinTable getminTable(int x, int y){
        for(MinTable minTable: minTables){
            if(x==minTable.getx()&&y==minTable.gety()){
                return minTable;
            }
        }
        return new MinTable(-1,-1);
    }
    
    void updateSquare(Square square, int pos) {
        int x = square.getx();
        int y = square.gety();
        for(int i = 0; i<this.minTables.size(); i++) {
            if(this.minTables.get(i).getx()==x%3 && this.minTables.get(i).gety()==y%3) {
                this.minTables.get(i).updateSquare(square, pos);
            }
        }
    }
    
    MinTable getMinTable(int x,int y){
        for(MinTable minTable: minTables){
            if(x==minTable.getx()&&y==minTable.gety()){
                return minTable;
            }
        }
        return new MinTable(-1,-1);
    }
    
    ArrayList<Square> next_turn(Square formerPlay, int activePlayer) {
        ArrayList<Square> next_turn = new ArrayList<Square>();
        formerPlay.setPos(activePlayer);
        MinTable minTable = this.getMinTable(formerPlay.getx()%3, formerPlay.gety()%3);
        if(minTable.getPos()==0 && formerPlay.getx()!=-1) {
            for(Square square : minTable.getPoss()) {
                if(square.getPos() == 0 ) {
                    next_turn.add(square);
                }
            }
        }
        else {
            for(MinTable macroTable : this.getMinTables()) {
                if(macroTable.getPos()==0) {
                    for(Square square : macroTable.getPoss()) {
                        if(square.getPos()==0) {
                            next_turn.add(square);
                        }
                    }
                }
            }
        }
        return next_turn;
    }

    
    
    
}