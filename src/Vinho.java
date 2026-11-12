import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Vinho {
    //Atributos privados (encapsulamento)
    private String nome;
    private String tipo;
    private String paisOrigem;
    private int ano;
    private double preco;
    private int quantidade;

    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    //Verifica se já existe um vinho com este nome
    public boolean existeVinho(String nome) {
        boolean achou = false;
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "SELECT nome FROM vinho WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            achou = rs.next();
            banco.fecharConexao();
        } catch (Exception ec) {
            System.out.println("Erro ao consultar vinho " + ec.getMessage());
        }
        return achou;
    }

    //CREATE - cadastra o vinho com os dados dos atributos
    public boolean cadastraVinho() {
        boolean resultado = false;
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "INSERT INTO vinho (nome, tipo, pais_origem, ano, preco, quantidade)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, nome);
            ps.setString(2, tipo);
            ps.setString(3, paisOrigem);
            ps.setInt(4, ano);
            ps.setDouble(5, preco);
            ps.setInt(6, quantidade);
            ps.execute();
            resultado = true;
            banco.fecharConexao();
        } catch (Exception ec) {
            System.out.println("Erro ao cadastrar vinho " + ec.getMessage());
            resultado = false;
        }
        return resultado;
    }

    //READ - devolve a lista de todos os vinhos
    public ArrayList<Vinho> listaVinhos() {
        ArrayList<Vinho> lista = new ArrayList<>();
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "SELECT nome, tipo, pais_origem, ano, preco, quantidade "
                    + "FROM vinho ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vinho v = new Vinho();
                v.setNome(rs.getString("nome"));
                v.setTipo(rs.getString("tipo"));
                v.setPaisOrigem(rs.getString("pais_origem"));
                v.setAno(rs.getInt("ano"));
                v.setPreco(rs.getDouble("preco"));
                v.setQuantidade(rs.getInt("quantidade"));
                lista.add(v);
            }
            banco.fecharConexao();
        } catch (Exception ec) {
            System.out.println("Erro ao listar vinhos " + ec.getMessage());
        }
        return lista;
    }

    //DELETE - exclui o vinho pelo nome
    public boolean excluiVinho(String nome) {
        boolean resultado = false;
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "DELETE FROM vinho WHERE nome = ?");
            ps.setString(1, nome);
            ps.execute();
            resultado = true;
            banco.fecharConexao();
        } catch (Exception ec) {
            System.out.println("Erro ao excluir vinho " + ec.getMessage());
            resultado = false;
        }
        return resultado;
    }
}