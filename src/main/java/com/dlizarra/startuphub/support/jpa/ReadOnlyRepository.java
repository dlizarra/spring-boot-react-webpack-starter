package com.dlizarra.startuphub.support.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id - must not be null.
	 * @return the entity with the given id or null if none found.
	 */
	T findOne(ID id);

	/**
	 * Returns all instances of the type.
	 *
	 * @return
	 */
	List<T> findAll();

	/**
	 * Returns all instances of the type sorted by the type.
	 *
	 * @param A {@link Sort} object applied to the returned elements list.
	 * @return sorted list
	 */
	List<T> findAll(Sort sort);

	/**
	 * Returns a Page of entities meeting the paging restriction provided in the Pageable object.
	 *
	 * @param Pageable object for pagination information.
	 * @return A Page of entities.
	 */
	Page<T> findAll(Pageable pageable);

}
