package br.com.orbiwork.bo;

import br.com.orbiwork.dao.TrilhaDAO;
import br.com.orbiwork.exception.BusinessException;
import br.com.orbiwork.exception.NotFoundException;
import br.com.orbiwork.model.Trilha;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TrilhaBO {

    @Inject
    TrilhaDAO dao;

    public void inserir(Trilha trilha) {
        validar(trilha);
        dao.inserir(trilha);
    }

    public Trilha buscarPorId(Long id) {
        Trilha trilha = dao.buscarPorId(id);

        if (trilha == null) {
            throw new NotFoundException("Trilha com ID " + id + " não foi encontrada.");
        }

        return trilha;
    }

    public List<Trilha> listar() {
        return dao.listar();
    }

    public void atualizar(Trilha trilha) {
        validar(trilha);

        if (dao.buscarPorId(trilha.getId()) == null) {
            throw new NotFoundException("Trilha com ID " + trilha.getId() + " não existe.");
        }

        dao.atualizar(trilha);
    }

    public void deletar(Long id) {
        if (dao.buscarPorId(id) == null) {
            throw new NotFoundException("Trilha com ID " + id + " não existe.");
        }

        dao.deletar(id);
    }

    private void validar(Trilha trilha) {
        if (trilha.getNome() == null || trilha.getNome().isBlank()) {
            throw new BusinessException("Nome da trilha é obrigatório.");
        }

        if (trilha.getDescricao() == null || trilha.getDescricao().isBlank()) {
            throw new BusinessException("Descrição da trilha é obrigatória.");
        }
    }
}
