import java.util.ArrayList;
import java.util.List;

public class GerenciadorVeiculo {
    private List<Veiculo> veiculos;

    public GerenciadorVeiculo(){
        veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(VeiculoFactory factory){
        Veiculo veiculo = factory.criarVeiculo();
        veiculos.add(veiculo);
        System.out.println(veiculo.getTipo() + " adicionado ao gerenciador.");
    }
    
    public void listarVeiculos() {
        System.out.println("\nVeículos no gerenciador:");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.print((i + 1) + ". ");
            veiculos.get(i).exibirDetalhes();
        }
    }

}
