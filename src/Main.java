
public class Main {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
        Cliente rubens = new Cliente();
        rubens.setNome("Rubens Anjos");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);
        Conta cc1 = new ContaCorrente(rubens);


		cc.depositar(100);
		cc.transferir(100, poupanca);
        cc.contratarChequeEspecial(200);
        cc1.contratarChequeEspecial(100);
        cc1.depositar(50);
        cc1.transferir(100, cc );
        cc1.depositar(40);


		cc.imprimirExtrato();
        poupanca.imprimirExtrato();
		cc1.imprimirExtrato();
	}

}
