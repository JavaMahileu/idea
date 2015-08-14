package com.epam.idea.core.repository;

import java.util.List;
import java.util.Optional;

import com.epam.idea.core.model.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface IdeaRepository extends BaseRepository<Idea, Long> {

	/**
	 * Return a list of ideas which belongs to the user with given id,
	 * or an empty list if the user has no ideas.
	 *
	 * @param userId The id of the user.
	 * @return All the ideas of the user.
	 */
	@Query("select i from Idea i where i.author.id = ?1")
	List<Idea> findByUserId(Long userId);

	@Query("select i from Idea i")
	List<Idea> findAll(Pageable pageable);

	@Query("select i from Idea i where UPPER(i.title) like %?1% or UPPER(i.description) like %?1%")
	List<Idea> findAllByQuery(Pageable pageable, String query);

	/**
	 * Return a list of ideas which marked by the tag with given id,
	 * or an empty list if no ideas marked by tag..
	 *
	 * @param tagId The id of the tag.
	 * @return All the ideas marked by the tag.
	 */

	@Query("select i from Idea i left join i.relatedTags t where t.id = ?1")
	List<Idea> findAllByTagId(Pageable pageable, Long tagId);

	@Query("select i from Idea i left join i.relatedTags t where t.id = ?1 and (UPPER(i.title) like %?2% or UPPER(i.description) like %?2%)")
	List<Idea> findAllByTagIdAndByQuery(Pageable pageable, Long tagId, String query);

	@Query("select i from Idea i left join i.likedUsers u where i.id = ?1 and u.id = ?#{ principal?.id }")
	Idea findByIdAndLikedByCurrentUser(long ideaId);
}