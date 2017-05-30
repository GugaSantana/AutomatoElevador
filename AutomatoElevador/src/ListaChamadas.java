
public class ListaChamadas {
	private int sequencia[];
	private int inicio;

	public ListaChamadas() {
		sequencia = new int[4];
		inicio = 0;
	}

	public void limpa() {
		for (int i = 0; i < 4; i++) {
			sequencia[i] = -1;
		}
	}

	public void add(int andar) {
		sequencia[inicio] = andar;
		if (inicio == 3) {
			inicio = 0;
		}
		{
			inicio++;
		}
	}

	public void removeUltimo() {
		if (inicio == 0) {
			inicio = 3;
			sequencia[inicio] = -1;
		} else {
			inicio--;
			sequencia[inicio] = -1;
		}
	}

	public int[] getSequencia() {
		return sequencia;
	}

}
