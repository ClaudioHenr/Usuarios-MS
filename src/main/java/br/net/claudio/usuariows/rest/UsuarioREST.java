package br.net.claudio.usuariows.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.claudio.usuariows.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class UsuarioREST {
    public static List<Usuario> lista = new ArrayList<>();

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsers() {
        return lista;
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUser(@PathVariable("id") int id) {
        for (Usuario user : lista) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @PostMapping("/usuarios")
    public Usuario addUser(@RequestBody Usuario usuario) {
        Usuario lastUser = lista.get(lista.size() - 1);
        int lastUserId = lastUser.getId();
        usuario.setId(lastUserId + 1);
        lista.add(usuario);
        return usuario;
    }

    @PutMapping("/usuarios/{id}")
    public Usuario modifyUser(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        for (Usuario user : lista) {
            if (user.getId() == id) {
                user.setName(usuario.getName());
                user.setLogin(usuario.getLogin());
                user.setPassword(usuario.getPassword());
                user.setRole(usuario.getRole());
                return user;
            }
        }
        return null;
    }

    @DeleteMapping("/usuarios/{id}")
    public Usuario deleteUser(@PathVariable("id") int id) {
        for (Usuario user : lista) {
            if (user.getId() == id) {
                lista.removeIf(removeUser -> removeUser.getId() == id);
                return user;
            }
        }
        return null;
    }
    

    static {
        lista.add(new Usuario(1, "Administrador", "admin", "admin", "ADMIN"));
        lista.add(new Usuario(2, "Gerente", "geren", "geren", "GERENTE"));
        lista.add(new Usuario(3, "Funcionario", "func", "func", "FUNC"));
        lista.add(new Usuario(4, "Alan", "alan", "alan", "FUNC"));
    }
}
