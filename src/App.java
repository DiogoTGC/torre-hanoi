import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
    System.out.print("Digite o número de discos da torre (máx. 6):");
    int num_disco = input.nextInt();
    input.nextLine();
		
    int[][] torres = new int[3][num_disco];

    for (int i = 0; i < torres.length; i++) {
      for (int j = 0; j < torres[i].length; j++) {
        if (i == 0) {
          torres[i][j] = j + 1;
        } else {
          torres[i][j] = 0;
        }
      }
    }

    int num_jogadas = 0;
		boolean fim = false;
    
    mostrar_torres(torres);
    System.out.println("A jogada deve ser escrita da seguinte forma: 1 3");
    System.out.println("Dessa forma o primeiro disco, de cima pra baixo, da torre 1 iria para a ultima posição possível da torre 3.");
		while (fim == false) {
      System.out.print("Digite sua jogada: ");
      String[] jogada = input.nextLine().split(" ");
			int origem = Integer.parseInt(jogada[0]);
			int destino = Integer.parseInt(jogada[1]); 
	  	
      if (origem != destino) {
			  num_jogadas += mover_disco(origem - 1, destino - 1, torres);
      } else {System.out.println("Jogada Inválida! Origem não pode ser igual a destino");}
      
      System.out.printf("\nJogadas: %d\n", num_jogadas);
      mostrar_torres(torres);
      fim = checar_jogo(torres[2], num_jogadas);
	}
		input.close();
	}

	public static void mostrar_torres(int[][] torres) {
		for (int j = 0; j < torres[0].length; j++) {
			for (int i = 0; i < torres.length; i++) {
				int pos = torres[i][j];
				switch (pos) {
					case 0:
						System.out.print("     |     ");
						break;
					case 1:
						System.out.print("     =     ");
						break;
					case 2:
						System.out.print("    ===    ");
						break;
					case 3:
						System.out.print("   =====   ");
						break;
					case 4:
						System.out.print("  =======  ");
						break;
					case 5:
						System.out.print(" ========= ");
						break;
					case 6:
						System.out.print("===========");
						break;
				}
			}
      if (j == torres[0].length - 1) {
        System.out.println("\n  Torre 1    Torre 2    Torre 3  ");
      }
			System.out.println();
		}
	}

	public static int mover_disco(int origem, int destino, int[][] torres) {
		for (int i = 0; i < torres[origem].length; i++) {
      if (torres[origem][i] != 0) {
			  for (int j = torres[destino].length - 1; j >= 0; j--) {	
			    if (torres[destino][j] == 0) {
            if (j == torres[destino].length - 1) {
              torres[destino][j] = torres[origem][i];
              torres[origem][i] = 0;
              return 1;
            } else {
              if (torres[destino][j + 1] > torres[origem][i]) {
                torres[destino][j] = torres[origem][i];
                torres[origem][i] = 0;
                return 1;
              } else {
                System.out.println("Jogada inválida. A peça da torre de origem é maior que a peça da torre de destino");
                return 0;
              }
            }
          }	
				}
        break;
			}
		}
    return 0;
	}

  public static boolean checar_jogo(int[] torre_destino, int num_jogadas) {
    int[] torre_completa = new int[torre_destino.length];
    int ok = 0;
    double num_jogadas_perfeitas = Math.pow(2, torre_destino.length) - 1;

    for (int i = 0; i < torre_destino.length; i++) {
      torre_completa[i] = i + 1;
    }

    for (int i = 0; i < torre_destino.length; i++) {
      if (torre_destino[i] == torre_completa[i]) {
        ok += 1;
      }
      if (ok == torre_completa.length) {
        System.out.println("Jogo completo! Parabéns!");
        if (num_jogadas == num_jogadas_perfeitas) {
          System.out.println("Número perfeito de jogadas.");
        } else {
          DecimalFormat df = new DecimalFormat("#");
          System.out.printf("O menor número de jogada era %s você fez %s a mais\n", df.format(num_jogadas_perfeitas), df.format(num_jogadas - num_jogadas_perfeitas)); 
        }
        return true;
      }
    }
    return false;
  }
}
