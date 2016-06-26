package com.dlizarra.starter.support.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.dlizarra.starter.support.jpa.CustomCrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID extends Serializable> extends CustomCrudRepository<T, ID> {

	/**
	 * Returns all instances of the type sorted by the type.
	 *
	 * @param sort {@link Sort} object applied to the returned elements list.
	 * @return ordered list
	 */
	List<T> findAll(Sort sort);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 *
	 * @param pageable object for pagination information.
	 * @return a page of entities
	 */
	Page<T> findAll(Pageable pageable);

	/**
	 * Flushes all pending changes to the database.
	 */
	void flush();

	/**
	 * Saves an entity and flushes changes instantly.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	<S extends T> S saveAndFlush(S entity);

	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will
	 * clear the {@link javax.persistence.EntityManager} after the call.
	 *
	 * @param entities
	 */
	void deleteInBatch(Iterable<T> entities);

	/**
	 * Deletes all entites in a batch call.
	 */
	void deleteAllInBatch();

	/**
	 * Returns a lazy-loaded proxy object of an entity with only its id populated. The rest of the data will be
	 * populated on demand only when it's needed. Used for saving the cost of bringing a heavy object from database. The
	 * implementation uses JPA's EntityManager.getReference method.
	 *
	 * @param id must not be {@literal null}.
	 * @return a proxy object of the entity with the given identifier.
	 * @see EntityManager#getReference(Class, Object)
	 */
	T getOne(ID id);
}
