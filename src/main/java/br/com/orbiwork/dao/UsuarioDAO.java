package br.com.orbiwork.dao;

import br.com.orbiwork.exception.DatabaseException;
import br.com.orbiwork.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioDAO {

    @Inject
    ConnectionFactory connectionFactory;

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (NOME, EMAIL) VALUES (?, ?)";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao inserir usuário.", e);
        }
    }

    public Usuario buscarPorId(Long id) {
        String sql = "SELECT * FROM USUARIO WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL")
                );
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao consultar usuário.", e);
        }

        return null;
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM USUARIO ORDER BY ID";
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao listar usuários.", e);
        }

        return lista;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE USUARIO SET NOME = ?, EMAIL = ? WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar usuário.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao deletar usuário.", e);
        }
    }
}
