package zup.com.br.ProjetofinalEstrelas.atividadeFisica.dtos;

public class AtividadeFisicaDTO {

    private String nome;
    private String cidade;
    private String bairro;
    private String horario;
    private String endereco;
    private String responsavel;
    private String contato;

    public AtividadeFisicaDTO() {
    }

    public AtividadeFisicaDTO(String nome, String cidade, String bairro, String horario, String endereco, String responsavel, String contato) {
        this.nome = nome;
        this.cidade = cidade;
        this.bairro = bairro;
        this.horario = horario;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}


