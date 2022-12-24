package tn.supcom.jnosql;
import jakarta.enterprise.context.ApplicationScoped;
import org.jnosql.artemis.Repository;

import java.util.List;

@ApplicationScoped
public interface userRepository extends Repository<user,String> {
    List<user> findAll();
    List<user> findByUsername(String username);
    List<user> findById(int id);
    void deleteById(int id);
}