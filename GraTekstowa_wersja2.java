import java.util.Random;

class Wrog {
	static int licznik;
	int pozycjaStartowaX;
	int pozycjaStartowaY;
}

class TworzPlansze {
	int x, y;
	int playerX, playerY;
	int liczbaWrogow;
	char gracz, wrog, pole;
	char plansza [][];
	Random losowo = new Random();
	Wrog ob [];
		
	TworzPlansze(int i, int j, char field, char player, char enemy, int enemyNumber) {
		x = i;
		y = j;
		playerY = ((i/2)-1);
		playerX = ((j/2)-1);
		pole = field;
		gracz = player;
		wrog = enemy;
		liczbaWrogow = enemyNumber;
		plansza = new char [x][y];
		ob = new Wrog [liczbaWrogow];
		Wrog.licznik = ob.length;
				
		for (int i1 = 0; i1 < liczbaWrogow; i1++) {
			ob[i1] = new Wrog();
			ob[i1].pozycjaStartowaX = losowo.nextInt(x-1);
			ob[i1].pozycjaStartowaY = losowo.nextInt(y-1);
			
		}
	}
	
	public void RysujPlansze() {
		for (int i = 0; i < x; i++) 
			for (int j = 0; j < y; j++) {
				plansza[i][j] = pole;
				if ((i == playerX) & (j == playerY)) plansza[i][j] = gracz;
				
				for (int i1 = 0; i1 < liczbaWrogow; i1++) {
					if ((ob[i1].pozycjaStartowaX == i) & (ob[i1].pozycjaStartowaY == j)) plansza[i][j] = wrog;
				}
				
			}	
		
		for (int i = 0; i < x; i++) { 
			for (int j = 0; j < y; j++) { 
				System.out.print(plansza[i][j]);
			}
		System.out.print("\n");
		}
	}
	
	public int AktualizacjaPlanszy(char przycisk) {
		
		if (przycisk == 'w') playerX--;
		else if (przycisk == 's') playerX++;
		else if (przycisk == 'a') playerY--;
		else if (przycisk == 'd') playerY++;
		
		if (playerX > (plansza.length -1)) playerX--;
		else if (playerX < (plansza.length - plansza.length)) playerX++;
		else if (playerY > (plansza[1].length -1)) playerY--;
		else if (playerY < (plansza[1].length - plansza[1].length)) playerY++;
		
		for (int i = 0; i < x; i++) 
			for (int j = 0; j < y; j++) {
				plansza[i][j] = pole;
				if ((i == playerX) & (j == playerY)) plansza[i][j] = gracz;
				for (int i1 = 0; i1 < liczbaWrogow; i1++) {
					if ((ob[i1].pozycjaStartowaX == i) & (ob[i1].pozycjaStartowaY == j)) plansza[i][j] = wrog;
					if ((playerX == ob[i1].pozycjaStartowaX) & (playerY == ob[i1].pozycjaStartowaY)) {
						plansza[ob[i1].pozycjaStartowaX][ob[i1].pozycjaStartowaY] = gracz;
						ob[i1].pozycjaStartowaX = -1;
						ob[i1].pozycjaStartowaY = -1;
						Wrog.licznik--;
						
						
					}
				}
				
			}	
		
		
		for (int i = 0; i < x; i++) { 
			for (int j = 0; j < y; j++) { 
				System.out.print(plansza[i][j]);
			}
		System.out.print("\n");
		}
		if (Wrog.licznik == 0) {
			System.out.println("BINGO. Wygrałeś!");
			return 1;
		}
		else return 0;
	}
	
	
}


public class GraTekstowa_wersja2 {

	public static void main(String[] args) throws java.io.IOException {
		
		int wynik;
		char ch = 'q';
		char answer = ' ';
		char ignore = ' ';
		TworzPlansze plansza = new TworzPlansze(5,5,'|', 'x','H', 5);
		plansza.RysujPlansze();
		
		do {
			
		    
		      answer = (char) System.in.read();
		      wynik = plansza.AktualizacjaPlanszy(answer);

		      do {
		        ignore = (char) System.in.read();
		      } while (ignore != '\n');
		    } while ((wynik != 1) & (answer != ch));
		
		System.out.println("KONIEC");

	}

}
