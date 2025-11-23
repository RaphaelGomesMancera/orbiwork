package br.com.orbiwork.dao;

import br.com.orbiwork.exception.DatabaseException;
import br.com.orbiwork.model.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CursoDAO {

    @Inject
    br.com.orbiwork.dao.ConnectionFactory connectionFactory;

    public void inserir(Curso curso) {
        String sql = "INSERT INTO CURSO (TITULO, CARGA_HORARIA, TRILHA_ID) VALUES (?, ?, ?)";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setLong(3, curso.getTrilhaId());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao inserir curso.", e);
        }
    }

    public Curso buscarPorId(Long id) {
        String sql = "SELECT * FROM CURSO WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getLong("ID"),
                        rs.getString("TITULO"),
                        rs.getInt("CARGA_HORARIA"),
                        rs.getLong("TRILHA_ID")
                );
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar curso.", e);
        }

        return null;
    }

    public List<Curso> listar() {
        String sql = "SELECT * FROM CURSO ORDER BY ID";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getLong("ID"),
                        rs.getString("TITULO"),
                        rs.getInt("CARGA_HORARIA"),
                        rs.getLong("TRILHA_ID")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao listar cursos.", e);
        }

        return cursos;
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE CURSO SET TITULO = ?, CARGA_HORARIA = ?, TRILHA_ID = ? WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setLong(3, curso.getTrilhaId());
            stmt.setLong(4, curso.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar curso.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM CURSO WHERE ID = ?";

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Erro ao deletar curso.", e);
        }
    }
}
