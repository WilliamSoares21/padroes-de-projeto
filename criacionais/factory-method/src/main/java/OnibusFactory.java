public class OnibusFactory extends VeiculoFactory {
    @Override
    public Veiculo createVeiculo() {
        return new Onibus();
    }
}
