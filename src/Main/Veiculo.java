package Main;

/**
 *
 * @author radames
 */
public class Veiculo {

    private int placa;
    private String nome;
    private String marca;
    private String modelo;
    private float peso;
    private double altura;
    private boolean ativo;

    public Veiculo() {
    }

    public Veiculo(int placa, String nome, String marca, String modelo, float peso, double altura, boolean ativo) {
        this.placa = placa;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.altura = altura;
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "placa=" + placa + ", nome=" + nome + ", marca=" + marca + ", modelo=" + modelo + ", peso=" + peso + ", altura=" + altura + ", ativo=" + ativo + '}';
    }
    
    

}
