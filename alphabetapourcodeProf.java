static int alphaBeta(Jeton[][] grille 9x9, int profondeur, CoupTicTacToe dernierCoup) {
    //si le noeud est une feuille, retourne la valeur
    
    int v = 0;
    
    if(profondeur == 1) {
        return score(grille9x9,dernierCoup);}
    else if(profondeur%2==0) {
        v = +10000000;
        ArrayList<CoupTicTacToe> prochainCoups= getListesCoups(dernierCoup.getJeton().getJoueur());
        for(CoupTicTacToe prochainCoup : prochainCoups ) {
            v = Math.min(v,alphaBeta(grille9x9, profondeur-1, prochainCoup));
            //System.err.println("Square :"+prochainCoup.getx()+" "+prochainCoup.gety()+" profondeur: "+profondeur+" v: "+v);
            if(alpha >= v ) {
               annuleDernierCoup();
                return v;
            }
            beta = Math.min(beta, v);

        }
        annuleDernierCoup();
    }

    else {
        v = -10000000;
        //1 ou -1 à vérifier
        ArrayList<CoupTicTacToe> prochainCoups= getListesCoups(dernierCoup.getJeton().getJoueur());
        for(CoupTicTacToe prochainCoup : prochainCoups ) {
            v = Math.min(v,alphaBeta(grille9x9, profondeur-1, prochainCoup));
            //System.err.println("Square :"+prochainCoup.getx()+" "+prochainCoup.gety()+" profondeur: "+profondeur+" v: "+v);
            if(v >= beta) {
                annuleDernierCoup();
                return v;
            }
            alpha = Math.max(alpha, v);

        }
        annuleDernierCoup();
    }
 
    
    return v;
