package br.com.orbiwork.bo;

import br.com.orbiwork.dao.UsuarioDAO;
import br.com.orbiwork.exception.BusinessException;
import br.com.orbiwork.exception.NotFoundException;
import br.com.orbiwork.model.Usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsuarioBO {

    @Inject
    UsuarioDAO dao;

    public void inserir(Usuario usuario) {
        validar(usuario);
        dao.inserir(usuario);
    }

    public Usuario buscarPorId(Long id) {
        Usuario usuario = dao.buscarPorId(id);

        if (usuario == null) {
            throw new NotFoundException("Usuário com ID " + id + " não encontrado.");
        }

        return usuario;
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public void atualizar(Usuario usuario) {
        validar(usuario);

        if (dao.buscarPorId(usuario.getId()) == null) {
            throw new NotFoundException("Usuário com ID " + usuario.getId() + " não existe.");
        }

        dao.atualizar(usuario);
    }

    public void deletar(Long id) {
        if (dao.buscarPorId(id) == null) {
            throw new NotFoundException("Usuário com ID " + id + " não existe.");
        }

        dao.deletar(id);
    }

    private void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new BusinessException("Nome do usuário é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new BusinessException("Email do usuário é obrigatório.");
        }
    }
}
