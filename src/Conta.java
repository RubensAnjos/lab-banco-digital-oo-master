import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

    @Getter
	protected int agencia;
    @Getter
    protected int numero;
    @Getter
    protected double saldo;
    @Getter
    protected double chequeEspecial;
    @Getter
    protected Cliente cliente;
    private double chequeEspecialcontratado;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
        if(valor <= saldo){
		saldo =  saldo-= valor;}
        else {  valor = valor -= saldo;
                saldo = 0;
                chequeEspecial -= valor;
        }
	}

	@Override
	public void depositar(double valor) {
    //se o cheque especial não foi usado soma o depósito ao saldo.
        if (chequeEspecial >= chequeEspecialcontratado) {
            saldo += valor;
    //se o cheque especial foi usado verifica se o deposito completa o valor do cheque especial
    // e então soma o restante no saldo.
        } else if (valor > (chequeEspecialcontratado - chequeEspecial)) {
            double diferença = chequeEspecialcontratado - chequeEspecial;
            chequeEspecial = chequeEspecialcontratado;
            valor = valor - diferença;
            saldo = saldo += valor;
   //se o valor não utrapassar o cheque especial soma apenas o cheque especial.
        } else {
            chequeEspecial += valor;
        }
    }

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}
    /**
     * Neste metodo o cliente pode contratar um cheque especial
     *que se soma ao saldo.
     * @param "valor" a ser contratado
     * */
    public  void contratarChequeEspecial(double valor){
        chequeEspecial += valor;
        chequeEspecialcontratado=valor;

    }

	protected void imprimirInfosComuns() {
        //System.out.println(cliente.toString());
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo + this.chequeEspecial ));
        System.out.println(String.format("Cheque Especial: %.2f", this.chequeEspecial));
	}
}
