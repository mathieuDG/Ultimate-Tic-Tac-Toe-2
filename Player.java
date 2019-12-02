class Player {
    static int alpha = -100000;
    static int beta = 100000;
    
static int[][][] allignes = {{{0,0},{0,1},{0,2}},{{1,0},{1,1},{1,2}},{{2,0},{2,1},{2,2}},{{0,0},{1,0},{2,0}},{{0,1},{1,1},{2,1}},{{0,2},{1,2},{2,2}},{{0,0},{1,1},{2,2}},{{0,2},{1,1},{2,0}}};
   
    static int alphaBeta(Table table, int profondeur, Square formerPlay) {
    //si le noeud est une feuille, retourne la valeur
    if(profondeur == 1) {
        return score(table,formerPlay);}
    if(profondeur%2==1) {
        int v = +10000000;
        ArrayList<Square> prochainCoups = table.next_turn(formerPlay, -1);
        for(Square prochainCoup : prochainCoups ) {
            v = Math.min(v,alphaBeta(table, profondeur-1, prochainCoup));
            if(alpha >= v ) {
                return v;
            }
            beta = Math.min(beta, v);

        }
        formerPlay.setPos(0);
    }

    else {
        int v = -10000000;
        //1 ou -1 à vérifier
        ArrayList<Square> prochainCoups = table.next_turn(formerPlay, 1);
        for(Square prochainCoup : prochainCoups ) {
            v =Math.max(v, alphaBeta(table, profondeur-1, prochainCoup));
            if(v >= beta) {
                return v;
            }
            alpha = Math.max(alpha, v);

        }
        formerPlay.setPos(0);

    }
    return -1;
    
}
    static int score(Table table, Square square) {
        int score = 0;
        int compt = 0;
        int x_part = 0;
        int y_part = 0;
        square.setPos(1);
        MinTable minTable = table.getMinTable(square.getx()%3, square.gety()%3);
        minTable.isPossessed();
        ArrayList<MinTable> minTables = table.getMinTables();
        ArrayList<MinTable> dangerousMinTables = new ArrayList<MinTable>();
        
    //  System.err.println("cc");

        //A ajouter : play mene à une case où l'adversaire possède deux cases ou non
        //A ajouter : adversaire ou nous possédent deux acro cases alignés
        for(int i =0; i<minTables.size(); i++) {
   //           System.err.println("cc<3");
                // System.err.println("x "+minTables.get(i).getx()+"y "+minTables.get(i).gety()+" "+minTables.get(i).getPos());
    
            if(minTables.get(i).getPos()!=0) {
               // System.err.println("cc1");
                if(minTables.get(i).getx()==1 && minTables.get(i).gety()==1)
                    score += minTables.get(i).getPos() * 50;
                else if(minTables.get(i).getx()%3 == 1 || minTables.get(i).gety()%3 == 1) 
                    score += minTables.get(i).getPos() * 20;
                else
                    score += minTables.get(i).getPos() * 30;
            }
            //minTable non complétés
            else {

                x_part = minTables.get(i).getx();
                y_part = minTables.get(i).gety();
                for (int[][] alligne : allignes) {
                    for (int[] pos : alligne) {
                        compt += minTables.get(i).getSquare(minTables.get(i).getx()*3+pos[0],minTables.get(i).gety()*3+pos[1]).getPos();
                    }
                    if(compt==2) {
    //                  System.err.println("ccbis");
                        score += 1;
                    }
                    if(compt==-2) {
    //                  System.err.println("ccbis2");
                        score -= 1;
                        if(!dangerousMinTables.contains(minTables.get(i))) {
                            dangerousMinTables.add(minTables.get(i));
                           // System.err.println("coucou");
                        }
                    }
                    compt  = 0;
                    
                }
            }
        }
        //si l'on envoit l'adversaire sur une case qu'il peut terminer
        for(int j =0; j<dangerousMinTables.size(); j++) {
            System.err.println("coucou2");
            if(square.getx()%3 == dangerousMinTables.get(j).getx() && (square.gety()%3 == dangerousMinTables.get(j).gety())) {
                System.err.println("cc2");
                score-=1;
            }
        }
        //si un joueur a deux macros cases alignés ou non
        int posCompt = 0;
        for (int[][] alligne : allignes) {
            for (int[] pos : alligne) {
                posCompt += table.getminTable(pos[0],pos[1]).getPos();
            }
            if(posCompt==2) {
              //  System.err.println("cc3");
                score += 2;
                    }
            if(posCompt==-2) {
              //  System.err.println("cc4");
                score -= 2;
            }
        }
        
        //si le play envoit l'adversaire sur une case déjà gagnée
        int pos = table.getMinTable(square.getx(), square.gety()).getPos();
            if(pos==1 ||pos==-1) {
                System.err.println("cc5");
                score -=1;
            }
        square.setPos(0);
        minTable.isPossessed();
        return score;
    }
    


    

    public static void main(String args[]) {
        ArrayList<int[]> randomTable = new ArrayList<int[]>();
        Scanner in = new Scanner(System.in);
        int turn = 0;
        int score = 0;
        ArrayList<MinTable> minTables = new ArrayList<MinTable>();
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                MinTable minTable = new MinTable(i,j);
                minTables.add(minTable);
            }
        }
        
        
        Table table = new Table(minTables);
        boolean Testrow = false;
        boolean column = false;
        
        
    
        
        // game loop
        while (true) {
            randomTable.clear();
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            Square square= table.getminTable(opponentRow/3, opponentCol/3).getSquare(opponentRow, opponentCol);
            square.setPos(-1);
            MinTable minTable = table.getMinTable(square.getx()/3, square.gety()/3);
            minTable.isPossessed();
            //table.updateSquare(square, -1);
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt();
                int col = in.nextInt();
                int[] tab = {row,col};
                randomTable.add(tab);
                //System.err.println("row " + randomTable.get(i)[0] + " column " + randomTable.get(i)[1]);
                
            }
            int maxScore = -1000;
            int v = 0;
            Square chosenSquare = new Square(-1,-1);
            for(MinTable testTables : table.getMinTables()) {
                for(Square testSquare : testTables.getPoss()) {
                    // System.err.println("x" + " " + testSquare.getx() + " y " + testSquare.gety() + " " + testSquare.getPos()); 
                }
            }
            for(Square possibleSquare : table.next_turn(square, -1)) {
                // System.err.println(possibleSquare.getx() + " " + possibleSquare.gety());
                v = alphaBeta(table, 3, possibleSquare);
                System.err.println("Score " + v);
                if(v>maxScore) {
                    maxScore = v;
                    chosenSquare = possibleSquare;
                }
            }
            
             System.out.println(chosenSquare.getx() + " " + chosenSquare.gety());
             /*Square chosenSquare= table.getMinTable(randomTable.get(choice)[0]/3 , randomTable.get(choice)[1]/3).getSquare(randomTable.get(choice)[0] , randomTable.get(choice)[1]);
             score = score(table, chosenSquare);*/
             chosenSquare.setPos(1);
             MinTable newMinTable = table.getMinTable(chosenSquare.getx()/3, chosenSquare.gety()/3);
             newMinTable.isPossessed();
             //table.updateSquare(chosenSquare, 1);
             
             
             turn++;
           

        }
    }
    
}
