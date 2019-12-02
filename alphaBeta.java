function alphaBeta(table, profondeur) {
	//si le noeud est une feuille, retourne la valeur
	if(profondeur == 1) {
		return score(Table.add(ProchainCoup));}
	if(profondeur%2==1) {
		int v = +10000000;
		ArrayList<squarre> ProchainCoup = table.next_turn();
		for(i=0, ProchainCoup.size(), i++) {
			v = min(v,alphaBeta(table, profondeur-1));
			if(alpha >= v ) {
				return v;
			}
			Beta = Min(Beta, v);

		}

	else {
		int v = -10000000;
		ArrayList<squarre> ProchainCoup = table.next_turn();
		for(i=1, ProchainCoup.size(), i++) {
			v =max(v, alphaBeta(table, profondeur-1));
			if(v >= beta) {
				return v;
			}
			alpha = Max(alpha, v);

		}

	}
	
}