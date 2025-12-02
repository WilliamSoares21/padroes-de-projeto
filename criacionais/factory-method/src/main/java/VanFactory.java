public class VanFactory extends VeiculoFactory {
    @Override
    public Veiculo createVeiculo() {
        return new Van();
    }
}
