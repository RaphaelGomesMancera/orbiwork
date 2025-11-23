package br.com.orbiwork.bo;

import br.com.orbiwork.dao.CursoDAO;
import br.com.orbiwork.dao.TrilhaDAO;
import br.com.orbiwork.exception.BusinessException;
import br.com.orbiwork.exception.NotFoundException;
import br.com.orbiwork.model.Curso;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CursoBO {

    @Inject
    CursoDAO cursoDAO;

    @Inject
    TrilhaDAO trilhaDAO;

    public void inserir(Curso curso) {
        validar(curso);

        if (trilhaDAO.buscarPorId(curso.getTrilhaId()) == null) {
            throw new NotFoundException("Trilha informada não existe.");
        }

        cursoDAO.inserir(curso);
    }

    public Curso buscarPorId(Long id) {
        Curso curso = cursoDAO.buscarPorId(id);

        if (curso == null) {
            throw new NotFoundException("Curso com ID " + id + " não encontrado.");
        }

        return curso;
    }

    public List<Curso> listar() {
        return cursoDAO.listar();
    }

    public void atualizar(Curso curso) {
        validar(curso);

        if (cursoDAO.buscarPorId(curso.getId()) == null) {
            throw new NotFoundException("Curso com ID " + curso.getId() + " não encontrado.");
        }

        cursoDAO.atualizar(curso);
    }

    public void deletar(Long id) {
        if (cursoDAO.buscarPorId(id) == null) {
            throw new NotFoundException("Curso com ID " + id + " não encontrado.");
        }

        cursoDAO.deletar(id);
    }

    private void validar(Curso curso) {
        if (curso.getTitulo() == null || curso.getTitulo().isBlank()) {
            throw new BusinessException("Título do curso é obrigatório.");
        }

        if (curso.getCargaHoraria() <= 0) {
            throw new BusinessException("Carga horária deve ser maior que zero.");
        }
    }
}
