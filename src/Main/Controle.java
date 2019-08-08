package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    List<Veiculo> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 
        //facilitar os testes, quando fizermos a persistência em arquivo ele deverá ser excluído
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(456);
        veiculo.setNome("Fusca");
        veiculo.setMarca("VW");
        veiculo.setPeso(1000);
        veiculo.setAltura(1.70);
        veiculo.setAtivo(true);
        adicionar(veiculo);

        veiculo = new Veiculo(123, "Fusion", "ford", "l", 1200, 1.50, false);
        adicionar(veiculo);

        veiculo = new Veiculo(222, "Camaro", "ford", "xt", 1500, 1.40, true);
        adicionar(veiculo);
        veiculo = new Veiculo(333, "Jeep", "Jeep", "1951", 800, 1.50, false);
        adicionar(veiculo);
    }

    public void adicionar(Veiculo veiculo) {
        lista.add(veiculo);
    }

    public List<Veiculo> listar() {
        return lista;
    }

    public Veiculo buscar(int placa) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPlaca()==placa) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Veiculo veiculo, Veiculo veiculoAntigo) {
        lista.set(lista.indexOf(veiculoAntigo), veiculo);

    }

    public void excluir(Veiculo veiculo) {
        lista.remove(veiculo);
    }

}
