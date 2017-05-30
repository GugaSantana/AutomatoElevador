
public class Elevador {
	private boolean andares[];
	private int andarAtual;
	private int direcao; //0-descendo  1-subindo
	
	public Elevador()
	{
		andares = new boolean[5];
		desativarTudo();
		andarAtual = 0;
		direcao = 1;
	}
	
	public boolean[] getAndares() {
		return andares;
	}

	public void setAndares(boolean andares[]) {
		this.andares = andares;
	}
	
	public boolean getAndares(int andar)
	{
		return andares[andar];
	}
	
	public void setAndares(int andar, boolean ativo) {
		this.andares[andar] = ativo;
	}
	
	public int getAndarAtual() {
		return andarAtual;
	}

	public void setAndarAtual(int andarAtual) {
		this.andarAtual = andarAtual;
	}
	
	/**
	 * 0-descendo
	 * 1-subindo
	 */
	public int getDirecao() {
		return direcao;
	}
	
	/**
	 * 0-descendo
	 * 1-subindo
	 */
	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
	
	public void desativarTudo()
	{
		for(int i = 0; i < 5; i++)
		{
			andares[i] = false;
		}
	}
	
	public boolean isDesativado()
	{
		for(int i = 0; i < 5; i++)
		{
			if(andares[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public void subir()
	{
		if(andarAtual < 4)
			andarAtual++;
	}
	
	public void descer()
	{
		if(andarAtual > 0)
			andarAtual--;
	}
	
	public void controle()
	{
		
	}


	
}
