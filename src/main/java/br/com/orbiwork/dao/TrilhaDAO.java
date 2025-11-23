package br.com.orbiwork.dao;

import br.com.orbiwork.exception.DatabaseException;
import br.com.orbiwork.model.Trilha;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TrilhaDAO {

    @Inject
    br.com.orbiwork.dao.ConnectionFactory connectionFactory;

    public void inserir(Trilha trilha) {
        String sql = "INSERT INTO TRILHA (NOME, DESCRICAO) VALUES (?, ?)";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trilha.getNome());
            stmt.setString(2, trilha.getDescricao());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao inserir trilha.", e);
        }
    }

    public Trilha buscarPorId(Long id) {
        String sql = "SELECT * FROM TRILHA WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Trilha(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("DESCRICAO")
                );
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar trilha.", e);
        }
        return null;
    }

    public List<Trilha> listar() {
        String sql = "SELECT * FROM TRILHA ORDER BY ID";
        List<Trilha> lista = new ArrayList<>();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Trilha(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("DESCRICAO")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao listar trilhas.", e);
        }

        return lista;
    }

    public void atualizar(Trilha trilha) {
        String sql = "UPDATE TRILHA SET NOME = ?, DESCRICAO = ? WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trilha.getNome());
            stmt.setString(2, trilha.getDescricao());
            stmt.setLong(3, trilha.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar trilha.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM TRILHA WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao deletar trilha.", e);
        }
    }
}
