public abstract class VeiculoFactory {
    public abstract Veiculo createVeiculo();
    
    public Veiculo criarVeiculo() {
        return createVeiculo();
    }
}
