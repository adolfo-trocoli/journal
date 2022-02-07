package persistencia;

import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {

	Optional<T> get(K id);

	List<T> findAll();

	void save(T t);

	void update(T t);

	void delete(T t);
}
