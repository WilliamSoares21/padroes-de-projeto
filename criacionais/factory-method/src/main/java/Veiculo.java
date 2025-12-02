public abstract class Veiculo {
    abstract String getCapacidade();
    abstract String getTipo();
    
    public void exibirDetalhes() {
        System.out.println("Tipo: " + getTipo());
        System.out.println("Capacidade: " + getCapacidade() + " passageiros");
        System.out.println();
    }
}
