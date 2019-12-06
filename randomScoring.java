static int randomSimulation(Table table, Square square) {
	Square chosenSquare = square;
	int x_part;
	int y_part;
	int compt = 0;
	Random rnd = new Random();
	int pos;
	MinTable min;
	MinTable target;
	int nbWin;
	ArrayList<Square> possiblePlays = new ArrayList<Square>();
	int randomInt;
	while(true) {
		x_part = chosenSquare.getx()/3;
		y_part = chosenSquare.gety()/3;
		pos = Math.pow(-1, compt);
		min = table.getMinTable(x_part, y_part);
		chosenSquare.setPos(pos);
		if(caseGagnante(table, min, pos)) {
			min.setPos(pos);
			if(isWin(table, min, pos)) {
				chosenSquare = square;
				nbWin +=pos;
				pos = 0;
				possiblePlays.clear();
			}
			else if(isTableFull(table)) {
	        	int comptPos= 0;
	        	for(MinTable min : table.getMinTables()) {
	        		comptPos += min.getPos();
	        		if(comptPos < 0 ) {
	        			nbWin--;
	        		}
	        		else if(comptPos > 0) {
	        			nbWin--;
	        		}
	        		pos = 0;
					possiblePlays.clear();
					chosenSquare = square;
	        	}
        	}
		}
		else {
			target = table.getMinTable(chosenSquare.getx()%3, chosenSquare.gety()%3);
			if(target.getPos(0)) {
				for(Square possibleSquare : target.getPoss()) {
					if(possibleSquare.getPos(0)) {
						possiblePlays.add(possibleSquare);
					}
				}
			}
			else {
				for(MinTable mins : table.getMinTables()) {
					for(Square possibleSquare : mins.getPoss()) {
						if(possibleSquare.getPos(0)) {
							possiblePlays.add(possibleSquare);
						}
					}
				}
			}
			randomInt = rnd.nextInt(possiblePlays.size());
			chosenSquare = possiblePlays.get(randomInt);
			compt++;
			possiblePlays.clear();
		}

	}
}

public boolean caseGagnante(Table table, MinTable minTable, Square square, int pos) {
        int[][] dir = {{1, 0}, {1, 1}, {0, 1}, {1, -1}};
        int[][] dirOps = {{-1, 0}, {-1, -1}, {0, -1}, {-1, 1}};
        int xMax = minTable.getx()*3 , yMax = minTable.gety();

        int x, y;

        int nbJetonAligne;

        int dernierJoueur = pos;

        /* Regarde si le dernier coup est gagnant */
        for (int d = 0; d < 4; d++) {
            nbJetonAligne = 0;
            x = square.getx();
            y = square.gety();

            while (x < xMax && x >= _x0 && y < yMax && y >= _y0 && minTable.getSquare(x, y) != null && minTable.getSquare(x, y).getPos() == dernierJoueur) {
                nbJetonAligne++;
                if (nbJetonAligne >= 3) {
                    return true;
                }
                x += dir[d][0];
                y += dir[d][1];
            }

            //regarde dans la direction opposée
            x = square.getx();
            y = square.gety();
            nbJetonAligne--;

            while (x < xMax && x >= _x0 && y < yMax && y >= _y0 && inTable.getSquare(x, y) != null && minTable.getSquare(x, y).getPos() == dernierJoueur) {
                nbJetonAligne++;
                if (nbJetonAligne >= 3) {
                    return true;
                }
                x += dirOps[d][0];
                y += dirOps[d][1];
            }
        }

        return false;

    }

    public boolean isWin() {

    	int[][] dir = {{1, 0}, {1, 1}, {0, 1}, {1, -1}};
        int[][] dirOps = {{-1, 0}, {-1, -1}, {0, -1}, {-1, 1}};
        int xMax = 3 , yMax = 3;

        int x, y;

        int nbJetonAligne;

        int dernierJoueur = pos;

        /* Regarde si le dernier coup est gagnant */
        for (int d = 0; d < 4; d++) {
            nbJetonAligne = 0;
            x = min.getx();
            y = min.gety();

            while (x < xMax && x >= _x0 && y < yMax && y >= _y0 &&  table.getMinTable(x,y) != null && table.getMinTable(x,y).getPos() == dernierJoueur) {
                nbJetonAligne++;
                if (nbJetonAligne >= 3) {
                    return true;
                }
                x += dir[d][0];
                y += dir[d][1];
            }

            //regarde dans la direction opposée
            x = min.getx();
            y = min.gety();
            nbJetonAligne--;

            while (x < xMax && x >= _x0 && y < yMax && y >= _y0 && table.getMinTable(x,y) != null && table.getMinTable(x,y).getPos() == dernierJoueur) {
                nbJetonAligne++;
                if (nbJetonAligne >= 3) {
                    return true;
                }
                x += dirOps[d][0];
                y += dirOps[d][1];
            }
        }


        return false;
    }

 public boolean isTableFull(Table table) {
 	for(MinTable min : table.getMinTables) {
 		if(min.getPos()==0) {
	 		for(Square square : min.getPoss()) {
	 			if(square.getPos()==0) {
	 				return true;
	 			}
	 		}
	 	}
 	}
 	return false;
 }